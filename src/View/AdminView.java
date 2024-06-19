package View;

import Business.UserManager;
import Core.ComboItem;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_admin_welcome;
    private JPanel pnl_admin_top;
    private JTabbedPane tab_menu;
    private JButton btn_admin_logout;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JComboBox<User.UserType> cmb_filter_user_type;
    private JButton btn_filter_user;
    private JButton btn_clear_user_filter;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    private Object[] col_user;

    public AdminView(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        this.add(container);
        this.guiInitialize(1000, 500);

        if (this.user == null) {
            dispose();
        }

        this.lbl_admin_welcome.setText("Welcome: " + this.user.getUserName());

        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();
    }

    public void loadUserTable(ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"User ID", "Username", "User Password", "User Type"};
        if (userList == null) {
            userList = this.userManager.getForTable(col_user.length, this.userManager.findAll()); //Transfer all users to the table with methods in UserManager
        }
        createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }

    public void loadUserComponent() {
        this.user_menu = new JPopupMenu();
        this.selectRow(this.tbl_user, this.user_menu);

        //We will use the new user creation and update features on the same window. If we send an empty user we will create a new user, if we send an existing user we will update it
        this.user_menu.add("New").addActionListener(e -> {
            UserView userView = new UserView(new User(), this.userManager);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId), this.userManager);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMessage("done");
                    loadUserTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(user_menu); //Integrating pop-up menu in the table

        this.btn_filter_user.addActionListener(e -> {
            ArrayList<User> userListForFilter = this.userManager.filterForTable(
                    (User.UserType) cmb_filter_user_type.getSelectedItem()
            );

            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_user.length, userListForFilter);
            loadUserTable(userRowListBySearch);
        });

        this.btn_clear_user_filter.addActionListener(e -> {
            this.cmb_filter_user_type.setSelectedItem(null);
            loadUserTable(null);
        });
    }

    public void loadUserFilter() {
        this.cmb_filter_user_type.setModel(new DefaultComboBoxModel<>(User.UserType.values()));
        this.cmb_filter_user_type.setSelectedItem(null);
    }
}
