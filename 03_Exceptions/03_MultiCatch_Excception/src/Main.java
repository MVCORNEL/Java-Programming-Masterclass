import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            int result = divide();
            System.out.println(result);
        }
        //A feature implemented in java 7
        //CHECK MULTIPLE EXCEPTION WITHIN A CATCH BLOCK
        catch (ArithmeticException | NoSuchElementException e){
            System.out.println(e.getMessage());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }
    //DIVIDE
    private static int divide() {
        int x = 0;
        int y = 0;
//        try {
            x = getInt();
            y = getInt();
            return x / y;
            //This catch block catches CTL + D kind of errors
//        } catch (NoSuchElementException e) {
//            throw new NoSuchElementException("No suitable input");
//        } catch (ArithmeticException e) {
//            throw new ArithmeticException("Attempt do divide by 0");
//            //if you throw a new exception your program will halt so you can avoid return something within this block
//        }
    }
    //SCANNER
    private static int getInt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer ");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                //THE CALL CAN AS WELL CAN PRODUCE NO SUCH ELEMENT EXCEPTION THAT IS WHY I HANDLED INTO THE MAIN AS WELL
                //CTRL D
                scanner.nextLine();
                // go round again. Read past the end of line in the input first
                System.out.println("Please enter a number using only the digits 0 to 9");
            }
        }
    }
}








