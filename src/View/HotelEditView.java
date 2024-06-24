package View;

import Business.HotelManager;
import Core.Helper;
import Entity.Hotel;

import javax.swing.*;

public class HotelEditView extends Layout {
    private JPanel container;
    private JLabel lbl_hotel_edit_header;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JLabel lbl_hotel_city;
    private JTextField fld_hotel_district;
    private JLabel lbl_hotel_district;
    private JTextField fld_hotel_address;
    private JLabel lbl_hotel_address;
    private JTextField fld_hotel_email;
    private JLabel lbl_hotel_email;
    private JLabel lbl_hotel_phone_number;
    private JTextField fld_hotel_phone_number;
    private JTextField fld_hotel_star;
    private JLabel lbl_hotel_star;
    private JTextField fld_hotel_facility;
    private JButton btn_hotel_save;
    private JLabel lbl_hotel_facility;
    private JComboBox<Hotel.HotelStar> cmb_hotel_star;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelEditView(Hotel hotel) {
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitialize(750, 550);

        this.cmb_hotel_star.setModel(new DefaultComboBoxModel<>(Hotel.HotelStar.values()));
        this.cmb_hotel_star.setSelectedItem(null);

        if (this.hotel.getHotelId() != 0) {
            this.fld_hotel_name.setText(this.hotel.getHotelName());
            this.fld_hotel_city.setText(this.hotel.getHotelCity());
            this.fld_hotel_district.setText(this.hotel.getHotelDistrict());
            this.fld_hotel_address.setText(this.hotel.getHotelAddress());
            this.fld_hotel_email.setText(this.hotel.getHotelEmail());
            this.fld_hotel_phone_number.setText(this.hotel.getHotelPhoneNumber());
            this.cmb_hotel_star.getModel().setSelectedItem(this.hotel.getHotelStar());
            this.fld_hotel_facility.setText(this.hotel.getHotelFacility());
        }

        this.btn_hotel_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_name, this.fld_hotel_city, this.fld_hotel_district, this.fld_hotel_address, this.fld_hotel_email, this.fld_hotel_phone_number, this.fld_hotel_facility})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.hotel.setHotelName(fld_hotel_name.getText());
                this.hotel.setHotelCity(fld_hotel_city.getText());
                this.hotel.setHotelDistrict(fld_hotel_district.getText());
                this.hotel.setHotelAddress(fld_hotel_address.getText());
                this.hotel.setHotelEmail(fld_hotel_email.getText());
                this.hotel.setHotelPhoneNumber(fld_hotel_phone_number.getText());
                this.hotel.setHotelStar((Hotel.HotelStar) cmb_hotel_star.getSelectedItem());
                this.hotel.setHotelFacility(fld_hotel_facility.getText());

                if (this.hotel.getHotelId() != 0) {
                    result = this.hotelManager.update(this.hotel);
                } else {
                    result = this.hotelManager.add(this.hotel);
                }

                if (result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
