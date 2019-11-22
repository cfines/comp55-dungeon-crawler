package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R2TEST extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private User user;
	private GImage enemy1, enemy2, E4, E5, rock1, hole1, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkRight,atkDown;
	Timer t = new Timer(30, this);
	private int degree;
	private int timerCont = 0;
	private boolean move = true;
	
	private KeyPressedManager mover;

	public mapBase_R2TEST(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(2,2,2,2,800,70,ElementType.EARTH, enemyType.EARTHBat);
		Enemy ienemy2 = new Enemy(2,2,2,2,575,70,ElementType.FIRE, enemyType.FIRESkull);
		Interactions iE4 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE5 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,575,325);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,230,163);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		E4 = iE4.getImage();
		E5 = iE5.getImage();
		rock1 = irock1.getImage();
		hole1 = ihole1.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);

		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy1);

		listOfInter.add(ihole1);
		listOfInter.add(iE5);
		listOfInter.add(iE4);
		listOfInter.add(irock1);

		userRep = new GImage("Rogue_(Sample User).gif", user.getX(), user.getY());
		userRep.setSize(75, 75);

		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		elements.add(rock1);
		elements.add(hole1);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(E4);
		elements.add(E5);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, atkUp, atkLeft, atkRight, atkDown);
		
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(99, 1);
	}

	@Override
	public void hideContents() {
		t.stop();
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		enemyMovement();
		nextRoom();
		mover.notReallyActionPerformed(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E4) {
			program.switchToR2();
			userRep.setLocation(90,300);
		}
		else if(obj == E5) {
			program.switchToR4();
			userRep.setLocation(1010,300);
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			program.switchToR2();
			userRep.setLocation(1030,300);
		}
		
		userX = userRep.getX() + 80;
		userY = userRep.getY() + 80;
		
		if(userX >= E5.getX() && userY >= E5.getY() && userX + 80 <= E5.getX() + 75 && userY + 80 <= E5.getY() + 75) {
			program.switchToR4();
			userRep.setLocation(40,300);
		}
	}
	
	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
		for (Enemy enem : listOfEnemies) {

			enem.getImage().movePolar(5, degree);
			degree+=5;
			degree%=360;
			if(enem.getEnemyType() == enemyType.EARTHBat) {
				if(move) {
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 4) / 100;
					double moveY = (distY * 4) / 100;
					enem.getImage().move(-moveX, -moveY);
				}else {enem.getImage().move(0, 0);}
			}
			else if(enem.getEnemyType() == enemyType.FIRESkull) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 2) / 100;
				double moveY = (distY * 2) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
}

