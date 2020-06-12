public class CountDown {

    private int i;
    //1 Synchronized method, can be used by only one thread at a time, until the thread release the method
    //public synchronized void doCountDown() {
    //no recommended here, we really want to keep the code we synchronized to an absolute minimum
    //preventing threads interference or race condition by synchronizing critical sections of code
    public  void doCountDown() {
        String color;
        switch (Thread.currentThread().getName()) {
            case "t1":
                color = ThreadColor.ANSI_RED;
                break;
            case "t2":
                color = ThreadColor.ANSI_GREEN;
                break;
            default:
                color = ThreadColor.ANSI_BLUE;
        }
        //2 ony the for loop needs to be synchronized
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + " : i =" + i);
            }
        }
    }

}
