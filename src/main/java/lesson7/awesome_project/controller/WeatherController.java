package lesson7.awesome_project.controller;

import lesson7.awesome_project.model.*;

public class WeatherController implements IWeatherController {

    private IWeatherRepository weatherRepository = new WeatherRepository();

    @Override
    public void onUserInput(int command) {

        switch (command) {
            case 1:
                getCurrentWeather();
                break;
            case 2:
                getWeatherForFiveDays();
                break;
            case 3:
                getAllFromDb();
            default:
                System.out.println("НЕТ ТАКОЙ КОМАНДЫ!");
                System.exit(1);
        }
    }

    private void getCurrentWeather() {
        weatherRepository.fetchWeatherFromApi(Period.NOW);
    }

    private void getWeatherForFiveDays() {weatherRepository.fetchWeatherFromApi(Period.FIVE_DAYS);}

    private void getAllFromDb() {
        weatherRepository.readWeatherFromDb();
    }
}
