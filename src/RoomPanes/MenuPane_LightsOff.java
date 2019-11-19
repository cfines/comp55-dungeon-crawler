package RoomPanes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;
import starter.GraphicsPane;
import starter.MainApplication;

public class MenuPane_LightsOff extends GraphicsPane /*implements ActionListener*/{
	private MainApplication program; // you will use program to get access to
	// all of the GraphicsProgram calls
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public GImage menuScreen;
	public GButton play, highScore, credits, exit;
	//public Timer timer = new Timer(5000, this);

	public MenuPane_LightsOff(MainApplication app) {
		super();
		program = app;
		menuScreen = new GImage("Main Menu (Lights on without koolaid).png", 0, 0);
		menuScreen.setSize(1155, 650);
		play = new GButton("Play", 200, WINDOW_HEIGHT - 75, 150, 50);
		highScore = new GButton("High Scores", 387.5, WINDOW_HEIGHT - 75, 150, 50);
		credits = new GButton("Credits", 575, WINDOW_HEIGHT - 75, 150, 50);
		exit = new GButton("Exit", 762.5, WINDOW_HEIGHT - 75, 150, 50);
		//timer.start();
	}

	@Override
	public void showContents() {
		program.add(menuScreen);
		program.add(play);
		program.add(highScore);
		program.add(credits);
		program.add(exit);
	}

	@Override
	public void hideContents() {
		program.remove(menuScreen);
		program.remove(play);
		program.remove(highScore);
		program.remove(credits);
		program.remove(exit);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) {
			program.switchToSome();
		}
		else if(obj == exit) {
			System.exit(0);
		}
		else if(obj == highScore) {
			program.switchToHighScorePane();
		}
		else if(obj == credits) {
			program.switchToCreditsPane();
		}
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		program.switchToMenu();
//		
//	}
}
