package com.example.whether.service;

import com.example.whether.entity.Weather;
import com.example.whether.repository.WeatherRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WeatherService {

    private final WeatherRepo weatherRepo;
    private final String apiKey = "4eae522022eb97c15cb7c751f5378c8a";


    public WeatherService(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }



    private void saveWeatherByCity(String city) {
        if (weatherRepo.findByName(city) != null){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
            weatherRepo.delete(weatherRepo.findByName(city));
        }
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        try {
            URL url = new URL(urlString);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JSONObject root = (JSONObject) new JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));
            System.out.println(root.toJSONString());
            request.disconnect();
            weatherRepo.save(parseApiResponse(root));

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Weather getWeatherByCity (String city){
        Weather weather = weatherRepo.findByName(city);
        return weather;
    }

    private Weather parseApiResponse(JSONObject response) {
        Weather weather = new Weather();
        JSONArray arr = (JSONArray) response.get("weather");
        weather.setLon((Double.parseDouble((((JSONObject) response.get("coord")).get("lon")).toString())));
        weather.setLat((Double.parseDouble((((JSONObject) response.get("coord")).get("lat")).toString())));
        weather.setMain((String)  ((JSONObject) arr.get(0)).get("main"));
        weather.setDescription((String) ((JSONObject) arr.get(0)).get("description"));
        weather.setIcon((String) ((JSONObject) arr.get(0)).get("icon"));
        weather.setTemp((Double) (((JSONObject) response.get("main")).get("temp")));
        weather.setFeelsLike((Double) (((JSONObject) response.get("main")).get("feels_like")));
        weather.setTempMin((Double) (((JSONObject) response.get("main")).get("temp_min")));
        weather.setTempMax((Double) (((JSONObject) response.get("main")).get("temp_max")));
        weather.setPressure((Long) (((JSONObject) response.get("main")).get("pressure")));
        weather.setHumidity((Long) (((JSONObject) response.get("main")).get("humidity")));
        weather.setVisibility((Long) response.get("visibility"));
        weather.setWindSpeed((Double.parseDouble((((JSONObject) response.get("wind")).get("speed")).toString()))) ;
        weather.setWindDeg((Long) (((JSONObject) response.get("wind")).get("deg")));
        weather.setCloudsAll((Long) (((JSONObject) response.get("clouds")).get("all")));
        weather.setDt((Long) response.get("dt"));
        weather.setCountry((String) (((JSONObject) response.get("sys")).get("country")));
        weather.setSunrise((Long) (((JSONObject) response.get("sys")).get("sunrise")));
        weather.setSunset((Long) (((JSONObject) response.get("sys")).get("sunset")));
        weather.setTimezone((Long) response.get("timezone"));
        weather.setName((String) response.get("name"));
        return weather;
    }


    @Scheduled(initialDelayString = "6000", fixedDelayString = "6000")
    private void saveCities() throws InterruptedException {
        saveWeatherByCity("Kyiv");
        //saveWeatherByCity("Kharkiv");
        //saveWeatherByCity("Odesa");
        //saveWeatherByCity("Dnipro");
        //saveWeatherByCity("Valky");
        //saveWeatherByCity("Lviv");
    }
}



