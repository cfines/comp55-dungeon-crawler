package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.Console;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R4 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background,userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private int degree;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	
	private KeyPressedManager mover;
	
	public mapBase_R4(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(2,2,2,2,575,216, ElementType.FIRE, enemyType.FIRESkull);
		Enemy ienemy2 = new Enemy(2,2,2,2,575,434, ElementType.WATER, enemyType.WATERBat);
		Enemy ienemy3 = new Enemy(2,2,2,2,500,420, ElementType.EARTH, enemyType.EARTHSkull);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,900,100); 
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,230,490);
		Interactions iE6 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE7 = new Interactions(interactionType.entry_door_SOUTH,575,505);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
		hole1 = ihole1.getImage();
		rock1 = irock1.getImage();
		E6 = iE6.getImage();
		E7 = iE7.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif", user.getX(), user.getY());
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		userRep.setSize(75, 75);
		
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfEnemies.add(ienemy3);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy1);
		
		listOfInter.add(iE7);
		listOfInter.add(iE6);
		listOfInter.add(ihole1);
		listOfInter.add(irock1);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(hole1);
		elements.add(rock1);
		elements.add(E6);
		elements.add(E7);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(4, 1);
	}

	@Override
	public void hideContents() {
		t.stop();
		program.setUser(user);
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.refreshOverlay();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E6) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR3();
		}
		else if(obj == E7) {
			user.setX(675);
			user.setY(300);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToR5();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		mover.notReallyKeyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) { 
	mover.notReallyKeyReleased(e);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mover.updateWeaponLoc();
		enemyMovement();
		mover.userCombat();
		mover.enemyCombat();
		nextRoom();
		user.tick();
		mover.checkCollision();
		mover.knockBack();
		userRep.setLocation(user.getX(), user.getY());
		mover.notReallyActionPerformed(e);
	}

	
	public void enemyMovement() {
		for (Enemy enem : listOfEnemies) {
			double distX = enem.getImage().getX() - userRep.getX();
			double distY = enem.getImage().getY() - userRep.getY();
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
			double moveX = (distX * 2) / 100;
			double moveY = (distY * 2) / 100;
			enem.getImage().move(-moveX, -moveY);
			enem.getImage().movePolar(4, degree);
			degree+=50;
			degree%=360;
			}
		}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userY2 = userRep.getY() + 80;
		if(userX >= E6.getX() && userY >= E6.getY() && userX <= E6.getX() + 75 && userY <= E6.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR3();
		}
		if(userX >= E7.getX() && userY <= E7.getY() - 50 && userY2 >= E7.getY()) {
			user.setX(575);
			user.setY(120);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR5();
		}
	}
	
}
