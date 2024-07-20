package com.example.weather; // Make sure this package matches your project structure

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;

public class SimpleWeatherForecastingUI {

    private static JTextField cityTextField;
    private static JLabel temperatureLabel;
    private static JLabel descriptionLabel;
    private static JLabel humidityLabel;
    private static JLabel windSpeedLabel;

    public static void main(String[] args) {
        // Create and set up the window.
        JFrame frame = new JFrame("Simple Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250); 
        frame.setLayout(new FlowLayout());

        // Create UI elements
        JLabel cityLabel = new JLabel("Enter City:");
        cityTextField = new JTextField(20);
        JButton getWeatherButton = new JButton("Get Weather");
        temperatureLabel = new JLabel("Temperature: ");
        descriptionLabel = new JLabel("Description: ");
        humidityLabel = new JLabel("Humidity: ");
        windSpeedLabel = new JLabel("Wind Speed: ");

        // Add action listener to the button
        getWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityTextField.getText();
                if (!city.isEmpty()) {
                    JSONObject weatherData = WeatherDataFetcher.getWeatherData(city);
                    if (weatherData != null) {
                        displayWeatherData(weatherData);
                    } else {
                        clearWeatherData();
                        temperatureLabel.setText("Error fetching data");
                    }
                } else {
                    clearWeatherData();
                    temperatureLabel.setText("Please enter a city");
                }
            }
        });

        // Add UI elements to the frame
        frame.add(cityLabel);
        frame.add(cityTextField);
        frame.add(getWeatherButton);
        frame.add(temperatureLabel);
        frame.add(descriptionLabel); 
        frame.add(humidityLabel);  
        frame.add(windSpeedLabel); 
        frame.setVisible(true);
    }

    // Helper function to display fetched weather data
    private static void displayWeatherData(JSONObject weatherData) {
        double temperature = weatherData.getJSONObject("main").getDouble("temp");
        String description = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
        int humidity = weatherData.getJSONObject("main").getInt("humidity");
        double windSpeed = weatherData.getJSONObject("wind").getDouble("speed");

        temperatureLabel.setText("Temperature: " + temperature + " °C");
        descriptionLabel.setText("Description: " + description);
        humidityLabel.setText("Humidity: " + humidity + "%");
        windSpeedLabel.setText("Wind Speed: " + windSpeed + " m/s");
    }

    // Helper function to clear displayed weather data
    private static void clearWeatherData() {
        temperatureLabel.setText("Temperature: ");
        descriptionLabel.setText("Description: ");
        humidityLabel.setText("Humidity: ");
        windSpeedLabel.setText("Wind Speed: ");
    }
}