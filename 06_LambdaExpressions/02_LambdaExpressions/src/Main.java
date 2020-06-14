public class Main {
    public static void main(String[] args) {
        (new AnotherClass()).runAnonymous();
        (new AnotherClass()).runLambda();
    }
}
class AnotherClass {
    //Anonymous is treated as a class
    public void runAnonymous() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class name is :"+ getClass().getSimpleName());
            }
        }).start();
    }
    //Lambda is treated as a nested block
    public void runLambda() {
        new Thread(() -> System.out.println("Lambda class name is : "  + getClass().getSimpleName())).start();
    }
}