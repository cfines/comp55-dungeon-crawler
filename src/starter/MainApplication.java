package starter;

import java.awt.Color;
import java.awt.event.ActionListener;

import RoomPanes.MenuPane_LightsOff;
import RoomPanes.mapBase_R2;
import RoomPanes.mapBase_R2TEST;
import RoomPanes.mapBase_R3;
import RoomPanes.mapBase_R4;
import RoomPanes.mapBase_R5;
import RoomPanes.mapBase_R6;
import RoomPanes.mapBase_R7;
import RoomPanes.mapBase_R8;
import RoomPanes.mapBase_R9;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class MainApplication extends GraphicsApplication implements ActionListener{
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "main_menu_background.mp3" };

	private SomePane somePane; 
	private mapBase_R2 mapbase_R2; 
	private mapBase_R2TEST testPane;
	private mapBase_R3 mapbase_R3;
	private mapBase_R4 mapbase_R4;
	private mapBase_R5 mapbase_R5;
	private mapBase_R6 mapbase_R6;
	private mapBase_R7 mapbase_R7;
	private mapBase_R8 mapbase_R8;
	private mapBase_R9 mapbase_R9;
	private TitleScreenPane tittle;
	private GameOverPane playerDied;
	private MenuPane menu;
	private MenuPane_LightsOff lightsoff;
	private HighScorePane highScorePane;
	private CreditsPane creditsPane;
	private AudioPlayer audio;
	
	//GRAPHICS Overlay Stuff
		public GImage creditsImg;
		public GImage hiScore;
		public GImage text;
		public GImage weapon;
		public GImage portrait;
		public GLabel health;
		public GLabel levelLabel;
		public GLabel roomLabel;
		public GRect weaponBox;
		public GRect weaponBoxOutline;
		public GRect emptySpace;
		public GImage title;
		public boolean firstSwordCall = true;
	
	private int count;
	private User user;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		user = new User(5, 5, 1000, 1, 300, 300);
		//userRep = new GImage("Rogue_(Sample User).gif");
		//userRep.setSize(75, 75);
		System.out.println("Hello, world!");
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		lightsoff = new MenuPane_LightsOff(this);
		highScorePane = new HighScorePane(this);
		creditsPane = new CreditsPane(this);
		mapbase_R2 = new mapBase_R2(this);
		testPane = new mapBase_R2TEST(this);
		mapbase_R3 = new mapBase_R3(this);
		mapbase_R4 = new mapBase_R4(this);
		mapbase_R5 = new mapBase_R5(this);
		mapbase_R6 = new mapBase_R6(this);
		mapbase_R7 = new mapBase_R7(this);
		mapbase_R8 = new mapBase_R8(this);
		mapbase_R9 = new mapBase_R9(this);
		tittle = new TitleScreenPane(this);
		playerDied = new GameOverPane(this);
		switchToR8(); //change which screen you want to switch to

	}
	
	public void switchToTitleScreen() 
	{
		switchToScreen(tittle);
	}

	public void switchToMenu() {
		count++;
		switchToScreen(menu);
	}
	
	public void switchToGameOver() 
	{
		switchToScreen(playerDied);
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
	
	public void switchToTest() {
		switchToScreen(testPane);
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
	
	public void switchToLightsOff() {
		switchToScreen(lightsoff);
	}

	private void playRandomSoundForever() {
		audio = AudioPlayer.getInstance();
		audio.playSoundWithOptions(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length],true);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User Buser) {
		this.user = Buser;
	}
	
	/////////////////////////////////////////////
	
	public void drawOverlay(int roomNum, int floorNum) {
		drawHealth();
		drawLevelLabel(floorNum);
		drawRoomLabel(roomNum);
		drawPortrait();
		weaponBoxOutline = new GRect(0,WINDOW_HEIGHT-100, 110,110);
		weaponBoxOutline.setColor(Color.GRAY);
		weaponBoxOutline.setFilled(true);
		add(weaponBoxOutline);
		
		weaponBox = new GRect(5,WINDOW_HEIGHT-100,101,101);
		weaponBox.setColor(Color.WHITE);
		weaponBox.setFilled(true);
		add(weaponBox);
		drawSword();
	}
	
	public void refreshOverlay() 
	{
		remove(health);
		remove(levelLabel);
		remove(roomLabel);
	}
	
	public void drawSword()	{
		
		if(!firstSwordCall) { remove(weapon); }
		firstSwordCall = false;
		
		if(user.getWeaponEquiped() == 0) {
			weapon = new GImage("Fire Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		} else if (user.getWeaponEquiped() == 1) {
			weapon = new GImage("Water Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		} else {
			weapon = new GImage("Earth Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		}
	}
	
	public void drawPortrait() 
	{
		portrait = new GImage("User_Portrait.png", 0,20);
		portrait.setSize(75,75);
		add(portrait);
	}
	
	public void drawHealth() {
		health = new GLabel("HP: " + user.getUserStats().getHP_cur() + " / " + user.getUserStats().getHP_tot(), 76, 50);
		health.setFont("Arial-Bold-22");
		health.setColor(Color.red);
		add(health);
	}
	
	public void drawLevelLabel(int floorNum) {
		levelLabel = new GLabel("CURRENT LEVEL: " + floorNum, 76, 70);
		levelLabel.setFont("Arial-Bold-22");
		levelLabel.setColor(Color.red);
		add(levelLabel);
	}
	
	public void drawRoomLabel(int roomNum) {
		roomLabel = new GLabel("CURRENT ROOM: " + roomNum, 76, 90);
		roomLabel.setFont("Arial-Bold-22");
		roomLabel.setColor(Color.red);
		add(roomLabel);
	}
	
}
