package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import miscMechanics.GButton;

public class instructionsPane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GButton exit;
	private GImage instruction;
	
	public instructionsPane(MainApplication app) {
		this.program = app;
		instruction = new GImage ("Instructions.png",program.WINDOW_WIDTH / 2 - 350, program.WINDOW_HEIGHT / 2 - 260);
		instruction.setSize(600,550);
		
		exit = new GButton("X", program.WINDOW_WIDTH/2 + 300, program.WINDOW_HEIGHT/2 - 250, 50, 50);
		exit.setFillColor(Color.red);

	}

	@Override
	public void showContents() {
		
		program.add(exit);
		program.add(instruction);
	}

	@Override
	public void hideContents() {
		program.remove(instruction);
		program.remove(exit);
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
	
	public void mouseReleased(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == exit) {
			program.noLongerPaused();
		}
	}
	
}
