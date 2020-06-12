public class Main {

    public static void main(String[] args) {

        CountDown countdown = new CountDown();
        Thread t1=new Thread(new MyThread(countdown));
        t1.setName("Thread 1");
        Thread t2=new Thread(new MyThread(countdown));
        t2.setName("Thread 2");
        t1.start();
        t2.start();

    }
}



