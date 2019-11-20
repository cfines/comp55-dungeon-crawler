package starter;


/* File: GraphicsPane.java
 * -----------------------
 * Like you did with your own graphics programs, simply
 * extend from GraphicsPane and implement
 * as little or as much of the mouse listeners that you need
 * for your own programs.  Notice however that in this situation
 * There is no access to the GraphicsProgram window.
 * Make sure to distinguish between your constructor
 * and using showContents and hideContents
 */

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import acm.graphics.GImage;
import acm.graphics.GObject;

public abstract class GraphicsPane implements Interfaceable {
	private GImage userRep;
	private User user;
	private boolean moveUp,moveDown,moveLeft,moveRight,swapWep,atkUp,atkDown,atkLeft,atkRight;
	private HashMap<Interactions, Coordinates> interactionHash = new HashMap<Interactions, Coordinates>();
	
	@Override
	public abstract void showContents();

	@Override
	public abstract void hideContents();

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyInput = e.getKeyCode();
		switch(keyInput) 
		{
		case KeyEvent.VK_W:
			checkCollision();
			moveUp = true;
			if(moveUp == true) 
			{
				userRep.move(0, -user.getMoveSpeedStat());
			}
			break;
		case KeyEvent.VK_A:
			checkCollision();
			moveLeft = true;
			if(moveLeft==true) 
			{	
				userRep.move(-user.getMoveSpeedStat(),0);
			}
			break;
		case KeyEvent.VK_S:
			checkCollision();
			moveDown = true;
			if(moveDown == true) 
			{
				userRep.move(0, user.getMoveSpeedStat());
			}
			break;
		case KeyEvent.VK_D:
			checkCollision();
			moveRight = true;
			if(moveRight == true) 
			{
				userRep.move(user.getMoveSpeedStat(),0);
			}
			//TODO attacks go here
		case KeyEvent.VK_E:
			break;
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		default:
			break;	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int keyInput = e.getKeyCode();
		
		if(keyInput == KeyEvent.VK_W) {
			moveUp = false;
		}
		if(keyInput == KeyEvent.VK_A) {
			moveLeft = false;
		}
		if(keyInput == KeyEvent.VK_S) {
			moveDown = false;
		}
		if(keyInput == KeyEvent.VK_D) {
			moveRight = false;
		}
		if(keyInput == KeyEvent.VK_E) {
			swapWep = false;
		}
		if(keyInput == KeyEvent.VK_UP) {
			atkUp = false;		
		}
		if(keyInput == KeyEvent.VK_LEFT) {
			atkLeft = false;
		}
		if(keyInput == KeyEvent.VK_DOWN) {
			atkDown = false;
		}
		if(keyInput == KeyEvent.VK_RIGHT) {
			atkRight = false;
		}
		//TODO attacks go here
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void checkCollision() 
	{
		for(Interactions inter : interactionHash.keySet()) {	
			if(user.getX() > 0) {
				user.getUserStats().setCoordX(user.getCoordX() - user.getMoveSpeedStat());					
			}
			else if(user.getX() < 0) {
				user.getUserStats().setCoordX(user.getCoordX() + user.getMoveSpeedStat());					
			}
			else if(user.getY() > 0) {
				user.getUserStats().setCoordY(user.getCoordY() - user.getMoveSpeedStat());					
			}
			else if(user.getY() < 0) {
				user.getUserStats().setCoordY(user.getCoordY() + user.getMoveSpeedStat());					
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
