public class Main {
    public static void main(String[] args) {
        // 1 Runnable interface
        // Advantage of using that it only requires to implement a single method
        // Another advantage if is the class already is extends from a parent class, won't be possible to extend the thread class again
        // It is also more flexible
        //1.1
        Thread myRunnableThread=new Thread(new MyRunnable());
        myRunnableThread.start();
        //1.2
        Thread myRunnableThreadAnonymous=new Thread(new MyRunnable(){
            @Override
            public void run(){
                System.out.println(ThreadColor.ANSI_BLUE + "Hello from MyRunnable's anonymous thread");
            }
        });
        myRunnableThreadAnonymous.start();
        //1.3
        Thread runnableThread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_PURPLE+ "Hello from the runnable anonymous class thread");
            }
        });
        runnableThread.start();
    }
}
