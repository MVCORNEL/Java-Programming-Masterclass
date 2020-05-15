import java.io.Serializable;
//When we write a serializable object the entire field of the class need to serializable.
//in our case the carrot class need to be serialized

public class DummyClass implements Serializable {
    private final int ID;
    private final String dummyName;
    private final Carrot carrot;
    private long serialVersionUID = 1L;

    public DummyClass(int ID, String dummyName,String color) {
        this.ID = ID;
        this.dummyName = dummyName;
        this.carrot=new Carrot(color);
    }

    public int getID() {
        return ID;
    }

    public String getDummyName() {
        return dummyName;
    }

    public Carrot getCarrot() {
        return carrot;
    }
}
