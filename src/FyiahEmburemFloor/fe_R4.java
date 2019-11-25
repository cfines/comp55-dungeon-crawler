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
import removeLater.User;

public class fe_R4 extends GraphicsPane implements ActionListener{
	private MainApplication program;
private GImage enemy1, enemy2 , enemy3 ,tree1, tree2, tree3, E6, E7, E8, background, userRep, userWeapon;
	
	private int degree;
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private KeyPressedManager mover;	
	
	public fe_R4(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		
		Interactions iE6 = new Interactions(interactionType.entry_door_SOUTH,575,535);
		Interactions iE7 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE8 = new Interactions(interactionType.entry_door_NORTH,575,30);
		E6 = iE6.getImage();
		E7 = iE7.getImage();
		E8 = iE8.getImage();
		Enemy ienemy1 = new Enemy (4,4,2,1,200,200, ElementType.FIRE, enemyType.Brigand);
		Enemy ienemy2 = new Enemy (4,4,2,1,300,90, ElementType.FIRE, enemyType.Brigand);
		Enemy ienemy3 = new Enemy (4,4,2,1,500,200, ElementType.FIRE, enemyType.Brigand);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
		Interactions itree1 = new Interactions(interactionType.treeFell,525,375);
		Interactions itree2 = new Interactions(interactionType.treeFell,725,69);
		Interactions itree3 = new Interactions(interactionType.treeFell,69,569);
		tree1 = itree1.getImage();
		tree2 = itree2.getImage();
		tree3 = itree3.getImage();
		
		background = new GImage("FE Forest Clearing.png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif", 0, 0);
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy3);
		listOfInter.add(itree1);
		listOfInter.add(itree2);
		listOfInter.add(itree3);
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		enemyImages.add(enemy3);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		elements.add(background);
		elements.add(E6);
		elements.add(E7);
		elements.add(E8);
		elements.add(tree1);
		elements.add(tree2);
		elements.add(tree3);
		elements.add(userRep);
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
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
			if(enem.getEnemyType() == enemyType.Brigand) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 3) / 50;
				double moveY = (distY * 2) / 100;
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

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		if(listOfEnemies.size() >= 1) {
			for(int i = 0; i < listOfEnemies.size(); i++) {
					if(listOfEnemies.get(i).getEnemyType() == enemyType.rip) {
						enemyImages.remove(i);
						listOfEnemies.remove(i);
					} else {
						program.add(enemyImages.get(i));
					}
				}
			}
		program.drawOverlay(3, program.getFloorNum());
		
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
	
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userY2 = userRep.getY() + 80;
		if(userX >= E6.getX() && userY >= E6.getY() && userX <= E6.getX() + 75 && userY <= E6.getY() + 75) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR3();
		}
		else if(userX <= E7.getX() && userY <= E7.getY() && userY2 >= E7.getY() + 75  && userX >= E7.getX() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR5();
		}
		else if(userX <= E8.getX() && userY <= E8.getY() && userY2 >= E8.getY() +90  && userX >= E8.getX() + 90) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR11();
		}
	}
	
	
	public boolean checkHitBack(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
}
