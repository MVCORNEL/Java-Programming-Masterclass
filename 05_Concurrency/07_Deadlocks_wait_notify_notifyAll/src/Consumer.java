import java.util.Random;

public class Consumer implements Runnable {
    private Message message;
    public Consumer(Message message){
        this.message=message;
    }
    @Override
    public void run() {
        Random random=new Random();
        for(String latestMessage=message.read(); !latestMessage.equals("Finished"); latestMessage=message.read()){
            System.out.println(latestMessage);
            try{
                Thread.sleep(random.nextInt(10000));
            }
            catch (InterruptedException e){
            }
        }
    }
}
