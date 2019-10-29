package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton play;
	private GButton highScore;
	private GButton credits;
	private GButton exit;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		play = new GButton("Play", 200, 200, 200, 200);
		highScore = new GButton("High Score", 500, 200, 200, 200);
		credits = new GButton("Credits", 800, 200, 200, 200);
		exit = new GButton("Exit", 1100, 200, 200, 200);
		play.setFillColor(Color.RED);
		highScore.setFillColor(Color.WHITE);
		credits.setFillColor(Color.WHITE);
		exit.setFillColor(Color.WHITE);
	}

	@Override
	public void showContents() {
		program.add(play);
		program.add(highScore);
		program.add(credits);
		program.add(exit);
	}

	@Override
	public void hideContents() {
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
