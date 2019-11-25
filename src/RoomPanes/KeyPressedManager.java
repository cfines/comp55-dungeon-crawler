package RoomPanes;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ChrisFloor.poniko;
import acm.graphics.GImage;
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import miscMechanics.User;

public class KeyPressedManager {

	private KeyPressedManager thisClass;
	private MainApplication program;
	private User user;
	private GImage userRep, userWeapon;
	private GImage breaking1 = new GImage("breaking1.png", 0, 0);
	private GImage breaking2 = new GImage("breaking2.png", 0, 0);
	private GImage breaking3 = new GImage("breaking3.png", 0, 0);
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkRight,atkDown;
	boolean keyDeleted = false;
	boolean deleteEnemy = false;
	private AudioPlayer audio = AudioPlayer.getInstance();
	public static final String MUSIC_FOLDER = "sounds";
	
	public KeyPressedManager(MainApplication program, User user, GImage userRep,
			ArrayList<Enemy> listOfEnemies, ArrayList<Interactions> listOfInter, ArrayList<GImage> elements,
			boolean atkUp, boolean atkLeft, boolean atkRight, boolean atkDown, GImage userWeapon){
		this.program = program;
		this.user = user;
		this.userRep = userRep;
		this.userRep.setImage("Rogue_(Sample User).gif");
		this.listOfEnemies = listOfEnemies;
		this.listOfInter = listOfInter;
		this.elements = elements;
		this.atkUp = atkUp;
		this.atkDown = atkDown;
		this.atkLeft = atkLeft;
		this.atkRight = atkRight;
		this.userWeapon = userWeapon;
		this.elements = elements;
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
			audio.playSound(MUSIC_FOLDER, "quick_slot.wav");
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
			System.out.println("esc is pressed!");
			program.pauseScreenSwitch();
			break;
		case KeyEvent.VK_Q:
			user.setDX(0);
			user.setDY(0);
			program.QScreenSwitch();
			break;
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
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(enemyCollisionTest(listOfEnemies.get(i), userWeapon)) 
			{
				//if the user is fighting
				if(atkUp || atkDown || atkLeft || atkRight) 
				{
					//damage dealt to enemy
					
					listOfEnemies.get(i).getEnemyStats().setHP_cur(calculateDamage(listOfEnemies.get(i)));
					if(program.getUser().getInvincibility()) {listOfEnemies.get(i).getEnemyStats().setHP_cur(0);}
					
					if(listOfEnemies.get(i).getEnemyType() == enemyType.bomb) {
						weMinecraftUpInHere(listOfEnemies.get(i));
					}
					
					if(listOfEnemies.get(i).getEnemyStats().getHP_cur() <= 0) 
					{
						if(listOfEnemies.get(i).getEnemyType() == enemyType.bomb) {program.remove(breaking1);program.remove(breaking2);program.remove(breaking3);}
						deleteEnemy = true;
						program.remove(listOfEnemies.get(i).getImage());
						listOfEnemies.get(i).setEnemyType(enemyType.rip);
						listOfEnemies.get(i).setImage(enemyType.rip);
						program.add(listOfEnemies.get(i).getImage());
						
						//listOfEnemies.remove(i);
						audio.playSound(MUSIC_FOLDER, "enemy hit.wav");
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
			if(program.getUser().getInvincibility()) {return;}
			if(intCollisionTest(inter.getImage())) {
				//TODO Set these comparisons to booleans
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					//System.out.println("bottom"); 
					if(keyCheck(inter)) { audio.playSound(MUSIC_FOLDER, "key.wav");removeKey(inter); }
					user.setY(user.getY() + user.getMoveSpeedStat()); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					//System.out.println("top"); 
					if(keyCheck(inter)) { audio.playSound(MUSIC_FOLDER, "key.wav");removeKey(inter); }
					user.setY(user.getY() - user.getMoveSpeedStat());
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					//System.out.println("right"); 
					if(keyCheck(inter)) {audio.playSound(MUSIC_FOLDER, "key.wav"); removeKey(inter); }
					user.setX(user.getX() + user.getMoveSpeedStat()); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					//System.out.println("left"); 
					if(keyCheck(inter)) { audio.playSound(MUSIC_FOLDER, "key.wav");removeKey(inter); }
					user.setX(user.getX() - user.getMoveSpeedStat());
				} 
			}
		}

		for(Enemy enem : listOfEnemies) {
			if(program.getUser().getInvincibility()) {return;}
			if(enemyCollisionTest(enem, userRep)) {
				if(enem.getEnemyType() == enemyType.bomb) {user.setY(user.getY()); user.setX(user.getX());}
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					//System.out.println("bottom"); 
					user.setY(user.getY() + 100); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					//System.out.println("top"); 
					user.setY(user.getY() - 100);
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					//System.out.println("right"); 
					user.setX(user.getX() + 100); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					//System.out.println("left"); 
					user.setX(user.getX() - 100);
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
		userRep.setImage("Rogue_Attack(Up).png");
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(UP).png");
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(UP).png");
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(UP).png");
		}
		program.add(userWeapon);
	}
	private void attackDown() {
		userRep.setImage("Rogue_Attack(Down).png");
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(DOWN).png");
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(DOWN).png");
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(DOWN).png");
		}
		program.add(userWeapon);
	}
	private void attackLeft() {
		userRep.setImage("Rogue_Attack(Left).png");
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(LEFT).png");
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(LEFT).png");
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(LEFT).png");
		}
		program.add(userWeapon);
	}
	private void attackRight() {
		userRep.setImage("Rogue_Attack(Right).png");
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userWeapon.setImage("Fire Sword(RIGHT).png");
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userWeapon.setImage("Water Sword(RIGHT).png");
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userWeapon.setImage("Earth Sword(RIGHT).png");
		}
		program.add(userWeapon);
	}

	private void attackReset() {
		userRep.setImage("Rogue_(Sample User).gif");
		program.remove(userWeapon);
	}

	public void knockBack() {
		if(program.getUser().getInvincibility()) {return;}
		for(Enemy enem : listOfEnemies)
			if(enemyCollisionTest(enem, userWeapon)) {
				if(enem.getEnemyType() == enemyType.bomb) {return;}
				if(enem.getEnemyType() == enemyType.plant_NORTH) {return;}
				GImage tempEnem = enem.getImage();
				if(atkUp) {
					audio.playSound(MUSIC_FOLDER, "sword hit.wav");
					enem.getImage().setLocation(tempEnem.getX(), tempEnem.getY() - 50);
				}
				if(atkDown) {
					audio.playSound(MUSIC_FOLDER, "sword hit.wav");
					enem.getImage().setLocation(tempEnem.getX(), tempEnem.getY() + 50);
				}
				if(atkLeft) {
					audio.playSound(MUSIC_FOLDER, "sword hit.wav");
					enem.getImage().setLocation(tempEnem.getX() - 50, tempEnem.getY());
				}
				if(atkRight) {
					audio.playSound(MUSIC_FOLDER, "sword hit.wav");
					enem.getImage().setLocation(tempEnem.getX() + 50, tempEnem.getY());
				}
			}
	}

	public void enemyCombat() {
		if(program.getUser().getInvincibility()) {return;}
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(enemyCollisionTest(listOfEnemies.get(i), userRep)) { 
				if(listOfEnemies.get(i).getEnemyType() == enemyType.bomb) {return;}
				int newHealth = program.getUser().getUserStats().getHP_cur() - 1;
				program.getUser().getUserStats().setHP_cur(newHealth);
				//System.out.println("User takes 1 damage, ouch.");
				audio.playSound(MUSIC_FOLDER, "user hit.wav");
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
	
	
	public void removeEnemyFromElementList() {
		for(int i = 0; i < elements.size(); i++) {
			if(listOfEnemies.get(i).getEnemyType() == enemyType.rip) {
				elements.remove(i);
				listOfEnemies.remove(i);
			}
		}
	}
	
	public void weMinecraftUpInHere(Enemy enem) {
		if (enem.getEnemyStats().getHP_cur() < 33) {
			program.remove(breaking3);
			breaking3 = new GImage("breaking3.png", enem.getCoordX(), enem.getCoordY());
			breaking3.setSize(75, 75);
			program.add(breaking3);
		} else if (enem.getEnemyStats().getHP_cur() < 66) {
			program.remove(breaking2);
			breaking2 = new GImage("breaking2.png", enem.getCoordX(), enem.getCoordY());
			breaking2.setSize(75, 75);
			program.add(breaking2);
		} else if (enem.getEnemyStats().getHP_cur() < 100) {
			program.remove(breaking1);
			breaking1 = new GImage("breaking1.png", enem.getCoordX(), enem.getCoordY());
			breaking1.setSize(75, 75);
			program.add(breaking1);
		}
	}
	
	public boolean getDeleteEnemy() {
		return deleteEnemy;
	}
	
	public void setDeleteEnemy(boolean prettyPlease) {
		this.deleteEnemy = prettyPlease;
	}
	
	public int calculateDamage(Enemy enem) {
		int newHealth = enem.getEnemyStats().getHP_cur() - (int)program.getUser().getPowerStat();
		if(enem.getElementType() == ElementType.FIRE && program.getUser().getWeaponEquiped() == 1) { newHealth -= 1;}
		if(enem.getElementType() == ElementType.WATER && program.getUser().getWeaponEquiped() == 2) { newHealth -= 1;}
		if(enem.getElementType() == ElementType.EARTH && program.getUser().getWeaponEquiped() == 0) { newHealth -= 1;}
		return newHealth;
	}
	
}
