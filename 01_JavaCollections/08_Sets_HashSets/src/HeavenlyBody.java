import java.util.HashSet;
import java.util.Set;

public class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public HeavenlyBody(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }


    public boolean addMoon(HeavenlyBody moon) {
        return satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        //creating a new hashet of the curretncontent of the satellite
        return new HashSet<>(satellites);
    }


}
