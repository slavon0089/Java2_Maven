package lesson7.awesome_project;

import java.sql.Connection;

public final class GlobalState {

    private static GlobalState INSTANCE;
    private String selectedCity = null;
    public final String API_KEY = "BzanpjI8QojtzbeYaawJD6pP3o3UKA41";
    //BzanpjI8QojtzbeYaawJD6pP3o3UKA41
    //qo5v658mtqpyUHIufOcSPy0npQZj7ivE
    public final String DB_NAME = "application.db";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    private GlobalState() {

    }

    public static GlobalState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalState();
        }
        return INSTANCE;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }
}
