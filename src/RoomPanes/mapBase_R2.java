package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R2 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage rock1,rock2 ,hole3,hole4,hole5,hole2, hole1, E2, E3, background, enemy1, enemy2,userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = true;
	
	private KeyPressedManager mover;
	
	public mapBase_R2(MainApplication app) {
		this.program = app;
		user = program.getUser();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		Interactions irock1 = new Interactions(interactionType.obstacle_concrete_rubble, 100,166);
		Interactions irock2 = new Interactions(interactionType.obstacle_concrete_rocks,900,150);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 500,91);
		Interactions ihole2 = new Interactions(interactionType.obstacle_hole, 775,400);
		Interactions ihole3 = new Interactions(interactionType.obstacle_hole, 500,166);
		Interactions ihole4 = new Interactions(interactionType.obstacle_hole, 500,241);
		Interactions ihole5 = new Interactions(interactionType.obstacle_hole,500,316);
		
		Interactions iE2 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE3 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,350,76, ElementType.EARTH, enemyType.EARTHSkull);
		Enemy ienemy2 = new Enemy(2,2,2,2,367,504, ElementType.WATER, enemyType.WATERSpooder);
		rock1 = irock1.getImage();
		hole2 = ihole2.getImage();
		hole1 = ihole1.getImage();
		hole3 = ihole3.getImage();
		rock2 = irock2.getImage();
		hole4 = ihole4.getImage();
		hole5 = ihole5.getImage();
		
		E2 = iE2.getImage();
		E3 = iE3.getImage();
		
		listOfInter.add(irock1);
		listOfInter.add(ihole1);
		listOfInter.add(ihole2);
		listOfInter.add(ihole3);
		listOfInter.add(irock2);
		listOfInter.add(iE2);
		listOfInter.add(iE3);
		listOfInter.add(ihole4);
		listOfInter.add(ihole5);
		
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(hole2);
		elements.add(hole3);
		elements.add(hole4);
		elements.add(hole5);
		elements.add(E2);
		elements.add(E3);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E2.getX() && userY >= E2.getY() && userX <= E2.getX() + 75 && userY <= E2.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToSome();
		}
		else if(userX2 >= E3.getX() && userY2 >= E3.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToR3();
		}
		
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
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
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(2, 1);
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
		if (obj == E2) {
			program.switchToSome();
			userRep.setLocation(1010,300);
		}
		else if(obj == E3) {
			program.switchToR3();
			userRep.setLocation(90,300);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}

	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
		for (Enemy enem : listOfEnemies) {
			enem.getImage().movePolar(5, degree);
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.EARTHSkull) {
				if(move) {
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 2) / 100;
					double moveY = (distY * 2) / 100;
					enem.getImage().move(-moveX, -moveY);
				}else {enem.getImage().move(0, 0);}
			}
			else if(enem.getEnemyType() == enemyType.WATERSpooder) {
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
}
