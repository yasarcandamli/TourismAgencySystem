package View;

import Entity.User;

import javax.swing.*;

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

    public AdminView(User user) {
        this.add(container);
        this.guiInitialize(1000, 500);
        this.user = user;

        if (this.user == null) {
            dispose();
        }

        this.lbl_admin_welcome.setText("Welcome: " + this.user.getUserName());
    }
}
