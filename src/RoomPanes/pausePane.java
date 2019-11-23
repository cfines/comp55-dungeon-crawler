package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import starter.GButton;
import starter.GraphicsPane;
import starter.MainApplication;

public class pausePane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	//private GraphicsPane prevPane;
	private GButton pauseButton;
	private GLabel pauseLabel;
	
	public pausePane(MainApplication app){
		this.program = app;
		pauseButton = new GButton("Return", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 130, 150, 50);
		pauseLabel = new GLabel("PAUSED", program.WINDOW_WIDTH / 2 - 135 ,85);
		pauseLabel.setColor(Color.red);
		pauseLabel.setFont("Arial-Bold-64");
	}

	@Override
	public void showContents() {
		program.add(pauseButton);
		program.add(pauseLabel);
		
	}

	@Override
	public void hideContents() {
		program.remove(pauseButton);
		program.remove(pauseLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			program.noLongerPaused();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == pauseButton) {
			program.noLongerPaused();
		}
	}
	
	
	
}
