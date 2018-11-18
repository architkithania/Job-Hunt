package ApplicantSide;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class ApplicationSignUpController extends JFrame {

    public static CloseWindow closeListener;

    ApplicationSignUpController() {
        super("Sign Up Page");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        SignUpFields fields = new SignUpFields();

        fields.closeThis(new CloseWindow() {
            @Override
            public void buttonListner(boolean open) {
                setVisible(!open);
                closeListener.buttonListner(true);
            }
        });

        add(fields, BorderLayout.CENTER);
    }

    public static void isClosed(CloseWindow emitter) {
        closeListener = emitter;
    }
}