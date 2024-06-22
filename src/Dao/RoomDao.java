package Dao;

import Core.Db;
import Entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Db.getInstance();
    }

    //DTO: Data Transfer Object
    public Room match(ResultSet resultSet) throws SQLException {
        Room object = new Room();
        object.setRoomId(resultSet.getInt("room_id"));
        object.setHotelId(resultSet.getInt("hotel_id"));
        object.setSeasonId(resultSet.getInt("season_id"));
        object.setHostelTypeId(resultSet.getInt("hostel_type_id"));
        object.setBedNumber(resultSet.getInt("bed_number"));
        object.setRoomType(resultSet.getString("room_type"));
        object.setRemainingRoomNumber(resultSet.getInt("remaining_room_number"));
        object.setAdultPrice(resultSet.getInt("adult_price"));
        object.setChildPrice(resultSet.getInt("child_price"));
        object.setRoomFeatures(resultSet.getString("room_features"));
        return object;
    }

    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id ASC;");
    }

    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                roomList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public boolean add(Room room) {
        String query = "INSERT INTO public.room " +
                "(" +
                "hotel_id, " +
                "season_id, " +
                "hostel_type_id, " +
                "bed_number, " +
                "room_type, " +
                "remaining_room_number, " +
                "adult_price, " +
                "child_price, " +
                "room_features" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setInt(2, room.getSeasonId());
            preparedStatement.setInt(3, room.getHostelTypeId());
            preparedStatement.setInt(4, room.getBedNumber());
            preparedStatement.setString(5, room.getRoomType());
            preparedStatement.setInt(6, room.getRemainingRoomNumber());
            preparedStatement.setInt(7, room.getAdultPrice());
            preparedStatement.setInt(8, room.getChildPrice());
            preparedStatement.setString(9, room.getRoomFeatures());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "(" +
                "hotel_id = ?, " +
                "season_id = ?, " +
                "hostel_type_id = ?, " +
                "bed_number = ?, " +
                "room_type = ?, " +
                "remaining_room_number = ?, " +
                "adult_price = ?, " +
                "child_price = ?, " +
                "room_features = ? " +
                ")" +
                "WHERE room_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setInt(2, room.getSeasonId());
            preparedStatement.setInt(3, room.getHostelTypeId());
            preparedStatement.setInt(4, room.getBedNumber());
            preparedStatement.setString(5, room.getRoomType());
            preparedStatement.setInt(6, room.getRemainingRoomNumber());
            preparedStatement.setInt(7, room.getAdultPrice());
            preparedStatement.setInt(8, room.getChildPrice());
            preparedStatement.setString(9, room.getRoomFeatures());
            preparedStatement.setInt(10, room.getRoomId());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int roomId) {
        String query = "DELETE FROM public.room WHERE room_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Room getById(int selectRoomId) {
        Room object = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, selectRoomId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) object = this.match(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    public boolean reduceRemainingRoomNumber(int id) {
        int remainingRoomNumber = getById(id).getRemainingRoomNumber();
        String query = "UPDATE room SET remaining_room_number = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, remainingRoomNumber - 1);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
