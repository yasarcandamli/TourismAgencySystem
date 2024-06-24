package Business;

import Core.Helper;
import Dao.ReservationDao;
import Entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll() {
        return reservationDao.findAll();
    }

    public boolean add(Reservation reservation) {
        if (reservation.getReservationId() != 0) {
            Helper.showMessage("error");
        }
        return this.reservationDao.add(reservation);
    }

    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getReservationId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int reservationId) {
        if (this.getById(reservationId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.reservationDao.delete(reservationId);
    }

    public Reservation getById(int selectReservationId) {
        return this.reservationDao.getById(selectReservationId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservationList) { //Create objects as many as the number of columns
        ArrayList<Object[]> reservationObjectList = new ArrayList<>();

        for (Reservation object : reservationList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getReservationId();
            rowObject[i++] = object.getRoomId();
            rowObject[i++] = object.getCustomerName();
            rowObject[i++] = object.getCustomerIdentityNumber();
            rowObject[i++] = object.getCustomerPhoneNumber();
            rowObject[i++] = object.getCustomerEmail();
            rowObject[i++] = object.getCheckInDate();
            rowObject[i++] = object.getCheckOutDate();
            rowObject[i++] = object.getAdultNumber();
            rowObject[i++] = object.getChildNumber();
            rowObject[i++] = object.getCustomerNote();
            rowObject[i++] = object.getTotalPrice();
            reservationObjectList.add(rowObject);
        }
        return reservationObjectList;
    }

//    public ArrayList<Hotel> filterForTable(Hotel.UserType userType) {
//        String select = "SELECT * FROM public.users";
//        ArrayList<String> whereList = new ArrayList<>();
//
//        if (userType != null) {
//            whereList.add("user_type = '" + userType.toString() + "'");
//        }
//        String whereString = String.join(" AND ", whereList);
//        String query = select;
//        if (!whereString.isEmpty()) {
//            query += " WHERE " + whereString;
//        }
//        return this.hotelDao.selectByQuery(query);
//    }
}
