package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import miscMechanics.GButton;

public class CreditsPane extends GraphicsPane{
	private MainApplication program;
	
	public static final int WINDOW_WIDTH = 1155, WINDOW_HEIGHT = 650;
	public GImage creditsImg;
	public GImage text;
	public GButton goBack;
	public GRect emptySpace;
	public AudioPlayer audio = AudioPlayer.getInstance();
	
	public CreditsPane(MainApplication app) 
	{
		/*
		 * super(); program = app; credits = new GImage("Credits.gif", 0,0); goBack =
		 * new GButton("Return", 1090,0, 150,50); //text = new
		 * GLabel("Credits\n\nProgrammers:\n-Chris Fines\n-Jordan Scharkey\n-Stan Yu\n-Alan Barragan\n\nWe do not own the music\nexcept for that main menu\nthing\nAlso most of the art is ours\nexcept for any obvious\nimported pngs or gifs"
		 * ,0,50); text.setColor(Color.white); emptySpace = new GRect(1155,650);
		 * emptySpace.setColor(Color.black); emptySpace.setFilled(true);
		 */
		
		this.program = app;
		creditsImg = new GImage("Credits.gif", 25,0);
		text = new GImage("Credits text.png", 10,0);
		creditsImg.setSize(WINDOW_WIDTH-50, WINDOW_HEIGHT);
		goBack = new GButton("Return", 1000,0, 150,50);
		AudioPlayer audio = AudioPlayer.getInstance();
		emptySpace = new GRect(1155,650);
		emptySpace.setColor(Color.black);
		emptySpace.setFilled(true);
		
	}
	
	@Override
	public void showContents() {
		program.add(emptySpace);
		program.add(creditsImg);
		program.add(goBack);
		program.add(text);
		audio.playSound("sounds","Patrick on a seahorse listening to fly me to the moon.mp3");
	}

	@Override
	public void hideContents() {
		program.remove(emptySpace);
		program.remove(creditsImg);
		program.remove(goBack);
		program.remove(text);
		audio.stopSound("sounds", "Patrick on a seahorse listening to fly me to the moon.mp3");
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
