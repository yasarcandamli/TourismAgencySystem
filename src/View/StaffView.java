package View;

import Business.HotelManager;
import Business.UserManager;
import Core.Helper;
import Entity.Hotel;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JPopupMenu hotel_menu;

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
        loadTableComponent();
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"Hotel ID", "Name", "City", "District", "Address", "E-Mail", "Phone Number", "Star", "Facilities"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll()); //Transfer all users to the table with methods in HotelManager
        }
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadTableComponent() {
        this.hotel_menu = new JPopupMenu();
        this.selectRow(this.tbl_hotel, this.hotel_menu);

        //We will use the new user creation and update features on the same window. If we send an empty user we will create a new user, if we send an existing user we will update it
        this.hotel_menu.add("New").addActionListener(e -> {
            HotelEditView hotelEditView = new HotelEditView(new Hotel());
            hotelEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });
        this.hotel_menu.add("Update").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelEditView hotelEditView = new HotelEditView(this.hotelManager.getById(selectHotelId));
            hotelEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });
        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectHotelId)) {
                    Helper.showMessage("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu); //Integrating pop-up menu in the table
    }
}
