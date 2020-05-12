import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();

        for (int i = 1; i <= 50; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }
        System.out.println("There are " + squares.size() + " squares and " + cubes.size() + " cubes");

        //1 UNION
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("UNION (A U B) contains : " + union.size() + " elements");
        System.out.println("===============================================================");
        //2 INTERSECTION
        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);

        System.out.println("INTERSECTION (A n B) contains : " + intersection.size());
        for (int i : intersection) {
            System.out.println(i + " is the square of " + Math.sqrt(i) + " and the cube of " + Math.cbrt(i));
        }
        System.out.println("===============================================================");

        //Extra info
        //Arrays.assList(), takes a array and transforms is into an ArrayList
        //addAll takes a parameter as collection
//        Set<String> wordsSet = new HashSet<>();
//        String sentence = " one day in the year of the fox";
//        String[] arrayWords = sentence.split(" ");
//        wordsSet.addAll(Arrays.asList(arrayWords));
//        for (String set : wordsSet) {
//            System.out.println(set);
//
//        }


        //3 SUBSET
        System.out.println("SUBSET");
        if (union.containsAll(squares)) {
            System.out.println("squares is a subset of union A c (A U B))");
        }
        System.out.println("========================================================================");


        //4 SYMMETRIC DIFFERENCE
        //IN JAVA SYMMETRIC DIFFERENCE IS REPRESENTED BY UNION - INTERSECTION
        System.out.println("SYMETRICE DIFFERENCE CAN BE REPRESETED AS (A U B) - (A n B)");
        Set<Integer> unionSet = new HashSet<>(union);
        union.removeAll(intersection);
        System.out.println("Symmetric differece result cotnains : " + unionSet.size());
        System.out.println("===============================================================");


        //5 ASYMMETRIC DIFFERENCE
        Set<String> natureSet = new HashSet<>();
        String natureString = "all the nature art unknown to thee";
        String[] natureArray = natureString.split(" ");
        natureSet.addAll(Arrays.asList(natureArray));

        Set<String> divineSet = new HashSet<>();
        String divineString = "to err is human to forgive is divine";
        String[] divineArray = divineString.split(" ");
        divineSet.addAll(Arrays.asList(divineArray));

        System.out.println("ASYMMETRIC DIFFERENCE (A - B) ");
        Set<String> diff1 = new HashSet<>(natureSet);
        diff1.removeAll(divineSet);
        for (String string : diff1) {
            System.out.print(string + " ");
        }
        System.out.println();
        System.out.println("ASYMMETRIC DIFFERENCE (B- A) ");
        Set<String> diff2 = new HashSet<>(divineSet);
        diff2.removeAll(natureSet);

        for (String set : diff2) {
            System.out.print(set + " ");
        }


    }

}
