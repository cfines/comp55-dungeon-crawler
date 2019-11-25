package ChrisFloor;
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

public class chris_R9 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage cheese1, cheese2, cheese3,E1, E3, E4, blue1,blue2,blue3,blue4,blue5,blue6,blue7,blue8, background,userRep, userWeapon;
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
	

	public chris_R9(MainApplication app) {
		this.program = app;
		user = program.getUser();

		//Interactions
		Interactions iE1 = new Interactions(interactionType.chrisEntry_NORTH, 575,-3);
		Interactions iE3 = new Interactions(interactionType.chrisEntry_EAST,1050,300);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		Interactions iblue1 = new Interactions(interactionType.blueboi,65,35);
		Interactions iblue2 = new Interactions(interactionType.blueboi,105,100);
		Interactions iblue3 = new Interactions(interactionType.blueboi,1005,35);
		Interactions iblue4 = new Interactions(interactionType.blueboi,975,65);
		Interactions iblue5 = new Interactions(interactionType.blueboi,1005,440);
		Interactions iblue6 = new Interactions(interactionType.blueboi,975,410);
		Interactions iblue7 = new Interactions(interactionType.blueboi,65,440);
		Interactions iblue8 = new Interactions(interactionType.blueboi,95,420);

		//Enemies
		Enemy icheese1 = new Enemy(5,5,2,2,250,140,ElementType.EARTH,enemyType.EARTHCheese);
		Enemy icheese2 = new Enemy(5,5,2,2,740,260,ElementType.FIRE,enemyType.FIRECheese);
		Enemy icheese3 = new Enemy(5,5,2,2,740,440,ElementType.WATER,enemyType.WATERCheese);

		//gImages
		background = new GImage("background_figures.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E1 = iE1.getImage();
		E3 = iE3.getImage();
		E4 = iE4.getImage();
		blue1 = iblue1.getImage();
		blue2 = iblue2.getImage();
		blue3 = iblue3.getImage();
		blue4 = iblue4.getImage();
		blue5 = iblue5.getImage();
		blue6 = iblue6.getImage();
		blue7 = iblue7.getImage();
		blue8 = iblue8.getImage();
		cheese1 = icheese1.getImage();
		cheese2 = icheese2.getImage();
		cheese3 = icheese3.getImage();

		//listOfInter.add();
		listOfInter.add(iE1);
		listOfInter.add(iE3);
		listOfInter.add(iE4);
		listOfInter.add(iblue1);
		listOfInter.add(iblue2);
		listOfInter.add(iblue3);
		listOfInter.add(iblue4);
		listOfInter.add(iblue5);
		listOfInter.add(iblue6);
		listOfInter.add(iblue7);
		listOfInter.add(iblue8);

		//listOfEnemies.add)();
		listOfEnemies.add(icheese1);
		listOfEnemies.add(icheese2);
		listOfEnemies.add(icheese3);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		//elements.add();
		elements.add(background);
		elements.add(E1);
		elements.add(E3);
		elements.add(E4);
		elements.add(blue1);
		elements.add(blue2);
		elements.add(blue3);
		elements.add(blue4);
		elements.add(blue5);
		elements.add(blue6);
		elements.add(blue7);
		elements.add(blue8);
		elements.add(userRep);
		
		enemyImages.add(cheese1);
		enemyImages.add(cheese2);
		enemyImages.add(cheese3);

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
		program.drawOverlay(9, program.getFloorNum());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
		//System.out.println("x: "+ user.getX() + " y: " + user.getY());	
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
		if(userX >= E1.getX() && userY >= E1.getY() && userX <= E1.getX() + 85 && userY <= E1.getY() + 85) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR3();
		}
		else if(userX <= E3.getX() && userY <= E3.getY() && userX2 >= E3.getX() && userY2 >= E3.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR10();
		}
		else if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR8();
		}
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}

	public void enemyMovement() {
		for (Enemy enem : listOfEnemies) {
			if(enem.getEnemyType() == enemyType.EARTHCheese) {
					degree+=5;
					degree%=360;
					enem.getImage().movePolar(2, degree);
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 5) / 100;
					double moveY = (distY * 5) / 100;
					enem.getImage().move(-moveX, -moveY);
				}
			else if(enem.getEnemyType() == enemyType.FIRECheese) {
				degree+=2;
				degree%=360;
				enem.getImage().movePolar(6, degree);
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
			else if(enem.getEnemyType() == enemyType.WATERCheese) {
				degree+=2;
				degree%=360;
				enem.getImage().movePolar(1, degree);
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
}
