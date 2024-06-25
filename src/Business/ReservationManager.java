package Business;

import Core.Helper;
import Dao.ReservationDao;
import Dao.RoomDao;
import Entity.Reservation;
import Entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;
    private final RoomManager roomManager;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
        this.roomManager = new RoomManager();
    }

    public ArrayList<Reservation> findAll() {
        return reservationDao.findAll();
    }

    public boolean add(Reservation reservation) {
        if (reservation.getReservationId() != 0) {
            Helper.showMessage("error");
            return false;
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
        ArrayList<Reservation> busyRoomID = new ArrayList<>();
        return reservationObjectList;
    }

    public ArrayList<Reservation> searchForReservation(String customerName, String customerIdentity, String customerPhone) {
        String query = "SELECT * FROM public.reservation AS re";
        ArrayList<String> where = new ArrayList<>();

        if (!customerName.isEmpty()) where.add("re.customer_name = '" + customerName + "'");
        if (!customerIdentity.isEmpty()) where.add("re.customer_identity_number = '" + customerIdentity + "'");
        if (!customerPhone.isEmpty()) where.add("re.customer_phone_number = '" + customerPhone + "'");

        String whereStr = String.join(" AND ", where);

        System.out.println(customerIdentity);
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }

        ArrayList<Reservation> searchedRoomList = this.reservationDao.selectByQuery(query);
        return searchedRoomList;
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
