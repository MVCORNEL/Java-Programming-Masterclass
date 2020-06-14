import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Lambdas are a new feature introduced with java 8 and provide us an an easier way to work with interfaces that have have only got one method
//Mostly used where we used anonymous classes
//When a compiler sees a lambda expression how does it know what to do
//Lambda works only work classes that have only one method that has to be implemented -> functional interfaces
//Compiler knows that one of the thread classes constructors accepts a runnable interface,
//and also know that the class has only one method which need to be implemented
//Whenever we see an anonymous class definition that only overrides one method we can consider using lambda
public class Main {
    static List<Employee> employees = new ArrayList<>();
    public static void main(String[] args) {

        //Each lambda expression has 3 parts->
        //Argument list
        //Arrow token
        //Body

    //1 Never add semicolon when you use lambda with only one statement
        new Thread(() -> System.out.println("Hello there")).start();
    //2 Lambda expression using a block of code
        new Thread(() -> {
            System.out.println("Executing a block of code using lambda");
            System.out.println("Executing a block of code using lambda");
        }).start();
        Employee John = new Employee("29", 30);
        Employee Tim = new Employee("Tim Buchalka", 21);
        Employee Jack = new Employee("Jack Hill", 49);
        Employee Snow = new Employee("Snow White", 22);


        employees.add(John);
        employees.add(Tim);
        employees.add(Jack);
        employees.add(Snow);
    //3 Lambda for a second paremeter parameter
        //no semicolon after for one lambda statement
        Collections.sort(employees, (Employee e1, Employee e2) ->
                e1.getName().compareTo(e2.getName())
        );
        //4 Even more simplified, the compiler can infer the parameter type
        Collections.sort(employees, (e1, e2) ->
                e1.getName().compareTo(e2.getName()));
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
    }

    //5 Lambda for a anonymous interface
    //No return type here
    private final static String doStringStuff(UpperConcate uc, String s1, String s2) {
        return uc.upperAndConcate(s1, s2);
    }
    String sillyString=doStringStuff(new UpperConcate() {
        @Override
        public String upperAndConcate(String s1, String s2) {
            return s1.toUpperCase()+s2.toUpperCase();
        }
    },employees.get(0).getName(),employees.get(1).getName());

    //6 Using lambda for an anonymous class interface
    UpperConcate uc=(String s1,String s2) -> s1.toUpperCase()+s2.toUpperCase();
}