import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
//Predicate is a functional interface
//Predicate arguments are just lambda expressions that match the predicate interface, they accept one parameter and return a boolean value
public class Main {

    public static void main(String[] args) {
        List<Person> persons=new ArrayList<>();
        persons.add(new Person("Bobo",27));
        persons.add(new Person("Radu",29));
        persons.add(new Person("Tati",60));
        persons.add(new Person("Ioana",32));
        persons.add(new Person("Cucu",35));
        
        //Predicate arguments by using anonymous class
        printEmployeeByAge(persons, new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge()<25;
            }
        });
        //Predicate arguments bu using lambda expressions that match the predicate interface
        printEmployeeByAge(persons, person-> person.age<35);


    }
    private static void printEmployeeByAge(List<Person> personsList, Predicate<Person> predicate){
        for(Person person: personsList){
            if(predicate.test(person)){
                System.out.println(person.getName());
            }
        }
    }


}
