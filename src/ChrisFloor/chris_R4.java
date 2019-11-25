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

public class chris_R4 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1, E2, color1,color2,color3,color4,color7,color8,
	color9,color10,color11,color12,color13,color14,color15,color16,pacific1,pacific2,background,userRep, userWeapon;
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
	

	public chris_R4(MainApplication app) {
		this.program = app;
		user = program.getUser();


		//Interactions
		Interactions iE1 = new Interactions(interactionType.chrisEntry_NORTH, 575,-3);
		Interactions iE2 = new Interactions(interactionType.chrisEntry_SOUTH, 575,535);
		Interactions icolor1 = new Interactions(interactionType.color2,140,35);
		Interactions icolor2 = new Interactions(interactionType.color1,215,35);
		Interactions icolor3 = new Interactions(interactionType.color2,930,35);
		Interactions icolor4 = new Interactions(interactionType.color1,855,35);
		Interactions icolor7 = new Interactions(interactionType.color2,65,184);
		Interactions icolor8 = new Interactions(interactionType.color1,65,444);
		Interactions icolor9 = new Interactions(interactionType.color2,930,500);
		Interactions icolor11 = new Interactions(interactionType.color1,855,500);
		Interactions icolor10 = new Interactions(interactionType.color2,140,500);
		Interactions icolor12 = new Interactions(interactionType.color1,215,500);
		Interactions icolor13 = new Interactions(interactionType.color2,1005,191);
		Interactions icolor14 = new Interactions(interactionType.color1,1005,243);
		Interactions icolor15 = new Interactions(interactionType.color2,1005,295);
		Interactions icolor16 = new Interactions(interactionType.color1,1005,347);

		//Enemies
		Enemy ipacific1 = new Enemy(5,5,2,2,855,120,ElementType.WATER,enemyType.insidePacific);
		Enemy ipacific2 = new Enemy(5,5,2,2,855,390,ElementType.WATER,enemyType.insidePacific);

		//gImages
		background = new GImage("background_sky.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E1 = iE1.getImage();
		E2 = iE2.getImage();
		color1 = icolor1.getImage();
		color2 = icolor2.getImage();
		color3 = icolor3.getImage();
		color4 = icolor4.getImage();
		color7 = icolor7.getImage();
		color8 = icolor8.getImage();
		color9 = icolor9.getImage();
		color10 = icolor10.getImage();
		color11 = icolor11.getImage();
		color12 = icolor12.getImage();
		color13 = icolor13.getImage();
		color14 = icolor14.getImage();
		color15 = icolor15.getImage();
		color16 = icolor16.getImage();
		pacific1 = ipacific1.getImage();
		pacific2 = ipacific2.getImage();

		//listOfInter.add();
		listOfInter.add(iE1);
		listOfInter.add(iE2);
		listOfInter.add(icolor1);
		listOfInter.add(icolor2);
		listOfInter.add(icolor3);
		listOfInter.add(icolor4);
		listOfInter.add(icolor7);
		listOfInter.add(icolor8);
		listOfInter.add(icolor9);
		listOfInter.add(icolor10);
		listOfInter.add(icolor11);
		listOfInter.add(icolor12);
		listOfInter.add(icolor13);
		listOfInter.add(icolor14);
		listOfInter.add(icolor15);
		listOfInter.add(icolor16);

		//listOfEnemies.add)();
		listOfEnemies.add(ipacific1);
		listOfEnemies.add(ipacific2);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		//elements.add();
		elements.add(background);
		elements.add(E1);
		elements.add(E2);
		elements.add(color1);
		elements.add(color2);
		elements.add(color3);
		elements.add(color4);
		elements.add(color7);
		elements.add(color8);
		elements.add(color9);
		elements.add(color10);
		elements.add(color11);
		elements.add(color12);
		elements.add(color13);
		elements.add(color14);
		elements.add(color15);
		elements.add(color16);
		elements.add(userRep);
		
		enemyImages.add(pacific1);
		enemyImages.add(pacific2);

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

		program.drawOverlay(4, program.getFloorNum());
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
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			t.stop();
		}
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
		if(userX >= E1.getX() && userY >= E1.getY() && userX <= E1.getX() + 85 && userY <= E1.getY() + 85) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR5();
		}
		else if(userX <= E2.getX() && userY <= E2.getY() && userY2 >= E2.getY() - 30  && userX >= E2.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToChrisR10();
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
			if(enem.getEnemyType() == enemyType.insidePacific) {
					degree+=5;
					degree%=360;
					enem.getImage().movePolar(2, degree);
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 5) / 100;
					double moveY = (distY * 5) / 100;
					enem.getImage().move(-moveX, -moveY);
				}

			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}
}
