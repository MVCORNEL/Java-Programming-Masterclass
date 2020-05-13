import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    //Try can exist with at least one of the catch and finally block
    //Finally block will always be executed even if the code inside the try block will throw an error
    public static void main(String[] args) {
        HashMap<Integer, String> mMap = new HashMap<>();
        mMap.put(1, "Valentin");
        mMap.put(2, "Cornel");
        mMap.put(3, "Ioana");
        mMap.put(4, "Andreea");

        FileWriter mWriter = null;

        try {
            mWriter = new FileWriter("text.txt");
            for (String name : mMap.values()) {
                mWriter.write(name + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mWriter != null) {
                try {
                    //Closing the file writer object is a must
                    mWriter.close();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    }
}
