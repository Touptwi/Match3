import game.Game;

import javax.swing.*;

import java.awt.*;

public class Menu extends JFrame {

    public Menu() {
        this.setTitle("Jewels Falls");
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Images/Jewels/Jewels Falls Icon.png")).getImage());
        this.setPreferredSize(new Dimension(400, 600));
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-200, this.getY()-300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));

        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> this.setVisible(false));
        playButton.addActionListener(e -> new Game());
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(playButton);

        //TODO: add volume manager

        this.add(mainPanel);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}