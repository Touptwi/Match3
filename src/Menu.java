import game.Game;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

import java.awt.*;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.net.URL;



public class Menu extends JFrame {

    private float volume = -25.0f;
    private Clip music;
    private FloatControl gainControl;

    public Menu() {
    	
    	String parentPath = new File(System.getProperty("user.dir")).getParent();
    	String iconPath = parentPath + "\\Images\\Jewels\\Jewels-Falls-Icon.ico";
    	System.out.println(iconPath);
    	
        this.setTitle("Jewels Falls");

//        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource(iconPath)).getImage());
        this.setPreferredSize(new Dimension(400, 600));

        //Set the windows at the center of the screen
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-200, this.getY()-300);

        try {
            //Load the music
        	
        	URL url = new File(parentPath + "\\Resources\\Joshua McLean - Mountain Trials.wav").toURI().toURL();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            this.music = AudioSystem.getClip();
            this.music.open(audioInputStream);
            this.music.loop(Clip.LOOP_CONTINUOUSLY);

            //Change the music Volume
            this.gainControl = (FloatControl) this.music.getControl(FloatControl.Type.MASTER_GAIN);
            this.gainControl.setValue(this.volume);

            this.music.start();
            System.out.println("Let's play Music !");
        } catch (Exception e) {System.out.println("Can't play Music");}

        //Set a background image
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

        //Add the Button to play with timer
        JButton playClassicButton = new JButton("Play Classic");
        playClassicButton.addActionListener(e -> this.setVisible(false));
        playClassicButton.addActionListener(e -> new Game(75, this.volume));
        playClassicButton.addActionListener(e -> this.music.stop());
        playClassicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(playClassicButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));

        //Add the Button to play in Endless mod
        JButton playEndlessButton = new JButton("Play Endless");
        playEndlessButton.addActionListener(e -> this.setVisible(false));
        playEndlessButton.addActionListener(e -> new Game(this.volume));
        playEndlessButton.addActionListener(e -> this.music.stop());
        playEndlessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(playEndlessButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));

        //Add Jlabel as feedback for volumeSlider
        JLabel volumeFeedback = new JLabel("Music: 50 %");
        volumeFeedback.setOpaque(true);
        volumeFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(volumeFeedback);

        //Add a Slider to control the music's volume
        JSlider volumeSlider = new JSlider(0);
        volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeSlider.addChangeListener(e -> volumeFeedback.setText("Music: "+volumeSlider.getValue()+" %"));
        volumeSlider.addChangeListener(e -> setVolume(-60.0f+50*(volumeSlider.getValue()/100.0f)));
        mainPanel.add(volumeSlider);

        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));

        //Add a Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> this.dispose());
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exitButton);

        this.add(mainPanel);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //method to correctly set the new volume
    private void setVolume(float newVolume) {
        if(newVolume<=-60.0f) {
            this.volume = this.gainControl.getMinimum();
            this.gainControl.setValue(this.gainControl.getMinimum());
        } else {
            this.volume= newVolume;
            this.gainControl.setValue(newVolume);
        }
    }
}