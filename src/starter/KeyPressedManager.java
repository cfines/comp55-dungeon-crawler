package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;

public class KeyPressedManager {

	private MainApplication program;
	private User user;
	private GImage userRep, userWeapon;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkRight,atkDown;
	boolean keyDeleted = false;

	public KeyPressedManager(MainApplication program, User user, GImage userRep,
			ArrayList<Enemy> listOfEnemies, ArrayList<Interactions> listOfInter,
			boolean atkUp, boolean atkLeft, boolean atkRight, boolean atkDown, GImage userWeapon){
		this.program = program;
		this.user = user;
		this.userRep = userRep;
		this.userRep.setImage("Rogue_(Sample User).gif");
		this.userRep.setSize(75,75);
		this.listOfEnemies = listOfEnemies;
		this.listOfInter = listOfInter;
		this.atkUp = atkUp;
		this.atkDown = atkDown;
		this.atkLeft = atkLeft;
		this.atkRight = atkRight;
		this.userWeapon = userWeapon;
	}

	public void notReallyActionPerformed(ActionEvent e) {
		updateWeaponLoc();
		userCombat();
		enemyCombat();
		user.tick();
		checkCollision();
		knockBack();
		
		if(program.getUser().getHasKey() && !keyDeleted) {
			removeKeyFromInteractionList();
		}
		
		userRep.setLocation(user.getX(), user.getY());
	}

	public void notReallyKeyPressed(KeyEvent e) {
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
			attackUp();
			break;
		case KeyEvent.VK_LEFT:
			atkLeft = true;
			attackLeft();
			break;
		case KeyEvent.VK_DOWN:
			atkDown = true;
			attackDown();
			break;
		case KeyEvent.VK_RIGHT:
			atkRight = true;
			attackRight();
			break;
		case KeyEvent.VK_ESCAPE:
			program.pauseScreenSwitch();
		}
	}

	public void notReallyKeyReleased(KeyEvent e) {
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
			attackReset();
			break;

		case KeyEvent.VK_LEFT:
			atkLeft = false;
			attackReset();
			break;

		case KeyEvent.VK_DOWN: 
			atkDown = false;
			attackReset();
			break;

		case KeyEvent.VK_RIGHT: 
			atkRight = false;
			attackReset();
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
				if(atkUp || atkDown || atkLeft || atkRight) 
				{
					//damage dealt to enemy
					newHealth = listOfEnemies.get(i).getEnemyStats().getHP_cur() - (int)program.getUser().getPowerStat();
					listOfEnemies.get(i).getEnemyStats().setHP_cur(newHealth);
					if(listOfEnemies.get(i).getEnemyStats().getHP_cur() <= 0) 
					{
						//should remove an enemy
						listOfEnemies.get(i).setImage(enemyType.rip);
						//program.add(listOfEnemies.get(i).getImage());
						listOfEnemies.remove(i);
					}
				}
				//TODO insert user getting hurt here
			}

			if (program.getUser().getUserStats().getHP_cur() <= 0) {
				program.switchToGameOver();

			}

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
					//System.out.println("bottom"); 
					if(keyCheck(inter)) { removeKey(inter); }
					user.setY(user.getY() + user.getMoveSpeedStat()); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					//System.out.println("top"); 
					if(keyCheck(inter)) { removeKey(inter); }
					user.setY(user.getY() - user.getMoveSpeedStat());
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					//System.out.println("right"); 
					if(keyCheck(inter)) { removeKey(inter); }
					user.setX(user.getX() + user.getMoveSpeedStat()); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					//System.out.println("left"); 
					if(keyCheck(inter)) { removeKey(inter); }
					user.setX(user.getX() - user.getMoveSpeedStat());
				} 
			}
		}

		for(Enemy enem : listOfEnemies) {
			if(enemyCollisionTest(enem, userRep)) {
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					//System.out.println("bottom"); 
					user.setY(user.getY() + 50); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					//System.out.println("top"); 
					user.setY(user.getY() - 50);
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					//System.out.println("right"); 
					user.setX(user.getX() + 50); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					//System.out.println("left"); 
					user.setX(user.getX() - 50);
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

//		userRep.setImage("Rogue_Attack(Up).png");
//		userRep.setSize(75,75);
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(UP).png");
			userWeapon.setSize(40, 75);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(UP).png");
			userWeapon.setSize(40, 75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(UP).png");
			userWeapon.setSize(40, 75);
		}
		program.add(userWeapon);
	}
	private void attackDown() {
//		userRep.setImage("Rogue_Attack(Down).png");
//		userRep.setSize(75,75);
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(DOWN).png");
			userWeapon.setSize(40, 75);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(DOWN).png");
			userWeapon.setSize(40, 75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(DOWN).png");
			userWeapon.setSize(40, 75);
		}
		program.add(userWeapon);
	}
	private void attackLeft() {
//		userRep.setImage("Rogue_Attack(Left).png");
//		userRep.setSize(75,75);
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(LEFT).png");
			userWeapon.setSize(75, 40);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(LEFT).png");
			userWeapon.setSize(75, 40);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(LEFT).png");
			userWeapon.setSize(75, 40);
		}
		program.add(userWeapon);
	}
	private void attackRight() {
//		userRep.setImage("Rogue_Attack(Right).png");
//		userRep.setSize(75,75);
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(RIGHT).png");
			userWeapon.setSize(75, 40);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(RIGHT).png");
			userWeapon.setSize(75, 40);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(RIGHT).png");
			userWeapon.setSize(75, 40);
		}
		program.add(userWeapon);
	}

	private void attackReset() {
//		userRep.setImage("Rogue_(Sample User).gif");
//		userRep.setSize(75,75);
		program.remove(userWeapon);
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

	public void enemyCombat() {
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(enemyCollisionTest(listOfEnemies.get(i), userRep)) { 
				int newHealth = program.getUser().getUserStats().getHP_cur() - 1;
				program.getUser().getUserStats().setHP_cur(newHealth);
				//System.out.println("User takes 1 damage, ouch.");
				program.combatRefreshOverlay();
			}
		}
	}
	
	public boolean keyCheck(Interactions inter) {
		return inter.getinteractionType() == interactionType.item_gif_key;
	}
	
	public void removeKey(Interactions inter) {
		program.getUser().setHasKey(true);
		program.combatRefreshOverlay();
		program.remove(inter.getImage());
		inter.setImage(interactionType.nullified);
		program.add(inter.getImage());
	}
	
	public void removeKeyFromInteractionList() {
		for(int i = 0; i < listOfInter.size(); i++) {
			if(listOfInter.get(i).getinteractionType() == interactionType.item_gif_key) {
				listOfInter.remove(i);
				keyDeleted = true;
			}
		}
	}
}
