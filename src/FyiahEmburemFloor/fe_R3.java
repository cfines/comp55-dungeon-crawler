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

public class fe_R3 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, tree1, tree2, E4, E5, background,userRep, userWeapon;
	
	private int degree;
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private GImage badGuy = new GImage ("Dark Mage.gif",575,70);
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private KeyPressedManager mover;
	private boolean attack = false, hit = false;
	private Enemy magic = new Enemy(100,100,99999,1,0,0,ElementType.FIRE, enemyType.Nagic);
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private Enemy DarkMage = new Enemy(2,2,2,2,575,70, ElementType.FIRE, enemyType.DarkMage);
	
	public fe_R3(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(10,10,2,2,900,100, ElementType.FIRE, enemyType.Samurai);
		
		Interactions iE4 = new Interactions(interactionType.entry_door_SOUTH,575,535);
		Interactions iE5 = new Interactions(interactionType.entry_door_NORTH,575,30);
		Interactions itree1 = new Interactions(interactionType.treeFell,325,59);
		Interactions itree2 = new Interactions(interactionType.tree,525,200);
		
		badGuy = DarkMage.getImage();
		tree1 = itree1.getImage();
		tree2 = itree2.getImage();
		enemy1 = ienemy1.getImage();
		E4 = iE4.getImage();
		E5 = iE5.getImage();
		background = new GImage("FE Forest Clearing.png",15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(DarkMage);
		listOfInter.add(iE4);
		listOfInter.add(iE5);
		listOfInter.add(itree1);
		listOfInter.add(itree2);
		
		elements.add(background);
		
		elements.add(E4);
		elements.add(E5);
		elements.add(tree1);
		elements.add(tree2);
		enemyImages.add(badGuy);
		enemyImages.add(enemy1);
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
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
			attack = !attack;
			if(attack) {
				magic = new Enemy(100, 100, 2, 2, (int)DarkMage.getCoordX(), (int)DarkMage.getCoordY() + 75, ElementType.FIRE, enemyType.projectile);
				listOfProjectiles.add(magic);
			} else {
				listOfProjectiles.remove(magic);
				program.remove(magic.getImage());
				hit = false;
			}
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
			else if(enem.getEnemyType() == enemyType.DarkMage) {
				enem.getImage().movePolar(1, degree);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
			}
		if(listOfProjectiles.size() >= 1) {
			for(Enemy arr : listOfProjectiles) {
				arr.getEnemyStats().setCoordX(DarkMage.getCoordX());
				arr.getEnemyStats().setCoordY(DarkMage.getCoordY());
				
				if(checkHitBack(arr, userWeapon) && atkUp) { 
					hit = true; 
				}
				program.add(arr.getImage());
				
				if(hit) { 
					arr.getImage().move(0, -10);
				} 
				else { 	
					arr.getImage().move(0, 10); 
				}
			}
		}
	}
	
	public void deleteEnemy() {
		mover.setDeleteEnemy(false);
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(listOfEnemies.get(i).getEnemyType() == enemyType.Nagic) {
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
		double userY2 = userRep.getY() + 80;
		if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR2();
		}
		else if(userX <= E5.getX() && userY <= E5.getY() && userY2 >= E5.getY() - 30  && userX >= E5.getX() - 30) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR4();
		}
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
	
	public boolean checkHitBack(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
	
	public void userCombat() {
		for(int i = 0; i < listOfProjectiles.size(); i++) {
			if(checkHitBack(listOfProjectiles.get(i), DarkMage.getImage())) { 
				DarkMage.getEnemyStats().setHP_cur(0);
				listOfProjectiles.remove(listOfProjectiles.get(i));
				program.remove(magic.getImage());
			}
		}
	}
}
