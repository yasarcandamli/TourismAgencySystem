package Business;

import Core.Helper;
import Dao.RoomDao;
import Entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll() {
        return roomDao.findAll();
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
            rowObject[i++] = object.getSeasonId();
            rowObject[i++] = object.getHostelTypeId();
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
}
