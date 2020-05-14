import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, String> names = new HashMap<>();

    static {
        names.put(1, "Andreea");
        names.put(2, "Corina");
        names.put(3, "Darius");
    }

    public static void main(String[] args) throws IOException {
        //Writing stream of bytes using DataOutputStream methods
        try (DataOutputStream data = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("name.dat")))) {
            for (Integer key : names.keySet()) {
                data.writeInt(key);
                data.writeUTF(names.get(key) + "\n");
            }
        }
    }
}
