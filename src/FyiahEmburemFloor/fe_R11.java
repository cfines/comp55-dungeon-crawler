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

public class fe_R11 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, E22, E23, background,userRep, userWeapon;
	private int degree;
	private Enemy Rinea = new Enemy(10,10,2,5,575,100, ElementType.FIRE, enemyType.Rinea);
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	private GImage burned = new GImage("Rinea.gif", 575,100);
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private boolean attack = false, hit = false;
	private Enemy fire = new Enemy(100,100,99999,1,0,0,ElementType.FIRE, enemyType.GuyFieri);
	private boolean unlocked = false;
	
	private KeyPressedManager mover;

	
	public fe_R11(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(50,50,2,3,575,100, ElementType.FIRE, enemyType.Berkut);
		Interactions iE23 = new Interactions(interactionType.entry_bossDoor,575,30);
		Interactions iE22 = new Interactions(interactionType.entry_door_SOUTH,575,535);
		E23 = iE23.getImage();
		E22 = iE22.getImage();
		background = new GImage("FE Room.png",15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		enemy1 = ienemy1.getImage();
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		burned = Rinea.getImage();
		
		listOfEnemies.add(Rinea);
		listOfEnemies.add(ienemy1);
		listOfInter.add(iE23);
		listOfInter.add(iE22);
		elements.add(background);
		elements.add(E23);
		elements.add(E22);
		enemyImages.add(burned);
		enemyImages.add(enemy1);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		enemyCombat();
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
		program.drawOverlay(11, program.getFloorNum());		
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
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userY2 = userRep.getY() + 80;
		if(userX >= E23.getX() && userY >= E23.getY() && userX <= E23.getX() + 85 && userY <= E23.getY() + 85) {
			if(!unlocked) {
				if(program.getUser().getHasKey()) {
					unlockProtocol();
				}
			}else if (program.getBossDefeated()) {
				user.setX(575);
				user.setY(410);
				userRep.setLocation(user.getX(), user.getY());
				program.switchToFeR12Complete();
			}
			else {
				user.setX(575);
				user.setY(410);
				userRep.setLocation(user.getX(), user.getY());
				program.switchToFeR12();
			}
		}
		else if(userX <= E22.getX() && userY <= E22.getY() && userY2 >= E22.getY() - 30  && userX >= E22.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR4();
		}
	}
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void enemyMovement() {
		if(everyXSeconds(40)) {
			move = !move;
			attack = !attack;
			if(Rinea.getEnemyStats().getHP_cur() >0) 
			{	
				if(attack) {
					fire = new Enemy(100, 100, 2, 2, (int)Rinea.getCoordX(), (int)Rinea.getCoordY() + 75, ElementType.FIRE, enemyType.GuyFieri);
					listOfProjectiles.add(fire);
				}
				else {
					listOfProjectiles.remove(fire);
					program.remove(fire.getImage());
					hit = false;
				}
			}
		}
		for (Enemy enem : listOfEnemies) {
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.Berkut) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().movePolar(2, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.Rinea) {
				enem.getImage().movePolar(2, degree);
				if(enem.getCoordX() > 700) {
					move = false;
				} else if (enem.getCoordX() < 400) {
					move = true;
				}
				if(move) { enem.getImage().move(8, 0); }
				else { enem.getImage().move(-8, 0); }
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
			}
		if(listOfProjectiles.size() >= 1) {
			for(Enemy arr : listOfProjectiles) {
				arr.getEnemyStats().setCoordX(Rinea.getCoordX());
				arr.getEnemyStats().setCoordY(Rinea.getCoordY());
				
				if(checkHitBack(arr, userWeapon) && atkUp) { 
					hit = true; 
				}
				program.add(arr.getImage());
				
				if(hit) { 
					arr.getImage().move(0, -20);
				} 
				else { 	
					arr.getImage().move(0, 20); 
				}
			}
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
	
	public void enemyCombat() {
		if(program.getUser().getInvincibility()) {return;}
		for(int i = 0; i < listOfProjectiles.size(); i++) {
			if(checkHitBack(listOfProjectiles.get(i), userRep)) { 
				int newHealth = program.getUser().getUserStats().getHP_cur() - 1;
				program.getUser().getUserStats().setHP_cur(newHealth);
				program.combatRefreshOverlay();
			}
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
			if(checkHitBack(listOfProjectiles.get(i), Rinea.getImage())) { 
				Rinea.getEnemyStats().setHP_cur(0);
				listOfProjectiles.remove(listOfProjectiles.get(i));
				program.remove(fire.getImage());
			}
		}
	}
	
	public void unlockProtocol() {
		user.setY(200);
		program.remove(E23);
		E23 = new GImage("entry_door_NORTH.png", 575, 28);
		program.add(E23);
		userRep.setLocation(user.getX(), user.getY());
		program.getUser().setHasKey(false);
		program.combatRefreshOverlay();
		unlocked = true;
	}
}
