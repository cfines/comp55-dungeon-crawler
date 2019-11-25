package FyiahEmburemFloor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.KeyPressedManager;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GRect;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;

public class fe_R7 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E13,E14,E15,E16,statue1,statue2,statue3,statue4,statue5,statue6,statue7,statue8,statue9, background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	
	
	private KeyPressedManager mover;

	
	public fe_R7(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Interactions iE13 = new Interactions(interactionType.entry_door_NORTH, 575,30);
		Interactions iE14 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions iE15 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions iE16 = new Interactions(interactionType.entry_door_WEST,27,300);
		
		E13 = iE13.getImage();
		E14 = iE14.getImage();
		E15 = iE15.getImage();
		E16 = iE16.getImage();
		
		Interactions istatue1 = new Interactions(interactionType.statue, 840,35);
		Interactions istatue2 = new Interactions(interactionType.statue, 775,105);
		Interactions istatue3 = new Interactions(interactionType.statue, 700,175);
		Interactions istatue4 = new Interactions(interactionType.statue, 635,235);
		Interactions istatue5 = new Interactions(interactionType.statue, 555,285);
		Interactions istatue6 = new Interactions(interactionType.statue, 495,335);
		Interactions istatue7 = new Interactions(interactionType.statue, 435,395);
		Interactions istatue8 = new Interactions(interactionType.statue, 375,435);
		Interactions istatue9 = new Interactions(interactionType.statue, 315,465);
		statue1 = istatue1.getImage();
		statue2 = istatue2.getImage();
		statue3 = istatue3.getImage();
		statue4 = istatue4.getImage();
		statue5 = istatue5.getImage();
		statue6 = istatue6.getImage();
		statue7 = istatue7.getImage();
		statue8 = istatue8.getImage();
		statue9 = istatue9.getImage();
		background = new GImage("FE Ice Cave.png", 10,20);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(istatue1);
		listOfInter.add(istatue2);
		listOfInter.add(istatue3);
		listOfInter.add(istatue4);
		listOfInter.add(istatue5);
		listOfInter.add(istatue6);
		listOfInter.add(istatue7);
		listOfInter.add(istatue8);
		listOfInter.add(istatue9);
		listOfInter.add(iE13);
		listOfInter.add(iE14);
		listOfInter.add(iE15);
		listOfInter.add(iE16);
		elements.add(background);
		elements.add(statue1);
		elements.add(statue2);
		elements.add(statue3);
		elements.add(statue4);
		elements.add(statue5);
		elements.add(statue6);
		elements.add(statue7);
		elements.add(statue8);
		elements.add(statue9);
		elements.add(E13);
		elements.add(E14);
		elements.add(E15);
		elements.add(E16);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());		
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E13.getX() && userY >= E13.getY() && userX <= E13.getX() + 90 && userY <= E13.getY() + 90) {	
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());	
			program.switchToFeR9();
		}
		else if(userX <= E15.getX() && userY <= E15.getY() && userX2 >= E15.getX() && userY2 >= E15.getY())	{
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR8();
		}
		else if(userX >= E16.getX() && userY >= E16.getY() && userX <= E16.getX() + 75 && userY <= E16.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR10();
		}
		else if(userX <= E14.getX() && userY <= E14.getY() && userY2 >= E14.getY() - 30  && userX >= E14.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR6();
		}
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
		program.drawOverlay(7, program.getFloorNum());		
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
}
