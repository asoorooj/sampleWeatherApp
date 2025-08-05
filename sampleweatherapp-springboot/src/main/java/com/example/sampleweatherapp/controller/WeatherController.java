package com.example.sampleweatherapp.controller;

import com.example.sampleweatherapp.dto.WeatherLocationResponse;
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

    @GetMapping("/now")
    public WeatherResponse getWeatherNow(@RequestParam double latitude, @RequestParam double longitude) { //performs the api call
        return weatherService.getWeatherNow(latitude, longitude);
    }

    @GetMapping("/today")
    public WeatherResponse getWeatherToday(@RequestParam double latitude, @RequestParam double longitude) { //performs the api call
        return weatherService.getWeatherToday(latitude, longitude);
    }

    @GetMapping("/location")
    public WeatherLocationResponse getWeatherLocation(@RequestParam double latitude, @RequestParam double longitude) { //performs the api call
        return weatherService.getWeatherLocation(latitude, longitude);
    }
}

