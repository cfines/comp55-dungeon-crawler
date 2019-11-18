package starter;

import acm.graphics.GImage;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "nymano-skate-at-night-full-version.mp3" };

	private SomePane somePane, mapBase_R1;
	private MenuPane menu;
	private HighScorePane highScorePane;
	
	private GImage menuScreen;
	private int count;
	private GraphicsGame game;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		//somePane = new SomePane(this);
		mapBase_R1 = new SomePane(this);
		menu = new MenuPane(this);
		highScorePane = new HighScorePane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}
	
	public void switchToHighScorePane() {
		switchToScreen(highScorePane);
	}

	public void switchToSome() {
		playRandomSound();
		switchToScreen(somePane);
	}
	
	public void switchToR1() {
		switchToScreen(mapBase_R1);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
}
