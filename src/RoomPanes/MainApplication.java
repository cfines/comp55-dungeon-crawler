/*Immortalize:*/
//Alan Barragan <3\\ 
//Christopher Fines no u \\
//Stanley Yu System.out("cya")\\
//Jordan Scharkey :D\\

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
import ChrisFloor.poniko;
import ChrisFloor.ponikoCOMPLETED;
import FyiahEmburemFloor.fe_R1;
import FyiahEmburemFloor.fe_R10;
import FyiahEmburemFloor.fe_R11;
import FyiahEmburemFloor.fe_R12;
import FyiahEmburemFloor.fe_R12Complete;
import FyiahEmburemFloor.fe_R2;
import FyiahEmburemFloor.fe_R3;
import FyiahEmburemFloor.fe_R4;
import FyiahEmburemFloor.fe_R5;
import FyiahEmburemFloor.fe_R6;
import FyiahEmburemFloor.fe_R7;
import FyiahEmburemFloor.fe_R8;
import FyiahEmburemFloor.fe_R9;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import bombRoom.bombRoom_BOMB1;
import bombRoom.bombRoom_BOMB2;
import bombRoom.bombRoom_BOMB3;
import bombRoom.bombRoom_KEY;
import bombRoom.bombRoom_R1;
import bombRoom.bombRoom_R2;
import bombRoom.bombRoom_R3;
import bombRoom.bombRoom_R6;
import bombRoom.bombRoom_R8;
import earthFloor.earth_boss;
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
import miscMechanics.GraphicsApplication;
import miscMechanics.User;
import bombRoom.osvaldoFloor_bossRoom;
import bombRoom.osvaldoFloor_bossRoomComplete;

