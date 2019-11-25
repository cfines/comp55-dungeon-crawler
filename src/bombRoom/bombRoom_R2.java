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


public class bombRoom_R2 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, EN, ES, background, userRep, userWeapon, rock1;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);
	private int decrementTimer = 0;
	private boolean move = true;
	private int degree = 0;

	private KeyPressedManager mover;

	public bombRoom_R2(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		Interactions oE1 = new Interactions(interactionType.entry_door_NORTH, 575,-3);
		Interactions oE2 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,600,325);
		Enemy ienemy1 = new Enemy(4,4,2,2,140,100,ElementType.FIRE, enemyType.FIREFish);
		Enemy ienemy2 = new Enemy(4,4,2,2,890,200,ElementType.WATER, enemyType.WATERFish);
		Enemy ienemy3 = new Enemy(4,4,2,2,405,400,ElementType.EARTH, enemyType.EARTHFish);

		listOfInter.add(oE1);
		listOfInter.add(oE2);
		listOfInter.add(irock1);
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy3);

		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		EN = oE1.getImage();
		ES = oE2.getImage();
		rock1 = irock1.getImage();
		
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();

		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		elements.add(EN);
		elements.add(ES);
		elements.add(rock1);
		elements.add(userRep);
		
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		enemyImages.add(enemy3);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		decrementTimer();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
		enemyMovement();
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
		program.drawOverlay(2, program.getFloorNum());
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
		if(userX >= EN.getX() && userY >= EN.getY() && userX <= EN.getX() + 85 && userY <= EN.getY() + 85) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToBombRoomR1();
		} else if(userX <= ES.getX() && userY <= ES.getY() && userY2 >= ES.getY() - 30  && userX >= ES.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToBombRoomR3();
		}

	}
	
	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
		for (Enemy enem : listOfEnemies) {
			if(enem.getEnemyType() == enemyType.FIREFish) {
				if(move) {
					degree+=5;
					degree%=360;
					enem.getImage().movePolar(2, degree);
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 5) / 100;
					double moveY = (distY * 5) / 100;
					enem.getImage().move(-moveX, -moveY);
				}else {enem.getImage().move(0, 0);}
			}
			else if(enem.getEnemyType() == enemyType.WATERFish) {
				degree+=2;
				degree%=360;
				enem.getImage().movePolar(6, degree);
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().move(-moveX, -moveY);
			}else if(enem.getEnemyType() == enemyType.EARTHFish) {
				degree+=7;
				degree%=360;
				enem.getImage().movePolar(3, degree);
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}

	public boolean everyXSeconds(double x) {
		return(decrementTimer %(x) == 0);
	}
	
}
