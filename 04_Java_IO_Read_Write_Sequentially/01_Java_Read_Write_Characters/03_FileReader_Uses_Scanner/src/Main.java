import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    }
    static {
        //automatically closes the scanner object because the scanner object implements AutoClosable
        //closes after the bufferedReader and after the fileReader because both classes implement Closable interface
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src//locations.txt")))) {
            while (scanner.hasNextLine()) {
                scanner.useDelimiter(" , ");
                int number = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String location = scanner.nextLine();
                System.out.println(number + " : " + location);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
