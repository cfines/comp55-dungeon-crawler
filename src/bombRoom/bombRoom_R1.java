package bombRoom;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.KeyPressedManager;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;


public class bombRoom_R1 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage rock1, rock2, hole1, EN, ES, EE, EW, background, userRep, userWeapon, bombIMG;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);
	private int decrementTimer = 0;
	private boolean unlocked = false;

	private KeyPressedManager mover;

	public bombRoom_R1(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		Interactions oE1 = new Interactions(interactionType.entry_bossDoor, 575,-3);
		Interactions oE2 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions oE3 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions oE4 = new Interactions(interactionType.entry_door_WEST,27,300);
		
		Enemy bomb1 = new Enemy(100,100,1,0,700,350, ElementType.FIRE, enemyType.bomb);

		listOfInter.add(oE1);
		listOfInter.add(oE2);
		listOfInter.add(oE3);
		listOfInter.add(oE4);
		
		listOfEnemies.add(bomb1);

		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		EN = oE1.getImage();
		ES = oE2.getImage();
		EE = oE3.getImage();
		EW = oE4.getImage();
		
		bombIMG = bomb1.getImage();

		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		elements.add(EN);
		elements.add(ES);
		elements.add(EE);
		elements.add(EW);
		elements.add(userRep);
		
		enemyImages.add(bombIMG);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		
		if(listOfEnemies.size() >= 1) {
			for(int i = 0; i < enemyImages.size(); i++) {
				if(listOfEnemies.get(i).getEnemyType() == enemyType.rip) {
					enemyImages.remove(i);
					listOfEnemies.remove(i);
				} else {
					program.add(enemyImages.get(i));
				}
			}
		}
		
		program.bombOverlay();
		program.drawOverlay(1, program.getFloorNum());
	}

	@Override
	public void hideContents() {
		t.stop();
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.refreshOverlay();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		decrementTimer();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}

	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userX + 80;
		double userY2 = userX + 80;
		//SOUTH DOOR
		if(userX <= ES.getX() && userY <= ES.getY() && userY2 >= ES.getY() - 30  && userX >= ES.getX() - 30) {
			System.out.println("south");
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToBombRoomR2();
			//EAST DOOR
		} else if(userX <= EE.getX() && userY <= EE.getY() && userX2 >= EE.getX() && userY2 >= EE.getY()) {
			System.out.println("east");
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToBombRoomR8();
			//WEST DOOR
		} else if(userX >= EW.getX() && userY >= EW.getY() && userX <= EW.getX() + 75 && userY <= EW.getY() + 75) {
			System.out.println("west");
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToBombRoomR6();
			//NORTH DOOR
		} else if(userX >= EN.getX() && userY >= EN.getY() && userX <= EN.getX() + 85 && userY <= EN.getY() + 85) {
			System.out.println("north");
			if(!unlocked) {
				if(program.getUser().getHasKey() && program.getBombsDeactivated()) {
					unlockProtocol();
				}
			} else {
				user.setX(575);
				user.setY(410);
				userRep.setLocation(user.getX(), user.getY());
				program.switchToBombRoomR10();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_Q)) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
	}
	
	public void decrementTimer() {
		decrementTimer++;
		if(decrementTimer == 40 && !program.getBombsDeactivated()) {
			decrementTimer = 0;
			program.setBombCounter(program.getBombCounter() - 1);
			program.bombOverlay();
		}
	}
	
	public void deleteEnemy() {
		mover.setDeleteEnemy(false);
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(listOfEnemies.get(i).getEnemyType() == enemyType.rip) {
				enemyImages.remove(i);
				listOfEnemies.remove(i);
			} else {
				program.add(enemyImages.get(i));
			}
		}
	}
	
	public void unlockProtocol() {
		user.setY(200);
		program.remove(EN);
		EN = new GImage("entry_door_NORTH.png", 575, 28);
		program.add(EN);
		userRep.setLocation(user.getX(), user.getY());
		program.getUser().setHasKey(false);
		program.combatRefreshOverlay();
		unlocked = true;
	}
	
}
