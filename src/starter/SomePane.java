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
	private GImage rock1, rock2, hole1, E1, background, userRep, enemy1, enemy2;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);


	public SomePane(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock, 170,189);
		Interactions irock2 = new Interactions(interactionType.obstacle_rock, 700, 150);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 172,425);
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST, 1050,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,350,300, ElementType.FIRE, enemyType.FIRESkull);
		Enemy ienemy2 = new Enemy(2,2,2,2,350,450, ElementType.FIRE, enemyType.FIRESkull);

		user = new User(5, 5, 1000, 1, 300, 300);
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
		userRep.setSize(75, 75);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
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
		elements.add(enemy2);
		elements.add(userRep);
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
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
	public void mouseMoved(MouseEvent e) {
		//user.setX(e.getX());
		//user.setY(e.getY());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		enemyMovement();
		nextRoom();
		user.tick();
		checkCollision();
		userRep.setLocation(user.getX(), user.getY());
	}

	private void nextRoom() {
		double userX = userRep.getX() + 80;
		double userY = userRep.getY() + 80;
		if(userX >= E1.getX() && userY >= E1.getY() && userX <= E1.getX() + 75 && userY <= E1.getY() + 75) {
			program.switchToR2();
		}
	}

	private void userUP() {
		user.setDY(-user.getMoveSpeedStat());
	}
	private void userDOWN() {
		user.setDY(user.getMoveSpeedStat());
	}
	private void userLEFT() {
		user.setDX(-user.getMoveSpeedStat());
	}
	private void userRIGHT() {
		user.setDX(user.getMoveSpeedStat());
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
		for (Enemy enem : listOfEnemies) {
			double distX = enem.getImage().getX() - userRep.getX();
			double distY = enem.getImage().getY() - userRep.getY();
			double moveX = (distX * 2) / 100;
			double moveY = (distY * 2) / 100;
			enem.getImage().move(-moveX, -moveY);
			enem.getImage().movePolar(4, degree);
			degree+=50;
			degree%=360;
		}
	}

	public void checkCollision() {
		for(Interactions inter : listOfInter) {	
			if(intCollisionTest(inter.getImage())) {
				//TODO Set these comparisons to booleans
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					System.out.println("bottom"); 
					user.setY(user.getY() + user.getMoveSpeedStat()); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					System.out.println("top"); 
					user.setY(user.getY() - user.getMoveSpeedStat());
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					System.out.println("right"); 
					user.setX(user.getX() + user.getMoveSpeedStat()); 
				} 
				if (user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					System.out.println("left"); 
					user.setX(user.getX() - user.getMoveSpeedStat());
				} 
			}
		}
	}

	public void enemyCollision() {
		for(Interactions inter : listOfInter) {
			for(Enemy enem : listOfEnemies) {
				if(enemyCollisionTest(enem, inter.getImage())) {
					
				}
			}
		}
	}

	public boolean intCollisionTest(GImage image) {
		return (user.getY() - image.getY() <= 60
				&& user.getY() - image.getY() >= -60
				&& user.getX() - image.getX() <= 60
				&& user.getX() - image.getX() >= -60);
	}

	public boolean enemyCollisionTest(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
}
