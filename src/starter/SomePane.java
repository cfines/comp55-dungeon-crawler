package starter;
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


public class SomePane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage rock1, rock2, hole1, E1, background, userRep, enemy1;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;


	public SomePane(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock, 170,189);
		Interactions irock2 = new Interactions(interactionType.obstacle_rock, 890, 200);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 172,425);
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST, 1040,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,350,300, ElementType.FIRE, enemyType.FIRESkull);
		user = new User(5, 5, 1000, 1, 300, 300);
		listOfInter.add(irock1);
		listOfInter.add(irock2);
		listOfInter.add(ihole1);
		listOfInter.add(iE1);
		listOfEnemies.add(ienemy1);

		background = new GImage("Base_Floor (Tutorial Floor).png", 15,30);
		rock1 = irock1.getImage();
		rock2 = irock2.getImage();
		hole1 = ihole1.getImage();
		E1 = iE1.getImage();
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		enemy1 = ienemy1.getImage();
		background.setSize(1125, 550);

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
		elements.add(userRep);
		
		Timer t = new Timer(50, this);
		t.start();
	}

	@Override
	public void showContents() {
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E1) {
			userRep.setLocation(90, 300);
			program.switchToR2();
		}
		else if(obj == rock1) {
			program.switchToMenu();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		enemy1.movePolar(4, degree);
		degree+=50;
		degree%=360;
		enemyMovement();
		user.tick();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	private void nextRoom() {
		double userX = userRep.getX() + 75;
		double userY = userRep.getY() + 75;
		if(userX >= E1.getX() && userY >= E1.getY() && userX <= E1.getX() + 75 && userY <= E1.getY() + 75) {
			program.switchToR2();
		}
	}
	
	private void userUP() {
		user.setDY(-user.getMoveSpeedStat());
		//nextRoom();
	}
	private void userDOWN() {
		user.setDY(user.getMoveSpeedStat());
		//nextRoom();
	}
	private void userLEFT() {
		user.setDX(-user.getMoveSpeedStat());
		//nextRoom();
	}
	private void userRIGHT() {
		user.setDX(user.getMoveSpeedStat());
		//nextRoom();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			userUP();
			break;
		case KeyEvent.VK_S:
			userDOWN();
			break;
		case KeyEvent.VK_A:
			userLEFT();
			break;
		case KeyEvent.VK_D:
			userRIGHT();
			break;
		case KeyEvent.VK_UP:
			atkUp = true;
			if(atkUp == true) 
			{
				userRep.setImage("Rogue_Attack(Up).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_LEFT:
			atkLeft = true;
			if(atkLeft == true) 
			{
				userRep.setImage("Rogue_Attack(Left).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_DOWN:
			atkDown = true;
			if(atkDown == true) 
			{
				userRep.setImage("Rogue_Attack(Down).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_RIGHT:
			atkRight = true;
			if(atkRight == true) 
			{
				userRep.setImage("Rogue_Attack(Right).png");
				userRep.setSize(75,75);
			}
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			user.setDY(0);
			break;
		case KeyEvent.VK_S:
			user.setDY(0);
			break;
		case KeyEvent.VK_A:
			user.setDX(0);
			break;
		case KeyEvent.VK_D:
			user.setDX(0);
			break;
		case KeyEvent.VK_UP:
			atkUp = false;
			if(atkUp == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_LEFT:
			atkLeft = false;
			if(atkLeft == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_DOWN: 
			atkDown = false;
			if(atkDown == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_RIGHT: 
			atkRight = false;
			if(atkRight == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;
		}
	}
	
	public void enemyMovement() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double enemyX = enemy1.getX();
		double enemyY = enemy1.getY();
		double distX = enemyX - userX;
		double distY = enemyY - userY;
		double moveX = (distX * 2) / 100;
		double moveY = (distY * 2) / 100;
		for (Enemy enem : listOfEnemies)
			enem.getImage().move(-moveX, -moveY);
	}
}
