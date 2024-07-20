package com.example.weather;  // Make sure this package matches your project structure

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class WeatherDataFetcher {

    private static final String API_KEY = "API_KEY"; // *** REPLACE WITH YOUR ACTUAL API KEY ***
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    public static JSONObject getWeatherData(String cityName) {
        try {
            URL url = new URL(BASE_URL + "q=" + cityName + "&appid=" + API_KEY + "&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            return new JSONObject(result.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}