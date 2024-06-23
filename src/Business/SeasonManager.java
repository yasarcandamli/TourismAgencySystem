package Business;

import Core.Helper;
import Dao.HotelDao;
import Dao.SeasonDao;
import Entity.Hotel;
import Entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> findAll() {
        return seasonDao.findAll();
    }

    public ArrayList<Season> findAllForTable(int hotelId) {
        return seasonDao.findAllforTable(hotelId);
    }

    public boolean add(Season season) {
        if (season.getSeasonId() != 0) {
            Helper.showMessage("error");
        }
        return this.seasonDao.add(season);
    }

    public boolean update(Season season) {
        if (this.getById(season.getSeasonId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.seasonDao.update(season);
    }

    public boolean delete(int seasonId) {
        if (this.getById(seasonId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.seasonDao.delete(seasonId);
    }

    public Season getById(int selectSeasonId) {
        return this.seasonDao.getById(selectSeasonId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList) { //Create objects as many as the number of columns
        ArrayList<Object[]> seasonObjectList = new ArrayList<>();

        for (Season object : seasonList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getSeasonId();
            rowObject[i++] = object.getHotelId();
            rowObject[i++] = object.getSeasonStartDate();
            rowObject[i++] = object.getSeasonEndDate();
            rowObject[i++] = object.getSeasonName();
            seasonObjectList.add(rowObject);
        }
        return seasonObjectList;
    }

//    public ArrayList<Season> filterForTable(Hotel.UserType userType) {
//        String select = "SELECT * FROM public.season";
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
