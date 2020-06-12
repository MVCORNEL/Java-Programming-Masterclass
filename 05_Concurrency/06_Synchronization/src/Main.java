public class Main {

    public static void main(String[] args) {

        CountDown countDown = new CountDown();

        Thread t1 = new MyThread(countDown);
        Thread t2 = new MyThread(countDown);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();



    }


}
