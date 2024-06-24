package Dao;

import Core.Db;
import Entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }

    //DTO: Data Transfer Object
    public Hotel match(ResultSet resultSet) throws SQLException {
        Hotel object = new Hotel();
        object.setHotelId(resultSet.getInt("hotel_id"));
        object.setHotelName(resultSet.getString("hotel_name"));
        object.setHotelCity(resultSet.getString("hotel_city"));
        object.setHotelDistrict(resultSet.getString("hotel_district"));
        object.setHotelAddress(resultSet.getString("hotel_address"));
        object.setHotelEmail(resultSet.getString("hotel_email"));
        object.setHotelPhoneNumber(resultSet.getString("hotel_phone_number"));
        object.setHotelStar(Hotel.HotelStar.valueOf(resultSet.getString("hotel_star")));
        object.setHotelFacility(resultSet.getString("hotel_facility"));
        return object;
    }

    public ArrayList<Hotel> findAll() {
        return this.selectByQuery("SELECT * FROM public.hotels ORDER BY hotel_id ASC;");
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                hotelList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public boolean add(Hotel hotel) {
        String query = "INSERT INTO public.hotels " +
                "(" +
                "hotel_name, " +
                "hotel_city, " +
                "hotel_district, " +
                "hotel_address, " +
                "hotel_email, " +
                "hotel_phone_number, " +
                "hotel_star, " +
                "hotel_facility" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, hotel.getHotelName());
            preparedStatement.setString(2, hotel.getHotelCity());
            preparedStatement.setString(3, hotel.getHotelDistrict());
            preparedStatement.setString(4, hotel.getHotelAddress());
            preparedStatement.setString(5, hotel.getHotelEmail());
            preparedStatement.setString(6, hotel.getHotelPhoneNumber());
            preparedStatement.setString(7, hotel.getHotelStar().toString());
            preparedStatement.setString(8, hotel.getHotelFacility());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotels SET " +
                "hotel_name = ?, " +
                "hotel_city = ?, " +
                "hotel_district = ?, " +
                "hotel_address = ?, " +
                "hotel_email = ?, " +
                "hotel_phone_number = ?, " +
                "hotel_star = ?, " +
                "hotel_facility = ? " +
                "WHERE hotel_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, hotel.getHotelName());
            preparedStatement.setString(2, hotel.getHotelCity());
            preparedStatement.setString(3, hotel.getHotelDistrict());
            preparedStatement.setString(4, hotel.getHotelAddress());
            preparedStatement.setString(5, hotel.getHotelEmail());
            preparedStatement.setString(6, hotel.getHotelPhoneNumber());
            preparedStatement.setString(7, hotel.getHotelStar().toString());
            preparedStatement.setString(8, hotel.getHotelFacility());
            preparedStatement.setInt(9, hotel.getHotelId());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotelId) {
        String query = "DELETE FROM public.hotels WHERE hotel_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Hotel getById(int selectHotelId) {
        Hotel object = null;
        String query = "SELECT * FROM public.hotels WHERE hotel_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, selectHotelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) object = this.match(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }
}