public class MainApplication extends GraphicsApplication implements ActionListener{
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "main_menu_background.mp3" };
	
	////////////////ALMIGHT DEVELOPER MODE///////////////////////////
	public boolean DEVELOPER_MODE = false;
	/////////////////////////////////////////////////////////////////	
	
	private pausePane pausePane;
	private GameWinPane gameWin;
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
	private poniko Poniko;
	private ponikoCOMPLETED ponikoDone;
	private osvaldoFloor_bossRoom osvaldoFloor_bossRoom;
	private osvaldoFloor_bossRoomComplete osvaldoFloor_bossRoomComplete;
	private earth_boss earth_boss;
	private bombRoom_R1 bombRoom_R1;
	private bombRoom_R2 bombRoom_R2;
	private bombRoom_R3 bombRoom_R3;
	private bombRoom_R6 bombRoom_R6;
	private bombRoom_R8 bombRoom_R8;
	private bombRoom_BOMB1 bombRoom_BOMB1;
	private bombRoom_BOMB2 bombRoom_BOMB2;
	private bombRoom_BOMB3 bombRoom_BOMB3;
	private bombRoom_KEY bombRoom_KEY;
	private TitleScreenPane tittle; 
	private GameOverPane playerDied;
	private MenuPane menu;
	private CreditsPane creditsPane;
	private AudioPlayer audio = AudioPlayer.getInstance();
	private fe_R1 feR1;
	private fe_R2 feR2;
	private fe_R3 feR3;
	private fe_R4 feR4;
	private fe_R5 feR5;
	private fe_R6 feR6;
	private fe_R7 feR7;
	private fe_R8 feR8;
	private fe_R9 feR9;
	private fe_R10 feR10;
	private fe_R11 feR11;
	private fe_R12 feR12;
	private fe_R12Complete feR12C;
	public boolean bossRun = false;
	public int bombCounter = 90;
	public boolean wonGame = false;
	
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
		public GRect bombRect = new GRect(695, 37, 245, 50);
		public GRect bombRect2 = new GRect(700, 70, 75, 10);
		public GRect bombRect3= new GRect(780, 70, 75, 10);
		public GRect bombRect4 = new GRect(860, 70, 75, 10);
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
	public boolean bomb1Dead = false;
	public boolean bomb2Dead = false;
	public boolean bomb3Dead = false;
	public boolean bombsDeactivated = false;
	private int floorNum = 0;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		user = new User(20, 20, 1000, 1, 300, 300);
		System.out.println("Hello, world!");
		bombRect.setFilled(true);
		bombRect2.setColor(Color.red);
		bombRect3.setColor(Color.red);
		bombRect4.setColor(Color.red);
		gameWin = new GameWinPane(this);
		somePane = new SomePane(this);
		menu = new MenuPane(this);
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

		earth_boss = new earth_boss(this);
		chris_R6 = new chris_R6(this);
		chris_R7 = new chris_R7(this);
		chris_R8 = new chris_R8(this);
		chris_R9 = new chris_R9(this);
		chris_R10 = new chris_R10(this);
		Poniko = new poniko(this);
		ponikoDone = new ponikoCOMPLETED(this);

		osvaldoFloor_bossRoom = new osvaldoFloor_bossRoom(this);
		osvaldoFloor_bossRoomComplete = new osvaldoFloor_bossRoomComplete(this);
		
		earth_boss = new earth_boss(this);
		
		bombRoom_R1 = new bombRoom_R1(this);
		bombRoom_R2 = new bombRoom_R2(this);
		bombRoom_R3 = new bombRoom_R3(this);
		bombRoom_R6 = new bombRoom_R6(this);
		bombRoom_R8 = new bombRoom_R8(this);
		bombRoom_BOMB1 = new bombRoom_BOMB1(this);
		bombRoom_BOMB2 = new bombRoom_BOMB2(this);
		bombRoom_BOMB3 = new bombRoom_BOMB3(this);
		bombRoom_KEY = new bombRoom_KEY(this);
		
		tittle = new TitleScreenPane(this);
		feR1 = new fe_R1(this);
		feR2 = new fe_R2(this);
		feR3 = new fe_R3(this);
		feR4 = new fe_R4(this);
		feR5 = new fe_R5(this);
		feR6 = new fe_R6(this);
		feR7 = new fe_R7(this);
		feR8 = new fe_R8(this);
		feR9 = new fe_R9(this);
		feR10 = new fe_R10(this);
		feR11 = new fe_R11(this);
		feR12 = new fe_R12(this);
		feR12C = new fe_R12Complete(this);
		try {
			playerDied = new GameOverPane(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pausePane = new pausePane(this);
		QPane = new instructionsPane(this);
		
		//user.setHasKey(true);
		//bossDefeated = true;
	
		//switchToTitleScreen(); //change which screen you want to switch to
		switchToTitleScreen(); //change which screen you want to switch to
	}
	
	public void stopSound() {
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		audio.stopSound(MUSIC_FOLDER, "Jet Set Radio Future - Fly Like a Butterfly.mp3");
	}
	
	public void switchToTitleScreen() 
	{
		switchToScreen(tittle);
	}
	
	public void switchToGameWin() {
		audio.stopSound(MUSIC_FOLDER, "megaroachania.mp3");
		audio.stopSound(MUSIC_FOLDER,"Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "p3credits.mp3", true);
		switchToScreen(gameWin);
	}

	public void switchToMenu() {
		stopSound();
		count++;
		audio.stopSound(MUSIC_FOLDER, "Patrick on a seahorse listening to fly me to the moon.mp3");
		audio.playSound(MUSIC_FOLDER, "menu_select.wav");
		switchToScreen(menu);
	}
	
	public void switchToGameOver()  
	{
		//TODO: add game over music
		restartGame = true;
		switchToScreen(playerDied);
	}
	
	public void switchToCreditsPane() {
		audio.stopSound(MUSIC_FOLDER, "p3credits.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Patrick on a seahorse listening to fly me to the moon.mp3", true);
		switchToScreen(creditsPane);
	}

	public void switchToSome() {
		audio.playSoundWithOptions(MUSIC_FOLDER,"20 ray of hope.mp3",true);
		switchToScreen(somePane);
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
			audio.playSoundWithOptions(MUSIC_FOLDER,"20 ray of hope.mp3",true);
			user.getUserStats().setHP_cur(user.getUserStats().getHP_tot());
			user.setX(800);
			user.setY(WINDOW_HEIGHT/2 - 40);
			floorNum = 1;
			resetBosses();
		}
		switchToScreen(mapbase_R9);
	}
	
	public void switchToR9Complete() {
		switchToScreen(mapbase_R9Complete);
	}
	
	public void switchToChrisR1() {
		audio.stopSound(MUSIC_FOLDER, "Jet Set Radio Future - Fly Like a Butterfly.mp3");
		audio.playSound(MUSIC_FOLDER, "1up.wav");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Barracks Settlement.mp3", true);
		switchToScreen(chris_R1);
	}
	
	public void switchToChrisR2() {
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Barracks Settlement.mp3", true);
		switchToScreen(chris_R2);
	}
	
	public void switchToChrisR3() {
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Barracks Settlement.mp3", true);
		switchToScreen(chris_R3);
	}
	
	public void switchToChrisR4() {
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Dense Woods.mp3", true);
		switchToScreen(chris_R4);
	}
	
	public void switchToChrisR5() {
		switchToScreen(chris_R5);
	}
	
	public void switchToChrisR6() {
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3" );
		audio.playSoundWithOptions(MUSIC_FOLDER, "Dense Woods.mp3", true);
		switchToScreen(chris_R6);
	}
	public void switchToChrisR7() {
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Barracks Settlement.mp3", true);
		switchToScreen(chris_R7);
	}
	
	public void switchToChrisR8() {
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Dark Water.mp3", true);
		switchToScreen(chris_R8);
	}
	
	public void switchToChrisR9() {
		switchToScreen(chris_R9);
	}
	
	public void switchToChrisR10() {
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Dark Water.mp3", true);
		switchToScreen(chris_R10);
	}
	
	public void switchToPoniko() {
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		audio.stopSound(MUSIC_FOLDER, "Jet Set Radio Future - Fly Like a Butterfly.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3", true);
		switchToScreen(Poniko);
	}
	
	public void switchToPonikoDone() {
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "yume nikki title .mp3", true);
		switchToScreen(ponikoDone);
	}
	
	public void switchToOsvaldoBoss() {
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Jet Set Radio Future - Fly Like a Butterfly.mp3", true);
		switchToScreen(osvaldoFloor_bossRoom);
	}
	
	public void switchToOsvaldoBossComplete() {
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "megaroachania.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		switchToScreen(osvaldoFloor_bossRoomComplete);
	}
	
	public void switchToEarthBoss() {
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "megaroachania.mp3", true);
		switchToScreen(earth_boss);
	}
	
	public void switchToBombRoomR1() {
		if(DEVELOPER_MODE) {stopSound();}
		audio.playSound(MUSIC_FOLDER, "1up.wav");
		audio.playSoundWithOptions(MUSIC_FOLDER, "Jet Set Radio Future - Fly Like a Butterfly.mp3", true);
		switchToScreen(bombRoom_R1);
	}
	
	public void switchToBombRoomR2() {
		switchToScreen(bombRoom_R2);
	}
	
	public void switchToBombRoomR3() {
		switchToScreen(bombRoom_R3);
	}
	
	public void switchToBombRoomR6() {
		switchToScreen(bombRoom_R6);
	}
	
	public void switchToBombRoomR8() {
		switchToScreen(bombRoom_R8);
	}
	
	public void switchToBombRoomBOMB1() {
		if(DEVELOPER_MODE) {stopSound();}
		switchToScreen(bombRoom_BOMB1);
	}
	
	public void switchToBombRoomBOMB2() {
		if(DEVELOPER_MODE) {stopSound();}
		switchToScreen(bombRoom_BOMB2);
	}
	
	public void switchToBombRoomBOMB3() {
		if(DEVELOPER_MODE) {stopSound();}
		switchToScreen(bombRoom_BOMB3);
	}
	
	public void switchToBombRoomKEY() {
		switchToScreen(bombRoom_KEY);
	}
	
	public void switchToFeR1() {
		if(DEVELOPER_MODE) {stopSound();}
		audio.playSoundWithOptions(MUSIC_FOLDER,"y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3",true);
		switchToScreen(feR1);
	}
	
	public void switchToFeR2() 
	{
		switchToScreen(feR2);
	}
	
	public void switchToFeR3() 
	{
		switchToScreen(feR3);
	}
	
	public void switchToFeR4() 
	{
		audio.pauseSound(MUSIC_FOLDER,"y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER,"y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3",true);
		switchToScreen(feR4);
	}
	
	public void switchToFeR5() 
	{
		audio.pauseSound(MUSIC_FOLDER,"y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER ,"y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3",true);
		switchToScreen(feR5);
	}
	
	public void switchToFeR6() 
	{
		switchToScreen(feR6);
	}
	
	public void switchToFeR7() 
	{
		switchToScreen(feR7);
	}
	
	public void switchToFeR8() 
	{
		switchToScreen(feR8);
	}
	
	public void switchToFeR9() 
	{
		switchToScreen(feR9);
	}
	
	public void switchToFeR10() 
	{
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3", true);
		switchToScreen(feR10);
	}
	
	public void switchToFeR11() 
	{
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3", true);
		switchToScreen(feR11);
	}
	
	public void switchToFeR12() 
	{
		audio.stopSound(MUSIC_FOLDER, "Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER,"20 ray of hope.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER,"y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3",true);
		switchToScreen(feR12);
	}
	
	public void switchToFeR12Complete() 
	{
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - dumas_ordeal_fire_emblem_echoes_shadows_of_valentia_EDYJ7KkJx7s.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - milas_ordeal_fire_emblem_echoes_shadows_of_valentia_r6ATWTZJmac.mp3");
		audio.stopSound(MUSIC_FOLDER, "y2mate.com - twilight_of_the_gods_fire_emblem_echoes_shadows_of_valentia_S5YGxMjywSk.mp3");
		audio.stopSound(MUSIC_FOLDER, "Barracks Settlement.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dark Water.mp3");
		audio.stopSound(MUSIC_FOLDER, "Dense Woods.mp3");
		audio.stopSound(MUSIC_FOLDER, "Once-_There_Was_an_Explosion_.mp3");
		audio.stopSound(MUSIC_FOLDER, "yume nikki title .mp3");
		audio.stopSound(MUSIC_FOLDER, "20 ray of hope.mp3");
		audio.playSoundWithOptions(MUSIC_FOLDER,"Fire Emblem Echoes Shadows of Valentia (OST) - The Heritors of Arcadia (English Credits Theme).mp3",true);
		switchToScreen(feR12C);
	}

	private void playRandomSoundForever() {
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
		tabForMenu = new GLabel("Press [ESC] for pause / [Q] for objective", 115, WINDOW_HEIGHT - 7);
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
		
		if(bombsDeactivated) {return;}
		
		//combatRefreshOverlay();
		
		add(bombRect);
		add(bombRect2);
		add(bombRect3);
		add(bombRect4);
		
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
	
	public void setFloorNum(int num) {
		this.floorNum = num;
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
	
	public boolean getBombsDeactivated() {
		return bombsDeactivated;
	}
	
	public void setBombsDeactivated(boolean bomb) {
		this.bombsDeactivated = bomb;
	}
	
	public boolean getDeveloperMode() {
		return DEVELOPER_MODE;
	}
	
	public void setDeveloperMode(boolean dev) {
		this.DEVELOPER_MODE = dev;
	}
	
	public void checkForBombsDestroyed() {
		if(bomb1Dead && bomb2Dead && bomb3Dead) {
			bombsDeactivated = true;
		}
	}
	
	public boolean getBomb1() {
		return bomb1Dead;
	}
	
	public void setBomb1(boolean bomb) {
		this.bomb1Dead = bomb;
		if(bomb1Dead) {bombRect2.setFilled(true);}
		checkForBombsDestroyed();
	}
	
	public boolean getBomb2() {
		return bomb2Dead;
	}
	
	public void setBomb2(boolean bomb) {
		this.bomb2Dead = bomb;
		if(bomb2Dead) {bombRect3.setFilled(true);}
		checkForBombsDestroyed();
	}
	
	public boolean getBomb3() {
		return bomb3Dead;
	}
	
	public void setBomb3(boolean bomb) {
		this.bomb3Dead = bomb;
		if(bomb3Dead) {bombRect4.setFilled(true);}
		checkForBombsDestroyed();
	}
	
	public void resetBombs() {
		bombCounter = 90;
		bomb1Dead = false;
		bomb2Dead = false;
		bomb3Dead = false;
		bombsDeactivated = false;
	}
	
	public void resetRooms() {
		user.getUserStats().setHP_cur(user.getUserStats().getHP_tot());
		user.setX(300);
		user.setY(300);
		somePane = null;
		menu = null;
		creditsPane = null;
		mapbase_R2 = null;
		testPane = null;
		mapbase_R3 = null;
		mapbase_R4 = null;
		mapbase_R5 = null;
		mapbase_R6 = null;
		mapbase_R7 = null;
		mapbase_R8 = null;
		mapbase_R9 = null;
		mapbase_R9Complete = null;
		chris_R1 = null;
		chris_R2 = null;
		chris_R3 = null;
		chris_R4 = null;
		chris_R5 = null;
		chris_R6 = null;
		chris_R7 = null;
		chris_R8 = null;
		chris_R9 = null;
		chris_R10 = null;
		Poniko = null;
		ponikoDone = null;
		osvaldoFloor_bossRoom = null;
		osvaldoFloor_bossRoomComplete = null;
		bombRoom_R1 = null;
		bombRoom_R2 = null;
		bombRoom_R3 = null;
		bombRoom_R6 = null;
		bombRoom_R8 = null;
		bombRoom_BOMB1 = null;
		bombRoom_BOMB2 = null;
		bombRoom_BOMB3 = null;
		bombRoom_KEY = null;
		tittle = null;
		feR1 = null;
		feR2 = null;
		feR3 = null;
		feR4 = null;
		feR5 = null;
		feR6 = null;
		feR7 = null;
		feR8 = null;
		feR9 = null;
		feR10 = null;
		feR11 = null;
		feR12 = null;
		feR12C = null;
		earth_boss = null;
		///////////////////////////////
		somePane = new SomePane(this);
		menu = new MenuPane(this);
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
		Poniko = new poniko(this);
		ponikoDone = new ponikoCOMPLETED(this);
		osvaldoFloor_bossRoom = new osvaldoFloor_bossRoom(this);
		osvaldoFloor_bossRoomComplete = new osvaldoFloor_bossRoomComplete(this);
		bombRoom_R1 = new bombRoom_R1(this);
		bombRoom_R2 = new bombRoom_R2(this);
		bombRoom_R3 = new bombRoom_R3(this);
		bombRoom_R6 = new bombRoom_R6(this);
		bombRoom_R8 = new bombRoom_R8(this);
		bombRoom_BOMB1 = new bombRoom_BOMB1(this);
		bombRoom_BOMB2 = new bombRoom_BOMB2(this);
		bombRoom_BOMB3 = new bombRoom_BOMB3(this);
		bombRoom_KEY = new bombRoom_KEY(this);
		tittle = new TitleScreenPane(this);
		feR1 = new fe_R1(this);
		feR2 = new fe_R2(this);
		feR3 = new fe_R3(this);
		feR4 = new fe_R4(this);
		feR5 = new fe_R5(this);
		feR6 = new fe_R6(this);
		feR7 = new fe_R7(this);
		feR8 = new fe_R8(this);
		feR9 = new fe_R9(this);
		feR10 = new fe_R10(this);
		feR11 = new fe_R11(this);
		feR12 = new fe_R12(this);
		feR12C = new fe_R12Complete(this);
		earth_boss = new earth_boss(this);
		resetBombs();
	}
	
	public void resetBosses() {
		mapbase_R9 = null;
		osvaldoFloor_bossRoom = null;
		Poniko = null;
		feR12 = null;
		earth_boss = null;
		////////////////////////////////
		mapbase_R9 = new mapBase_R9(this);
		osvaldoFloor_bossRoom = new osvaldoFloor_bossRoom(this);
		Poniko = new poniko(this);
		feR12 = new fe_R12(this);
		earth_boss = new earth_boss(this);
	}
	
	public void setWonGame(boolean bruh) {
		this.wonGame = bruh;
	}
	
	public boolean getWonGame() {
		return wonGame;
	}
	
	
}
