import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        //Used instead synchronized block
        //Buffer.lock used to acquire the lock and the unlock method to release the lock
        //When using lock objects we are responsible for releasing the lock
        //Drawback of using object lock it we need to to handle the lock and unlock by ourselves
        //When a thread calls the lock method will either obtain the lock and continue executing or won't be able to
        //->obtain the lock because another thread already has it, in this case the called lock thread will be suspended until it can get that lock
        this.bufferLock = bufferLock;
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = {"1", "2", "3", "4", "5"};
        for (String num : numbers) {
            try {
                System.out.println(color + "Adding..." + num);
               bufferLock.lock();
                    //Using try, finally because there are 2 advantages
                    //1 we,ve only have to put the unlock in one place
                    //2 the unlock method is going to be called no matter what happens in the try block
                    try {
                        buffer.add(num);
                    } finally {
                        bufferLock.unlock();
                    }

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting...");
        //used instead synchronized block
        bufferLock.lock();
        try {
            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }
    }
}
