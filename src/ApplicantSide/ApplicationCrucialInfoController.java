package ApplicantSide;

import javax.swing.*;
import java.awt.*;

public class ApplicationCrucialInfoController extends JFrame {
    public boolean signUpComplete;

    ApplicationCrucialInfoController() {
        super("Job Info Page");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ApplicationSignUpController.isClosed(new CloseWindow() {
            @Override
            public void buttonListner(boolean open) {
                setVisible(open);
            }
        });

        CrucialInfoFields fields = new CrucialInfoFields();

        add(fields, BorderLayout.CENTER);
    }
}
