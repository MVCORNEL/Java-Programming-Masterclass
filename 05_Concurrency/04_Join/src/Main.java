public class Main {

    public static void main(String[] args) {

        //1 SLEEPING THREAD
        Thread sleepingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //SLEEP 10 seconds
                try {
                    System.out.println(ThreadColor.ANSI_RED+"I will sleep for 10 seconds");
                    Thread.sleep(10000);
                    System.out.println(ThreadColor.ANSI_RED+ "ZZZZZ i just woke up and i finished my task");

                } catch (InterruptedException e) {
                    System.out.println(ThreadColor.ANSI_RED+"I'we been woke up i.ve been interrupted");
                }
            }
        });
        sleepingThread.start();
        //2 JOINING THREAD WILL WAIT TO JOIN
        // Join method allows a thread  to wait until another thread completes its execution.
        Thread joiningThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Joining method can take a parameter as time
                try {
                    System.out.println(ThreadColor.ANSI_GREEN + "I will wait him to wake up");
                    //sleeping method can have a timeout value, prevents crashes if the joined thread won't terminate
                    //sleepingThread.join(15000);
                    sleepingThread.join();
                    System.out.println(ThreadColor.ANSI_GREEN+"He's awake good, it terminate its process now i can execute my operations");

                } catch (InterruptedException e) {
                    System.out.println(ThreadColor.ANSI_GREEN + "I couldn't wait after all. I was interrupted");
                }
            }
        });
        joiningThread.start();
    }
}
