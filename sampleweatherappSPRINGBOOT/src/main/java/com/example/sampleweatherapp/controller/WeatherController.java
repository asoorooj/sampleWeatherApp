package com.example.sampleweatherapp.controller;

import com.example.sampleweatherapp.dto.WeatherResponse;
import com.example.sampleweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin("*") // Allow frontend to access it during dev
public class WeatherController {

    @Autowired
    private WeatherService weatherService; //weatherService object

    @GetMapping
    public WeatherResponse getWeather(@RequestParam double latitude, @RequestParam double longitude) { //performs the api call
        return weatherService.getWeather(latitude, longitude);
    }
}
