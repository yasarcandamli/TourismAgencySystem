package View;

import Business.HotelManager;
import Business.UserManager;
import Core.Helper;
import Entity.Hotel;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class StaffView extends Layout {
    private JPanel container;
    private JPanel pnl_staff_top;
    private JButton btn_log_out;
    private JTabbedPane tab_menu;
    private JTable tbl_hotel;
    private JScrollPane scrl_hotel;
    private JLabel lbl_staff_welcome;
    private JPanel pnl_hotel_management;
    private JPanel pnl_room_search;
    private JTextField fld_city_district_hotel_room_search;
    private JFormattedTextField fld_checkin_date_room_search;
    private JFormattedTextField fld_checkout_date_room_search;
    private JComboBox cmb_adult_number;
    private JComboBox cmb_child_number;
    private JButton btn_filter_room_search;
    private JButton btn_clear_room_search;
    private JTable tbl_room_search;
    private JScrollPane scrl_room_search;
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

        //General
        loadComponent();

        //Hotel Management
        loadHotelTable(null);
        loadTableComponent();

        //Room Search

    }

    private void loadComponent() {
        this.btn_log_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
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
        this.hotel_menu.add("Hotel Details").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelDetailView hotelDetailView = new HotelDetailView(this.hotelManager.getById(selectHotelId));
            hotelDetailView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu); //Integrating pop-up menu in the table
    }

    private void createUIComponents() throws ParseException {
        this.fld_checkin_date_room_search = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkout_date_room_search = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }
}
