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
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R3 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private User user;
	private GImage enemy1, enemy2, E4, E5, rock1, hole1, background, userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkRight,atkDown;
	Timer t = new Timer(30, this);
	private int degree;
	private int timerCont = 0;
	private boolean move = true;

	public mapBase_R3(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(100,100,2,2,800,70,ElementType.EARTH, enemyType.EARTHBat);
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
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		userWeapon.setSize(75, 75);
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
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(3, 1);
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
		updateWeaponLoc();
		enemyMovement();
		userCombat();
		enemyCombat();
		nextRoom();
		user.tick();
		checkCollision();
		knockBack();
		userRep.setLocation(user.getX(), user.getY());
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
		case KeyEvent.VK_E:
			program.getUser().cycleWeapon();
			program.drawSword();
			break;
		case KeyEvent.VK_UP:
			atkUp = true;
			if(atkUp == true) 
			{
				attackUp();
			}
			break;
		case KeyEvent.VK_LEFT:
			atkLeft = true;
			if(atkLeft == true) 
			{
				attackLeft();
			}
			break;
		case KeyEvent.VK_DOWN:
			atkDown = true;
			if(atkDown == true) 
			{
				attackDown();
			}
			break;
		case KeyEvent.VK_RIGHT:
			atkRight = true;
			if(atkRight == true) 
			{
				attackRight();
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
				attackReset();
			}
			break;

		case KeyEvent.VK_LEFT:
			atkLeft = false;
			if(atkLeft == false) 
			{
				attackReset();
			}
			break;

		case KeyEvent.VK_DOWN: 
			atkDown = false;
			if(atkDown == false) 
			{
				attackReset();
			}
			break;

		case KeyEvent.VK_RIGHT: 
			atkRight = false;
			if(atkRight == false) 
			{
				attackReset();
			}
			break;
		}
	}

	public void updateWeaponLoc() {
		if(atkUp) {userWeapon.setLocation(user.getX() + 20, user.getY() - 50);}
		if(atkDown) {userWeapon.setLocation(user.getX() + 20, user.getY() + 50);}
		if(atkLeft) {userWeapon.setLocation(user.getX() - 50, user.getY() + 20);}
		if(atkRight) {userWeapon.setLocation(user.getX() + 50, user.getY() + 20);}
	}

	public void userCombat() 
	{
		int newHealth;
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(enemyCollisionTest(listOfEnemies.get(i), userWeapon)) 
			{
				//if the user is fighting
				if(atkUp == true || atkDown == true || atkLeft == true || atkRight == true) 
				{
					//damage dealt to enemy
					newHealth = listOfEnemies.get(i).getEnemyStats().getHP_cur() - (int)program.getUser().getPowerStat();
					listOfEnemies.get(i).getEnemyStats().setHP_cur(newHealth);
					if( listOfEnemies.get(i).getEnemyStats().getHP_cur() <= 0) 
					{
						//should remove an enemy
						int tempX = (int)listOfEnemies.get(i).getCoordX();
						int tempY = (int)listOfEnemies.get(i).getCoordY();
						Interactions rip = new Interactions(interactionType.rip, tempX, tempY);
						Interactions rip2 = new Interactions(interactionType.rip2, tempX, tempY);
						program.add(rip2.getImage());
						program.add(rip.getImage());
						listOfEnemies.remove(i);
					}
				}
				//TODO insert user getting hurt here
			}
			/*
			 * if (program.getUser().getUserStats().getHP_cur() == 0) {
			 * program.switchToGameOver();
			 * 
			 * }
			 */
		}
	}

	public void enemyCombat() {
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(enemyCollisionTest(listOfEnemies.get(i), userRep)) { 
				int newHealth = program.getUser().getUserStats().getHP_cur() - 1;
				program.getUser().getUserStats().setHP_cur(newHealth);
				System.out.println("User takes 1 damage, ouch.");
				program.refreshOverlay();
				program.drawOverlay(3, 1);
			}
		}
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
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					System.out.println("left"); 
					user.setX(user.getX() - user.getMoveSpeedStat());
				} 
			}
		}

		for(Enemy enem : listOfEnemies) {
			if(enemyCollisionTest(enem, userRep)) {
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					System.out.println("bottom"); 
					user.setY(user.getY() + 35);
					for(int i = 0; i < 40; i++) {}
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					System.out.println("top"); 
					user.setY(user.getY() - 35);
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					System.out.println("right"); 
					user.setX(user.getX() + 35); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					System.out.println("left"); 
					user.setX(user.getX() - 35);
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

	private void attackUp() {

		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("Rogue_Attack(Up).png");
			userRep.setSize(75,75);
			userWeapon.setImage("Fire Sword(UP).png");
			userWeapon.setSize(40, 75);
			program.add(userWeapon);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("Rogue_Attack(Up).png");
			userRep.setSize(75,75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("Rogue_Attack(Up).png");
			userRep.setSize(75,75);
		}
	}
	private void attackDown() {
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("Rogue_Attack(Down).png");
			userRep.setSize(75,75);
			userWeapon.setImage("Fire Sword(DOWN).png");
			userWeapon.setSize(40, 75);
			program.add(userWeapon);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("Rogue_Attack(Down).png");
			userRep.setSize(75,75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("Rogue_Attack(Down).png");
			userRep.setSize(75,75);
		}
	}
	private void attackLeft() {
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("Rogue_Attack(Left).png");
			userRep.setSize(75,75);
			userWeapon.setImage("Fire Sword(LEFT).png");
			userWeapon.setSize(75, 40);
			program.add(userWeapon);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("Rogue_Attack(Left).png");
			userRep.setSize(75,75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("Rogue_Attack(Left).png");
			userRep.setSize(75,75);
		}
	}
	private void attackRight() {
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("Rogue_Attack(Right).png");
			userRep.setSize(75,75);
			userWeapon.setImage("Fire Sword(RIGHT).png");
			userWeapon.setSize(75, 40);
			program.add(userWeapon);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("Rogue_Attack(Right).png");
			userRep.setSize(75,75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("Rogue_Attack(Right).png");
			userRep.setSize(75,75);
		}
	}

	private void attackReset() {
		userRep.setImage("Rogue_(Sample User).gif");
		userRep.setSize(75,75);
		program.remove(userWeapon);
	}


	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
		for (Enemy enem : listOfEnemies) {
			if(enem.getEnemyType() == enemyType.EARTHBat) {
				if(move) {
					degree+=5;
					degree%=360;
					enem.getImage().movePolar(2, degree);
					double distX = enem.getImage().getX() - userRep.getX();
					double distY = enem.getImage().getY() - userRep.getY();
					double moveX = (distX * 5) / 100;
					double moveY = (distY * 5) / 100;
					enem.getImage().move(-moveX, -moveY);
				}else {enem.getImage().move(0, 0);}
			}
			else if(enem.getEnemyType() == enemyType.FIRESkull) {
				degree+=2;
				degree%=360;
				enem.getImage().movePolar(6, degree);
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

	public void knockBack() {
		for(Enemy enem : listOfEnemies)
			if(enemyCollisionTest(enem, userWeapon)) {
				GImage tempEnem = enem.getImage();
				if(atkUp) {
					enem.getImage().setLocation(tempEnem.getX(), tempEnem.getY() - 50);
				}
				if(atkDown) {
					enem.getImage().setLocation(tempEnem.getX(), tempEnem.getY() + 50);
				}
				if(atkLeft) {
					enem.getImage().setLocation(tempEnem.getX() - 50, tempEnem.getY());
				}
				if(atkRight) {
					enem.getImage().setLocation(tempEnem.getX() + 50, tempEnem.getY());
				}
			}
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
}

