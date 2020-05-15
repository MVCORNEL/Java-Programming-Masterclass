import java.io.Serializable;

public class Carrot implements Serializable {
    private final String color;
    private long serialVersionUID = 1L;

    public Carrot(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
