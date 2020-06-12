public class CountDown {
    private int i;
    public void doCountDown() {
        String color;
        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_RED;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_GREEN;
                break;
            default:
                color = ThreadColor.ANSI_BLUE;
        }
        for ( i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + " : i =" + i);
        }
    }
}
