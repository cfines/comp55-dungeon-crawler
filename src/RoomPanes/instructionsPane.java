package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import removeLater.GButton;

public class instructionsPane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GRect background;
	
	public instructionsPane(MainApplication app) {
		this.program = app;
		background = new GRect(700, 500, program.WINDOW_WIDTH / 2 - 350, program.WINDOW_HEIGHT / 2 - 250);
	}

	@Override
	public void showContents() {
		program.add(background);
	}

	@Override
	public void hideContents() {
		program.remove(background);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_TAB) {
			program.noLongerPaused();
		}
	}
	
}
