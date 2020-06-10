public class MyRunnable implements Runnable {
    @Override
    public void run(){
        try {
            //Sleep method is used to pause the execution of a thread
            Thread.sleep(10000);
        }
        catch (InterruptedException e){
            System.out.println("The thread has been interrupted"+ e.getMessage());
        }
        System.out.println("Here i am ");
        }
    }




