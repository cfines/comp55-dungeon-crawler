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

public class fe_R12 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage Duma,enemy1, enemy2, enemy3, enemy4,wall1,wall2,wall3,background,userRep, userWeapon;
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private Enemy iDuma = new Enemy (69,69,2,4,900,100, ElementType.FIRE, enemyType.Duma);
	private User user;
	private int degree;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private KeyPressedManager mover;
	private int timerCont = 0;
	private boolean move = true;
	
	public fe_R12(MainApplication app) 
	{
		this.program = app;
		user = program.getUser(); 
		background = new GImage("Final floor.png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		Duma = iDuma.getImage();
		
		Enemy ienemy1 = new Enemy(2,2,2,2,120,150, ElementType.FIRE, enemyType.Dread);
		Enemy ienemy2 = new Enemy(2,2,2,2,120,150, ElementType.FIRE, enemyType.Samurai);
		Enemy ienemy3 = new Enemy(2,2,2,2,120,150, ElementType.FIRE, enemyType.Deadass);
		Enemy ienemy4 = new Enemy(2,2,2,2,120,150, ElementType.FIRE, enemyType.FIREFish);
		
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
		enemy4 = ienemy4.getImage();
		
		Interactions iwall1 = new Interactions(interactionType.FinalWall, 540,35);
		Interactions iwall2 = new Interactions(interactionType.FinalWall, 315,105);
		Interactions iwall3 = new Interactions(interactionType.FinalWall, 755,105);
	
		wall1 = iwall1.getImage();
		wall2 = iwall2.getImage();
		wall3 = iwall3.getImage();
		
		listOfInter.add(iwall1);
		listOfInter.add(iwall2);
		listOfInter.add(iwall3);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy3);
		listOfEnemies.add(ienemy4);
		elements.add(background);
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		enemyImages.add(enemy3);
		enemyImages.add(enemy4);
		elements.add(wall1);
		elements.add(wall2);
		elements.add(wall3);
		elements.add(Duma);
		elements.add(userRep);
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(iDuma.getEnemyStats().getHP_cur() <= 0) {
			if(program.getBossRun()) {
				user.setX(150);
				user.setY(300);
				program.switchToTitleScreen(); //TODO change when next boss is implemented
				program.setBossRun(false);
				return;
			}
			t.stop();
			program.setBossDefeated(true);
			program.switchToFeR12Complete();
		}
		
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		userRep.setLocation(user.getX(), user.getY());
	}

	@Override
	public void showContents() {
		if(iDuma.getEnemyStats().getHP_cur() <= 0) {
			program.setBossDefeated(true);
			program.switchToPonikoDone();
		}
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(12, program.getFloorNum());	
		program.bossOverlay(iDuma);
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
	
	public boolean everyXSeconds(double x) {
		program.bossOverlay(iDuma);
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
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 3) / 100;
				enem.getImage().movePolar(2, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.Dread) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 3) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().movePolar(1, degree);
				
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.Deadass) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 1) / 100;
				double moveY = (distY * 1) / 100;
				enem.getImage().movePolar(3, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.FIREFish) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 5) / 100;
				double moveY = (distY * 5) / 100;
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
}
