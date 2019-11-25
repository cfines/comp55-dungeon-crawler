package mapBase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.KeyPressedManager;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import miscMechanics.User;

public class mapBase_R5 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage overlay,enemy1, hole1, E8, E9, E10, background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = true;

	private KeyPressedManager mover;

	public mapBase_R5(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(40,40,2,999999,1000,500, ElementType.FIRE, enemyType.FIREDeath);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 230,325);
		Interactions iE8 = new Interactions(interactionType.entry_door_NORTH, 575,30);
		Interactions iE9 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions iE10 = new Interactions(interactionType.entry_door_EAST,1050,300);
		
		enemy1 = ienemy1.getImage();
		hole1 = ihole1.getImage();
		E8 = iE8.getImage();
		E9 = iE9.getImage();
		E10 = iE10.getImage();
		background = new GImage("Water_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		overlay = new GImage("dark_overlay.png",15,30);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfEnemies.add(ienemy1);
		listOfInter.add(iE10);
		listOfInter.add(iE9);
		listOfInter.add(iE8);
		listOfInter.add(ihole1);

		elements.add(background);
		elements.add(hole1);
		elements.add(E8);
		elements.add(E9);
		elements.add(E10);
		elements.add(userRep);
		elements.add(overlay);
		
		enemyImages.add(enemy1);

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
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.refreshOverlay();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E8) {
			program.switchToR4();
			userRep.setLocation(575,48);
		}
		else if(obj == E9) {
			program.switchToR6();
			userRep.setLocation(575,435);
		}
		else if(obj == E10) {
			program.switchToR7();
			userRep.setLocation(90,300);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}

	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E8.getX() && userY >= E8.getY() && userX <= E8.getX() + 85 && userY <= E8.getY() + 85) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR4();
		}
		else if(userX <= E9.getX() && userY <= E9.getY() && userY2 >= E9.getY() - 30  && userX >= E9.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToR6();
		}
		else if(userX <= E10.getX() && userY <= E10.getY() && userX2 >= E10.getX() && userY2 >= E10.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR7();
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

			enem.getImage().movePolar(5, degree);
			degree+=5;
			degree%=360;
			if(move) {
				if(enem.getEnemyType() == enemyType.FIREDeath) {
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 1) / 100;
					double moveY = (distY * 1) / 100;
					enem.getImage().move(-moveX, -moveY);
				}
			}else {enem.getImage().move(0, 0);}
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
