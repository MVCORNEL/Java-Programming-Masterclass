import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable {
    public static final String EOF="EOF";
    private ReentrantLock bufferLock;
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color,ReentrantLock bufferLock) {
        this.bufferLock=bufferLock;
        this.buffer = buffer;
        this.color = color;
    }
    public void run(){
        while(true) {
            bufferLock.lock();
                if (buffer.isEmpty()) {
                    //must be added here as well if not maximum lock count exceeded error will be throw
                    //because after the while loop we acquired the lock but we never release it because continue will get us back to the beginning of the loop
                    bufferLock.unlock();
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    //must be added here as well if not maximum lock count exceeded error will be throw
                    //because after the while loop we acquired the lock but we never release it because continue will get us back to the beginning of the loop
                    bufferLock.unlock();
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            bufferLock.unlock();
        }
    }
}
