package lesson7.awesome_project.entity;

public class WeatherObject {
    private String city;
    private String date;
    private String weather;

    public WeatherObject() {
    }

    public WeatherObject(String city, String date, String weather) {
        this.city = city;
        this.date = date;
        this.weather = weather;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", weather='" + weather + '\'' +
                '}';
    }
}
