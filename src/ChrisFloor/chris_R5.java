package ChrisFloor;
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
import removeLater.User;

public class chris_R5 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage goon1, goon2, goon3, goon4,E2, E4, color1, color2, color3, color4, background,userRep, userWeapon;
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


	public chris_R5(MainApplication app) {
		this.program = app;
		user = program.getUser();


		//Interactions
		Interactions iE2 = new Interactions(interactionType.chrisEntry_SOUTH, 575,535);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		Interactions icolor1 = new Interactions(interactionType.color2,65,48);
		Interactions icolor2 = new Interactions(interactionType.color2,65,490);
		Interactions icolor3 = new Interactions(interactionType.color2,1005,35);
		Interactions icolor4 = new Interactions(interactionType.color2,1005,490);

		//Enemies
		Enemy igoon1 = new Enemy(5,5,2,2,925,110,ElementType.EARTH,enemyType.goon1);
		Enemy igoon2 = new Enemy(5,5,2,2,1005,230,ElementType.WATER,enemyType.goon1);
		Enemy igoon3 = new Enemy(5,5,2,2,175,85,ElementType.WATER,enemyType.goon2);
		Enemy igoon4 = new Enemy(5,5,2,2,115,175,ElementType.FIRE,enemyType.goon2);

		//gImages
		background = new GImage("background_sky.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E2 = iE2.getImage();
		E4 = iE4.getImage();
		color1 = icolor1.getImage();
		color2 = icolor2.getImage();
		color3 = icolor3.getImage();
		color4 = icolor4.getImage();
		goon1 = igoon1.getImage();
		goon2 = igoon2.getImage();
		goon3 = igoon3.getImage();
		goon4 = igoon4.getImage();

		//listOfInter.add();
		listOfInter.add(iE2);
		listOfInter.add(iE4);
		listOfInter.add(icolor1);
		listOfInter.add(icolor2);
		listOfInter.add(icolor3);
		listOfInter.add(icolor4);

		//listOfEnemies.add)();
		listOfEnemies.add(igoon4);
		listOfEnemies.add(igoon3);
		listOfEnemies.add(igoon2);
		listOfEnemies.add(igoon1);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		//elements.add();
		elements.add(background);
		elements.add(E2);
		elements.add(E4);
		elements.add(color1);
		elements.add(color2);
		elements.add(color3);
		elements.add(color4);
		elements.add(userRep);

		enemyImages.add(goon1);
		enemyImages.add(goon2);
		enemyImages.add(goon3);
		enemyImages.add(goon4);

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
		System.out.println("x: "+ user.getX() + " y: " + user.getY());	
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
		if(userX <= E2.getX() && userY <= E2.getY() && userY2 >= E2.getY() - 30  && userX >= E2.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToChrisR4();
		}
		else if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR6();
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
				if(enem.getEnemyType() == enemyType.goon1) {
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 1) / 100;
					double moveY = (distY * 1) / 100;
					enem.getImage().move(-moveX, -moveY);
				}
				else if(enem.getEnemyType() == enemyType.goon2) {
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 3) / 100;
					double moveY = (distY * 3) / 100;
					enem.getImage().move(-moveX, -moveY);
				}
			}else {enem.getImage().move(0, 0);}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}
}
