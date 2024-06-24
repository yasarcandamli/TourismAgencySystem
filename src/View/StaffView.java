package View;

import Business.*;
import Core.Helper;
import Entity.Hotel;
import Entity.Room;
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
    private JButton btn_filter_room_search;
    private JButton btn_clear_room_search;
    private JTable tbl_room_search;
    private JScrollPane scrl_room_search;
    private JPanel pnl_new_reservation;
    private JTable tbl_new_reservation;
    private JScrollPane scrl_new_reservation;
    private JPanel pnl_top;
    private JTextField fld_address_hotel_name_new_reservation;
    private JButton btn_filter_new_reservation;
    private JButton btn_clear_new_reservation;
    private JFormattedTextField fld_check_in_date_new_reservation;
    private JFormattedTextField fld_check_out_date_new_reservation;
    private JTextField fld_address_hotel_name_reservations;
    private JButton btn_filter_reservations;
    private JButton btn_clear_reservations;
    private JScrollPane scrl_reservations;
    private JPanel pnl_reservations_top;
    private JTable tbl_reservations;
    private JFormattedTextField fld_check_in_date_reservations;
    private JFormattedTextField fld_check_out_date_reservations;
    private User user;
    private UserManager userManager;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;
    private Object[] col_hotel;
    private Object[] col_room_search;
    private Object[] col_new_reservation;
    private Object[] col_reservations;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room_search = new DefaultTableModel();
    private DefaultTableModel tmdl_new_reservation = new DefaultTableModel();
    private DefaultTableModel tmdl_reservations = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private JPopupMenu room_search_menu;
    private JPopupMenu new_reservation_menu;
    private JPopupMenu reservations_menu;

    public StaffView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitialize(1500, 500);

        if (this.user == null) {
            dispose();
        }

        this.lbl_staff_welcome.setText("Welcome: " + this.user.getUserName());

        //General
        loadComponent();

        //Hotel Management
        loadHotelTable(null);
        loadTableComponent();

        //All Rooms
        loadRoomSearchTable(null);
        loadRoomSearchComponent();

        //New Reservation
        loadNewReservationTable(null);
        loadNewReservationComponent();

        //Reservations
        loadReservationsTable(null);
        loadReservationComponent();
    }

    public void loadReservationsTable(ArrayList<Object[]> reservationList) {
        this.col_reservations = new Object[]{
                "Reservation ID",
                "Room ID",
                "Name",
                "Identity No",
                "Phone No",
                "Email",
                "Check In Date",
                "Check Out Date",
                "Adult Number",
                "Child Number",
                "Note",
                "Price"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservations.length, this.reservationManager.findAll()); //Transfer all users to the table with methods in HotelManager
        }
        createTable(this.tmdl_reservations, this.tbl_reservations, col_reservations, reservationList);
    }

    public void loadReservationComponent() {
        this.reservations_menu = new JPopupMenu();
        this.selectRow(this.tbl_reservations, this.reservations_menu);

        this.reservations_menu.add("Update").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(this.tbl_reservations, 0);
            UpdateReservationView updateReservationView = new UpdateReservationView(this.reservationManager.getById(selectReservationId));
            updateReservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationsTable(null);
                }
            });
        });
        this.reservations_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectReservationId = this.getTableSelectedRow(tbl_reservations, 0);
                if (this.reservationManager.delete(selectReservationId)) {
                    Helper.showMessage("done");
                    loadReservationsTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_reservations.setComponentPopupMenu(reservations_menu);
    }

    public void loadNewReservationTable(ArrayList<Object[]> roomList) {
        this.col_new_reservation = new Object[]{"Room ID",
                "Hotel Name",
                "Hotel Star",
                "Address",
                "Room Type",
                "Hostel Type",
                "Season",
                "Bed Number",
                "Room Area (m2)",
                "Room Number",
                "Adult Price",
                "Child Price",
                "Facilities",
                "TV",
                "Minibar",
                "Game Console",
                "Safe Box",
                "Projection"};
        if (roomList == null) {
            roomList = this.roomManager.getForTableRoomSearch(col_new_reservation.length, this.roomManager.findAll()); //Transfer all users to the table with methods in HotelManager
        }
        createTable(this.tmdl_new_reservation, this.tbl_new_reservation, col_new_reservation, roomList);
    }

    public void loadNewReservationComponent() {
        this.new_reservation_menu = new JPopupMenu();
        this.selectRow(this.tbl_new_reservation, this.new_reservation_menu);

        this.new_reservation_menu.add("Make a Reservation").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(this.tbl_new_reservation, 0);
            MakingAReservationView makingAReservationView = new MakingAReservationView(
                    this.roomManager.getById(selectRoomId),
                    this.fld_check_in_date_new_reservation.getText(),
                    this.fld_check_out_date_new_reservation.getText()
            );
            makingAReservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadNewReservationTable(null);
                    loadReservationsTable(null);
                }
            });
        });
        this.tbl_new_reservation.setComponentPopupMenu(new_reservation_menu);

        btn_filter_new_reservation.addActionListener(e -> {
            ArrayList<Room> roomList = this.roomManager.searchForNewReservation(
                    fld_check_in_date_new_reservation.getText(),
                    fld_check_out_date_new_reservation.getText(),
                    fld_address_hotel_name_new_reservation.getText());
            ArrayList<Object[]> roomSearchRow = this.roomManager.getForTableRoomSearch(this.col_new_reservation.length, roomList);
            loadNewReservationTable(roomSearchRow);
        });

        btn_clear_new_reservation.addActionListener(e -> {
//            if (Helper.isFormattedFieldListEmpty(new JFormattedTextField[] {this.fld_check_in_date_new_reservation, this.fld_check_out_date_new_reservation})) {
//                Helper.showMessage("fill");
//            }
            fld_address_hotel_name_new_reservation.setText(null);
            loadNewReservationTable(null);
        });
    }

    public void loadRoomSearchTable(ArrayList<Object[]> roomList) {
        this.col_room_search = new Object[]{"Room ID",
                "Hotel Name",
                "Hotel Star",
                "Address",
                "Room Type",
                "Hostel Type",
                "Season",
                "Bed Number",
                "Room Area (m2)",
                "Room Number",
                "Adult Price",
                "Child Price",
                "Facilities",
                "TV",
                "Minibar",
                "Game Console",
                "Safe Box",
                "Projection"};
        if (roomList == null) {
            roomList = this.roomManager.getForTableRoomSearch(col_room_search.length, this.roomManager.findAll()); //Transfer all users to the table with methods in HotelManager
        }
        createTable(this.tmdl_room_search, this.tbl_room_search, col_room_search, roomList);
    }

    public void loadRoomSearchComponent() {
        this.room_search_menu = new JPopupMenu();
        this.selectRow(this.tbl_room_search, this.room_search_menu);

//        this.room_search_menu.add("Make a Reservation").addActionListener(e -> {
//
//        });
        this.tbl_room_search.setComponentPopupMenu(room_search_menu);

        btn_filter_room_search.addActionListener(e -> {
            ArrayList<Room> roomList = this.roomManager.searchForRoomSearch(
//                    fld_checkin_date_room_search.getText(),
//                    fld_checkout_date_room_search.getText(),
                    fld_city_district_hotel_room_search.getText());
            ArrayList<Object[]> roomSearchRow = this.roomManager.getForTableRoomSearch(this.col_room_search.length, roomList);
            loadRoomSearchTable(roomSearchRow);
        });

        btn_clear_room_search.addActionListener(e -> {
            fld_city_district_hotel_room_search.setText(null);
//            fld_checkin_date_room_search.setText(null);
//            fld_checkout_date_room_search.setText(null);
            loadRoomSearchTable(null);
        });
    }

