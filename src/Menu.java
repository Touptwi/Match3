import game.Game;

import javax.swing.*;

import java.awt.*;

public class Menu extends JFrame {

    public Menu() {
        this.setTitle("Jewels Falls");
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Images/Jewels/Jewels Falls Icon.png")).getImage());
        this.setPreferredSize(new Dimension(400, 600));
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-200, this.getY()-250);

        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> this.setVisible(false));
        playButton.addActionListener(e -> new Game());
        this.add(playButton);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}