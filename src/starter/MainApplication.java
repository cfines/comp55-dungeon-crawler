package starter;

import java.awt.event.ActionListener;

import RoomPanes.mapBase_R2;
import RoomPanes.mapBase_R3;
import RoomPanes.mapBase_R4;
import RoomPanes.mapBase_R5;
import RoomPanes.mapBase_R6;
import RoomPanes.mapBase_R7;
import RoomPanes.mapBase_R8;
import RoomPanes.mapBase_R9;
import acm.graphics.GImage;

public class MainApplication extends GraphicsApplication implements ActionListener{
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "main_menu_background.mp3" };

	private SomePane somePane; 
	private mapBase_R2 mapbase_R2; 
	private mapBase_R3 mapbase_R3;
	private mapBase_R4 mapbase_R4;
	private mapBase_R5 mapbase_R5;
	private mapBase_R6 mapbase_R6;
	private mapBase_R7 mapbase_R7;
	private mapBase_R8 mapbase_R8;
	private mapBase_R9 mapbase_R9;
	private TitleScreenPane tittle;
	private MenuPane menu;
	private HighScorePane highScorePane;
	private CreditsPane creditsPane;
	private AudioPlayer audio;

	private int count;
	
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		highScorePane = new HighScorePane(this);
		creditsPane = new CreditsPane(this);
		mapbase_R2 = new mapBase_R2(this);
		mapbase_R3 = new mapBase_R3(this);
		mapbase_R4 = new mapBase_R4(this);
		mapbase_R5 = new mapBase_R5(this);
		mapbase_R6 = new mapBase_R6(this);
		mapbase_R7 = new mapBase_R7(this);
		mapbase_R8 = new mapBase_R8(this);
		mapbase_R9 = new mapBase_R9(this);
		tittle = new TitleScreenPane(this);
		switchToTitleScreen();
	}
	public void switchToTitleScreen() 
	{
		switchToScreen(tittle);
	}

	public void switchToMenu() {
		count++;
		switchToScreen(menu);
	}
	
	public void switchToHighScorePane() {
		switchToScreen(highScorePane);
	}
	
	public void switchToCreditsPane() {
		switchToScreen(creditsPane);
	}

	public void switchToSome() {
		switchToScreen(somePane);
		audio = AudioPlayer.getInstance();
		audio.playSoundWithOptions(MUSIC_FOLDER,"Corpse Party BCR (PSP) Chapter 1 Main Theme.mp3",true);
	}
	
	public void switchToR2() {
		switchToScreen(mapbase_R2);
	}
	
	public void switchToR3() {
		switchToScreen(mapbase_R3);
	}
	
	public void switchToR4() {
		switchToScreen(mapbase_R4);
	}
	
	public void switchToR5() {
		switchToScreen(mapbase_R5);
	}
	
	public void switchToR6() {
		switchToScreen(mapbase_R6);
	}
	
	public void switchToR7() {
		switchToScreen(mapbase_R7);
	}
	
	public void switchToR8() {
		switchToScreen(mapbase_R8);
	}
	
	public void switchToR9() {
		switchToScreen(mapbase_R9);
	}

	private void playRandomSoundForever() {
		audio = AudioPlayer.getInstance();
		audio.playSoundWithOptions(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length],true);
	}
}
