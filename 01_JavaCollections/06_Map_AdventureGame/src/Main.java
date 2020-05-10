import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, Location> locationMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        locationMap.put(0, new Location(0, "You are sitting in from of ca computer"));
        locationMap.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locationMap.put(2, new Location(2, "You are at the top of a hill"));
        locationMap.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locationMap.put(4, new Location(4, "You are in a valle beside a stream"));
        locationMap.put(5, new Location(5, "You are in the forest"));

        locationMap.get(1).addExit("W", 2);
        locationMap.get(1).addExit("E", 3);
        locationMap.get(1).addExit("S", 4);
        locationMap.get(1).addExit("N", 5);
        locationMap.get(1).addExit("Q", 0);

        locationMap.get(2).addExit("N", 5);
        locationMap.get(2).addExit("Q", 0);

        locationMap.get(3).addExit("W", 1);
        locationMap.get(3).addExit("Q", 0);

        locationMap.get(3).addExit("N", 1);
        locationMap.get(3).addExit("W", 2);

        locationMap.get(4).addExit("N", 1);
        locationMap.get(4).addExit("W", 2);
        locationMap.get(4).addExit("Q", 0);

        locationMap.get(5).addExit("S", 1);
        locationMap.get(5).addExit("W", 2);
        locationMap.get(5).addExit("Q", 0);

        Map<String, String> vocabulary = new HashMap<>();

        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("QUIT", "Q");


        int loc = 1;
        while (true) {
            System.out.println(locationMap.get(loc).getDescription());
            if (loc == 0) {
                break;
            }

            Map<String, Integer> exitsMap = locationMap.get(loc).getExists();
            System.out.println("Available exists are :");
            for (String key : exitsMap.keySet()) {
                System.out.print(key + ", ");
            }
            System.out.println();
            String direction = scanner.nextLine().toUpperCase().trim();
            
            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }

                }
            }
            if (exitsMap.containsKey(direction)) {
                loc = exitsMap.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }

}
