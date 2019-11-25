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
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import miscMechanics.User;

public class fe_R9 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E19,E20,statue1,statue2,statue3,statue4,statue5, enemy1, enemy2, background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private int degree;
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	
	private KeyPressedManager mover;
	public fe_R9(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Interactions iE19 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions iE20 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions istatue1 = new Interactions(interactionType.statue, 540,35);
		Interactions istatue2 = new Interactions(interactionType.statue, 315,105);
		Interactions istatue3 = new Interactions(interactionType.statue, 755,105);
		Interactions istatue4 = new Interactions(interactionType.statue, 635,175);
		Interactions istatue5 = new Interactions(interactionType.statue, 435,175);
		statue1 = istatue1.getImage();
		statue2 = istatue2.getImage();
		statue3 = istatue3.getImage();
		statue4 = istatue4.getImage();
		statue5 = istatue5.getImage();
		Enemy ienemy1 = new Enemy(2,2,2,2,120,150, ElementType.FIRE, enemyType.Dread);
		Enemy ienemy2 = new Enemy(2,2,2,2,125,400, ElementType.FIRE, enemyType.Samurai);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		background = new GImage("FE Ice Cave.png", 10,20);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		E19 = iE19.getImage();
		E20 = iE20.getImage();
		listOfInter.add(iE19);
		listOfInter.add(iE20);
		listOfInter.add(istatue1);
		listOfInter.add(istatue2);
		listOfInter.add(istatue3);
		listOfInter.add(istatue4);
		listOfInter.add(istatue5);
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		elements.add(background);
		elements.add(statue1);
		elements.add(statue2);
		elements.add(statue3);
		elements.add(statue4);
		elements.add(statue5);
		elements.add(E19);
		elements.add(E20);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
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
		
		program.drawOverlay(9, program.getFloorNum());
	}

	@Override
	public void hideContents() {
		t.stop();
		program.setUser(user);
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
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
		for (Enemy enem : listOfEnemies) {
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.Samurai) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 3) / 100;
				double moveY = (distY * 5) / 100;
				enem.getImage().movePolar(2, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.Dread) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 5) / 100;
				double moveY = (distY * 3) / 100;
				enem.getImage().movePolar(1, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
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
		if(userX <= E19.getX() && userY <= E19.getY() && userY2 >= E19.getY() - 30  && userX >= E19.getX() - 30) {
			user.setX(575);
			user.setY(120);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR7();
		}
		else if(userX <= E20.getX() && userY <= E20.getY() && userX2 >= E20.getX() && userY2 >= E20.getY())	{
			user.setX(150);
			user.setY(150);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR8();
		}
	}
}
