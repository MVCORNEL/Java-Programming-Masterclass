import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {

        //1 MERCURY
        HeavenlyBody planet = new HeavenlyBody("Mercury", 88);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);

        //2 VENUS
        planet = new HeavenlyBody("Venus", 225);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);

        //3 EARTH
        planet = new HeavenlyBody("Earth", 365);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);
        //3.1 EARTH'S MOONS
        HeavenlyBody moon = new HeavenlyBody("Moon", 27);
        solarSystem.put(moon.getName(), moon);
        planet.addMoon(moon);

        //4 MARS
        planet = new HeavenlyBody("Mars", 687);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);
        //4.1 MARS' MOONS
        moon = new HeavenlyBody("Deimos", 1.3);
        solarSystem.put(moon.getName(), moon);
        planet.addMoon(moon);
        //4.2 MARS' MOONS
        moon = new HeavenlyBody("Phobos", 0.3);
        solarSystem.put(moon.getName(), moon);
        planet.addMoon(moon);

        //4 JUPITER
        planet = new HeavenlyBody("Jupiter", 4332);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);
        //4.1 MARS' MOONS
        moon = new HeavenlyBody("Io", 1.8);
        solarSystem.put(moon.getName(), moon);
        planet.addMoon(moon);
        //4.2 MARS' MOONS
        moon = new HeavenlyBody("Europa", 3.5);
        solarSystem.put(moon.getName(), moon);
        planet.addMoon(moon);


        //5 PRINT
        System.out.println("Planets");
        for (HeavenlyBody plan : planets) {
            System.out.print("\t" + plan.getName() + "'s moons :");
            Set<HeavenlyBody> moons = plan.getSatellites();
            for (HeavenlyBody satelite : moons) {
                System.out.print(" " + satelite.getName());

            }
            System.out.println();
        }

        //6 ADDALL(UNION) - USED TO CREATE A SET UNION
        //UNION USING THE SET THEORY
        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody plan : planets) {
            moons.addAll(plan.getSatellites());
        }
        System.out.println("All Moons are :");
        for (HeavenlyBody satelite : moons) {
            System.out.println("\t"+satelite.getName());
        }

    }
}
