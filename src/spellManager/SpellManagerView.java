package spellManager;

import java.awt.Color;


import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SpellManagerView {
	
	private SpellManager controller;
	
	private JPanel spellManagerPanel;
	
	private BoxLayout boxLayout;
	private JProgressBar blueSpellBar;
	private JProgressBar redSpellBar;
	private JProgressBar greenSpellBar;
	private JProgressBar yellowSpellBar;
	private JProgressBar purpleSpellBar;
	
	
	public SpellManagerView(SpellManager controller) {
		this.controller = controller;
		
		spellManagerPanel = new JPanel();
		boxLayout = new BoxLayout(spellManagerPanel, BoxLayout.Y_AXIS);
		spellManagerPanel.setLayout(boxLayout);
		
		setupProgressBars();
	}

	private void setupProgressBars() {
		blueSpellBar = new JProgressBar();
		redSpellBar = new JProgressBar();
		greenSpellBar = new JProgressBar();
		yellowSpellBar = new JProgressBar();
		purpleSpellBar = new JProgressBar();
		
		blueSpellBar.setStringPainted(true);
		blueSpellBar.setForeground(Color.BLUE);
		redSpellBar.setStringPainted(true);
		redSpellBar.setForeground(Color.RED);
		greenSpellBar.setStringPainted(true);
		greenSpellBar.setForeground(Color.GREEN);
		yellowSpellBar.setStringPainted(true);
		yellowSpellBar.setForeground(Color.YELLOW);
		purpleSpellBar.setStringPainted(true);
		purpleSpellBar.setForeground(new Color(128,0,128));
		
		blueSpellBar.setMaximum(controller.getModel().getBlueSpell().getMaximumCharge());
		greenSpellBar.setMaximum(controller.getModel().getGreenSpell().getMaximumCharge());
		redSpellBar.setMaximum(controller.getModel().getRedSpell().getMaximumCharge());
		yellowSpellBar.setMaximum(controller.getModel().getYellowSpell().getMaximumCharge());
		purpleSpellBar.setMaximum(controller.getModel().getPurpleSpell().getMaximumCharge());
		
		spellManagerPanel.add(blueSpellBar);
		spellManagerPanel.add(greenSpellBar);
		spellManagerPanel.add(purpleSpellBar);
		spellManagerPanel.add(redSpellBar);
		spellManagerPanel.add(yellowSpellBar);
	}
	
	public JPanel getPanel() {return this.spellManagerPanel;}
	
	public void updateSpellBars() {
		blueSpellBar.setValue(controller.getModel().getBlueSpell().getCurrentCharge());
		greenSpellBar.setValue(controller.getModel().getGreenSpell().getCurrentCharge());
		redSpellBar.setValue(controller.getModel().getRedSpell().getCurrentCharge());
		purpleSpellBar.setValue(controller.getModel().getPurpleSpell().getCurrentCharge());
		yellowSpellBar.setValue(controller.getModel().getYellowSpell().getCurrentCharge());
	}
	

}
