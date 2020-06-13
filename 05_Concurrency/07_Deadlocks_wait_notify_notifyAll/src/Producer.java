import java.util.Random;

public class Producer implements Runnable {
    private Message message;

    public Producer(Message message){
        this.message=message;
    }

    @Override
    public void run() {
        String messageList[]={
                "Humpty Dumpty sat on the wall",
                "Humpty Dumty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"};

        Random random=new Random();
        for(int i=0; i<messageList.length;i++){
            message.write(messageList[i]);

            try {
                Thread.sleep(random.nextInt(10000));
            }
            catch (InterruptedException e){
            }

        }
        message.write("Finished");
    }
}
