package View;

import Business.HostelTypeManager;
import Business.ReservationManager;
import Business.RoomManager;
import Business.SeasonManager;
import Core.ComboItem;
import Core.Helper;
import Entity.HostelType;
import Entity.Hotel;
import Entity.Room;
import Entity.Season;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelDetailView extends Layout {
    private JPanel container;
    private JTabbedPane tab_menu_hotel_detail;
    private JRadioButton rbtn_ultra_all_inclusive;
    private JRadioButton rbtn_full_board;
    private JRadioButton rbtn_all_inclusive;
    private JRadioButton rbtn_room_breakfast;
    private JRadioButton rbtn_half_board;
    private JRadioButton rbtn_bed_only;
    private JRadioButton rbtn_full_credit_excluding_alcohol;
    private JLabel lbl_hotel_name;
    private JButton btn_add_hostel_type;
    private JTable tbl_hostel_type;
    private JScrollPane scrl_hostel_type;
    private JFormattedTextField fld_season_start_date;
    private JFormattedTextField fld_season_end_date;
    private JButton btn_add_season;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JTextField fld_season_name;
    private JScrollPane scrl_hote_room;
    private JTable tbl_hotel_room;
    private JComboBox cmb_season_hotel_room;
    private JComboBox cmb_hostel_type_hotel_room;
    private JComboBox cmb_room_type_hotel_room;
    private JTextField fld_room_number_hotel_room;
    private JTextField fld_adult_price_hotel_room;
    private JTextField fld_child_price_hotel_room;
    private JTextField fld_properties_hotel_room;
    private JButton btn_add_hotel_room;
    private JTextField fld_bed_number_hotel_room;
    private JTextField fld_room_area_hotel_room;
    private JComboBox cmb_tv_hotel_room;
    private JComboBox cmb_minibar_hotel_room;
    private JComboBox cmb_game_console_hotel_room;
    private JComboBox cmb_safe_box_hotel_room;
    private JComboBox cmb_projection_hotel_room;
    private JRadioButton rbtn_single_hotel_room;
    private JRadioButton rbtn_double_hotel_room;
    private JRadioButton rbtn_suite_hotel_room;
    private JRadioButton rbtn_junior_suite_hotel_room;
    private Hotel hotel;
    private Season season;
    private Room room;
    private HostelTypeManager hostelTypeManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private DefaultTableModel tmdl_hostel_type = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_hotel_room = new DefaultTableModel();
    private JPopupMenu hotel_type_menu;
    private JPopupMenu season_menu;
    private JPopupMenu hotel_room_menu;

    public HotelDetailView(Hotel hotel) {
        this.hotel = hotel;
        this.season = new Season();
        this.room = new Room();
        this.hostelTypeManager = new HostelTypeManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitialize(1200, 750);

        this.lbl_hotel_name.setText(this.hotel.getHotelName());

        this.tbl_hostel_type.setModel(tmdl_hostel_type);
        this.tbl_hostel_type.getTableHeader().setReorderingAllowed(false);

        this.tbl_season.setModel(tmdl_season);
        this.tbl_season.getTableHeader().setReorderingAllowed(false);

        //Hostel Type
        loadHostelTypeTable();
        loadHostelTypeComponent();

        //Season
        loadSeasonTable();
        loadSeasonComponent();

        //Hotel Room
        loadHotelRoomTable();
        loadHotelRoomComponent();
    }

    private void loadHotelRoomTable() {
        Object[] column_hotel_room = {"Room ID", "Season", "Hostel Type", "Room Type", "Bed Number", "Room Area (m2)", "Room Number", "Adult Price", "Child Price", "TV", "Minibar", "Game Console", "Safe Box", "Projection"};
        ArrayList<Object[]> hotelRoomList = this.roomManager.getForTable(column_hotel_room.length, this.roomManager.findAllForTable(this.hotel.getHotelId()));
        this.createTable(this.tmdl_hotel_room, this.tbl_hotel_room, column_hotel_room, hotelRoomList);
    }

    private void loadHotelRoomComponent() {
        this.hotel_room_menu = new JPopupMenu();
        this.selectRow(this.tbl_hotel_room, this.hotel_room_menu);

        this.hotel_room_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectRoomId = this.getTableSelectedRow(tbl_hotel_room, 0);
                if (this.roomManager.delete(selectRoomId)) {
                    Helper.showMessage("done");
                    loadHotelRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.rbtn_single_hotel_room);
        buttonGroup.add(this.rbtn_double_hotel_room);
        buttonGroup.add(this.rbtn_junior_suite_hotel_room);
        buttonGroup.add(this.rbtn_suite_hotel_room);

        refreshHostelTypeComboBox();
        refreshSeasonComboBox();

        this.cmb_tv_hotel_room.setModel(new DefaultComboBoxModel<>(Room.isThere.values()));
        this.cmb_minibar_hotel_room.setModel(new DefaultComboBoxModel<>(Room.isThere.values()));
        this.cmb_game_console_hotel_room.setModel(new DefaultComboBoxModel<>(Room.isThere.values()));
        this.cmb_safe_box_hotel_room.setModel(new DefaultComboBoxModel<>(Room.isThere.values()));
        this.cmb_projection_hotel_room.setModel(new DefaultComboBoxModel<>(Room.isThere.values()));

        btn_add_hotel_room.addActionListener(e -> {
            if (!rbtn_suite_hotel_room.isSelected() && !rbtn_junior_suite_hotel_room.isSelected() && !rbtn_single_hotel_room.isSelected() && !rbtn_double_hotel_room.isSelected()) {
                Helper.showMessage("Please, choose a Room Type!");
            } else {
                if (Helper.isFieldListEmpty(new JTextField[]{this.fld_bed_number_hotel_room, this.fld_room_area_hotel_room, this.fld_room_number_hotel_room, this.fld_adult_price_hotel_room, this.fld_child_price_hotel_room})) {
                    Helper.showMessage("fill");
                } else {
                    String selectedRoomType = "";
                    if (rbtn_suite_hotel_room.isSelected()) {
                        selectedRoomType = rbtn_suite_hotel_room.getText();
                    } else if (rbtn_junior_suite_hotel_room.isSelected()) {
                        selectedRoomType = rbtn_junior_suite_hotel_room.getText();
                    } else if (rbtn_single_hotel_room.isSelected()) {
                        selectedRoomType = rbtn_single_hotel_room.getText();
                    } else if (rbtn_double_hotel_room.isSelected()) {
                        selectedRoomType = rbtn_double_hotel_room.getText();
                    }
                    boolean result;
                    ComboItem selectedSeason = (ComboItem) cmb_season_hotel_room.getSelectedItem();
                    ComboItem selectedHostelType = (ComboItem) cmb_hostel_type_hotel_room.getSelectedItem();
                    this.room.setHotelId(this.hotel.getHotelId());
                    this.room.setSeasonId(selectedSeason.getKey());
                    this.room.setHostelTypeId(selectedHostelType.getKey());
                    this.room.setBedNumber(Integer.parseInt(fld_bed_number_hotel_room.getText()));
                    this.room.setRoomArea(Integer.parseInt(fld_room_area_hotel_room.getText()));
                    this.room.setRoomNumber(Integer.parseInt(fld_room_number_hotel_room.getText()));
                    this.room.setAdultPrice(Integer.parseInt(fld_adult_price_hotel_room.getText()));
                    this.room.setChildPrice(Integer.parseInt(fld_child_price_hotel_room.getText()));
                    this.room.setTv((Room.isThere) cmb_tv_hotel_room.getSelectedItem());
                    this.room.setMinibar((Room.isThere) cmb_minibar_hotel_room.getSelectedItem());
                    this.room.setGameConsole((Room.isThere) cmb_game_console_hotel_room.getSelectedItem());
                    this.room.setSafeBox((Room.isThere) cmb_safe_box_hotel_room.getSelectedItem());
                    this.room.setProjection((Room.isThere) cmb_projection_hotel_room.getSelectedItem());
                    result = this.roomManager.add(this.room, selectedRoomType);

                    if (result) {
                        Helper.showMessage("done");
                        loadHotelRoomTable();
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
    }

    private void refreshSeasonComboBox() {
        cmb_season_hotel_room.removeAllItems();
        for (Season season : this.seasonManager.findAllForTable(this.hotel.getHotelId())) {
            cmb_season_hotel_room.addItem(new ComboItem(season.getSeasonId(), season.getSeasonName()));
        }
    }

    private void refreshHostelTypeComboBox() {
        cmb_hostel_type_hotel_room.removeAllItems();
        for (HostelType hostelType : this.hostelTypeManager.findAllForTable(this.hotel.getHotelId())) {
            cmb_hostel_type_hotel_room.addItem(new ComboItem(hostelType.getHostelTypeId(), hostelType.getHostelType()));
        }
    }

    private void loadHostelTypeTable() {
        Object[] column_season = {"Hostel Type ID", "Hotel ID", "Hostel Type"};
        ArrayList<Object[]> hostelTypeList = this.hostelTypeManager.getForTable(column_season.length, this.hostelTypeManager.findAllForTable(this.hotel.getHotelId()));
        this.createTable(this.tmdl_hostel_type, this.tbl_hostel_type, column_season, hostelTypeList);
    }

    private void loadHostelTypeComponent() {
        this.hotel_type_menu = new JPopupMenu();
        this.selectRow(this.tbl_hostel_type, this.hotel_type_menu);

        this.hotel_type_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelTypeId = this.getTableSelectedRow(tbl_hostel_type, 0);
                if (this.hostelTypeManager.delete(selectHotelTypeId)) {
                    this.roomManager.deleteByHostelTypeId(selectHotelTypeId);
                    Helper.showMessage("done");
                    loadHostelTypeTable();
                    loadHotelRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.btn_add_hostel_type.addActionListener(e -> {

            if (!rbtn_ultra_all_inclusive.isSelected() && !rbtn_all_inclusive.isSelected() && !rbtn_room_breakfast.isSelected() && !rbtn_full_board.isSelected() && !rbtn_half_board.isSelected() && !rbtn_bed_only.isSelected() && !rbtn_full_credit_excluding_alcohol.isSelected()) {
                Helper.showMessage("choose");
            } else {
                if (rbtn_ultra_all_inclusive.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_ultra_all_inclusive.getText());
                }
                if (rbtn_all_inclusive.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_all_inclusive.getText());
                }
                if (rbtn_room_breakfast.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_room_breakfast.getText());
                }
                if (rbtn_full_board.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_full_board.getText());
                }
                if (rbtn_half_board.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_half_board.getText());
                }
                if (rbtn_bed_only.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_bed_only.getText());
                }
                if (rbtn_full_credit_excluding_alcohol.isSelected()) {
                    this.hostelTypeManager.add(this.hotel, rbtn_full_credit_excluding_alcohol.getText());
                }
                loadHostelTypeTable();

                refreshHostelTypeComboBox();

                rbtn_ultra_all_inclusive.setSelected(false);
                rbtn_all_inclusive.setSelected(false);
                rbtn_room_breakfast.setSelected(false);
                rbtn_full_board.setSelected(false);
                rbtn_half_board.setSelected(false);
                rbtn_bed_only.setSelected(false);
                rbtn_full_credit_excluding_alcohol.setSelected(false);
            }
        });
    }

    private void loadSeasonTable() {
        Object[] column_hostel_type = {"Season ID", "Hotel ID", "Season Start Date", "Season End Date", "Season Name"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(column_hostel_type.length, this.seasonManager.findAllForTable(this.hotel.getHotelId()));
        this.createTable(this.tmdl_season, this.tbl_season, column_hostel_type, seasonList);
    }

    private void loadSeasonComponent() {
        this.season_menu = new JPopupMenu();
        this.selectRow(this.tbl_season, this.season_menu);

        this.season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectSeasonId)) {
                    this.roomManager.deleteBySeasonId(selectSeasonId);
                    Helper.showMessage("done");
                    loadSeasonTable();
                    loadHotelRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        btn_add_season.addActionListener(e -> {
            if (Helper.isFormattedFieldEmpty(fld_season_start_date) || Helper.isFormattedFieldEmpty(fld_season_end_date) || Helper.isFieldEmpty(fld_season_name)) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.season.setHotelId(this.hotel.getHotelId());
                this.season.setSeasonStartDate(LocalDate.parse(fld_season_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setSeasonEndDate(LocalDate.parse(fld_season_end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setSeasonName(fld_season_name.getText());

                result = this.seasonManager.add(this.season);

                if (result) {
                    Helper.showMessage("done");
                    loadSeasonTable();
                    refreshSeasonComboBox();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    private void createUIComponents() throws ParseException {
        this.fld_season_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_start_date.setText("01/01/2025");
        this.fld_season_end_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_end_date.setText("31/05/2025");
    }
}
