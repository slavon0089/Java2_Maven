package lesson7.awesome_project.model;

import lesson7.awesome_project.entity.WeatherObject;

import java.util.List;

public interface ILocalStorageProvider {



    void saveWeather(WeatherObject weatherObject);

    List<WeatherObject> getAllWeather();

}
