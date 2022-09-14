import javax.swing.*;
import java.awt.*;

public class Presentation extends JFrame {

    private Controller controller;

    public Presentation(Controller controller) {
        this.controller = controller;
        setupWindow();
    }

    private void setupWindow() {
        this.setTitle("Jewel Falls");
        this.setPreferredSize(new Dimension(1000, 800));

        //TODO

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
