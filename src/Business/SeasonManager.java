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

    // Brings all seasons
    public ArrayList<Season> findAll() {
        return seasonDao.findAll();
    }

    // Fetches seasons with hotel ID
    public ArrayList<Season> findAllForTable(int hotelId) {
        return seasonDao.findAllforTable(hotelId);
    }

    // New season inserts
    public boolean add(Season season) {
        if (this.getByDate(season) != null) {
            Helper.showMessage("The season for these start and end dates already exists!");
            return false;
        }
        return this.seasonDao.add(season);
    }

    // Updates season
    public boolean update(Season season) {
        if (this.getById(season.getSeasonId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.seasonDao.update(season);
    }

    // Deletes the season
    public boolean delete(int seasonId) {
        if (this.getById(seasonId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.seasonDao.delete(seasonId);
    }

    public boolean deleteByHotelId(int hotelId) {
        return this.seasonDao.deleteByHotelId(hotelId);
    }

    // Fetches season with ID
    public Season getById(int selectSeasonId) {
        return this.seasonDao.getById(selectSeasonId);
    }

    public Season getByDate(Season season) {
        return this.seasonDao.getByDate(season, season.getSeasonId());
    }

    // Gets season data for the table
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
