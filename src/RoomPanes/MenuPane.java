package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import miscMechanics.GButton;

public class MenuPane extends GraphicsPane implements ActionListener{
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public GImage menuScreen;
	public GButton play, bossRush, credits, exit;
	public static final String MUSIC_FOLDER = "sounds";
	private AudioPlayer audio = AudioPlayer.getInstance();
	public Timer timer = new Timer(30, this);
	private int timerCont = 0;
	public boolean swapBack = false, move = false;

	public MenuPane(MainApplication app){
		super();
		program = app;
		menuScreen = new GImage("Main Menu (Lights on without koolaid).png", 0, 0);
		menuScreen.setSize(1155, 650);
		play = new GButton("Play", 200, WINDOW_HEIGHT - 75, 150, 50);
		bossRush = new GButton("BOSS RUSH", 387.5, WINDOW_HEIGHT - 75, 150, 50);
		bossRush.setFillColor(Color.red);
		credits = new GButton("Credits", 575, WINDOW_HEIGHT - 75, 150, 50);
		exit = new GButton("Exit", 762.5, WINDOW_HEIGHT - 75, 150, 50);
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void backgroundSwap(boolean swap) {
		if(!move) {
			audio.playSound(MUSIC_FOLDER, "flicker.mp3");
			menuScreen.setImage("Main Menu (Lights off).png");
			}
		else{
			audio.playSound(MUSIC_FOLDER, "flicker.mp3");
			menuScreen.setImage("Main Menu (Lights on without koolaid).png");
			}
		hideContents();
		showContents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		if(everyXSeconds(50)){
			move =! move;
		}
		if(move) {
			backgroundSwap(move);
			swapBack =! swapBack;
			move =! move;
			if(swapBack) {backgroundSwap(move);}
		}
	}

	@Override
	public void showContents() {
		timer.start();
		program.add(menuScreen);
		program.add(play);
		program.add(bossRush);
		program.add(credits);
		program.add(exit);
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.remove(menuScreen);
		program.remove(play);
		program.remove(bossRush);
		program.remove(credits);
		program.remove(exit);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == play) {
			audio.playSound(MUSIC_FOLDER, "menu_select.wav");
			program.setFloorNum(1);
			program.resetRooms();
			program.switchToSome();
		}
		else if(obj == exit) {
			audio.playSound(MUSIC_FOLDER, "menu_select.wav");
			System.exit(0);
		}
		else if(obj == credits) {
			audio.playSound(MUSIC_FOLDER, "menu_select.wav");
			program.switchToCreditsPane();
		}
		else if(obj == bossRush) {
			audio.playSound(MUSIC_FOLDER, "menu_select.wav");
			program.setBossRun(true);
			program.switchToR9();
		}
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		program.switchToLightsOff();
//	}
}
