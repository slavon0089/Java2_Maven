package lesson7.awesome_project.model;

import lesson7.awesome_project.GlobalState;
import lesson7.awesome_project.entity.WeatherObject;

import java.sql.Connection;
import java.sql.SQLException;
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
        return null;
    }
}
