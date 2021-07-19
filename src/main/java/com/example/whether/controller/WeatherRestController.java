package com.example.whether.controller;

import com.example.whether.entity.Weather;
import com.example.whether.service.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherRestController {
    private final WeatherService service;
    public WeatherRestController(WeatherService service) {
        this.service = service;
    }

    @GetMapping
    public Weather getWeather (@RequestParam(value="city",defaultValue = "Kyiv") String city){
        System.out.println("GET GET GET GET GET GET GET GET GET");
        return service.getWeatherByCity(city);
    }
}
