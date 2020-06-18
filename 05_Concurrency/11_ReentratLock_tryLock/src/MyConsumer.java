import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable {
    public static final String EOF = "EOF";
    private ReentrantLock bufferLock;
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.bufferLock = bufferLock;
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        // INFORMATIVE PURPOSE
        int counter=0;
        while (true) {
            //TRY LOCK METHOD
            //A THREAD CAN TEST TO SEE WHETHER A LOCK IS AVAILABLE USING THE TRY LOCK METHOD
            //->IF A LOCK IS AVAILABLE THE THREAD WILL ACQUIRE THE LOCK AND WILL CONTINUE EXECUTING
            if(bufferLock.tryLock()) {
                //Using try, finally because there are 2 advantages
                //1 we,ve only have to put the unlock in one place
                //2 the unlock method is going to be called no matter what happens in the try block
                //in the previous example , the ReentrantLock object unlock method was called 3 times, instead of one with the try finally blocks
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    // INFORMATIVE PURPOSE
                    System.out.println(color+"The counter is : " +counter);
                    counter=0;
                    if (buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Exiting");
                        //must be added here as well if not maximum lock count exceeded error will be throw
                        //because after the while loop we acquired the lock but we never release it because continue will get us back to the beginning of the loop
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                } finally {
                    //we've guaranteed the lock will be released
                    bufferLock.unlock();
                }
            }
            // INFORMATIVE PURPOSE
            else {
                counter++;
            }

            }
        }

}
