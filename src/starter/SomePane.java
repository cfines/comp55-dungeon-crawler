package starter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class SomePane extends GraphicsPane {
	private MainApplication program;
	private Hardcoded code;
	private GImage rock1, rock2, hole1, E1, background, userRep, enemy1;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private ArrayList<GImage> you = new ArrayList<GImage>();
	private GRect voidSpace;
	private Console game;
	private int keyInput;
	private User user;


	public SomePane(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock, 170,189);
		Interactions irock2 = new Interactions(interactionType.obstacle_rock, 890, 200);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 172,425);
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST, 1040,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,350,76, ElementType.FIRE);
		user = new User(2,2,2,2,2,2);
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
		game = new Console();

		space.add(voidSpace);
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E1);
		elements.add(enemy1);
		you.add(userRep);
	}

	@Override
	public void showContents() {
		program.add(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		for (Interactions inter : listOfInter) {
			program.add(inter.getImage());
		}
		program.add(enemy1);
		program.add(you.get(0));

	}

	@Override
	public void hideContents() {
		program.remove(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.remove(you.get(0));
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

	public void actionPerformed(KeyEvent ae) {

		//Checks for...
		//if(gamePaused) { return; }		//Game being paused
		//checkEnemyAttack();				//Enemy attacks
		//if(!getCanMove()) { return; }	//Interaction blocks
		//getNextRoom();
		//Now you can move!
		user.tick();
	}

	public void keyPressed(KeyEvent e) {

		keyInput = e.getKeyCode();

		switch(keyInput) {
		case KeyEvent.VK_W:
			checkCollision();
			user.setDY(-user.getMoveSpeedStat());
			//keyDown[0] = true;
			break;
		case KeyEvent.VK_A:
			checkCollision();
			user.setDX(-user.getMoveSpeedStat());
			//getNextRoom();
			//keyDown[1] = true;
			break;
		case KeyEvent.VK_S:
			checkCollision();
			user.setDY(user.getMoveSpeedStat());
			//getNextRoom();
			//keyDown[2] = true;
			break;
		case KeyEvent.VK_D:
			checkCollision();
			user.setDX(user.getMoveSpeedStat());
			//getNextRoom();
			//keyDown[3] = true;
			break;
		case KeyEvent.VK_E:
			//keyDown[4] = true;
			user.cycleWeapon();
			break;
			//TODO fix this method, moves the user when changing weapons and shouldn't
			/*
			 * case KeyEvent.VK_UP: //keyDown[5] = true; generateHitbox(e); break; case
			 * KeyEvent.VK_LEFT: //keyDown[6] = true; generateHitbox(e); break; case
			 * KeyEvent.VK_DOWN: //keyDown[7] = true; generateHitbox(e); break; case
			 * KeyEvent.VK_RIGHT: //keyDown[8] = true; generateHitbox(e); break;
			 */
		default:
			break;
		}
		
	}

	public void keyReleased(KeyEvent e) {

		keyInput = e.getKeyCode();
		
		switch(keyInput) {
		case KeyEvent.VK_W:
			checkCollision();
			user.setDY(0);
			break;
		case KeyEvent.VK_A:
			checkCollision();
			user.setDX(0);
			break;
		case KeyEvent.VK_S:
			checkCollision();
			user.setDY(0);
			break;
		case KeyEvent.VK_D:
			checkCollision();
			user.setDX(0);
			break;
		default:
			break;
		}

	}

	public void checkCollision() {
		//TODO have some boundary checks called in here	
		Interactions tempWest = new Interactions(interactionType.entry_door_WEST,0,0);
		Interactions tempEast = new Interactions(interactionType.entry_door_EAST,0,0);
		Interactions tempNorth = new Interactions(interactionType.entry_door_NORTH,0,0);
		Interactions tempSouth = new Interactions(interactionType.entry_door_SOUTH,0,0);

		for(Interactions inter : listOfInter) {
			//System.out.println("String = " + inter + " Interaction = " + inter.getImage());
			if(intCollisionTest(inter.getImage())) {
				//make for loop
				System.out.println("touching interaction");
				if(user.getDX() > 0) {
					user.getUserStats().setCoordX(user.getCoordX() - user.getMoveSpeedStat());					
				}
				else if(user.getDX() < 0) {
					user.getUserStats().setCoordX(user.getCoordX() + user.getMoveSpeedStat());					
				}
				else if(user.getDY() > 0) {
					user.getUserStats().setCoordY(user.getCoordY() - user.getMoveSpeedStat());					
				}
				else if(user.getDY() < 0) {
					user.getUserStats().setCoordY(user.getCoordY() + user.getMoveSpeedStat());					
				}
			}
		}
	}

	public boolean intCollisionTest(GImage image) {
		return (user.getCoordY() - image.getY() <= 75
				&& user.getCoordY() - image.getY() >= -75
				&& user.getCoordX() - image.getX() <= 75
				&& user.getCoordX() - image.getX() >= -75);
	}

}
