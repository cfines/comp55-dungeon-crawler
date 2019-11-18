package starter;

import RoomPanes.mapBase_R2;
import RoomPanes.mapBase_R3;
import acm.graphics.GImage;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "nymano-skate-at-night-full-version.mp3" };

	private SomePane somePane; 
	private mapBase_R2 mapbase_R2; 
	private mapBase_R3 mapbase_R3;
	private MenuPane menu;
	private HighScorePane highScorePane;
	private CreditsPane creditsPane;
	
	private GImage menuScreen;
	private int count;
	private GraphicsGame game;

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
		//mapbase_R3 = new mapBase_R3(this);
		
		
		switchToMenu();
	}

	public void switchToMenu() {
		//playRandomSound();
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
		//playRandomSound();
		switchToScreen(somePane);
	}
	
	public void switchToR2() {
		switchToScreen(mapbase_R2);
	}
	
	public void switchToR3() {
		switchToScreen(mapbase_R3);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
}
