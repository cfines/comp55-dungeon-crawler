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


public class bombRoom_R6 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage rock1, hole1, EN, ES, EE, EW, background, userRep, userWeapon;
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
	private int degree = 0;
	private boolean move = true;

	private KeyPressedManager mover;

	public bombRoom_R6(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		Interactions oE2 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions oE3 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,260,150);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,850,450);

		listOfInter.add(oE2);
		listOfInter.add(oE3);
		listOfInter.add(irock1);
		listOfInter.add(ihole1);

		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		ES = oE2.getImage();
		EE = oE3.getImage();
		rock1 = irock1.getImage();
		hole1 = ihole1.getImage();

		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		elements.add(ES);
		elements.add(EE);
		elements.add(rock1);
		elements.add(hole1);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		decrementTimer();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
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
		program.drawOverlay(6, program.getFloorNum());
	}

	@Override
	public void hideContents() {
		t.stop();
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		for (int i = 0; i <= enemyImages.size() - 1; i++) {
			program.remove(enemyImages.get(i));
		}
		program.refreshOverlay();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
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
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX <= ES.getX() && userY <= ES.getY() && userY2 >= ES.getY() - 30  && userX >= ES.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToBombRoomBOMB2();
		}
		else if(userX <= EE.getX() && userY <= EE.getY() && userX2 >= EE.getX() && userY2 >= EE.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToBombRoomR1();
		}
	}
	
}
