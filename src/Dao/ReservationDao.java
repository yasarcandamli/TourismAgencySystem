package Dao;

import Core.Db;
import Entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection connection;

    public ReservationDao() {
        this.connection = Db.getInstance();
    }

    //DTO: Data Transfer Object
    public Reservation match(ResultSet resultSet) throws SQLException {
        Reservation object = new Reservation();
        object.setReservationId(resultSet.getInt("reservation_id"));
        object.setRoomId(resultSet.getInt("room_id"));
        object.setCustomerName(resultSet.getString("customer_name"));
        object.setCustomerName(resultSet.getString("customer_identity_number"));
        object.setCustomerEmail(resultSet.getString("customer_email"));
        object.setCheckInDate(resultSet.getString("check_in_date"));
        object.setCheckOutDate(resultSet.getString("check_out_date"));
        object.setAdultNumber(resultSet.getInt("adult_number"));
        object.setChildNumber(resultSet.getInt("child_number"));
        object.setCustomerNote(resultSet.getString("customer_note"));
        object.setTotalPrice(resultSet.getInt("total_price"));
        return object;
    }

    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY hotel_id ASC;");
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                reservationList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public boolean add(Reservation reservation) {
        String query = "INSERT INTO public.reservation " +
                "(" +
                "room_id, " +
                "customer_name, " +
                "customer_identity_number, " +
                "customer_email, " +
                "check_in_date, " +
                "check_out_date, " +
                "adult_number, " +
                "child_number, " +
                "customer_note," +
                "total_price" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getRoomId());
            preparedStatement.setString(2, reservation.getCustomerName());
            preparedStatement.setString(3, reservation.getCustomerIdentityNumber());
            preparedStatement.setString(4, reservation.getCustomerEmail());
            preparedStatement.setString(5, reservation.getCheckInDate());
            preparedStatement.setString(6, reservation.getCheckOutDate());
            preparedStatement.setInt(7, reservation.getAdultNumber());
            preparedStatement.setInt(8, reservation.getChildNumber());
            preparedStatement.setString(9, reservation.getCustomerNote());
            preparedStatement.setInt(10, reservation.getTotalPrice());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "(" +
                "room_id = ?, " +
                "customer_name = ?, " +
                "customer_identity_number = ?, " +
                "customer_email = ?, " +
                "check_in_date = ?, " +
                "check_out_date = ?, " +
                "adult_number = ?, " +
                "child_number = ?, " +
                "customer_note = ?, " +
                "total_price = ? " +
                ")" +
                "WHERE reservation_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getRoomId());
            preparedStatement.setString(2, reservation.getCustomerName());
            preparedStatement.setString(3, reservation.getCustomerIdentityNumber());
            preparedStatement.setString(4, reservation.getCustomerEmail());
            preparedStatement.setString(5, reservation.getCheckInDate());
            preparedStatement.setString(6, reservation.getCheckOutDate());
            preparedStatement.setInt(7, reservation.getAdultNumber());
            preparedStatement.setInt(8, reservation.getChildNumber());
            preparedStatement.setString(9, reservation.getCustomerNote());
            preparedStatement.setInt(10, reservation.getTotalPrice());
            preparedStatement.setInt(11, reservation.getReservationId());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int reservationId) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationId);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Reservation getById(int selectReservationId) {
        Reservation object = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, selectReservationId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) object = this.match(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }
}
