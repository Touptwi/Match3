import game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.net.URL;


public class Menu extends JFrame {

    public Menu() {
    	
    	String parentPath = new File(System.getProperty("user.dir")).getParent();
    	String iconPath = parentPath + "\\Images\\Jewels\\Jewels-Falls-Icon.ico";
    	System.out.println(iconPath);
    	
        this.setTitle("Jewels Falls");

       // this.setIconImage(new ImageIcon(ClassLoader.getSystemResource(iconPath)).getImage());
        this.setPreferredSize(new Dimension(400, 600));

        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-200, this.getY()-300);

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                
                try {
                	String parentPath = new File(System.getProperty("user.dir")).getParent();
                	System.out.println("parent path : "+ parentPath);
                	
                	//URL url = ClassLoader.getSystemResource(parentPath + "\\Resources\\Images\\Menu Background Title.png");
                	URL url = new File(parentPath + "\\Resources\\Images\\Menu Background Title.png").toURI().toURL();
                	g.drawImage(ImageIO.read(url), 0, 0, null);
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

        JLabel volumeFeedback = new JLabel("Music: 50 %");
        volumeFeedback.setOpaque(true);
        volumeFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(volumeFeedback);

        JSlider volumeSlider = new JSlider(0);
        volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeSlider.addChangeListener(e -> volumeFeedback.setText("Music: "+volumeSlider.getValue()+" %"));
        mainPanel.add(volumeSlider);

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