//    public void loadRoomSearchComponent() {
//        this.room_search_menu = new JPopupMenu();
//        this.selectRow(this.tbl_room_search, this.room_search_menu);
//
//        btn_filter_room_search.addActionListener(e -> {
//            String cityDistrictHotelName = fld_city_district_hotel_room_search.getText();
//            LocalDate checkIn = LocalDate.parse(fld_checkin_date_room_search.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            LocalDate checkOut = LocalDate.parse(fld_checkout_date_room_search.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//            LocalDate checkInDate = null;
//            LocalDate checkOutDate = null;
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//            String query = "SELECT * FROM hotel WHERE name LIKE '%(name)%' OR address LIKE '%(address)%'";
//            query = query.replace("(name)", cityDistrictHotelName);
//            query = query.replace("(address)", cityDistrictHotelName);
//            ArrayList<Hotel> searchHotel = this.hotelManager.selectByQuery(query);
//
//            ArrayList<Room> temporaryRoomList = new ArrayList<>();
//
//            for (Hotel hotel : searchHotel) {
//                for (Room room : this.roomManager.findAll()) {
//                    if (room.getHotelId() == hotel.getHotelId() && room.getRoomNumber() > 0) { // Oda stok kontrolü yapılıyor
//                        temporaryRoomList.add(room);
//                    }
//                }
//            }
//            ArrayList<Room> searchingRoomList = new ArrayList<>();
//            for (Room room : temporaryRoomList) {
//                Season temporarySeason = this.seasonManager.getById(room.getRoomId());
//
//                if (checkInDate.isAfter(this.seasonManager.getById(room.getSeasonId()).getSeasonStartDate()) && checkInDate.isAfter(this.seasonManager.getById(room.getSeasonId()).getSeasonEndDate())) {
//                    searchingRoomList.add(room);
//                }
//            }
//            loadRoomSearchTable(searchingRoomList); // Değerlendirme 14 : Uygun odalar listelenip kullanıcıya gösteriliyor
//        });
//    }

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

    public void createUIComponents() throws ParseException {
        this.fld_checkin_date_room_search = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkin_date_room_search.setText("02/05/2025");
        this.fld_checkout_date_room_search = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkout_date_room_search.setText("08/05/2025");
        this.fld_check_in_date_new_reservation = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_in_date_new_reservation.setText("02/05/2025");
        this.fld_check_out_date_new_reservation = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_out_date_new_reservation.setText("08/05/2025");
    }

    public void loadComponent() {
        this.btn_log_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }
}
