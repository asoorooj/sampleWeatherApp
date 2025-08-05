package com.example.sampleweatherapp.dto;

public class WeatherLocationResponse {

    private String city;
    private String state;
    private double[] coordinates;

    public WeatherLocationResponse(){

    }

    public WeatherLocationResponse(String city, String state, double[] coordinates){
        this.city = city;
        this.state = state;
        this.coordinates = coordinates;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public double[] getCoordinates(){
        return coordinates;
    }

    public void setCoordinates(double[] coordinates){
        this.coordinates = coordinates;
    }

}
