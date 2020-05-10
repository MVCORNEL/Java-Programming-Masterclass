import java.util.HashMap;
import java.util.Map;

public class Location {

    private final int locationId;
    private final String description;
    private final Map<String,Integer> exists;

    public Location(int locationId, String description) {
        this.locationId = locationId;
        this.description = description;
        this.exists=new HashMap<>();
    }

    public void addExit(String direction,int location){
        exists.put(direction,location);
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExists() {
        return new HashMap<>(exists);
    }
}
