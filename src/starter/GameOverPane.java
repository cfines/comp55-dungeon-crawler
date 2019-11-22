package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GLabel;

public class GameOverPane extends GraphicsPane implements ActionListener {
	private GImage gameOver;
	private MainApplication program;
	private GButton returnMenu;
	private String userName;
	
	public GameOverPane(MainApplication app) 
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
			program.switchToTitleScreen();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
