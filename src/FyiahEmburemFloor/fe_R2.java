package FyiahEmburemFloor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GRect;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import removeLater.User;

public class fe_R2 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private int degree;
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	
	
	public fe_R2(MainApplication app) {
		this.program = app;
		user = program.getUser();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}

}
