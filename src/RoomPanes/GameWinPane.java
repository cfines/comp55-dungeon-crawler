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
import miscMechanics.GButton;


public class GameWinPane extends GraphicsPane implements ActionListener {
	private GImage gameWin;
	private MainApplication program;
	private GButton returnMenu;
	
	public GameWinPane(MainApplication app) throws IOException 
	{
		this.program = app;
		gameWin = new GImage ("Game Over Screen.png",0,0);
		gameWin.setSize(1150,650);
		returnMenu = new GButton("Congratualtions!", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 150, 150, 50);
		returnMenu.setFillColor(Color.green);
	}
	
	@Override
	public void showContents() {
		program.add(gameWin);
		program.add(returnMenu);
	}

	@Override
	public void hideContents() {
		program.remove(gameWin);
		program.remove(returnMenu);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == returnMenu) {
			program.switchToCreditsPane();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}