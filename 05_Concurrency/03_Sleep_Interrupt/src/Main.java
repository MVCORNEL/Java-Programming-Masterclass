public class Main {

    public static void main(String[] args) {
        Thread mThread = new Thread(new MyRunnable());
        mThread.start();

        new Thread() {
            @Override
            public void run() {
                //The interrupt is used to interrupt a sleeping or waiting thread state, it can be interrupt the thread execution by throwing InterruptedException.
                mThread.interrupt();
            }
        }.start();
    }
}
