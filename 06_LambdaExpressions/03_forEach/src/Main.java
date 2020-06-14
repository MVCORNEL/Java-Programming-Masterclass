import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Person> persons=new ArrayList<>();
        persons.add(new Person("Bobo",27));
        persons.add(new Person("Radu",29));
        persons.add(new Person("Cucu",31));

        persons.forEach(person -> System.out.println(person.getName()));




    }
}
