import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> grades = null;
        Person bobo = new Person(2525, "Manea", grades);
        bobo.getExists().put("hei", 2);
        bobo.printMap();
    }
}
