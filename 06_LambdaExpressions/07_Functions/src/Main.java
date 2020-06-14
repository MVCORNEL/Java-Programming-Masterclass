import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        ArrayList<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee("Valentin Cornel", 27));
        employeesList.add(new Employee("Radu Manea", 28));
        employeesList.add(new Employee("Cornel Sorin", 57));
        employeesList.add(new Employee("Kristof Selecz", 28));

        //1
        // Function one
        Function<Employee,String> functionReturnLastName= employee ->
                employee.getName().substring(employee.getName().indexOf(" ")+1);
        //1.1
        // Function two
        Function<Employee,String> functionReturnFirstName=employee->
                employee.getName().substring(0,employee.getName().indexOf(" "));
        //2
        //Example of using a simple function
        //employeesList.forEach(employee -> System.out.println(functionReturnFirstName.apply(employee)));

        //3
        //example for using both functions
        employeesList.forEach(employee -> {
            Random randomBoolean=new Random();
            if(randomBoolean.nextBoolean()){
                getEmployeeName(employee,functionReturnFirstName);
            }
            else{
                getEmployeeName(employee,functionReturnLastName);
            }

        });

    }


    public static void getEmployeeName(Employee employee, Function<Employee,String> function){
        System.out.println(function.apply(employee));


    }

}
