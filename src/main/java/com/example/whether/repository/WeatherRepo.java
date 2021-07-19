package com.example.whether.repository;

import com.example.whether.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepo extends JpaRepository<Weather,Long> {
    Weather findByName(String name);
}
