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
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R7 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage rock1, E12, E13, enemy1,rock2 ,enemy2, enemy3, background,userRep,userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = true;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private User user;
	private KeyPressedManager mover;
	private int degree;
	
	public mapBase_R7(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,300,200);
		Interactions irock2 = new Interactions(interactionType.obstacle_concrete_rubble,700,369);
		Interactions iE12 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE13 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,575,100,ElementType.EARTH, enemyType.EARTHSpooder);
		Enemy ienemy2 = new Enemy(2,2,2,2,575,250, ElementType.FIRE, enemyType.FIRESpider);
		Enemy ienemy3 = new Enemy(2,2,2,2,575,420, ElementType.WATER, enemyType.WATERDrawing);
		rock1 = irock1.getImage();
		rock2 = irock2.getImage();
		E12 = iE12.getImage();
		E13 = iE13.getImage();
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfEnemies.add(ienemy3);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy1);
		
		listOfInter.add(iE13);
		listOfInter.add(iE12);
		listOfInter.add(irock1);
		listOfInter.add(irock2);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(E12);
		elements.add(E13);
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
		program.drawOverlay(7, 1);
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
		if (obj == E12) {
			program.switchToR5();
			userRep.setLocation(1010,300);
		}
		else if(obj == E13) {
			program.switchToR8();
			userRep.setLocation(90,300);
		}
	}
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
		for (Enemy enem : listOfEnemies) {

			enem.getImage().movePolar(5, degree);
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.EARTHSpooder) {
				if(move) {
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 2) / 100;
					double moveY = (distY * 2) / 100;
					enem.getImage().move(-moveX, -moveY);
				}else {enem.getImage().move(0, 0);}
			}
			else if(enem.getEnemyType() == enemyType.WATERDrawing) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 4) / 100;
				double moveY = (distY * 4) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.FIRESpider) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 3) / 100;
				double moveY = (distY * 3) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E12.getX() && userY >= E12.getY() && userX <= E12.getX() + 75 && userY <= E12.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR5();
		}
		else if(userX2 >= E13.getX() && userY2 >= E13.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToR8();
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
		timerCont++;
		mover.updateWeaponLoc();
		enemyMovement();
		mover.userCombat();
		mover.enemyCombat();
		nextRoom();
		user.tick();
		mover.checkCollision();
		mover.knockBack();
		userRep.setLocation(user.getX(), user.getY());
	}
}
