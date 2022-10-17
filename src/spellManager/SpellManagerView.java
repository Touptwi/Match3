package spellManager;

import java.awt.Color;
import java.awt.Graphics;

public class SpellManagerView {
	
	
	public SpellManagerView() {
		
	}
	
	public void paint(Graphics g, SpellManager spellManager) {
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, spellManager.getWidth(), spellManager.getHeight());
	}
}
