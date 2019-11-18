package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class HighScorePane extends GraphicsPane {
	public MainApplication program;
	
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	
	public GImage hiScore;
	public GButton goBack;
	
	public HighScorePane(MainApplication app) {
		this.program = app;
		hiScore = new GImage("High Scores.png",0,0);
		hiScore.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		goBack = new GButton("Return", 1000,0, 150,50);
	}
	
	@Override
	public void showContents() {
		program.add(hiScore);
		program.add(goBack);
	}

	@Override
	public void hideContents() {
		program.remove(hiScore);
		program.remove(goBack);
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == goBack) 
		{
			program.switchToMenu();
		}
	}
	
}
