package com.example.sampleweatherapp.dto;

public class WeatherResponse {

    private int number;
    private String name;
    private String startTime;
    private String endTime;
    private boolean dayTime;
    private int temperature;
    private String temperatureUnit;
    private String temperatureTrend;
    private ProbabilityOfPercipitation probabilityOfPercipitation;
    private String dewpointUnitCode;
    private int dewpointValue;
    private String relativeHumidityUnitCode;
    private int relativeHumidityValue;
    private String windSpeed;
    private String windDirection;
    private String icon;
    private String shortForcast;
    private String detailedForcast;

    public WeatherResponse(){  //no args constructor

    }

    public WeatherResponse(int number, String name, String startTime, String endTime, boolean isDayTime, int temperature,
                           String temperatureUnit, String temperatureTrend, ProbabilityOfPercipitation probabilityOfPercipitation,
                           String dewpointUnitCode, int dewpointValue, String relativeHumidityUnitCode, int relativeHumidityValue, String windSpeed,
                           String windDirection, String icon, String shortForcast, String detailedForcast){

        this.number = number;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayTime = isDayTime;
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
        this.temperatureTrend = temperatureTrend;
        this.probabilityOfPercipitation = probabilityOfPercipitation;
        this.dewpointUnitCode = dewpointUnitCode;
        this.dewpointValue = dewpointValue;
        this.relativeHumidityUnitCode = relativeHumidityUnitCode;
        this.relativeHumidityValue = relativeHumidityValue;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.icon = icon;
        this.shortForcast = shortForcast;
        this.detailedForcast = detailedForcast;

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

    public ProbabilityOfPercipitation getProbabilityOfPercipitation(){
        return probabilityOfPercipitation;
    }

    public void setProbabilityOfPercipitation(ProbabilityOfPercipitation probabilityOfPercipitation){
        this.probabilityOfPercipitation = probabilityOfPercipitation;
    }

//        public String getProbabilityOfPercipitationUnitCode() {
//            return probabilityOfPercipitation.getProbabilityOfPercipitationUnitCode();
//        }
//
//        public void setProbabilityOfPercipitationUnitCode(String probabilityOfPercipitationUnitCode) {
//            this.probabilityOfPercipitation.setProbabilityOfPercipitationUnitCode(probabilityOfPercipitationUnitCode);
//        }
//
//        public int getProbabilityOfPercipitationValue() {
//            return probabilityOfPercipitation.getProbabilityOfPercipitationValue();
//        }
//
//        public void setProbabilityOfPercipitationUnitCode(int probabilityOfPercipitationValue) {
//            this.probabilityOfPercipitation.setProbabilityOfPercipitationValue(probabilityOfPercipitationValue);
//        }

    public String getDewpointUnitCode() {
        return dewpointUnitCode;
    }

    public void setDewpointUnitCode(String dewpointUnitCode) {
        this.dewpointUnitCode = dewpointUnitCode;
    }

    public int getDewpointValue() {
        return dewpointValue;
    }

    public void setDewpointValue(int dewpointValue) {
        this.dewpointValue = dewpointValue;
    }

    public String getRelativeHumidityUnitCode() {
        return relativeHumidityUnitCode;
    }

    public void setRelativeHumidityUnitCode(String relativeHumidityUnitCode) {
        this.relativeHumidityUnitCode = relativeHumidityUnitCode;
    }

    public int getRelativeHumidityValue() {
        return relativeHumidityValue;
    }

    public void setRelativeHumidityValue(int relativeHumidityValue) {
        this.relativeHumidityValue = relativeHumidityValue;
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

    public String getShortForcast() {
        return shortForcast;
    }

    public void setShortForcast(String shortForcast) {
        this.shortForcast = shortForcast;
    }

    public String getDetailedForcast() {
        return detailedForcast;
    }

    public void setDetailedForcast(String detailedForcast) {
        this.detailedForcast = detailedForcast;
    }

    public static class ProbabilityOfPercipitation{

        private String probabilityOfPercipitationUnitCode;
        private int probabilityOfPercipitationValue;

        public ProbabilityOfPercipitation(){//no args

        }

        public ProbabilityOfPercipitation(String probabilityOfPercipitationUnitCode, int probabilityOfPercipitationValue){

            this.probabilityOfPercipitationUnitCode = probabilityOfPercipitationUnitCode;
            this.probabilityOfPercipitationValue = probabilityOfPercipitationValue;

        }

        //Getters and Setters

        @Override
        public String toString(){
            return "probabilityOfPercipitationUnitCode: "+this.getProbabilityOfPercipitationUnitCode()+
                    "\nprobabilityOfPercipitationValue: "+this.getProbabilityOfPercipitationValue();
        }

        public String getProbabilityOfPercipitationUnitCode(){
            return probabilityOfPercipitationUnitCode;
        }

        public void setProbabilityOfPercipitationUnitCode(String probabilityOfPercipitationUnitCode){
            this.probabilityOfPercipitationUnitCode = probabilityOfPercipitationUnitCode;
        }

        public int getProbabilityOfPercipitationValue() {
            return probabilityOfPercipitationValue;
        }

        public void setProbabilityOfPercipitationValue(int probabilityOfPercipitationValue) {
            this.probabilityOfPercipitationValue = probabilityOfPercipitationValue;
        }

    }
}
