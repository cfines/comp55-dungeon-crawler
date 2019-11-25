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

public class fe_R8 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, E17, E18, wall1, wall2,wall3, wall4, enemy2, background,userRep,userWeapon;
	private int degree;
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private GImage badGuy = new GImage ("Dark Mage.gif",890,100);
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private KeyPressedManager mover;
	private boolean attack = false, hit = false;
	private Enemy magic = new Enemy(100,100,99999,1,0,0,ElementType.FIRE, enemyType.Nagic);
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private Enemy DarkMage = new Enemy(2,2,2,2,890,100, ElementType.FIRE, enemyType.DarkMage);
	
	public fe_R8(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		badGuy = DarkMage.getImage();
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		background = new GImage("FE Rock Room.png", 15,30);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		Enemy ienemy1 = new Enemy(10,10,2,2,900,400, ElementType.FIRE,enemyType.Dread);
		Enemy ienemy2 = new Enemy(10,10,2,2,890,350, ElementType.FIRE,enemyType.Deadass);
		Interactions iE17 = new Interactions(interactionType.entry_door_WEST,27,450);
		Interactions iE18 = new Interactions(interactionType.entry_door_WEST,27,125);
		
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		E17 = iE17.getImage();
		E18 = iE18.getImage();
		
		Interactions iwall1 = new Interactions(interactionType.RockWall, 30,300);
		Interactions iwall2 = new Interactions(interactionType.RockWall, 105,300);
		Interactions iwall3 = new Interactions(interactionType.RockWall, 180,300);
		Interactions iwall4 = new Interactions(interactionType.RockWall, 255,300);
		
		wall1 = iwall1.getImage();
		wall2 = iwall2.getImage();
		wall3 = iwall3.getImage();
		wall4 = iwall4.getImage();
		
		listOfInter.add(iE17);
		listOfInter.add(iE18);
		listOfInter.add(iwall1);
		listOfInter.add(iwall2);
		listOfInter.add(iwall3);
		listOfInter.add(iwall4);
		
		listOfEnemies.add(DarkMage);
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		
		elements.add(background);
		elements.add(E17);
		elements.add(E18);
		elements.add(wall1);
		elements.add(wall2);
		elements.add(wall3);
		elements.add(wall4);
		enemyImages.add(badGuy);
		enemyImages.add(enemy1);
		enemyImages.add(enemy2);
		
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
		program.drawOverlay(8, program.getFloorNum());
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
			if(DarkMage.getEnemyStats().getHP_cur() >0) 
			{	
				if(attack) {
					magic = new Enemy(100, 100, 2, 2, (int)DarkMage.getCoordX(), (int)DarkMage.getCoordY() + 75, ElementType.FIRE, enemyType.Nagic);
					listOfProjectiles.add(magic);
				}
				else {
					listOfProjectiles.remove(magic);
					program.remove(magic.getImage());
					hit = false;
				}
			}
		}
		for (Enemy enem : listOfEnemies) {
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.Dread) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 5) / 100;
				double moveY = (distY * 5) / 100;
				enem.getImage().movePolar(1, degree);
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.Deadass) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 1) / 100;
				double moveY = (distY * 1) / 100;
				enem.getImage().movePolar(1, degree);
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
	
	public void nextRoom() 
	{
		double userX = userRep.getX();
		double userY = userRep.getY();
		if(userX >= E17.getX() && userY >= E17.getY() && userX <= E17.getX() + 75 && userY <= E17.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR7();
		}
		else if(userX >= E18.getX() && userY >= E18.getY() && userX <= E18.getX() + 75 && userY <= E18.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR9();
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
			if(checkHitBack(listOfProjectiles.get(i), DarkMage.getImage())) { 
				DarkMage.getEnemyStats().setHP_cur(0);
				listOfProjectiles.remove(listOfProjectiles.get(i));
				program.remove(magic.getImage());
			}
		}
	}
}
