import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    //The program need to specify is goint to catch the exception or that it is going to throw it
    //Thorws is the way of specifying the method is going to throw that error
    public static void main(String[] args) throws IOException {
        Map<Integer, String> mMap = new HashMap<Integer, String>();
        mMap.put(1, "Andreea");
        mMap.put(2, "Gabi");
        mMap.put(3, "Future");

        FileWriter fw = null;

        try {
            //creating a fw object points a checked Exception
            fw = new FileWriter("text.txt");
            for (String name : mMap.values()) {
                fw.write(name);
            }
        } finally {
            if (fw != null) {
                //fw close points a checked Exception
                fw.close();
            }
        }


    }
}
