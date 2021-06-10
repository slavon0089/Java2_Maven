package lesson7.awesome_project.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import lesson7.awesome_project.GlobalState;
import lesson7.awesome_project.entity.WeatherObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccuWeatherProvider implements IWeatherProvider {

    private final String BASE_HOST = "dataservice.accuweather.com";
    private final String VERSION = "v1";
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    String city;

    @Override
    public List<WeatherObject> getWeather(Period period) {
        String key = detectCityKeyByName();
        if (period.equals(Period.NOW)) {
            String days = "1day";
            httpRequestMethod(key, days);
        } else if (period.equals(Period.FIVE_DAYS)) {
            String days = "5day";
            httpRequestMethod(key, days);
        }
        return new ArrayList<>();
    }
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/{locationKey}

    private String httpRequestMethod(String key, String days) {

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("forecasts")
                .addPathSegment(VERSION)
                .addPathSegment("daily")
                .addPathSegment(days)
                .addPathSegment(key)
                .addQueryParameter("apikey", GlobalState.getInstance().API_KEY)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            String answerWeather = response.body().string();

            if (objectMapper.readTree(answerWeather).size() > 0) {

                if (days == "1day") {
                    String DATE = objectMapper.readTree(answerWeather).at("/DailyForecasts/0/Date").asText();
                    String TEMPERATURE = objectMapper.readTree(answerWeather).at("/DailyForecasts/0/Temperature/Minimum/Value").asText();
                    String WEATHER_TEXT = objectMapper.readTree(answerWeather).at("/DailyForecasts/0/Day/IconPhrase").asText();
                    System.out.printf("В городе %s на дату %s ожидается %s, температура - %s\n", city, DATE, WEATHER_TEXT, TEMPERATURE);
                    void populationDB();
                }
                if (days == "5day") {
                    int daysCount = 5;
                    for (int i = 0; i < daysCount; i++) {
                        String DATE = objectMapper.readTree(answerWeather).at("/DailyForecasts/" + i + "/Date").asText();
                        String TEMPERATURE = objectMapper.readTree(answerWeather).at("/DailyForecasts/" + i + "/Temperature/Minimum/Value").asText();
                        String WEATHER_TEXT = objectMapper.readTree(answerWeather).at("/DailyForecasts/" + i + "/Day/IconPhrase").asText();
                        System.out.printf("В городе %s на дату %s ожидается %s, температура - %s\n", city, DATE, WEATHER_TEXT, TEMPERATURE);

                    }


                }


                return city;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static String populationDB (String city,String date, String weather) {
        String query = String.format("INSERT INTO weather(city,date,weather) VALUES ('%s', '%s', '%s')",city,date,weather) ;
        return city;
    }


    private String detectCityKeyByName() {
        String selectedCity = GlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(VERSION)
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", GlobalState.getInstance().API_KEY)
                .addQueryParameter("q", selectedCity)
                .addQueryParameter("metric", "true")
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response locationResponse = null;
        try {
            locationResponse = okHttpClient.newCall(request).execute();

            if (!locationResponse.isSuccessful()) {
                throw new RuntimeException("Сервер ответил " + locationResponse.code());
            }

            String jsonResponse = locationResponse.body().string();

            if (objectMapper.readTree(jsonResponse).size() > 0) {
                String code = objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
                String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
                String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();

                System.out.printf("Найден город %s в стране %s, код - %s\n", cityName, countryName, code);
                return code;
            } else {
                throw new RuntimeException(selectedCity + " - такой город не найден");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}