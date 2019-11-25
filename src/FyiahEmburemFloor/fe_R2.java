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

public class fe_R2 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, enemy2, tree1, tree2, tree3, tree4, tree5, E2, E3, background,userRep, userWeapon;
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
	
	public fe_R2(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(2,2,2,2,575,216, ElementType.FIRE, enemyType.Brigand);
		Enemy ienemy2 = new Enemy(2,2,2,2,775,300, ElementType.FIRE, enemyType.Samurai);
		Interactions iE2 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE3 = new Interactions(interactionType.entry_door_NORTH,575,30);
		Interactions itree1 = new Interactions (interactionType.tree,700,75);
		Interactions itree2 = new Interactions (interactionType.tree,525,450);
		Interactions itree3 = new Interactions (interactionType.tree,450,75);
		Interactions itree4 = new Interactions (interactionType.tree,375,150);
		Interactions itree5 = new Interactions (interactionType.tree,850,225);
		
		background = new GImage("FE Forest Clearing.png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif", 0, 0);
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		E2 = iE2.getImage();
		E3 = iE3.getImage();
		tree1 = itree1.getImage();
		tree2 = itree2.getImage();
		tree3 = itree3.getImage();
		tree4 = itree4.getImage();
		tree5 = itree5.getImage();
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		listOfInter.add(iE2);
		listOfInter.add(iE3);
		listOfInter.add(itree1);
		listOfInter.add(itree2);
		listOfInter.add(itree3);
		listOfInter.add(itree4);
		listOfInter.add(itree5);
		
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		
		elements.add(background);
		elements.add(E2);
		elements.add(E3);
		elements.add(tree1);
		elements.add(tree2);
		elements.add(tree3);
		elements.add(tree4);
		elements.add(tree5);
		elements.add(userRep);
		
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
		
		program.drawOverlay(2, program.getFloorNum());
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
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
				double moveX = (distX * 4) / 100;
				double moveY = (distY * 4) / 100;
				enem.getImage().movePolar(2, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.Brigand) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().movePolar(8, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
			}
		}
	
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		if(userX >= E2.getX() && userY >= E2.getY() && userX <= E2.getX() + 75 && userY <= E2.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR1();
		}
		if(userX >= E3.getX() && userY >= E3.getY() && userX <= E3.getX() + 90 && userY <= E3.getY() + 90) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR3();
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
}
