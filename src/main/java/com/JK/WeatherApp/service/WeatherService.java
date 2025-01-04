package com.JK.WeatherApp.service;

import com.JK.WeatherApp.model.Cities;
import com.JK.WeatherApp.model.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    @Value("${weather.api.url}")
    private String apiUrl;

    public ResponseEntity<WeatherData> randomCity() {
        List<String> citiesInTheWorld = getAllCities();
        String city = citiesInTheWorld.get(new Random().nextInt(citiesInTheWorld.size()));
        return getWeatherDataResponseEntity(city);
    }

    public ResponseEntity<WeatherData> searchLocation(String location) throws BadRequestException {
        List<String> citiesInTheWorld = getAllCities();
        if(!citiesInTheWorld.contains(location)){
            System.out.println("Incorrect location pass");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return getWeatherDataResponseEntity(location);
        }
    }


    public List<String> getAllCities(){
        // Following API will provide all cities around the world
        String citiesURL = "https://countriesnow.space/api/v0.1/countries";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseFromCities = restTemplate.getForEntity(citiesURL, JsonNode.class);
        JsonNode responseFromCitiesBody = responseFromCities.getBody();

        // Alternatively, we can even store the list of the cities in our database.
        // With database, it would be much faster
        List<String> citiesInTheWorld = new ArrayList<>();
        if (responseFromCitiesBody != null && responseFromCitiesBody.path("data").isArray()) {
            for (JsonNode countryNode : responseFromCitiesBody.path("data")) {
                if (countryNode.path("cities").isArray()) {
                    for (JsonNode cityNode : countryNode.path("cities")) {
                        if(cityNode.asText().contains(" ")) {
                            citiesInTheWorld.add(cityNode.asText());
                        }
                    }
                }
            }
        }
        if (citiesInTheWorld.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return citiesInTheWorld;
        }
    }

    public ResponseEntity<WeatherData> getWeatherDataResponseEntity(String location) {
        String url = apiUrl + "current?access_key=" + apiKey + "&query=" + location;

        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate2.getForEntity(url, JsonNode.class);
        JsonNode responseBody = response.getBody();

        WeatherData weatherData = new WeatherData();
        assert responseBody != null;
        weatherData.setTemperature(responseBody.path("current").path("temperature").asDouble());
        weatherData.setHumidity(responseBody.path("current").path("humidity").asDouble());
        weatherData.setWindSpeed(responseBody.path("current").path("wind_speed").asDouble());
        weatherData.setCountry(responseBody.path("location").path("country").asText());
        weatherData.setCity(responseBody.path("location").path("name").asText());

        return ResponseEntity.ok(weatherData);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
