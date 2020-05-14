import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

    }

    static {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src//locations.txt"))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String data[] = input.split(",");
                System.out.println(data[0] + " : " + data[1] + " : " + data[2]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}