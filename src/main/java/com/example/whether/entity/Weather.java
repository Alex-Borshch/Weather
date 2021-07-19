package com.example.whether.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "weather")
@Data
public class Weather extends Model {


    private double lon;
      private double      lat;
    private String main;
    private String description;
    private String icon;
    private double temp;
    @Column(name = "feels_like")
    private double feelsLike;
    @Column(name = "temp_min")
    private double tempMin;
    @Column(name = "temp_max")
    private double tempMax;
    private long pressure;
    private long humidity;
    private long visibility;
    @Column(name = "wind_speed")
    private double windSpeed;
    @Column(name = "wind_deg")
    private long windDeg;
    @Column(name = "wind_gust")
    private long windGust;
    @Column(name = "clouds_all")
    private long cloudsAll;
    private long dt;
    private String country;
    private long sunrise;
    private long sunset;
    private long timezone;
    private String name;




}
