import java.util.ArrayList;

    public class Main {
        //Generics
        //Instead of creating methods which can take arguments, we can replace the parameters with TYPE PARAMETERS
        //we can create class, interface and method which take types as parameters called (TYPE PARAMETERS)

        public static void main(String[] args) {
            ArrayList mArrayList2=new ArrayList();
            mArrayList2.add(1);
            mArrayList2.add("2");
            mArrayList2.add(3);
            //In java 1.5 Generics type were introduced
            //-> One good reason of using them is the cwe can detect the errors at the compile time, before the program is executed
            // and announce us something is wrong before we carry out the instructions
            ArrayList<Integer> mArrayList=new ArrayList<>();
            mArrayList.add(1);
            mArrayList.add(2);
            mArrayList.add(3);

            //compile time error
            //bunt undetected until we run the program
           printDoubled(mArrayList2);

        }
        private static void printDoubled(ArrayList n){
            for(Object i : n){
                System.out.println((Integer)i*2);
            }


        }

    }
