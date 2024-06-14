package View;

import Business.UserManager;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu userMenu;

    public AdminView(User user, UserManager userManager) {
        this.userManager = userManager;
        this.add(container);
        this.guiInitialize(1000, 500);
        this.user = user;

        if (this.user == null) {
            dispose();
        }

        this.lbl_admin_welcome.setText("Welcome: " + this.user.getUserName());

        Object[] column_user = {"User Id", "User Name", "User Password", "User Type"};
        ArrayList<User> userList = userManager.findAll();
        this.tmdl_user.setColumnIdentifiers(column_user);

        for (User u : userList) {
            Object[] object = {u.getUserId(), u.getUserName(), u.getUserPassword(), u.getUserType()};
            this.tmdl_user.addRow(object);
        }

        this.tbl_user.setModel(this.tmdl_user);
        this.tbl_user.getTableHeader().setReorderingAllowed(false); //Prevent table headers from being changed
        this.tbl_user.setEnabled(false); //Prevent the table from being edited by double-clicking

        this.userMenu = new JPopupMenu();
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row, selected_row);
                //To make a pop_up menu appear on right click
                if (SwingUtilities.isRightMouseButton(e)) {
                    userMenu.show(tbl_user, e.getX(), e.getY());
                }
            }
        });
        this.userMenu.add("Yeni").addActionListener(e -> {

        });
        this.userMenu.add("Güncelle");
        this.userMenu.add("Sil");

        this.tbl_user.setComponentPopupMenu(userMenu);
    }
}
