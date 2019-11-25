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

public class fe_R5 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E9, E10, wall1,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9,wall10,wall11,wall12,background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GImage badGuy1 = new GImage ("Dark Mage.gif",575,100);
	private GRect voidSpace;
	private boolean attack = false, hit = false;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private int degree;
	private int timerCont = 0;
	private Enemy magic1 = new Enemy(100,100,99999,1,0,0,ElementType.FIRE, enemyType.Nagic);
	private Enemy DarkMage1 = new Enemy(2,2,2,2,575,100, ElementType.FIRE, enemyType.DarkMage);
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	private KeyPressedManager mover;
	
	public fe_R5(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		background = new GImage("FE Rock Room.png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif", 0, 0);
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		Interactions iE9 = new Interactions(interactionType.entry_door_WEST,27,300);
		listOfInter.add(iE9);
		Interactions iE10 = new Interactions(interactionType.entry_door_EAST,1050,300);
		E9 = iE9.getImage();
		E10 = iE10.getImage();
		listOfInter.add(iE10);
		Interactions iwall1 = new Interactions(interactionType.RockWall, 120,200);
		Interactions iwall2 = new Interactions(interactionType.RockWall, 197,200);
		Interactions iwall3 = new Interactions(interactionType.RockWall, 274,200);
		Interactions iwall4 = new Interactions(interactionType.RockWall, 351,200);
		Interactions iwall5 = new Interactions(interactionType.RockWall, 428,200);
		Interactions iwall6 = new Interactions(interactionType.RockWall, 659,200);
		Interactions iwall7 = new Interactions(interactionType.RockWall, 736,200); 
		Interactions iwall8 = new Interactions(interactionType.RockWall, 813,200);
		Interactions iwall9 = new Interactions(interactionType.RockWall, 890,200);
		Interactions iwall10 = new Interactions(interactionType.RockWall, 503,200);
		Interactions iwall11 = new Interactions(interactionType.RockWall, 578,200);
		Interactions iwall12 = new Interactions(interactionType.RockWall, 967,200);
		
		elements.add(background);
		wall1 = iwall1.getImage();
		wall2 = iwall2.getImage();
		wall3 = iwall3.getImage();
		wall4 = iwall4.getImage();
		wall5 = iwall5.getImage();
		wall6 = iwall6.getImage();
		wall7 = iwall7.getImage();
		wall8 = iwall8.getImage();
		wall9 = iwall9.getImage();
		wall10 = iwall10.getImage();
		wall11 = iwall11.getImage();
		wall12 = iwall12.getImage();
		
		badGuy1 = DarkMage1.getImage();
		listOfInter.add(iwall1);
		listOfInter.add(iwall2);
		listOfInter.add(iwall3);
		listOfInter.add(iwall4);
		listOfInter.add(iwall5);
		listOfInter.add(iwall6);
		listOfInter.add(iwall7);
		listOfInter.add(iwall8);
		listOfInter.add(iwall9);
		listOfInter.add(iwall10);
		listOfInter.add(iwall11);
		listOfInter.add(iwall12);
		listOfEnemies.add(DarkMage1);
		elements.add(background);
		elements.add(wall1);
		elements.add(wall2);
		elements.add(wall3);
		elements.add(wall4);
		elements.add(wall5);
		elements.add(wall6);
		elements.add(wall7);
		elements.add(wall8);
		elements.add(wall9);
		elements.add(wall10);
		elements.add(wall11);
		elements.add(wall12);
		enemyImages.add(badGuy1);
		elements.add(E9);
		elements.add(E10);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		enemyCombat();
		timerCont++;
		enemyMovement();
		nextRoom();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
		userRep.setLocation(user.getX(), user.getY());
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
	
	public void userCombat() {
		for(int i = 0; i < listOfProjectiles.size(); i++) {
			if(checkHitBack(listOfProjectiles.get(i), DarkMage1.getImage())) { 
				DarkMage1.getEnemyStats().setHP_cur(0);
				listOfProjectiles.remove(listOfProjectiles.get(i));
				program.remove(magic1.getImage());
			}
		}
	}
	
	public void enemyMovement() {
		if(everyXSeconds(30)) {
			move = !move;
			attack = !attack;
			if(DarkMage1.getEnemyStats().getHP_cur() >0) 
			{	
				if(attack) {
					magic1 = new Enemy(100, 100, 2, 2, (int)DarkMage1.getCoordX(), (int)DarkMage1.getCoordY() + 75, ElementType.FIRE, enemyType.Nagic);
					listOfProjectiles.add(magic1);
				}
				else {
					listOfProjectiles.remove(magic1);
					program.remove(magic1.getImage());
					hit = false;
				}
			}
		}
		for (Enemy enem : listOfEnemies) {
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.DarkMage) {
				enem.getImage().movePolar(1, degree);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
		if(listOfProjectiles.size() >= 1) {
			for(Enemy arr : listOfProjectiles) {
				arr.getEnemyStats().setCoordX(DarkMage1.getCoordX());
				arr.getEnemyStats().setCoordY(DarkMage1.getCoordY());

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
	
	public boolean checkHitBack(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_Q)) {
			t.stop();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			atkUp = true;
		}
		mover.notReallyKeyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			atkUp = false;
		}
		mover.notReallyKeyReleased(e);
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
		program.drawOverlay(5, program.getFloorNum());
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
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E9.getX() && userY >= E9.getY() && userX <= E9.getX() + 75 && userY <= E9.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR6();
		}
		else if(userX <= E10.getX() && userY <= E10.getY() && userX2 >= E10.getX() && userY2 >= E10.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR4();
		}
	}
}
