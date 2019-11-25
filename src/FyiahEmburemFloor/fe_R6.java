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

public class fe_R6 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3,enemy4, enemy5, E11, E12, wall1, wall2, background,userRep, userWeapon;
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
	
	public fe_R6(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(5,5,2,2,575,216, ElementType.FIRE, enemyType.Deadass);
		Enemy ienemy2 = new Enemy(5,5,2,2,775,300, ElementType.FIRE, enemyType.Deadass);
		Enemy ienemy3 = new Enemy(5,5,2,2,120,200, ElementType.FIRE, enemyType.Deadass);
		Enemy ienemy4 = new Enemy(5,5,2,2,120,400,ElementType.FIRE,enemyType.Deadass);
		Enemy ienemy5 = new Enemy(5,5,2,2,120,300,ElementType.FIRE,enemyType.Deadass);
		
		Interactions iE11 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions iE12 = new Interactions(interactionType.entry_door_NORTH,575,30);
		Interactions iwall1 = new Interactions(interactionType.RockWall,150,60);
		Interactions iwall2 = new Interactions(interactionType.RockWall,675,60);
		E11 = iE11.getImage();
		E12 = iE12.getImage();
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
		enemy4 = ienemy4.getImage();
		enemy5 = ienemy5.getImage();
		wall1 = iwall1.getImage();
		wall2 = iwall2.getImage();
		
		background = new GImage("FE Rock Room.png", 10,20);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy3);
		listOfEnemies.add(ienemy4);
		listOfEnemies.add(ienemy5);
		listOfInter.add(iE11);
		listOfInter.add(iE12);
		listOfInter.add(iwall1);
		listOfInter.add(iwall2);
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		enemyImages.add(enemy3);
		enemyImages.add(enemy4);
		enemyImages.add(enemy5);
		
		elements.add(background);
		elements.add(E11);
		elements.add(E12);
		elements.add(wall1);
		elements.add(wall2);
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
	
	public void nextRoom() 
	{
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX <= E11.getX() && userY <= E11.getY() && userX2 >= E11.getX() && userY2 >= E11.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR5();
		}
		else if(userX >= E12.getX() && userY >= E12.getY() && userX <= E12.getX() + 90 && userY <= E12.getY() + 90) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR7();
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
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.Deadass) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 1) / 100;
				double moveY = (distY * 1) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
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
		
		program.drawOverlay(6, program.getFloorNum());
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
