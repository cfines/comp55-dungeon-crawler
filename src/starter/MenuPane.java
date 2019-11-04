package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public GImage menuScreen;
	public GButton play, highScore, credits, exit;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		menuScreen = new GImage("Main Menu (Lights on without koolaid).png", 0, 0);
		menuScreen.setSize(1155, 650);
		play = new GButton("Play", 50, WINDOW_HEIGHT - 75, 150, 50);
		highScore = new GButton("High Scores", 401, WINDOW_HEIGHT - 75, 150, 50);
		credits = new GButton("Credits", 751, WINDOW_HEIGHT - 75, 150, 50);
		exit = new GButton("Exit", 955, WINDOW_HEIGHT - 75, 150, 50);
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
	}
}
