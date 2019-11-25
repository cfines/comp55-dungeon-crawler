package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import miscMechanics.GButton;

public class pausePane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GButton pauseButton;
	private GButton resumeButton;
	private GLabel pauseLabel;
	private Color pink = new Color(255,20,147);
	
	///////DEVELOPER_MODE_LABELS_AND_BUTTONS/////////////////
	private GRect devBox = new GRect(0, 80, 490, 470);
	private GRect devBox2 = new GRect(915, 80, 235, 540);
	private ArrayList<GButton> buttonArr = new ArrayList<GButton>();
	private ArrayList<GButton> buttonGod = new ArrayList<GButton>();
	private ArrayList<GLabel> labelArr = new ArrayList<GLabel>();
	private GLabel baseFloor = new GLabel("BASE", 10 ,97);
	private GLabel osvaldoomFloor = new GLabel("OSVALDOOM", 10, 172);
	private GLabel shadowRealm = new GLabel("SHADOW_REALM", 10, 247);
	private GLabel lucinaDomain = new GLabel("LUCINA_IS_THE_ANTAGONIST_OF_PEPPA_PIG", 10, 322);
	private GLabel schtanlyParable = new GLabel("SCHTANLY_PARABLE", 10, 397);
	private GLabel bombFloor = new GLabel("BOMB_ROOMS", 10, 472);
	private GButton BF_R1 = new GButton("R1", 10, 100, 50, 50);
	private GButton BF_RKey = new GButton("KEY", 65, 100, 50, 50);
	private GButton BF_RBoss = new GButton("BOSS", 120, 100, 50, 50);
	private GButton OF_R1 = new GButton("R1", 10, 175, 50, 50);
	private GButton OF_RKey = new GButton("KEY", 65, 175, 50, 50);
	private GButton OF_RBoss = new GButton("BOSS", 120, 175, 50, 50);
	private GButton SR_R1 = new GButton("R1", 10, 250, 50, 50);
	private GButton SR_RKey = new GButton("KEY", 65, 250, 50, 50);
	private GButton SR_RBoss = new GButton("BOSS", 120, 250, 50, 50);
	private GButton LD_R1 = new GButton("R1", 10, 325, 50, 50);
	private GButton LD_RKey = new GButton("KEY", 65, 325, 50, 50);
	private GButton LD_RBoss = new GButton("BOSS", 120, 325, 50, 50);
	private GButton SP_R1 = new GButton("R1", 10, 400, 50, 50);
	private GButton SP_RKey = new GButton("KEY", 65, 400, 50, 50);
	private GButton SP_RBoss = new GButton("BOSS", 120, 400, 50, 50);
	private GButton BOMB_Bomb1 = new GButton("BOMB1", 10, 475, 50, 50);
	private GButton BOMB_Bomb2 = new GButton("BOMB2", 65, 475, 50, 50);
	private GButton BOMB_Bomb3 = new GButton("BOMB3", 120, 475, 50, 50);
	
	private GButton invincibility = new GButton("GOD_MODE", 925, 100, 215, 50); 
	private GButton resetHealth = new GButton("RESET_HEALTH", 925, 175, 215, 50);
	private GButton giveKey = new GButton("GIVE_KEY", 925, 250, 215, 50); 
	private GButton bossRespawn = new GButton("RESPAWN_BOSSES", 925, 325, 215, 50); 
	private GButton stopBombs = new GButton("DEACTIVATE_BOMBS", 925, 400, 215, 50);
	private GButton resetTimer = new GButton("RESET_BOMB_TIMER", 925, 475, 215, 50);
	private GButton roomReset = new GButton("RESET_ALL_FLOORS", 925, 550, 215, 50); 
	private GButton devMode;
	
	private boolean exit = false;
	
	public pausePane(MainApplication app){
		this.program = app;
		exit = false;
		pauseButton = new GButton("Quit", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 130, 150, 50);
		resumeButton = new GButton("Resume", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 190, 150, 50);
		pauseLabel = new GLabel("PAUSED", program.WINDOW_WIDTH / 2 - 135 ,85);
		pauseLabel.setColor(Color.red);
		pauseLabel.setFont("Arial-Bold-64");
		devMode = new GButton("ACTIVATE_DEVMODE", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 70, 150, 50);
		
		devBox.setColor(Color.lightGray);
		devBox.setFilled(true);
		devBox2.setColor(Color.lightGray);
		devBox2.setFilled(true);
		
		baseFloor.setColor(Color.white);
		osvaldoomFloor.setColor(Color.cyan);
		shadowRealm.setColor(Color.black);
		lucinaDomain.setColor(pink);
		schtanlyParable.setColor(Color.gray);
		bombFloor.setColor(Color.red);
		labelArr.add(baseFloor);
		labelArr.add(osvaldoomFloor);
		labelArr.add(shadowRealm);
		labelArr.add(lucinaDomain);
		labelArr.add(schtanlyParable);
		labelArr.add(bombFloor);
		
		for(int i = 0; i < labelArr.size(); i++) {
			labelArr.get(i).setFont("Arial-Bold-20");
		}
		
		buttonArr.add(BF_R1);
		buttonArr.add(BF_RKey);
		buttonArr.add(BF_RBoss);
		buttonArr.add(OF_R1);
		buttonArr.add(OF_RKey);
		buttonArr.add(OF_RBoss);
		buttonArr.add(SR_R1);
		buttonArr.add(SR_RKey);
		buttonArr.add(SR_RBoss);
		buttonArr.add(LD_R1);
		buttonArr.add(LD_RKey);
		buttonArr.add(LD_RBoss);
		buttonArr.add(SP_R1);
		buttonArr.add(SP_RKey);
		buttonArr.add(SP_RBoss);
		buttonArr.add(BOMB_Bomb1);
		buttonArr.add(BOMB_Bomb2);
		buttonArr.add(BOMB_Bomb3);
		
		buttonGod.add(invincibility);
		buttonGod.add(resetHealth);
		buttonGod.add(giveKey);
		buttonGod.add(bossRespawn);
		buttonGod.add(stopBombs);
		buttonGod.add(resetTimer);
		buttonGod.add(roomReset);
		
		refreshColors();
		
		for(int i = 0; i < buttonArr.size(); i++) {
			if(i == 0 || i == 1 || i == 2) { buttonArr.get(i).setFillColor(Color.white); }
			if(i == 3 || i == 4 || i == 5) { buttonArr.get(i).setFillColor(Color.cyan); }
			if(i == 6 || i == 7 || i == 8) { buttonArr.get(i).setFillColor(Color.black); buttonArr.get(i).setColor(Color.white); }
			if(i == 9 || i == 10 || i == 11) { buttonArr.get(i).setFillColor(pink); }
			if(i == 12 || i == 13 || i == 14) { buttonArr.get(i).setFillColor(Color.gray); }
			if(i == 15 || i == 16 || i == 17 || i == 18) { buttonArr.get(i).setFillColor(Color.red); }
		}
		
	}

	@Override
	public void showContents() {
		exit = false;
		program.add(pauseButton);
		program.add(pauseLabel);
		program.add(resumeButton);
		program.add(devMode);
		
		if(program.getDeveloperMode()) {
			program.add(devBox);
			program.add(devBox2);
			for(int i = 0; i < buttonArr.size(); i++) {
				program.add(buttonArr.get(i));
			}
			for(int i = 0; i < labelArr.size(); i++) {
				program.add(labelArr.get(i));
			}
			for(int i = 0; i < buttonGod.size(); i++) {
				program.add(buttonGod.get(i));
			}
		}
		
	}

	@Override
	public void hideContents() {
		program.remove(pauseButton);
		program.remove(pauseLabel);
		program.remove(resumeButton);
		program.remove(devMode);
		
		if(program.getDeveloperMode()) {
			program.remove(devBox);
			program.remove(devBox2);
			for(int i = 0; i < buttonArr.size(); i++) {
				program.remove(buttonArr.get(i));
			}
			for(int i = 0; i < labelArr.size(); i++) {
				program.remove(labelArr.get(i));
			}
			for(int i = 0; i < buttonGod.size(); i++) {
				program.remove(buttonGod.get(i));
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			program.noLongerPaused();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		for(int i = 0; i < buttonArr.size(); i++) {
			if(obj == buttonArr.get(i)) {
				if(i == 0) { program.switchToSome(); }			//BASE-R1
				if(i == 1) { program.switchToR6(); }			//BASE-KEY
				if(i == 2) { program.switchToR9(); }			//BASE-BOSS
				if(i == 3) { program.switchToBombRoomR1(); }		//DOOM-R1
				if(i == 4) { program.switchToBombRoomKEY(); }						//DOOM-KEY
				if(i == 5) { program.switchToOsvaldoBoss(); }	//DOOM-BOSS
				if(i == 6) { program.switchToChrisR1(); }			//SHAD-R1
				if(i == 7) { program.switchToChrisR7(); }			//SHAD-KEY
				if(i == 8) { program.switchToPoniko(); }			//SHAD-BOSS
				if(i == 9) { program.switchToFeR1(); }			//FIRE-R1
				if(i == 10) { program.switchToFeR10(); }			//FIRE-KEY
				if(i == 11) { program.switchToFeR12(); }			//FIRE-BOSS
				if(i == 12) { /*TODO add*/ }			//STAN-R1
				if(i == 13) { /*TODO add*/ }			//STAN-KEY
				if(i == 14) { program.switchToEarthBoss(); }			//STAN-BOSS
				if(i == 15) { program.switchToBombRoomBOMB1(); }			//BOMB-BOMB1
				if(i == 16) { program.switchToBombRoomBOMB2(); }			//BOMB-BOMB2
				if(i == 17) { program.switchToBombRoomBOMB3(); }			//BOMB-BOMB3
				refreshColors();
			}
		}
		
		for(int i = 0; i < buttonGod.size(); i++) {
			if(obj == buttonGod.get(i)) {
				if(i == 0) { program.getUser().setInvincibility(!program.getUser().getInvincibility()); }
				if(i == 1) { program.getUser().getUserStats().setHP_cur(program.getUser().getUserStats().getHP_tot()); }
				if(i == 2) { program.getUser().setHasKey(true); }
				if(i == 3) { program.resetBosses(); }
				if(i == 4) { program.setBombsDeactivated(true); }
				if(i == 5) { program.setBombCounter(90); }
				if(i == 6) { program.resetRooms(); program.switchToMenu(); }
				refreshColors();
			}
		}
		
		
		
		if (obj == pauseButton) {
			program.switchToMenu();
		} else if (obj == resumeButton) {
			removeColors(true);
			program.noLongerPaused();
		} else if (obj == devMode) {
			program.setDeveloperMode(!program.getDeveloperMode());
			refreshColors();
		}
		
	}
	
	public void refreshColors() {
		if(program.getUser().getHasKey()) {
			giveKey.setFillColor(Color.yellow);
			program.drawKey();
			program.remove(giveKey);
			program.add(giveKey);
		}
		
		if(program.getBombsDeactivated()) {
			stopBombs.setFillColor(Color.green);
		} else {
			stopBombs.setFillColor(Color.red);
		}
		
		if(program.getUser().getInvincibility()) {
			invincibility.setFillColor(Color.green);
		} else {
			invincibility.setFillColor(Color.red);
		}
		
		program.remove(stopBombs);
		program.add(stopBombs);
		program.remove(invincibility);
		program.add(invincibility);
		
		if(program.getDeveloperMode()) {
			program.remove(devMode);
			devMode.setFillColor(Color.green);
			program.add(devMode);
			showDevMenu();
		} else {
			program.remove(devMode);
			devMode.setFillColor(Color.red);
			program.add(devMode);
			removeColors(true);
			hideDevMenu();
		}
		
		
		
	}
	
	public void removeColors(boolean exit) {
		if(exit) {
			program.remove(invincibility);
			program.remove(giveKey);
			program.remove(stopBombs);
			exit = false;
		}
	}
	
	public void showDevMenu() {
		program.add(devBox);
		program.add(devBox2);
		for(int i = 0; i < buttonArr.size(); i++) {
			program.add(buttonArr.get(i));
		}
		for(int i = 0; i < labelArr.size(); i++) {
			program.add(labelArr.get(i));
		}
		for(int i = 0; i < buttonGod.size(); i++) {
			program.add(buttonGod.get(i));
		}
	}
	
	public void hideDevMenu() {
		program.remove(devBox);
		program.remove(devBox2);
		for(int i = 0; i < buttonArr.size(); i++) {
			program.remove(buttonArr.get(i));
		}
		for(int i = 0; i < labelArr.size(); i++) {
			program.remove(labelArr.get(i));
		}
		for(int i = 0; i < buttonGod.size(); i++) {
			program.remove(buttonGod.get(i));
		}
	}
	
}
