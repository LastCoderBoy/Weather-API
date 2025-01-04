package com.JK.WeatherApp.model;

import java.util.List;

public class Cities {
    private List<String> citiesInTheWorld;

    public Cities(List<String> citiesInTheWorld) {
        this.citiesInTheWorld = citiesInTheWorld;
    }

    public List<String> getCitiesInTheWorld() {
        return citiesInTheWorld;
    }

    public void setCitiesInTheWorld(List<String> citiesInTheWorld) {
        this.citiesInTheWorld = citiesInTheWorld;
    }
}
