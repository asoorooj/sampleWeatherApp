package com.example.sampleweatherapp.service;

import com.example.sampleweatherapp.dto.WeatherLocationResponse;
import com.example.sampleweatherapp.dto.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherService {

    private final WebClient client;
    //a coordinates variable so coordinates can be decided clientside

    //Base parameters for weather service
    public WeatherService() {
        this.client = WebClient.builder()
                .baseUrl("https://api.weather.gov")
                .defaultHeader(HttpHeaders.USER_AGENT, "sampleweatherapp (sooroojballyanthony@gmail.com)") //Required by API
                .build();
    }

    //calls api hourly forecast
    public WeatherResponse getWeatherNow(double latitude, double longitude) {
        try {
            //Call the points endpoint
            String forecastUrl = client.get()
                    .uri("/points/" + latitude + "," + longitude) //lat&long = 40.7306,-73.9352 ?latitude=40.7306&longitude=-73.9352
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block()
                    .path("properties")
                    .path("forecastHourly")
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
        } catch (WebClientResponseException.NotFound ex) {
            // External API responded with 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        } catch (WebClientResponseException ex) {
            // Other WebClient errors
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Weather API error: " + ex.getStatusCode());
        } catch (Exception ex) {
            // Other general errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    //calls api daily forecast
    public WeatherResponse getWeatherToday(double latitude, double longitude) {
        try {
            //Call the points endpoint
            String forecastUrl = client.get()
                    .uri("/points/" + latitude + "," + longitude) //lat&long = 40.7306,-73.9352 ?latitude=40.7306&longitude=-73.9352
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
        } catch (WebClientResponseException.NotFound ex) {
            // External API responded with 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        } catch (WebClientResponseException ex) {
            // Other WebClient errors
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Weather API error: " + ex.getStatusCode());
        } catch (Exception ex) {
            // Other general errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    public WeatherLocationResponse getWeatherLocation(double latitude, double longitude) {
        try {
            //Call the points endpoint
            JsonNode locationData = client.get()
                    .uri("/points/" + latitude + "," + longitude) //lat&long = 40.7306,-73.9352 ?latitude=40.7306&longitude=-73.9352
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            JsonNode period = locationData.path("properties").path("relativeLocation").path("properties");

            JsonNode theCoordinates = locationData.path("properties").path("relativeLocation").path("geometry").path("coordinates");

            double[] coordinates = {theCoordinates.get(0).asDouble(), theCoordinates.get(1).asDouble()};

            return new WeatherLocationResponse(
                    period.path("city").asText(),
                    period.path("state").asText(),
                    coordinates
            );
        } catch (WebClientResponseException.NotFound ex) {
            // External API responded with 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        } catch (WebClientResponseException ex) {
            // Other WebClient errors
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Weather API error: " + ex.getStatusCode());
        } catch (Exception ex) {
            // Other general errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

}


