//THE WAYS OF APPROACHING THE ERRORS IN PROGRAMMING
//1 LBYL LOOK BEFORE YOU LEAP -> CHECKING IF A OBJECT IS NOT NULL
//2 EAFP EASY TO ASK FOR FORGIVENESS THAN PERMISSION -> HANDLE THE PERMISSION
//IN JAVA IS MORE COMMON TO LOOK BEFORE YOU LEAP

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int x = getIntLBYL();
        System.out.println("x = " + x);
        int y = getIntEAFP();
        System.out.println("y = " + y);
//        System.out.println(divideLBYL(x,y));
//        System.out.println(divideEAFP(x,y));
//        System.out.println(divide(x,y));

    }

    //********************** ARITHMETIC EXCEPTION / DIVISION BY ZERO ****************************//

    //1.1 LOOK BEFORE YOU LEAP DIVISION BY ZERO
    private static int divideLBYL(int x, int y) {
        if (y != 0) {
            return x / y;
        } else {
            return 0;
        }
    }
    //1.2 EAFP -> EASIER TO ASK FOR FORGIVENESS THAN PERMISSION DIVISION BY ZERO
    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            return 0;
        }
    }
    //1.3 ARITHMETIC EXCEPTION / BY ZERO IF Y = 0
    private static int divide(int x, int y) {
        return x / y;
    }

    //********************** INPUT MISMATCH EXCEPTION ****************************//

    //2.1 LOOK BEFORE YOU LEAP INPUT MISMATCH EXCEPTION
    private static int getIntLBYL() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        System.out.println("Please enter and integer");
        String input = scanner.next();

        for (int i = 0; i < input.length(); i++) {
            //if the character is not a digit
            if (!Character.isDigit(input.charAt(i))) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }
    //2.2 EAFP ->EASIER TO ASK FOR FORGIVENESS THAN PERMISSION
    private static int getIntEAFP(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter an integer");
        try{
            return scanner.nextInt();
        }
        catch(InputMismatchException e){
            return 0;
        }
    }
}