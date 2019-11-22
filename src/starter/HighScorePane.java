package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class HighScorePane extends GraphicsPane {
	public MainApplication program;
	public File file;
	public int floorNum;
	public String userName;
	
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	
	public GImage hiScore;
	public GButton goBack;
	public GLabel highestScore;
	
	public HighScorePane(MainApplication app) {
		this.program = app;
		hiScore = new GImage("High Scores Image.png",0,0);
		hiScore.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		goBack = new GButton("Return", 1000,0, 150,50);
		file = new File("highest score.txt");
		
		try {
			PrintWriter output = new PrintWriter(file);
			output.println(userName);
			output.println(floorNum);
			output.close();
		}catch (FileNotFoundException ex) 
		{
			System.out.printf("ERROR: %s\n", ex);
		}
		
		try {
			Scanner input = new Scanner(file);
			int lastFloor = input.nextInt();
			String lastName = input.next();
			System.out.printf("Someone by the name of " + userName + " was last seen on Floor" + lastFloor);
			input.close();
		}catch (IOException ex) {
			System.err.println("ERROR puting stuff into file");
		}
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
