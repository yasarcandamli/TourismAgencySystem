package View;

import Business.HostelTypeManager;
import Business.SeasonManager;
import Core.Helper;
import Entity.Hotel;
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
    private Hotel hotel;
    private Season season;
    private HostelTypeManager hostelTypeManager;
    private SeasonManager seasonManager;
    private DefaultTableModel tmdl_hostel_type = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private JPopupMenu hotel_type_menu;
    private JPopupMenu season_menu;

    public HotelDetailView(Hotel hotel) {
        this.hotel = hotel;
        this.season = new Season();
        this.hostelTypeManager = new HostelTypeManager();
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitialize(500, 500);

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
    }

    private void loadHostelTypeTable() {
        Object[] column_season = {"Hostel Type ID", "Hotel ID", "Hostel Type"};
        ArrayList<Object[]> hostelTypeList = this.hostelTypeManager.getForTable(column_season.length, this.hostelTypeManager.findAll());
        this.createTable(this.tmdl_hostel_type, this.tbl_hostel_type, column_season, hostelTypeList);
    }

    private void loadHostelTypeComponent() {
        this.hotel_type_menu = new JPopupMenu();
        this.selectRow(this.tbl_hostel_type, this.hotel_type_menu);

        this.hotel_type_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelTypeId = this.getTableSelectedRow(tbl_hostel_type, 0);
                if (this.hostelTypeManager.delete(selectHotelTypeId)) {
                    Helper.showMessage("done");
                    loadHostelTypeTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.btn_add_hostel_type.addActionListener(e -> {

            if (!rbtn_ultra_all_inclusive.isSelected() && !rbtn_all_inclusive.isSelected() && !rbtn_room_breakfast.isSelected() && !rbtn_full_board.isSelected() && !rbtn_half_board.isSelected() && !rbtn_bed_only.isSelected() && !rbtn_full_credit_excluding_alcohol.isSelected()){
                Helper.showMessage("choose");
            } else {
                if (rbtn_ultra_all_inclusive.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_ultra_all_inclusive.getText());
                }
                if (rbtn_all_inclusive.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_all_inclusive.getText());
                }
                if (rbtn_room_breakfast.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_room_breakfast.getText());
                }
                if (rbtn_full_board.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_full_board.getText());
                }
                if (rbtn_half_board.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_half_board.getText());
                }
                if (rbtn_bed_only.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_bed_only.getText());
                }
                if (rbtn_full_credit_excluding_alcohol.isSelected()){
                    this.hostelTypeManager.add(this.hotel, rbtn_full_credit_excluding_alcohol.getText());
                }
                loadHostelTypeTable();

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
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(column_hostel_type.length, this.seasonManager.findAll());
        this.createTable(this.tmdl_season, this.tbl_season, column_hostel_type, seasonList);
    }

    private void loadSeasonComponent() {
        this.season_menu = new JPopupMenu();
        this.selectRow(this.tbl_season, this.season_menu);

        this.season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectSeasonId)) {
                    Helper.showMessage("done");
                    loadSeasonTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        btn_add_season.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_season_start_date) || Helper.isFieldEmpty(fld_season_end_date) || Helper.isFieldEmpty(fld_season_name)){
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
