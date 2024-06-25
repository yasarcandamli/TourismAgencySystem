package View;

import Business.ReservationManager;
import Business.RoomManager;
import Core.Helper;
import Entity.Reservation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UpdateReservationView extends Layout {
    private JPanel container;
    private JTextField fld_name_update;
    private JTextField fld_identity_no_update;
    private JTextField fld_phone_no_update;
    private JTextField fld_email_update;
    private JTextField fld_check_in_date_update;
    private JTextField fld_check_out_date_update;
    private JTextField fld_adult_number_update;
    private JTextField fld_child_number_update;
    private JTextArea tarea_customer_note_update;
    private JTextField fld_total_price_update;
    private JButton btn_update;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private Reservation reservation;

    public UpdateReservationView(Reservation reservation) {
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitialize(700,300);

        //Dynamic day calculation
        fld_adult_number_update.getDocument().addDocumentListener(new DocumentListener() {
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

        fld_child_number_update.getDocument().addDocumentListener(new DocumentListener() {
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

        this.fld_check_in_date_update.setText(LocalDate.parse(this.reservation.getCheckInDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.fld_check_out_date_update.setText(LocalDate.parse(this.reservation.getCheckOutDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.fld_name_update.setText(this.reservation.getCustomerName());
        this.fld_identity_no_update.setText(this.reservation.getCustomerIdentityNumber());
        this.fld_phone_no_update.setText(this.reservation.getCustomerPhoneNumber());
        this.fld_email_update.setText(this.reservation.getCustomerEmail());
        this.fld_adult_number_update.setText(String.valueOf(this.reservation.getAdultNumber()));
        this.fld_child_number_update.setText(String.valueOf(this.reservation.getChildNumber()));
        this.tarea_customer_note_update.setText(this.reservation.getCustomerNote());


        btn_update.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_name_update,
                    this.fld_identity_no_update,
                    this.fld_phone_no_update,
                    this.fld_email_update,
                    this.fld_adult_number_update,
                    this.fld_child_number_update,
                    this.fld_total_price_update,
                    this.fld_check_in_date_update,
                    this.fld_check_out_date_update
            };

            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMessage("fill");
            } else {
//                this.reservation.setRoomId(this.room.getRoomId());
                this.reservation.setCustomerName(this.fld_name_update.getText());
                this.reservation.setCustomerIdentityNumber(this.fld_identity_no_update.getText());
                this.reservation.setCustomerPhoneNumber(this.fld_phone_no_update.getText());
                this.reservation.setCustomerEmail(this.fld_email_update.getText());
                this.reservation.setCheckInDate(String.valueOf(LocalDate.parse(this.fld_check_in_date_update.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                this.reservation.setCheckOutDate(String.valueOf(LocalDate.parse(this.fld_check_in_date_update.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                this.reservation.setAdultNumber(Integer.parseInt(this.fld_adult_number_update.getText()));
                this.reservation.setChildNumber(Integer.parseInt(this.fld_child_number_update.getText()));
                this.reservation.setCustomerNote(this.tarea_customer_note_update.getText());
                this.reservation.setTotalPrice(Integer.parseInt(this.fld_total_price_update.getText()));

                if (this.reservationManager.update(this.reservation)) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
    //Dynamic price calculation
    private void updateTotalPrice() {
        try {
            LocalDate checkInDate = LocalDate.parse(this.fld_check_in_date_update.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate checkOutDate = LocalDate.parse(this.fld_check_out_date_update.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int daysBetween = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            int adultNumber = Integer.parseInt(this.fld_adult_number_update.getText());
            int childNumber = Integer.parseInt(this.fld_child_number_update.getText());

            int totalPrice = ((adultNumber * this.roomManager.getById(this.reservation.getRoomId()).getAdultPrice()) + (childNumber * this.roomManager.getById(this.reservation.getRoomId()).getChildPrice())) * (daysBetween);
            this.fld_total_price_update.setText(String.valueOf(totalPrice));
        } catch (NumberFormatException e) {
            this.fld_total_price_update.setText("0");
        }
    }
}
