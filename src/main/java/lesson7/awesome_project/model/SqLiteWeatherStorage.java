package lesson7.awesome_project.model;

import lesson7.awesome_project.GlobalState;
import lesson7.awesome_project.entity.WeatherObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqLiteWeatherStorage implements ILocalStorageProvider {

    static {
        try {
            GlobalState.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeUpdate("CREATE TABLE IF NOT EXISTS weather (id INTEGER PRIMARY KEY AUTOINCREMENT, city TEXT, date TEXT, weather TEXT)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void saveWeather(WeatherObject weatherObject) {

    }

    @Override
    public List<WeatherObject> getAllWeather() {

        //   PreparedStatement ps = connection.prepareStatement(
        Connection conn = GlobalState.getInstance().getConnection();
        String weatherQuery = String.format("SELECT * FROM weather;") ;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(weatherQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
        while (rs.next()) {                     // Пока есть строки
            System.out.println(rs.getString(2) + " " + rs.getString(3)+ " " + rs.getString(4) );      // Или rs.getString("Name");
        }} catch (SQLException e) {}


        return null;
    }
}

