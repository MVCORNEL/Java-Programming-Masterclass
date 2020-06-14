import java.util.function.IntPredicate;

public class Main {
    public static void main(String[] args) {
        //1
        //Testing 2 chaining predicates using .and() function
        IntPredicate greaterThan15= number-> number>15;
        IntPredicate lessThan100= number-> number<100;
        System.out.println(greaterThan15.and(lessThan100).test(60));

        //2
        // Chaining Predicates for a function
        int numberList[]={10,12,15,16,20,26};
        IntPredicate smallerThan20=i->i<20;
        IntPredicate greaterThan10=i->i>10;
        //Chaining the predicates in the same method argument
        printNumber(numberList,smallerThan20.and(greaterThan10));






    }

    public static void printNumber(int[] numbers, IntPredicate predicate){
        for(int number:numbers){
            //if the predicate is evaluated as being true, testing predicate
            if(predicate.test(number)){
                System.out.println(number);
            }

        }



    }




}
