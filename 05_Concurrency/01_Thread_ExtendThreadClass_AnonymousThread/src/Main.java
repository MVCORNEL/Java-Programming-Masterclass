public class Main {
    public static void main(String[] args){
        System.out.println(ThreadColor.ANSI_RED + "Hello from main thread");
        // 1
        // CREATING A THREAD BY CREATING AN INSTANCE OF A NAMED CLASS
        // It is up to the system to schedule when the threads will run
        Thread anotherThread=new AnotherThread();
        anotherThread.start();
        // we are not allowed to start an instance of a thread more than once which is already running
        //anotherThread.start();
        //that will result in throwing IllegalThreadStateException

        // 2
        // CREATING A THREAD BY CREATING AN ANONYMOUS CLASS
        // it is very useful if you are ever going to run the code once in that situation
        //when we are using an anonymous class instead of a named class we have to run the thread immediately
        new Thread(){
            public void run(){
                System.out.println(ThreadColor.ANSI_BLUE + "Hello from the anonymous class");
            }
        }.start();
    }
}
