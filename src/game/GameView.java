package game;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;

import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.*;

public class GameView extends JFrame {

    private Game controller;

    private JSplitPane MainPanel;
    private JPanel gridPanel;
    private JPanel spellsPanel;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    
    public GameView(Game controller, float volume) {
        this.controller = controller;
        setupWindow();
        try {
            //Load the music
        	
        	String parentPath = new File(System.getProperty("user.dir")).getParent();
        	URL url = new File(parentPath + "\\Resources\\Eric Skiff - A Night Of Dizzy Spells.wav").toURI().toURL();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            //Change the music Volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            clip.start();
            System.out.println("Let's play Music !");
        } catch (Exception e) {System.out.println("Can't play Music");}
    }

    
    //Getters 
    public JPanel getSpellsPanel() {return spellsPanel; }
    public JLabel getScoreLabel() { return scoreLabel; }
    public JLabel getTimerLabel() {return timerLabel;}

    
    
    private void setupWindow() {
        this.setTitle("Jewels Falls");
    //    this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Images/Jewels/Jewels Falls Icon.png")).getImage());
        this.setPreferredSize(new Dimension(875, 735));
        //Set the windows at the center of the screen
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-500, this.getY()-400);

        setupGrid();
        setupSpellsPanel();

        setupMainPanel();

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
	private void setupScorePanel() {

		scoreLabel = new JLabel();
        int score = this.controller.getModel().getScore();
        scoreLabel.setText("Score : " + score);
        System.out.println("Point " + scoreLabel.getX() + ","+ scoreLabel.getY());

	}
	
	private void setupTimerLabel() {
		timerLabel = new JLabel();
		int timer = this.controller.getModel().getClock();
		timerLabel.setText(Integer.toString(timer));
		
		timerLabel.setFont(new Font("Serif", Font.BOLD, 40));
		
	}

    private void setupMainPanel() {
        JSplitPane MainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        MainPanel.setLeftComponent(this.gridPanel);
        MainPanel.setRightComponent(this.spellsPanel);
        MainPanel.setDividerLocation(0.75);
        MainPanel.setDividerSize(0);
        
       
        
        this.MainPanel = MainPanel;
        this.add(this.MainPanel);
    }

    public void setupGrid() {
        this.gridPanel = new JPanel();
        this.gridPanel.add(this.controller.getModel().getGrid());
        int w = 0;
        int h = 0;
        
    }

    private void setupSpellsPanel(){
        this.spellsPanel = new JPanel();
        
        System.out.println("width : " + this.getPreferredSize().width);
        int preferredWidth = this.getPreferredSize().width - gridPanel.getPreferredSize().width;
        int preferredHeight = this.getPreferredSize().height;
        
        System.out.println("("+preferredWidth + "," + preferredHeight + ")");
        this.spellsPanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        this.spellsPanel.setMaximumSize(new Dimension(preferredWidth, preferredHeight));
        
        this.setupScorePanel();
        this.setupTimerLabel();
        
        this.spellsPanel.setLayout(new BoxLayout(spellsPanel, BoxLayout.Y_AXIS));
        
        
        this.spellsPanel.add(this.timerLabel);
        this.spellsPanel.add(Box.createVerticalStrut(50));
        this.spellsPanel.add(this.controller.getSpellManager().getView().getPanel());
        this.spellsPanel.add(Box.createVerticalStrut(50));
        this.spellsPanel.add(this.scoreLabel, BorderLayout.SOUTH);
    }
    
    
    public void updateScoreLabel() {

    	int score = this.controller.getModel().getScore();
    	this.scoreLabel.setText("Score : " + score);

    }
    
    public void updateTimerLabel() {
    	int timer = this.controller.getModel().getClock();
    	this.timerLabel.setText(Integer.toString(timer));
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	
    	// La grid se paint deja de son cote, pas besoin d'appeler GridView.paint
    	
    	g.setColor(Color.BLUE);
    	System.out.println(scoreLabel.getText());
    	Point absolutePosition = SwingUtilities.convertPoint(spellsPanel, 
    														 new Point(scoreLabel.getX(), scoreLabel.getY()),
    														 MainPanel);
    	//g.drawString(scoreLabel.getText(), absolutePosition.x , absolutePosition.y); 
    }
}
