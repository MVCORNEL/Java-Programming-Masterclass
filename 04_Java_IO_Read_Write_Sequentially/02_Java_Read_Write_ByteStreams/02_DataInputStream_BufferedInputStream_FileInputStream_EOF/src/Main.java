import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, String> map = new LinkedHashMap<>();
    public static void main(String[] args) {
    }
    static {
        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("src//name.dat")))) {
            //Handling EOF exception, not necessary but recommended
            //->because when reading a stream of data, that exception will always be throw
            boolean eof = false;
            while (!eof) {
                try {

                    int id = locFile.readInt();
                    String name = locFile.readUTF();
                    System.out.println("Id number :" + id + " and name is: " + name);

                } catch (EOFException e) {
                    System.out.println(e.getMessage());
                    eof = true;
                }
            }
        } catch (IOException e) {
            //EOF EOF exception END OF FILE EXCEPTION
            e.printStackTrace();
        }
    }
}
