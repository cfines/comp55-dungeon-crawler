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
	private Color color = new Color(213, 196, 161);
	
	public instructionsPane(MainApplication app) {
		this.program = app;
		background = new GRect(program.WINDOW_WIDTH / 2 - 350, program.WINDOW_HEIGHT / 2 - 250, 700, 500);
		background.setColor(color);
		background.setFilled(true);
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
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			program.noLongerPaused();
		}
	}
	
}
