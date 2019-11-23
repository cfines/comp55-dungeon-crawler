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
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.interactionType;

public class mapBase_R6 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage rock1, E11, key1, background,userRep,userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private int degree;
	private User user;
	private Timer t = new Timer(30, this);
	
	private KeyPressedManager mover;

	public mapBase_R6(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,900,150);
		Interactions iE11 = new Interactions(interactionType.entry_door_NORTH,575,28);
		Interactions ikey1 = new Interactions(interactionType.item_gif_key,575,300);
		rock1 = irock1.getImage();
		E11 = iE11.getImage();
		key1 = ikey1.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(ikey1);
		listOfInter.add(iE11);
		listOfInter.add(irock1);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(E11);
		elements.add(key1);
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
		
		if(program.getUser().getHasKey()) {
			program.remove(key1);
		}
		
		program.drawOverlay(6, program.getFloorNum());
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
		if (obj == E11) {
			program.switchToR5();
			userRep.setLocation(575,48);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) { 
	mover.notReallyKeyReleased(e);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		enemyMovement();
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
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
		if(userX >= E11.getX() && userY >= E11.getY() && userX <= E11.getX() + 75 && userY <= E11.getY() + 75) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR5();
		}
	}
	
}
