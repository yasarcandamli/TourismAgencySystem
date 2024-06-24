package Dao;

import Core.Db;
import Entity.Hotel;
import Entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    //DTO: Data Transfer Object
    public Season match(ResultSet resultSet) throws SQLException {
        Season object = new Season();
        object.setSeasonId(resultSet.getInt("season_id"));
        object.setHotelId(resultSet.getInt("hotel_id"));
        object.setSeasonStartDate(LocalDate.parse(resultSet.getString("season_start_date")));
        object.setSeasonEndDate(LocalDate.parse(resultSet.getString("season_end_date")));
        object.setSeasonName(resultSet.getString("season_name"));
        return object;
    }

    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.season ORDER BY hotel_id ASC;");
    }

    public ArrayList<Season> findAllforTable(int hotelId) {
        return this.selectByQuery("SELECT * FROM public.season WHERE hotel_id = "+ hotelId +" ORDER BY hotel_id ASC;");
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                seasonList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public boolean add(Season season) {
        String query = "INSERT INTO public.season " +
                "(" +
                "hotel_id, " +
                "season_start_date, " +
                "season_end_date, " +
                "season_name " +
                ")" +
                " VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, season.getHotelId());
            preparedStatement.setDate(2, Date.valueOf(season.getSeasonStartDate()));
            preparedStatement.setDate(3, Date.valueOf(season.getSeasonEndDate()));
            preparedStatement.setString(4, season.getSeasonName());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Season season) {
        String query = "UPDATE public.season SET " +
                "hotel_id = ?, " +
                "season_start_date = ?, " +
                "season_end_date = ?, " +
                "season_name = ? " +
                "WHERE season_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, season.getHotelId());
            preparedStatement.setDate(2, Date.valueOf(season.getSeasonStartDate()));
            preparedStatement.setDate(3, Date.valueOf(season.getSeasonEndDate()));
            preparedStatement.setString(4, season.getSeasonName());
            preparedStatement.setInt(5, season.getSeasonId());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int seasonId) {
        String query = "DELETE FROM public.season WHERE season_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, seasonId);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Season getById(int selectSeasonId) {
        Season object = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, selectSeasonId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) object = this.match(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }
}
