package Business;

import Core.Helper;
import Dao.*;
import Entity.Reservation;
import Entity.Room;
import Entity.Season;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private final SeasonDao seasonDao;
    private final HostelTypeDao hostelTypeDao;
    private final HotelDao hotelDao;
    private final ReservationDao reservationDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.seasonDao = new SeasonDao();
        this.hostelTypeDao = new HostelTypeDao();
        this.hotelDao = new HotelDao();
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Room> findAll() {
        return roomDao.findAll();
    }

    public ArrayList<Room> findAllAvailableRoom() {
        return roomDao.findAllAvailableRoom();
    }

    public ArrayList<Room> findAllForTable(int hotelId) {
        return roomDao.findAllForTable(hotelId);
    }

    public boolean add(Room room, String roomType) {
        if (room.getRoomId() != 0) {
            Helper.showMessage("error");
        }
        return this.roomDao.add(room, roomType);
    }

    public boolean update(Room room) {
        if (this.getById(room.getRoomId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int roomId) {
        if (this.getById(roomId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.roomDao.delete(roomId);
    }

    public Room getById(int selectRoomId) {
        return this.roomDao.getById(selectRoomId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) { //Create objects as many as the number of columns
        ArrayList<Object[]> roomObjectList = new ArrayList<>();

        for (Room object : roomList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getRoomId();
            rowObject[i++] = this.seasonDao.getById(object.getSeasonId()).getSeasonName();
            rowObject[i++] = this.hostelTypeDao.getById(object.getHostelTypeId()).getHostelType();
            rowObject[i++] = object.getRoomType();
            rowObject[i++] = object.getBedNumber();
            rowObject[i++] = object.getRoomArea();
            rowObject[i++] = object.getRoomNumber();
            rowObject[i++] = object.getAdultPrice();
            rowObject[i++] = object.getChildPrice();
            rowObject[i++] = object.getTv();
            rowObject[i++] = object.getMinibar();
            rowObject[i++] = object.getGameConsole();
            rowObject[i++] = object.getSafeBox();
            rowObject[i++] = object.getProjection();
            roomObjectList.add(rowObject);
        }
        return roomObjectList;
    }

    public ArrayList<Object[]> getForTableRoomSearch(int size, ArrayList<Room> roomList) { //Create objects as many as the number of columns
        ArrayList<Object[]> roomObjectList = new ArrayList<>();

        for (Room object : roomList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getRoomId();
            rowObject[i++] = this.hotelDao.getById(object.getHotelId()).getHotelName();
            rowObject[i++] = this.hotelDao.getById(object.getHotelId()).getHotelStar();
            rowObject[i++] = this.hotelDao.getById(object.getHotelId()).getHotelAddress();
            rowObject[i++] = object.getRoomType();
            rowObject[i++] = this.hostelTypeDao.getById(object.getHostelTypeId()).getHostelType();
            rowObject[i++] = this.seasonDao.getById(object.getSeasonId()).getSeasonName();
            rowObject[i++] = object.getBedNumber();
            rowObject[i++] = object.getRoomArea();
            rowObject[i++] = object.getRoomNumber();
            rowObject[i++] = object.getAdultPrice();
            rowObject[i++] = object.getChildPrice();
            rowObject[i++] = this.hotelDao.getById(object.getHotelId()).getHotelFacility();
            rowObject[i++] = object.getTv();
            rowObject[i++] = object.getMinibar();
            rowObject[i++] = object.getGameConsole();
            rowObject[i++] = object.getSafeBox();
            rowObject[i++] = object.getProjection();
            roomObjectList.add(rowObject);
        }
        return roomObjectList;
    }

    public ArrayList<Room> searchForRoomSearch(String addressOrName) {
        String query = "SELECT * FROM public.room as r LEFT JOIN public.hotels as h";
        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();

        joinWhere.add("r.hotel_id = h.hotel_id");

        if (addressOrName != null) where.add(
                "h.hotel_address LIKE '%" + addressOrName + "%' " +
                "OR h.hotel_name LIKE '%" + addressOrName + "%' " +
                "OR h.hotel_city LIKE '%" + addressOrName + "%'" +
                "OR h.hotel_district LIKE '%" + addressOrName + "%'"
        );

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (joinStr.length() > 0) {
            query += " ON " + joinStr;
        }
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr + " ORDER BY room_id ASC";
        }
        return this.roomDao.selectByQuery(query);
    }

    public ArrayList<Room> searchForNewReservation(String checkInDate, String checkOutDate, String addressOrName) {
        String query = "SELECT * FROM public.room as r LEFT JOIN public.hotels as h";
        ArrayList<String> where = new ArrayList<>();
        where.add("room_number > 0");
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> reservationOrWhere = new ArrayList<>();

        joinWhere.add("r.hotel_id = h.hotel_id");

        checkInDate = LocalDate.parse(checkInDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        checkOutDate = LocalDate.parse(checkOutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if (addressOrName != null) where.add("h.hotel_address LIKE '%" + addressOrName + "%'");

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (joinStr.length() > 0) {
            query += " ON " + joinStr;
        }
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr + " ORDER BY room_id ASC";
        }

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);
        ArrayList<Integer> allRoomId = new ArrayList<>();
        for (Room room : searchedRoomList) {
            allRoomId.add(room.getRoomId());
        }

        reservationOrWhere.add("('" + checkInDate + "' BETWEEN check_in_date AND check_out_date)");
        reservationOrWhere.add("('" + checkOutDate + "' BETWEEN check_in_date AND check_out_date)");
        reservationOrWhere.add("(check_in_date BETWEEN '" + checkInDate + "' AND '" + checkOutDate + "')");
        reservationOrWhere.add("(check_out_date BETWEEN '" + checkInDate + "' AND '" + checkOutDate + "')");

        String reservationOrWhereStr = String.join(" OR ", reservationOrWhere);
        String reservationQuery = "SELECT * FROM public.reservation WHERE " + reservationOrWhereStr;

        ArrayList<Reservation> reservationList = this.reservationDao.selectByQuery(reservationQuery);
        ArrayList<Integer> busyRoomId = new ArrayList<>();
        //BURASI ÖNEMLİ, BU KODU KULLANARAK ODA SAYISINI DÜZELTEBİLİRİZ!!!
        /*for (Reservation reservation : reservationList) {
            busyRoomId.add(reservation.getRoomId());
        }

        for (Integer roomId : busyRoomId) {
            if (allRoomId.contains(roomId)) {
                reduceRoomNumber(roomId);
            }
        }*/

//        if (this.roomDao.getById(reservation.getRoomId()).getRoomNumber() > 0) {
//            this.roomDao.reduceRoomNumber(reservation.getRoomId());
//        } else {
//            searchedRoomList.removeIf(room -> busyRoomId.contains(room.getRoomId()));
//        }
//        searchedRoomList.removeIf(room -> busyRoomId.contains(room.getRoomId()));
        return searchedRoomList;
    }

//    public ArrayList<Room> filterForTable(Hotel.UserType userType) {
//        String select = "SELECT * FROM public.room";
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
//        return this.roomDao.selectByQuery(query);
//    }
    // Oda numarasını azaltma
    public void reduceRoomNumber(int roomId) {
        Room room = roomDao.getById(roomId);
        if (room != null && room.getRoomNumber() > 0) {
            room.setRoomNumber(room.getRoomNumber() - 1);
            roomDao.update(room);
        }
    }

    // Oda numarasını artırma
    public void increaseRoomNumber(int roomId) {
        Room room = this.roomDao.getById(roomId);
        if (room != null) {
            room.setRoomNumber(room.getRoomNumber() + 1);
            this.roomDao.update(room);
        }
    }
}
