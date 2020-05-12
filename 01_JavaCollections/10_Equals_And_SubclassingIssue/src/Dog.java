import java.util.Objects;

public class Dog {
    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object o) {
       if(this == o){
           return true;
       }
       if(o instanceof Dog){
          return Objects.equals(this.getName(),((Dog) o).getName());
       }
       return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
