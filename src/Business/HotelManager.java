package Business;

import Core.Helper;
import Dao.HotelDao;
import Entity.Hotel;
import Entity.User;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    // Brings all hotels
    public ArrayList<Hotel> findAll() {
        return hotelDao.findAll();
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        return hotelDao.selectByQuery(query);
    }

    // Adds new hotel
    public boolean add(Hotel hotel) {
        if (hotel.getHotelId() != 0) {
            Helper.showMessage("error");
        }
        return this.hotelDao.add(hotel);
    }

    // Updates the hotel
    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getHotelId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.hotelDao.update(hotel);
    }

    // Deletes the hotel
    public boolean delete(int hotelId) {
        if (this.getById(hotelId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.hotelDao.delete(hotelId);
    }

    // Fetches the hotel with ID
    public Hotel getById(int selectHotelId) {
        return this.hotelDao.getById(selectHotelId);
    }

    // Gets hotel data for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotelList) { //Create objects as many as the number of columns
        ArrayList<Object[]> hotelObjectList = new ArrayList<>();

        for (Hotel object : hotelList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getHotelId();
            rowObject[i++] = object.getHotelName();
            rowObject[i++] = object.getHotelCity();
            rowObject[i++] = object.getHotelDistrict();
            rowObject[i++] = object.getHotelAddress();
            rowObject[i++] = object.getHotelEmail();
            rowObject[i++] = object.getHotelPhoneNumber();
            rowObject[i++] = object.getHotelStar();
            rowObject[i++] = object.getHotelFacility();
            hotelObjectList.add(rowObject);
        }
        return hotelObjectList;
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
