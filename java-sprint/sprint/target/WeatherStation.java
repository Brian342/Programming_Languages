package sprint;
// import packages
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;
 
public class WeatherStation {

    private final Map<DataPoint, Double> state = new EnumMap<>(DataPoint.class);

    public WeatherStation(){
        clearState();
    }

    public void updateState(String str){
        if(str == null || str.isBlank()){
            return;
        }
        for (String line: str.split("\\r?\\n")){
            applyLine(line);
        }
    }

    public void updateStateFromFile(String filepath) throws IOException{
        List<String> lines = Files.readAllLines(Path.of(filepath));
        for(String line : lines){
            applyLine(line);
        }
    }

    private void applyLine(String line){
        line = line.trim();
        if (line.isEmpty()){
            return;
        }

        String[] parts = line.split(",", 2);
        if(parts.length != 2){
            return;
        }

        DataPoint dp = DataPoint.fromId(Integer.parseInt(parts[0].trim()));
        if(dp == null){
            return;
        }

        String rawValue = parts[1].trim();
        Double value = rawValue.equalsIgnoreCase("NULL") ? null : Double.parseDouble(rawValue);
        state.put(dp, value);
    }
 // Get the state of the csv file
    public String getState() {
        StringBuilder sb = new StringBuilder();
        for (DataPoint dp : DataPoint.values()) {
            Double value = state.get(dp);
            sb.append(dp.key).append(':').append(value == null ? "NULL" : value).append('\n');
        }
        return sb.toString();
    }
    // clear the csv file data
 
    public void clearState() {
        for (DataPoint dp : DataPoint.values()) {
            state.put(dp, null);
        }

    }

    // store the datapoint in id and key format
    public enum DataPoint{
    AIR_TEMP(1, "airTemp"),
    AIR_PRESSURE(2, "airPressure"),
    PRECIPITATION(7, "precipitation"),
    WIND_SPEED(11, "windSpeed"),
    WIND_DIRECTION(12, "windDirection"),
    HUMIDITY(13, "humidity"),
    DEW_POINT(14, "dewPoint"),
    SOIL_MOISTURE(15, "soilMoisture"),
    CLOUD_COVER(22, "cloudCover");

    public final int id;
    public final String key;

    DataPoint(int id, String key){
        this.id = id;
        this.key = key;
    }

    // getting the id and key of the code 
    private static final Map<Integer, DataPoint> BY_ID = 
    Arrays.stream(values())
        .collect(Collectors.toMap(dp -> dp.id, dp-> dp));

        public static DataPoint fromId(int id){
            return BY_ID.get(id);
        }
    }

    
}
