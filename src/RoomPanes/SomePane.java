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


public class SomePane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage rock1, rock2, hole1, E1, background, userRep, enemy1, enemy2, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);

	private KeyPressedManager mover;


	public SomePane(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		Interactions irock1 = new Interactions(interactionType.obstacle_concrete_rocks, 170,189);
		Interactions irock2 = new Interactions(interactionType.obstacle_concrete_rocks, 700, 150);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 172,425);
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST, 1050,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,1000,300, ElementType.WATER, enemyType.WATERSpider);
		Enemy ienemy2 = new Enemy(2,2,2,2,900,450, ElementType.FIRE, enemyType.FIREDrawing);

		listOfInter.add(irock1);
		listOfInter.add(irock2);
		listOfInter.add(ihole1);
		listOfInter.add(iE1);
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);

		background = new GImage("Base_Floor (Tutorial Floor).png", 15,30);
		rock1 = irock1.getImage();
		rock2 = irock2.getImage();
		hole1 = ihole1.getImage();
		E1 = iE1.getImage();

		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E1);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(1, program.getFloorNum());
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
		if (obj == E1) {
			program.switchToR2();
			userRep.setLocation(70,300);
		}
		else if(obj == rock1) {
			program.switchToMenu();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		enemyMovement();
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}

	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userX + 80;
		double userY2 = userX + 80;
		if(userX <= E1.getX() && userY <= E1.getY() && userX2 >= E1.getX() && userY2 >= E1.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR2();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
	}

	public void enemyMovement() {
		for (Enemy enem : listOfEnemies) {
			double distX = enem.getImage().getX() - userRep.getX();
			double distY = enem.getImage().getY() - userRep.getY();
			double moveX = (distX * 2) / 100;
			double moveY = (distY * 2) / 100;
			enem.getImage().move(-moveX, -moveY);
			enem.getImage().movePolar(4, degree);
			degree+=50;
			degree%=360;
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}
}
