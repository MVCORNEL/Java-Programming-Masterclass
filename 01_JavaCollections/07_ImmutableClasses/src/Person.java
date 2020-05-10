import java.util.HashMap;
import java.util.Map;

public class Person {

    private final int personId;
    private final String name;
    private final Map<String, Integer> grades;

    public Person(int personId, String name, Map<String, Integer> grades) {
        this.personId = personId;
        this.name = name;
        //that is a must because the null reference is pas into the constructor when you try to add an object from the main will throw exception
        if (grades != null) {
            this.grades = new HashMap<>(grades);

        } else {
            this.grades = new HashMap<>();
        }
    }

    public Map<String, Integer> getExists() {
        return new HashMap<>(grades);
    }

    public void printMap() {
        for (String string : grades.keySet()) {
            System.out.println(string + grades.get(string));
        }
    }
}
