package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;

import ChrisFloor.chris_R1;
import ChrisFloor.chris_R10;
import ChrisFloor.chris_R2;
import ChrisFloor.chris_R3;
import ChrisFloor.chris_R4;
import ChrisFloor.chris_R5;
import ChrisFloor.chris_R6;
import ChrisFloor.chris_R7;
import ChrisFloor.chris_R8;
import ChrisFloor.chris_R9;
import FyiahEmburemFloor.fe_R1;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import enemyInteraction.Enemy;
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
import osvaldoFloor.osvaldoFloor_bossRoomComplete;
import bombRoom.bombRoom_R1;
import removeLater.GraphicsApplication;
import removeLater.User;

public class MainApplication extends GraphicsApplication implements ActionListener{
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "main_menu_background.mp3" };
	
	////////////////ALMIGHT DEVELOPER MODE///////////////////////////
	public static final boolean DEVELOPER_MODE = true;
	/////////////////////////////////////////////////////////////////	
	
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
	private chris_R3 chris_R3;
	private chris_R4 chris_R4;
	private chris_R5 chris_R5;
	private chris_R6 chris_R6;
	private chris_R7 chris_R7;
	private chris_R8 chris_R8;
	private chris_R9 chris_R9;
	private chris_R10 chris_R10;
	private osvaldoFloor_R1 osvaldoFloor_R1;
	private osvaldoFloor_bossRoom osvaldoFloor_bossRoom;
	private osvaldoFloor_bossRoomComplete osvaldoFloor_bossRoomComplete;
	private bombRoom_R1 bombRoom_R1;
	private TitleScreenPane tittle; 
	private GameOverPane playerDied;
	private MenuPane menu;
	private MenuPane_LightsOff lightsoff;
	private CreditsPane creditsPane;
	private AudioPlayer audio;
	private fe_R1 feR1;
	public boolean bossRun = false;
	public int bombCounter = 180;
	
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
		public GLabel bombTimer;
		public GRect bombRect = new GRect(695, 37, 260, 30);
		public GRect weaponBox;
		public GRect weaponBoxOutline;
		public GRect emptySpace;
		public GRect bossHealthBackground;
		public GRect bossHealth;
		public GImage title;
		
		public boolean firstSwordCall = true;
		public boolean restartGame = true;
	
	private int count;
	private User user;
	public boolean comingFromBoss = false;
	public boolean bossDefeated = false;
	public boolean bombDeactivated = false;
	private int floorNum = 0;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		user = new User(5, 5, 1000, 1, 300, 300);
		System.out.println("Hello, world!");
		bombRect.setFilled(true);
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		lightsoff = new MenuPane_LightsOff(this);
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
		chris_R3 = new chris_R3(this);
		chris_R4 = new chris_R4(this);
		chris_R5 = new chris_R5(this);
		chris_R6 = new chris_R6(this);
		chris_R7 = new chris_R7(this);
		chris_R8 = new chris_R8(this);
		chris_R9 = new chris_R9(this);
		chris_R10 = new chris_R10(this);
		
		osvaldoFloor_R1 = new osvaldoFloor_R1(this);
		osvaldoFloor_bossRoom = new osvaldoFloor_bossRoom(this);
		osvaldoFloor_bossRoomComplete = new osvaldoFloor_bossRoomComplete(this);
		
		bombRoom_R1 = new bombRoom_R1(this);
		
		tittle = new TitleScreenPane(this);
		feR1 = new fe_R1(this);
		
		try {
			playerDied = new GameOverPane(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pausePane = new pausePane(this);
		QPane = new instructionsPane(this);
		
		user.setHasKey(true);
		//bossDefeated = true;
	
		switchToTest(); //change which screen you want to switch to

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
			chris_R4 = new chris_R4(this);
			chris_R5 = new chris_R5(this);
			chris_R6 = new chris_R6(this);
			chris_R7 = new chris_R7(this);
			chris_R8 = new chris_R8(this);
			chris_R9 = new chris_R9(this);
			chris_R10 = new chris_R10(this);
			feR1 = new fe_R1(this);
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
		if(bossRun) {
			user = new User(5, 5, 1000, 1, 300, 300);
			mapbase_R9 = new mapBase_R9(this);
			osvaldoFloor_bossRoom = new osvaldoFloor_bossRoom(this);
		}
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
	
	public void switchToChrisR3() {
		switchToScreen(chris_R3);
	}
	
	public void switchToChrisR4() {
		switchToScreen(chris_R4);
	}
	
	public void switchToChrisR5() {
		switchToScreen(chris_R5);
	}
	
	public void switchToChrisR6() {
		switchToScreen(chris_R6);
	}
	public void switchToChrisR7() {
		switchToScreen(chris_R7);
	}
	
	public void switchToChrisR8() {
		switchToScreen(chris_R8);
	}
	
	public void switchToChrisR9() {
		switchToScreen(chris_R9);
	}
	
	public void switchToChrisR10() {
		switchToScreen(chris_R10);
	}
	
	public void switchToOsvaldoR1() {
		switchToScreen(osvaldoFloor_R1);
	}
	
	public void switchToOsvaldoBoss() {
		switchToScreen(osvaldoFloor_bossRoom);
	}
	
	public void switchToOsvaldoBossComplete() {
		switchToScreen(osvaldoFloor_bossRoomComplete);
	}
	
	public void switchToBombRoomR1() {
		switchToScreen(bombRoom_R1);
	}
	
	public void switchToFeR1() {
		audio = AudioPlayer.getInstance();
		audio.playSoundWithOptions(MUSIC_FOLDER,"y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3",true);
		switchToScreen(feR1);
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
	
	public void bossOverlay(Enemy boss) {
		
		combatRefreshOverlay();
		
		double percentage = (double)boss.getEnemyStats().getHP_cur() / (double)boss.getEnemyStats().getHP_tot();
		bossHealthBackground = new GRect(325, 50, 750, 12);
		bossHealthBackground.setFilled(true);
		bossHealth = new GRect(330, 54, (double)(percentage * 750) - 10, 4);
		bossHealth.setColor(Color.red);
		bossHealth.setFilled(true);
		
		remove(bossHealthBackground);
		remove(bossHealth);
		add(bossHealthBackground);
		add(bossHealth);
		
	}
	
	public void bombOverlay() {
		
		if(bombDeactivated) {return;}
		
		combatRefreshOverlay();
		
		add(bombRect);
		
		bombTimer = new GLabel("TIME REMAINING:" + bombCounter, 700, 60);
		bombTimer.setColor(Color.black);
		add(bombTimer);
		
		bombTimer = new GLabel("TIME REMAINING:" + bombCounter, 700, 60);
		bombTimer.setFont("Arial-Bold-24");
		bombTimer.setColor(Color.red);

		add(bombTimer);
		
		if(bombCounter == 0) {
			switchToGameOver();
		}
		
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
	
	public void setBossRun(boolean run) {
		bossRun = run;
	}
	
	public boolean getBossRun() {
		return bossRun;
	}
	
	public void setBombCounter(int count) {
		bombCounter = count;
	}
	
	public int getBombCounter() {
		return bombCounter;
	}
	
	public boolean getBombDeactivated() {
		return bombDeactivated;
	}
	
	public void setBombDeactivated(boolean bomb) {
		this.bombDeactivated = bomb;
	}
	
}
