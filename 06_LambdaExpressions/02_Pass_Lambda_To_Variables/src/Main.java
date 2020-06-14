public class Main {
    public static void main(String[] args) {

        OnClickListener listenrObject = () -> System.out.println("Button Pressed");
        Button mButton = new Button();
        mButton.setOnClickListener(listenrObject);
        mButton.pressButton();
    }
}

interface OnClickListener {
    void onClick();
}

class Button {
    OnClickListener onClickListener;

    public Button() {
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void pressButton() {
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick();
    }
}



