package View;

import Business.HotelManager;
import Business.ReservationManager;
import Business.RoomManager;
import Core.Helper;
import Entity.Reservation;
import Entity.Room;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MakingAReservationView extends Layout {
    private JPanel container;
    private JTextField fld_customer_name_reservation_make;
    private JTextField fld_identity_no_reservation_make;
    private JTextField fld_phone_no_reservation_make;
    private JTextField fld_email_reservation_make;
    private JTextField fld_check_in_date_reservation_make;
    private JTextField fld_check_out_date_reservation_make;
    private JTextField fld_adult_number_reservation_make;
    private JTextField fld_child_number_reservation_make;
    private JTextField fld_total_price_reservation_make;
    private JButton btn_reservation_make;
    private JTextArea tarea_note_reservation_make;
    private JLabel lbl_room_info_reservation_make;
    private Room room;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private HotelManager hotelManager;

    public MakingAReservationView(Room selectedRoom, String checkInDate, String checkOutDate) {
        this.room = selectedRoom;
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();

        this.add(container);
        this.guiInitialize(700, 400);

        lbl_room_info_reservation_make.setText(
                "Hotel: " + this.hotelManager.getById(this.room.getHotelId()).getHotelName() +
                        " / Bed Number: " + this.room.getBedNumber() +
                        " / Room Area (m2): " + this.room.getRoomArea() +
                        " / Adult Price: " + this.room.getAdultPrice() +
                        " / Child Price: " + this.room.getChildPrice()
        );

        this.fld_check_in_date_reservation_make.setText(checkInDate);
        this.fld_check_out_date_reservation_make.setText(checkOutDate);

        //test için
//        this.fld_customer_name_reservation_make.setText("Yaşar Can");
//        this.fld_identity_no_reservation_make.setText("000000000123123");
//        this.fld_phone_no_reservation_make.setText("000023123");
//        this.fld_email_reservation_make.setText("test@gmail.com");
//        this.fld_adult_number_reservation_make.setText("2");
//        this.fld_child_number_reservation_make.setText("1");
//        this.tarea_note_reservation_make.setText("NOTE");
        //Dinamik Fiyat Hesaplama
        fld_adult_number_reservation_make.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPrice();
            }
        });

        fld_child_number_reservation_make.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPrice();
            }
        });
//        this.fld_total_price_reservation_make.setText(String.valueOf((Integer.parseInt(this.fld_adult_number_reservation_make.getText()) * this.room.getAdultPrice()) + (Integer.parseInt(this.fld_child_number_reservation_make.getText()) * this.room.getChildPrice())));

        btn_reservation_make.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_customer_name_reservation_make,
                    this.fld_identity_no_reservation_make,
                    this.fld_phone_no_reservation_make,
                    this.fld_email_reservation_make,
                    this.fld_adult_number_reservation_make,
                    this.fld_child_number_reservation_make,
                    this.fld_total_price_reservation_make,
                    this.fld_check_in_date_reservation_make,
                    this.fld_check_out_date_reservation_make
            };

            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMessage("fill");
            } else {
                Reservation reservation = new Reservation();
                reservation.setRoomId(this.room.getRoomId());
                reservation.setCustomerName(this.fld_customer_name_reservation_make.getText());
                reservation.setCustomerIdentityNumber(this.fld_identity_no_reservation_make.getText());
                reservation.setCustomerPhoneNumber(this.fld_phone_no_reservation_make.getText());
                reservation.setCustomerEmail(this.fld_email_reservation_make.getText());
                reservation.setCheckInDate(String.valueOf(LocalDate.parse(checkInDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                reservation.setCheckOutDate(String.valueOf(LocalDate.parse(checkOutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                reservation.setAdultNumber(Integer.parseInt(this.fld_adult_number_reservation_make.getText()));
                reservation.setChildNumber(Integer.parseInt(this.fld_child_number_reservation_make.getText()));
                reservation.setCustomerNote(this.tarea_note_reservation_make.getText());
                reservation.setTotalPrice(Integer.parseInt(this.fld_total_price_reservation_make.getText()));

                if (this.reservationManager.add(reservation)) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    //Dinamik hesaplama için
    private void updateTotalPrice() {
        try {
            int adultNumber = Integer.parseInt(fld_adult_number_reservation_make.getText());
            int childNumber = Integer.parseInt(fld_child_number_reservation_make.getText());
            int totalPrice = (adultNumber * this.room.getAdultPrice()) + (childNumber * this.room.getChildPrice());
            fld_total_price_reservation_make.setText(String.valueOf(totalPrice));
        } catch (NumberFormatException e) {
            fld_total_price_reservation_make.setText("0");
        }
    }
}
