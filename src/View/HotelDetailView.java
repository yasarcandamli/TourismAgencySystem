package View;

import Business.HostelTypeManager;
import Core.Helper;
import Dao.HostelTypeDao;
import Entity.HostelType;
import Entity.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JButton btn_save_hostel_type;
    private JTable tbl_hostel_type;
    private JScrollPane scrl_hostel_type;
    private Hotel hotel;
    private HostelTypeManager hostelTypeManager;
    private DefaultTableModel tmdl_hostel_type = new DefaultTableModel();
    private JPopupMenu hotel_type_menu;

    public HotelDetailView(Hotel hotel) {
        this.hotel = hotel;
        this.hostelTypeManager = new HostelTypeManager();
        this.add(container);
        this.guiInitialize(500, 500);

        this.lbl_hotel_name.setText(this.hotel.getHotelName());

        this.tbl_hostel_type.setModel(tmdl_hostel_type);
        this.tbl_hostel_type.getTableHeader().setReorderingAllowed(false);

        loadHostelTypeTable();
        loadHostelTypeComponent();
    }

    private void loadHostelTypeTable() {
        Object[] column_hostel_type = {"Hostel Type ID", "Hotel ID", "Hostel Type"};
        ArrayList<Object[]> hostelTypeList = this.hostelTypeManager.getForTable(column_hostel_type.length, this.hostelTypeManager.findAll());
        this.createTable(this.tmdl_hostel_type, this.tbl_hostel_type, column_hostel_type, hostelTypeList);
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

        this.btn_save_hostel_type.addActionListener(e -> {

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
}
