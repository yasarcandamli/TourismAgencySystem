package Dao;

import Core.Db;
import Entity.HostelType;
import Entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HostelTypeDao {
    private final Connection connection;

    public HostelTypeDao() {
        this.connection = Db.getInstance();
    }

    //DTO: Data Transfer Object
    public HostelType match(ResultSet resultSet) throws SQLException {
        HostelType object = new HostelType();
        object.setHostelTypeId(resultSet.getInt("hostel_type_id"));
        object.setHotelId(resultSet.getInt("hotel_id"));
        object.setHostelType(HostelType.HostelTypes.valueOf(resultSet.getString("hotel_type")));
        return object;
    }

    public ArrayList<HostelType> findAll() {
        return this.selectByQuery("SELECT * FROM public.hostel_type ORDER BY hotel_id ASC;");
    }

    public ArrayList<HostelType> selectByQuery(String query) {
        ArrayList<HostelType> hostelTypeList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                hostelTypeList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hostelTypeList;
    }

    public boolean add(HostelType hostelType) {
        String query = "INSERT INTO public.hostel_type " +
                "(" +
                "hotel_id, " +
                "hostel_type" +
                ")" +
                " VALUES (?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hostelType.getHotelId());
            preparedStatement.setString(2, hostelType.getHostelType().name());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(HostelType hostelType) {
        String query = "UPDATE public.hostel_type SET " +
                "(" +
                "hotel_id = ?, " +
                "hostel_type = ?, " +
                ")" +
                "WHERE hostel_type_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hostelType.getHotelId());
            preparedStatement.setString(2, hostelType.getHostelType().name());
            preparedStatement.setInt(3, hostelType.getHostelTypeId());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hostelTypeId) {
        String query = "DELETE FROM public.hotels WHERE hostel_type = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hostelTypeId);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public HostelType getById(int selectHostelTypeId) {
        HostelType object = null;
        String query = "SELECT * FROM public.hotels WHERE hostel_type = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, selectHostelTypeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) object = this.match(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }
}
