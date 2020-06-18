import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        //Fairness parameter first come first serve
        ReentrantLock bufferLock=new ReentrantLock(true);
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_RED,bufferLock);
        MyConsumer consumer = new MyConsumer(buffer, ThreadColor.ANSI_CYAN,bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE,bufferLock);

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();

    }
}
