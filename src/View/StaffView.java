package View;

import Business.HotelManager;
import Business.UserManager;
import Entity.Hotel;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

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
    private HotelManager hotelManager;
    private Object[] col_hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();

    public StaffView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitialize(1000, 500);

        if (this.user == null) {
            dispose();
        }

        this.lbl_staff_welcome.setText("Welcome: " + this.user.getUserName());

        loadHotelTable(null);
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"Hotel ID", "Name", "City", "District", "Address", "E-Mail", "Phone Number", "Star", "Facilities"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll()); //Transfer all users to the table with methods in HotelManager
        }
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }
}
