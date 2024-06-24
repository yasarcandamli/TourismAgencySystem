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
        object.setRoomType(resultSet.getString("room_type"));
        object.setBedNumber(resultSet.getInt("bed_number"));
        object.setRoomArea(resultSet.getInt("room_area"));
        object.setRoomNumber(resultSet.getInt("room_number"));
        object.setAdultPrice(resultSet.getInt("adult_price"));
        object.setChildPrice(resultSet.getInt("child_price"));
        object.setTv(Room.isThere.valueOf(resultSet.getString("tv")));
        object.setMinibar(Room.isThere.valueOf(resultSet.getString("minibar")));
        object.setGameConsole(Room.isThere.valueOf(resultSet.getString("game_console")));
        object.setSafeBox(Room.isThere.valueOf(resultSet.getString("safe_box")));
        object.setProjection(Room.isThere.valueOf(resultSet.getString("projection")));

        return object;
    }

    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id ASC;");
    }

    public ArrayList<Room> findAllAvailableRoom() {
        return this.selectByQuery("SELECT * FROM public.room WHERE room_number > 0;");
    }

    public ArrayList<Room> findAllForTable(int hotelId) {
        return this.selectByQuery("SELECT * FROM public.room WHERE hotel_id = "+ hotelId +" ORDER BY room_id ASC;");
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

    public boolean add(Room room, String roomType) {
        String query = "INSERT INTO public.room " +
                "(" +
                "hotel_id, " +
                "season_id, " +
                "hostel_type_id, " +
                "room_type, " +
                "bed_number, " +
                "room_area, " +
                "room_number, " +
                "adult_price, " +
                "child_price, " +
                "tv, " +
                "minibar, " +
                "game_console, " +
                "safe_box, " +
                "projection" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setInt(2, room.getSeasonId());
            preparedStatement.setInt(3, room.getHostelTypeId());
            preparedStatement.setString(4, roomType);
            preparedStatement.setInt(5, room.getBedNumber());
            preparedStatement.setInt(6, room.getRoomArea());
            preparedStatement.setInt(7, room.getRoomNumber());
            preparedStatement.setInt(8, room.getAdultPrice());
            preparedStatement.setInt(9, room.getChildPrice());
            preparedStatement.setString(10, room.getTv().toString());
            preparedStatement.setString(11, room.getMinibar().toString());
            preparedStatement.setString(12, room.getGameConsole().toString());
            preparedStatement.setString(13, room.getSafeBox().toString());
            preparedStatement.setString(14, room.getProjection().toString());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "hotel_id = ?, " +
                "season_id = ?, " +
                "hostel_type_id = ?, " +
                "room_type = ?, " +
                "bed_number = ?, " +
                "room_area = ?, " +
                "room_number = ?, " +
                "adult_price = ?, " +
                "child_price = ?, " +
                "tv = ?, " +
                "minibar = ?, " +
                "game_console = ?, " +
                "safe_box = ?, " +
                "projection = ? " +
                "WHERE room_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setInt(2, room.getSeasonId());
            preparedStatement.setInt(3, room.getHostelTypeId());
            preparedStatement.setString(4, room.getRoomType());
            preparedStatement.setInt(5, room.getBedNumber());
            preparedStatement.setInt(6, room.getRoomArea());
            preparedStatement.setInt(7, room.getRoomNumber());
            preparedStatement.setInt(8, room.getAdultPrice());
            preparedStatement.setInt(9, room.getChildPrice());
            preparedStatement.setString(10, room.getTv().toString());
            preparedStatement.setString(11, room.getMinibar().toString());
            preparedStatement.setString(12, room.getGameConsole().toString());
            preparedStatement.setString(13, room.getSafeBox().toString());
            preparedStatement.setString(14, room.getProjection().toString());
            preparedStatement.setInt(15, room.getRoomId());

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
}
