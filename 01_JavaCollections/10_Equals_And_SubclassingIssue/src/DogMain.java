public class DogMain {
    public static void main(String[] args) {
        Labrador labrador=new Labrador("Rover");
        Dog dogLabrador=new Dog("Rover");

        System.out.println(dogLabrador.equals(labrador));
        System.out.println(labrador.equals(dogLabrador));

        //The subclass should not override the equals method from the dog class if it will do the rover will not be a Rover
        //Because Rover is a Dog, bur Dog is not a Rover.
        //-> Check the Labrador class, and the commented hashcode to understand
        //make the method equals() from the Dog final, so it can be overriden at that point


    }
}
