import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Integer,String> mMap= new HashMap<>();
        mMap.put(1,"Andreea");
        mMap.put(2,"Gabi");
        //try resources supported in language level 7
        //as a resource cen be used nay object that implements AutoClosable interface
        //ensured the fr will be close if the code runs normally or even if a exception occurs
        try(FileWriter fr=new FileWriter("text.txt")){
            for(String name: mMap.values()){
                fr.write(name + " \n");
            }
        }
    }
}
