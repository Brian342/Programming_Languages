package javaPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherStation {
    private final Map<Integer, String> weather = new LinkedHashMap<>();

    public WeatherStation() {
        weather.put(1, "airTemp");
        weather.put(2, "airPressure");
        weather.put(7, "precipitation");
        weather.put(11, "windSpeed");
        weather.put(12, "windDirection");
        weather.put(13, "humidity");
        weather.put(14, "dewPoint");
        weather.put( 15, "soilMoisture");
        weather.put( 22, "cloudCover");

        clearState();
    }

    // Method which accepts a single string representing a message
    // from a weather station.
    public void  updateState(String str) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {}
        String line;
        while((line = reader.readLine()) != null){
            line = line.trim();
            if(!line.isEmpty()){
                processLine(line);
            }
        }
    }
    // Direct string update method (for single lines or batch multiline string
    public void updateState(String message){
        if(message == null || message.isEmpty()){
            return;
        }
        String[] lines = message.split("\\r?\\n");
        for(String line : lines){
            processLine(line);
        }
    }
    private void processLine(String line){
        String[] data = line.split(",");
        if(parts.length == 2){
            try{
                int id = Integer.parseInt(data[0].trim());
                String temp = data[1].trim();
                
            }
        }
    }
    // Method which accepts no parameters, and just gets the state
    public static String getState(){
        return null;
    }

    // Method that accepts no parameters,
    // and sets all weather station values to NULL.
    public static String clearState(){
        return null;
    }
}
