package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R9 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E16, ENext, boss, background,userRep, userWeapon;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = true;

	private KeyPressedManager mover;
	
	public mapBase_R9(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions iE16 = new Interactions(interactionType.entry_door_SOUTH,575,505);
		Interactions iENext = new Interactions(interactionType.entry_stair,575,300);
		E16 = iE16.getImage();
		ENext = iENext.getImage();
		
		boss = new GImage("Kirb_BOSS.gif", 575,200);
		boss.setSize(100, 100);
		
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(iENext);
		listOfInter.add(iE16);
		
		elements.add(background);
		elements.add(E16);
		elements.add(boss);
		elements.add(ENext);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(9, 1);
	}

	@Override
	public void hideContents() {
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.refreshOverlay();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ENext) {
			program.switchToMenu();
		}
		else if(obj == E16) {
			userRep.setLocation(575,435);
			program.switchToR8();
		}
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
		mover.notReallyActionPerformed(e);
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= ENext.getX() && userY >= ENext.getY() && userX <= ENext.getX() + 75 && userY <= ENext.getY() + 75) {
			user.setX(575);
			user.setY(325);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToSome();
		}
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
			else if(enem.getEnemyType() == enemyType.WATERSkull) {
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
