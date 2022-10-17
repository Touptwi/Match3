import game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class Menu extends JFrame {

    public Menu() {
        this.setTitle("Jewels Falls");
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Images/Jewels/Jewels Falls Icon.png")).getImage());
        this.setPreferredSize(new Dimension(400, 630));
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-200, this.getY()-300);

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {g.drawImage(ImageIO.read(ClassLoader.getSystemResource("Images/Menu Background Title.png")), 0, 0, null);
                } catch (IOException e) {e.fillInStackTrace();}
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));

        JButton playClassicButton = new JButton("Play Classic");
        playClassicButton.addActionListener(e -> this.setVisible(false));
        playClassicButton.addActionListener(e -> new Game(300));
        playClassicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(playClassicButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));

        JButton playEndlessButton = new JButton("Play Endless");
        playEndlessButton.addActionListener(e -> this.setVisible(false));
        playEndlessButton.addActionListener(e -> new Game());
        playEndlessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(playEndlessButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));

        //TODO: add volume manager

        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> this.dispose());
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exitButton);

        this.add(mainPanel);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}