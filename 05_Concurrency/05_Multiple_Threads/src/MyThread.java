public class MyThread implements Runnable {
    private CountDown countDown;
    public MyThread(CountDown countDown) {
        this.countDown = countDown;
    }
    @Override
    public void run() {
        countDown.doCountDown();
    }
}
