import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> gents = new HashMap<>();
        gents.put(1, "Valentin");
        gents.put(2, "Radu");
        gents.put(3, "Catalin");

        Map<Integer, String> ladies = new HashMap<>();
        ladies.put(1, "Andreea");
        ladies.put(2, "Gabriela");
        ladies.put(3, "Maria");

        //TRY WITH RESOURCE ensure the fr object is close even if the code executed normally or even if an exception occurs
        //Object needs to implement AutoClosable interface
        try (FileWriter fwOne = new FileWriter("src//first.txt");
             FileWriter fwSecond = new FileWriter("src//second.txt")) {
            for (Integer key : gents.keySet()) {
                fwOne.write(key + " : " + gents.get(key) + " \n ");
            }
            for (Integer key : ladies.keySet()) {
                fwSecond.write(key + " : " + ladies.get(key) + " \n ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
