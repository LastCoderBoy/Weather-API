package com.JK.WeatherApp.controller;


import com.JK.WeatherApp.model.WeatherData;
import com.JK.WeatherApp.service.WeatherService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    @Autowired
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Why we need to return a random city,
    // because when the User doesn't allow its location on the web, we will display a random city
    @GetMapping
    public ResponseEntity<WeatherData> randomCity(){
        return weatherService.randomCity();
    }

    @GetMapping("/city")
    public ResponseEntity<WeatherData> searchLocation(@RequestParam String location) throws BadRequestException {
        return weatherService.searchLocation(location);
    }
}
