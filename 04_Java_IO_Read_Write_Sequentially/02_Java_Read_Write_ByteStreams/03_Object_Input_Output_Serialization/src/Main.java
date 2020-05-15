//The process of translating a data structure or object into a format that can be stored and recreated is called SERIALIZATION
//In java we need to make the class SERIALIZABLE -> by implementing the SERIALIZABLE interface.
//That interface has no methods -> is just used to give JVM a heads up, that we may serialise the object to storage and to read it back at some point
//Now when we make a class serializable, it is strongly recommended that we declare a long field called serialVersionUID
//If we don't do so the compiler will give us a warning
//Different compiler implementations may calculate different default values and that can lead to problems down the road if we change the compiler we using
//So we have to set the serialVersionUID field ourselves -> field recommend to be private another classes shouldn't be able to use it.
//serialVersionUID= kind of a version number for the class

import java.io.*;
import java.util.HashSet;

public class Main {
    private final static HashSet<DummyClass> dummySet = new HashSet<>();

    public static void main(String[] args) {
        //READ OBJECT FROM A STREAM OF BYTES
        boolean eof = false;
        try (ObjectInputStream data = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src//dummy.dat")))) {

            while (!eof) {
                try {
                    DummyClass dummyClass = (DummyClass) data.readObject();
                    System.out.println(dummyClass.getID() + ":" + dummyClass.getDummyName() + " and carrot color is " + dummyClass.getCarrot().getColor());
                } catch (EOFException e) {
                    //loops end here because we reach the Enf of the file EOF
                    eof = true;
                }
            }
        } catch (InvalidClassException e) {
            //this block will be invoked if something goes wrong with the UID
            //quick example- if we write the data with our current UID and read it with a different one, for example the
            // standard one which the compiler will create
            System.out.println("Invalid class exception " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Io Excpetion" + e.getMessage());
        } catch (ClassNotFoundException e) {
            //this block will be invoked if in our pgram we dont have a classed "DummyClass" as the one we cast from the object
            System.out.println("Class wasn't found");
        }
    }
    //WRITE OBJECT AS BYTES STREAM
    static {
        dummySet.add(new DummyClass(1, "Kristof", "orange"));
        dummySet.add(new DummyClass(2, "Bobo", "yellow"));
        dummySet.add(new DummyClass(3, "Dummy", "red"));
        //ObjectOutPutStream instead of DataOutputStream
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src//dummy.dat")))) {
            for (DummyClass dummy : dummySet) {
                locFile.writeObject(dummy);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
