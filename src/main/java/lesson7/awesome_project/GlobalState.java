package lesson7.awesome_project;

public final class GlobalState {

    private static GlobalState INSTANCE;
    private String selectedCity = null;
    public final String API_KEY = "BzanpjI8QojtzbeYaawJD6pP3o3UKA41";

    private GlobalState() { }

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
