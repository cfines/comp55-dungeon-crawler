package earthFloor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

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

public class earth_boss extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage background,userRep, userWeapon; 
	private GImage boss = new GImage("plant_NORTH.gif", 1155, 100); 
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private Enemy plant = new Enemy(50,50,2,2,1155,100, ElementType.EARTH, enemyType.plant_NORTH);
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = false;
	private boolean attack = false;
	private boolean hit = false;
	private Random randomNum = new Random();
	private Random determineNegative = new Random();
	private int degree;
	private int dN = determineNegative.nextInt(1);
	private int random = randomNum.nextInt(5);

	private KeyPressedManager mover;

	public earth_boss(MainApplication app) {
		this.program = app;
		user = program.getUser();

		boss = plant.getImage();
		boss.setSize(1000, 500);

		background = new GImage("Earth_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfEnemies.add(plant);

		elements.add(background);
		elements.add(boss);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {

		if(plant.getEnemyStats().getHP_cur() <= 0) {
			program.setBossDefeated(true);
			program.switchToOsvaldoBossComplete();
		}

		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(2, program.getFloorNum());
		program.bossOverlay(plant);
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
	public void actionPerformed(ActionEvent e) {

		if(plant.getEnemyStats().getHP_cur() <= 0) {
			if(program.getBossRun()) {
				user.setX(150);
				user.setY(300);
				program.setFloorNum(program.getFloorNum() + 1);
				program.switchToTitleScreen();
				program.setBossRun(false);
				return;
			}
			t.stop();
			program.setBossDefeated(true);
			program.switchToTitleScreen();
		}
		
		timerCont++;
		mover.notReallyActionPerformed(e);
		enemyMovement();
		userRep.setLocation(user.getX(), user.getY());
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

	public boolean checkHitBack(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}

	public void enemyMovement() {
		int counter = 2;
		if(everyXSeconds(5)) {
			if(enemyCollisionTest(plant, userWeapon)) {
				attack = true;
			}
		}

		if(attack) {
			plant.getImage().move(10, 0); 		
		}
		else {
			plant.getImage().move(-10, 0);
		}

	}

	public boolean enemyCollisionTest(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
}
