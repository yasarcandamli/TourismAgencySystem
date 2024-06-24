package View;

import Entity.Reservation;

import javax.swing.*;

public class UpdateReservationView extends Layout {
    private JPanel container;

    public UpdateReservationView(Reservation reservation) {
        this.add(container);
        this.guiInitialize(500,750);
    }
}
