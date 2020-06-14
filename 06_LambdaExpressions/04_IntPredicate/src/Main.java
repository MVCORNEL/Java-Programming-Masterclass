import java.util.function.IntPredicate;

public class Main {
    public static void main(String[] args) {
        //Functional interface
        //1 Storing the predicate into an variable for a boolean expression
        int numberOne=50;
        int numberTwo=10;
        IntPredicate intp=number-> number>20;
        System.out.println(intp.test(numberOne));
        System.out.println(intp.test(numberTwo));

        //2 Using intPredicate for a method
        //declaring the array
        int[] array=new int[20];
        for(int i=0;i<20;i++){
            array[i]=i;
        }
        //Declaring the predicate condition
        IntPredicate myPredicate= i-> i>10;
        //Predicate value
        printNumbers(array,myPredicate);
    }

    public static void printNumbers(int numberList[], IntPredicate predicate){
        for(int number:numberList){
            if(predicate.test(number)){
                System.out.println(number);
            }
        }
    }

}
