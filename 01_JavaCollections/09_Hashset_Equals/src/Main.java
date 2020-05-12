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

        //5 PLUTO
        planet = new HeavenlyBody("Pluto", 248);
        solarSystem.put(planet.getName(), planet);
        planets.add(planet);

        //6 PRINT
        System.out.println("Planets");
        for (HeavenlyBody plan : planets) {
            System.out.print("\t" + plan.getName() + "'s moons :");
            Set<HeavenlyBody> moons = plan.getSatellites();
            for (HeavenlyBody satelite : moons) {
                System.out.print(" " + satelite.getName());

            }
            System.out.println();
        }

        //7 UNION USING THE SET THEORY
        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody plan : planets) {
            moons.addAll(plan.getSatellites());
        }
        System.out.println("Moons UNION SET :");
        for (HeavenlyBody satelite : moons) {
            System.out.println("\t" + satelite.getName());
        }


        //8 ADD ANOTHER OBJECT WITH THE SAME NAME OF PLUTO
        HeavenlyBody pluto = new HeavenlyBody("Pluto", 842);
        planets.add(pluto);
        //8.1 PRINT
        System.out.println("Planets are again :");
        for (HeavenlyBody plan : planets) {
            System.out.println("\t" + plan.getName() + " : " + plan.getOrbitalPeriod());
        }
    }
}
