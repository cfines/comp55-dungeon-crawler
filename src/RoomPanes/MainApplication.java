package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;

import ChrisFloor.chris_R1;
import ChrisFloor.chris_R2;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import mapBase.SomePane;
import mapBase.mapBase_R2;
import mapBase.mapBase_R2TEST;
import mapBase.mapBase_R3;
import mapBase.mapBase_R4;
import mapBase.mapBase_R5;
import mapBase.mapBase_R6;
import mapBase.mapBase_R7;
import mapBase.mapBase_R8;
import mapBase.mapBase_R9;
import mapBase.mapBase_R9Complete;
import osvaldoFloor.osvaldoFloor_R1;
import osvaldoFloor.osvaldoFloor_bossRoom;
import removeLater.GraphicsApplication;
import removeLater.User;

public class MainApplication extends GraphicsApplication implements ActionListener{
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "main_menu_background.mp3" };

	private pausePane pausePane;
	private instructionsPane QPane;
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
	private mapBase_R9Complete mapbase_R9Complete;
	private chris_R1 chris_R1;
	private chris_R2 chris_R2;
	private osvaldoFloor_R1 osvaldoFloor_R1;
	private osvaldoFloor_bossRoom osvaldoFloor_bossRoom;
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
		public GImage weaponFire = new GImage("Fire Sword.gif", 0, WINDOW_HEIGHT - 100);
		public GImage weaponWater = new GImage("Water Sword.gif", 0, WINDOW_HEIGHT - 100);
		public GImage weaponEarth = new GImage("Earth Sword.gif", 0, WINDOW_HEIGHT - 100);
		public GImage portrait;
		public GImage keyImage = new GImage("gray_key.png", 120, WINDOW_HEIGHT - 85);
		public GLabel health;
		public GLabel levelLabel;
		public GLabel roomLabel;
		public GLabel tabForMenu;
		public GRect weaponBox;
		public GRect weaponBoxOutline;
		public GRect emptySpace;
		public GImage title;
		
		public boolean firstSwordCall = true;
		public boolean restartGame = true;
	
	private int count;
	private User user;
	public boolean comingFromBoss = false;
	public boolean bossDefeated = false;
	private int floorNum = 0;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		user = new User(5, 5, 1000, 1, 300, 300);
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
		mapbase_R9Complete = new mapBase_R9Complete(this);
		chris_R1 = new chris_R1(this);
		chris_R2 = new chris_R2(this);
		osvaldoFloor_R1 = new osvaldoFloor_R1(this);
		osvaldoFloor_bossRoom = new osvaldoFloor_bossRoom(this);
		tittle = new TitleScreenPane(this);
		try {
			playerDied = new GameOverPane(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pausePane = new pausePane(this);
		QPane = new instructionsPane(this);
		
		user.setHasKey(true);
		bossDefeated = true;
		
		switchToOsvaldoBoss(); //change which screen you want to switch to

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
		restartGame = true;
		switchToScreen(playerDied);
	}
	
	public void switchToHighScorePane() {
		switchToScreen(highScorePane);
	}
	
	public void switchToCreditsPane() {
		switchToScreen(creditsPane);
	}

	public void switchToSome() {
		
		if(restartGame || comingFromBoss) {
			user = new User(5, 5, 1000, 1, 300, 300);
			System.out.println("Hello, world! New game!");
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
			mapbase_R9Complete = new mapBase_R9Complete(this);
			chris_R1 = new chris_R1(this);
			chris_R2 = new chris_R2(this);
			tittle = new TitleScreenPane(this);
			bossDefeated = false;
			try {
				playerDied = new GameOverPane(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(comingFromBoss) {
				floorNum++;
				user.setHasKey(false);
				comingFromBoss = false;
				combatRefreshOverlay();
			} else {
				floorNum = 1;
				restartGame = false;
			}
		}
		
		
		
		switchToScreen(somePane);
//		audio = AudioPlayer.getInstance();
//		audio.playSoundWithOptions(MUSIC_FOLDER,"Corpse Party BCR (PSP) Chapter 1 Main Theme.mp3",true);
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
	
	public void switchToR9Complete() {
		switchToScreen(mapbase_R9Complete);
	}
	
	public void switchToChrisR1() {
		switchToScreen(chris_R1);
	}
	
	public void switchToChrisR2() {
		switchToScreen(chris_R2);
	}
	
	public void switchToOsvaldoR1() {
		switchToScreen(osvaldoFloor_R1);
	}
	
	public void switchToOsvaldoBoss() {
		switchToScreen(osvaldoFloor_bossRoom);
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
	
	public void switchToSpecificPane(GraphicsPane pane) {
		switchToScreen(pane);
	}
	
	public void pauseScreenSwitch() {
		switchToScreenWithoutRemove(pausePane);
	}
	
	public void QScreenSwitch() {
		switchToScreenWithoutRemove(QPane);
	}
	
	public void noLongerPaused() {
		returnFromPause();
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
		drawTabForMenu();
		drawKey();
	}
	
	public void refreshOverlay() 
	{
		remove(health);
		remove(levelLabel);
		remove(roomLabel);
	}
	
	public void combatRefreshOverlay() 
	{
		remove(health);
		remove(keyImage);
		drawHealth();
		drawKey();
	}
	
	public void drawSword()	{
		
		if(!firstSwordCall) { 
			remove(weaponFire); 
			remove(weaponWater); 
			remove(weaponEarth); 
		}
		firstSwordCall = false;
		
		if(user.getWeaponEquiped() == 0) {
			add(weaponFire);
		} else if (user.getWeaponEquiped() == 1) {
			add(weaponWater);
		} else {
			add(weaponEarth);
		}
	}
	
	public void drawPortrait() 
	{
		portrait = new GImage("User_Portrait.png", 0,7);
		add(portrait);
	}
	
	public void drawHealth() {
		health = new GLabel("HP: " + user.getUserStats().getHP_cur() + " / " + user.getUserStats().getHP_tot(), 76, 25);
		health.setFont("Arial-Bold-22");
		health.setColor(Color.green);
		add(health);
	}
	
	public void drawTabForMenu() {
		tabForMenu = new GLabel("Press [Q] for menu", 115, WINDOW_HEIGHT - 7);
		tabForMenu.setFont("Arial-Bold-22");
		tabForMenu.setColor(Color.green);
		add(tabForMenu);
	}
	
	public void drawLevelLabel(int floorNum) {
		levelLabel = new GLabel("CURRENT LEVEL: " + floorNum, 76, 45);
		levelLabel.setFont("Arial-Bold-22");
		levelLabel.setColor(Color.green);
		add(levelLabel);
	}
	
	public void drawRoomLabel(int roomNum) {
		roomLabel = new GLabel("CURRENT ROOM: " + roomNum, 76, 65);
		roomLabel.setFont("Arial-Bold-22");
		roomLabel.setColor(Color.green);
		add(roomLabel);
	}
	
	public void drawKey() {
		if(user.getHasKey()) {
			keyImage = new GImage("item_png_key.png", 117, WINDOW_HEIGHT - 80);
		} else {
			keyImage = new GImage("gray_key.png", 120, WINDOW_HEIGHT - 85);
		}
		add(keyImage);
	}
	
	public int getFloorNum() {
		return floorNum;
	}
	
	public void setComingFromBoss(boolean coming) {
		this.comingFromBoss = coming;
	}
	
	public boolean getBossDefeated() {
		return bossDefeated;
	}
	
	public void setBossDefeated(boolean bruh) {
		this.bossDefeated = bruh;
	}
	
}
