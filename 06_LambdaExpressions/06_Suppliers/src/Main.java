import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        //Suppliers can be used to instantiate objects or to populate them with random values -> our case
        Random random=new Random();
        Supplier<Integer> randomSupplier=() -> random.nextInt(1000);
        for(int i=0;i<10;i++){
            System.out.println(randomSupplier.get());
        }
    }
}
