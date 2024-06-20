package View;

import Business.UserManager;
import Entity.User;

import javax.swing.*;

public class StaffView extends Layout {
    private JPanel container;
    private JPanel pnl_staff_top;
    private JButton logOutButton;
    private JTabbedPane tab_menu;
    private JTable tbl_hotel;
    private JScrollPane scrl_hotel;
    private JLabel lbl_staff_welcome;
    private User user;
    private UserManager userManager;

    public StaffView(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        this.add(container);
        this.guiInitialize(1000, 500);

        if (this.user == null) {
            dispose();
        }

        this.lbl_staff_welcome.setText("Welcome: " + this.user.getUserName());

        loadHotelTable();
    }

    public void loadHotelTable() {

    }
}
