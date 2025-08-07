package com.example.sampleweatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private int number;
    private String name;
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;
    private boolean dayTime;
    @NotNull
    private int temperature;
    @NotNull
    private String temperatureUnit;
    private String temperatureTrend;
    private ProbabilityOfPrecipitation probabilityOfPrecipitation;
    private Dewpoint dewpoint;
    private RelativeHumidity relativeHumidity;
    private String windSpeed;
    private String windDirection;
    private String icon;
    private String shortForecast;
    private String detailedForecast;

    public WeatherResponse(){  //no args constructor

    }

    public WeatherResponse(int number, String name, String startTime, String endTime, boolean isDayTime, int temperature,
                           String temperatureUnit, String temperatureTrend, ProbabilityOfPrecipitation probabilityOfPrecipitation,
                           Dewpoint dewpoint, RelativeHumidity relativeHumidity, String windSpeed,
                           String windDirection, String icon, String shortForecast, String detailedForecast){

        this.number = number;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayTime = isDayTime;
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
        this.temperatureTrend = temperatureTrend;
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
        this.dewpoint = dewpoint;
        this.relativeHumidity = relativeHumidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.icon = icon;
        this.shortForecast = shortForecast;
        this.detailedForecast = detailedForecast;

    }

    //Getters and Setters

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isDayTime() {
        return dayTime;
    }

    public void setDayTime(boolean dayTime) {
        this.dayTime = dayTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getTemperatureTrend() {
        return temperatureTrend;
    }

    public void setTemperatureTrend(String temperatureTrend) {
        this.temperatureTrend = temperatureTrend;
    }

    public ProbabilityOfPrecipitation getProbabilityOfPrecipitation(){
        return probabilityOfPrecipitation;
    }

    public void setProbabilityOfPrecipitation(ProbabilityOfPrecipitation probabilityOfPrecipitation){
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public Dewpoint getDewpoint(){
        return dewpoint;
    }

    public void setDewpoint(Dewpoint dewpoint){
        this.dewpoint = dewpoint;
    }

    public RelativeHumidity getRelativeHumidity(){
        return relativeHumidity;
    }

    public void setRelativeHumidity(RelativeHumidity relativeHumidity){
        this.relativeHumidity = relativeHumidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    public static class ProbabilityOfPrecipitation{

        private String unitCode;
        private int value;

        public ProbabilityOfPrecipitation(){//no args

        }

        public ProbabilityOfPrecipitation(String unitCode, int value){

            this.unitCode = unitCode;
            this.value = value;

        }

        //Getters and Setters

        @Override
        public String toString(){
            return "probabilityOfPrecipitationUnitCode: "+this.getUnitCode()+
                    "\nprobabilityOfPrecipitationValue: "+this.getValue();
        }

        public String getUnitCode(){
            return unitCode;
        }

        public void setUnitCode(String unitCode){
            this.unitCode = unitCode;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    public static class Dewpoint {

        public Dewpoint(){//no args

        }

        public Dewpoint(String unitCode, int value){

            this.unitCode = unitCode;
            this.value = value;

        }

        private String unitCode;
        private int value;

        // Getters and setters
        public String getUnitCode() { return unitCode; }
        public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }

    public static class RelativeHumidity {

        public RelativeHumidity(){//no args

        }

        public RelativeHumidity(String unitCode, int value){

            this.unitCode = unitCode;
            this.value = value;

        }

        private String unitCode;
        private int value;

        public String getUnitCode() { return unitCode; }
        public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }
}
