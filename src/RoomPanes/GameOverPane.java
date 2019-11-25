package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import removeLater.GButton;


public class GameOverPane extends GraphicsPane implements ActionListener {
	private GImage gameOver;
	private MainApplication program;
	private GButton returnMenu;
	
	public GameOverPane(MainApplication app) throws IOException 
	{
		this.program = app;
		gameOver = new GImage ("Game Over Screen.png",0,0);
		gameOver.setSize(1150,650);
		returnMenu = new GButton("Return to main menu", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 150, 150, 50);
	}
	
	@Override
	public void showContents() {
		program.add(gameOver);
		program.add(returnMenu);
	}

	@Override
	public void hideContents() {
		program.remove(gameOver);
		program.remove(returnMenu);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == returnMenu) {
			program.setBossRun(false);
			program.switchToTitleScreen();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
