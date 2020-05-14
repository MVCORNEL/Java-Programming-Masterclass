import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    static HashMap<Integer, String> names = new HashMap<>();

    public static void main(String[] args) {
    }

    static {
        names.put(0, "Andreea");
        names.put(1, "Gabriela");
        names.put(2, "Maria");
        names.put(3, "Luciana");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src//names.txt"))) {
            for (Integer key : names.keySet()) {
                bufferedWriter.write(key + " : " + names.get(key) + "\n");
            }
        } catch (IOException e) {
        }
    }
}
