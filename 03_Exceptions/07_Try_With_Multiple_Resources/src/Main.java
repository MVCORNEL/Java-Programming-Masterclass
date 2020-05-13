import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Integer, String> ladies = new HashMap<>();
        ladies.put(1, "Andreea");
        ladies.put(2, "Gabi");
        ladies.put(3, "Ioana");


        Map<Integer, String> gents = new HashMap<>();
        gents.put(1, "Bobo");
        gents.put(2, "Kristof");
        gents.put(3, "Radu");

        try (FileWriter gentsFW = new FileWriter("gents.txt");
             FileWriter ladiesFW = new FileWriter("ladies.txt")) {

            for (String gentName : gents.values()) {
                gentsFW.write(gentName + " \n ");
            }

            for (String ladiesName : ladies.values()) {
                ladiesFW.write(ladiesName + " \n ");
            }


        }


    }
}
