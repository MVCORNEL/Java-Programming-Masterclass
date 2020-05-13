import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Exception is defined as an event which occurred during the execution of a program that disrupts the normal flow of the program's instructions
// catch block used to handle the exception including attempting to recover from it
// Exception and runtime exception are classes defined in java.lang
// IF ONE OF THE ERRORS OCCURS IT IS PRINTED A STACK TRACE WHICH IS SHOWING THE CALL STACK
// CALL STACK IS A LIST OF ALL THE METHODS CALLED AT ANY PARTICULAR POINT IN THE PROGRAM EXECUTION
//the last line from the call stack created an exception object which contains the current stack trace
//IF nothing handles the exception thrown the java will print the stack trace and Java Run Time terminates
public class Main {
    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }
    //DIVIDE
    private static int divide() {
        int x=0;
        int y=0;
        try {
            x = getInt();
            y = getInt();
            //This catch block catches CTL + D kind of errors
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No suitable input");
        }
        System.out.println("x is " + x + ", y is " + y);

        try {
            return x / y;
        }
        catch (ArithmeticException e){
            throw new ArithmeticException("attempt to dive by zero");
        }
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
