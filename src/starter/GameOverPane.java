package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GLabel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

import com.sun.glass.events.KeyEvent;

public class GameOverPane extends GraphicsPane implements ActionListener {
	private GImage gameOver;
	private MainApplication program;
	private GButton returnMenu;
	private GLabel userName;
	private int lastFloorNum;
	private File file;
	private GLabel text;
	
	public GameOverPane(MainApplication app) 
	{
		this.program = app;
		gameOver = new GImage ("Game Over Screen.png",0,0);
		gameOver.setSize(1150,650);
		returnMenu = new GButton("Return to main menu", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 150, 150, 50);
		file = new File("highestScore.txt");
		userName = new GLabel ("");
		lastFloorNum = 0;
		text = new GLabel("Enter name of the fallen: ",100,100);
		text.setColor(Color.red);
		text.setLocation(500,325);
		try {
			BufferedReader ob = new BufferedReader(new FileReader(file));
			ob.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void showContents() {
		program.add(gameOver);
		program.add(returnMenu);
		program.add(text);
	}

	@Override
	public void hideContents() {
		program.remove(gameOver);
		program.remove(returnMenu);
		program.remove(text);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == returnMenu) {
			program.switchToTitleScreen();
		}
	}
	
	
	public void keyPressed(KeyEvent e) 
	{
	/*	
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			break;
		case KeyEvent.VK_B:
			break;
		case KeyEvent.VK_C:
			break;
		case KeyEvent.VK_D:
			break;
		case KeyEvent.VK_E:
			break;
		case KeyEvent.VK_F:
			break;
		case KeyEvent.VK_G:
			break;
		case KeyEvent.VK_H:
			break;
		case KeyEvent.VK_I:
			break;
		case KeyEvent.VK_J:
			break;
		case KeyEvent.VK_K:
			break;
		case KeyEvent.VK_L:
			break;
		case KeyEvent.VK_M:
			break;
		case KeyEvent.VK_N:
			break;
		case KeyEvent.VK_O:
			break;
		case KeyEvent.VK_P:
			break;
		case KeyEvent.VK_Q:
			break;
		case KeyEvent.VK_R:
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_T:
			break;
		case KeyEvent.VK_U:
			break;
		case KeyEvent.VK_V:
			break;
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_X:
			break;
		case KeyEvent.VK_Y:
			break;
		case KeyEvent.VK_Z:
			break;
		default:
			break;
		}
		*/
	}
	
	public void KeyReleased(KeyEvent e) 
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
