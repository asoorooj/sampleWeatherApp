package com.example.sampleweatherapp.service;

import com.example.sampleweatherapp.dto.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient client;
    //a coordinates variable so coordinates can be decided clientside

    public WeatherService() {
        this.client = WebClient.builder()
                .baseUrl("https://api.weather.gov")
                .defaultHeader(HttpHeaders.USER_AGENT, "sampleweatherapp (sooroojballyanthony@gmail.com)") //Required by API
                .build();
    }

    public WeatherResponse getWeather(double latitude, double longitude) {
        //Call the points endpoint
        String forecastUrl = client.get()
                .uri("/points/"+latitude+","+longitude) //lat&long = 40.7306,-73.9352
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block()
                .path("properties")
                .path("forecast")
                .asText();

        //Call the forecast endpoint
        JsonNode forecastData = client.get()
                .uri(forecastUrl.replace("https://api.weather.gov", ""))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        JsonNode period = forecastData.path("properties").path("periods").get(0);

        WeatherResponse.ProbabilityOfPercipitation probabilityOfPercipitation = new WeatherResponse.ProbabilityOfPercipitation(
                period.path("probabilityOfPrecipitation").path("unitCode").asText(),
                period.path("probabilityOfPrecipitation").path("value").asInt()
        );

        return new WeatherResponse(
                period.path("number").asInt(),
                period.path("name").asText(),
                period.path("startTime").asText(),
                period.path("endTime").asText(),
                period.path("isDaytime").asBoolean(),
                period.path("temperature").asInt(),
                period.path("temperatureUnit").asText(),
                period.path("temperatureTrend").asText(null),
                probabilityOfPercipitation,
                period.path("dewpoint").path("unitCode").asText(),
                period.path("dewpoint").path("value").asInt(),
                period.path("relativeHumidity").path("unitCode").asText(),
                period.path("relativeHumidity").path("value").asInt(),
                period.path("windSpeed").asText(),
                period.path("windDirection").asText(),
                period.path("icon").asText(),
                period.path("shortForecast").asText(),
                period.path("detailedForecast").asText()
        );
    }
}

