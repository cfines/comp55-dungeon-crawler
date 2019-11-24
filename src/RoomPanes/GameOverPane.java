package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import removeLater.GButton;


public class GameOverPane extends GraphicsPane implements ActionListener {
	private GImage gameOver;
	private MainApplication program;
	private GButton returnMenu;
	private GLabel userName  = new GLabel ("", 500,375);
	private int lastFloorNum;
	private File file;
	private GLabel text;
	private String temp = new String("");
	private String name;
	private Formatter x;
	
	public GameOverPane(MainApplication app) throws IOException 
	{
		this.program = app;
		gameOver = new GImage ("Game Over Screen.png",0,0);
		gameOver.setSize(1150,650);
		returnMenu = new GButton("Return to main menu", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 150, 150, 50);
		file = new File("highestScore.txt");
		userName.setColor(Color.RED);
		lastFloorNum = 0;
		text = new GLabel("Enter name of the fallen (press enter when done): ",100,100);
		text.setColor(Color.red);
		text.setLocation(480,325);
		openFile();
		addRecords();
		closeFile();
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
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			FileWriter writer = new FileWriter("highestScores.txt", true);
			writer.write(userName.getLabel().toString());
			writer.write("haha");
			writer.write("\r\n");   // write new line
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(GLabel name) 
	{
		name.setLabel(name.getLabel());
	}
	
	public GLabel getNewName() 
	{
		return userName;
	}
	
	public void nameDone() 
	{
		try {
			FileWriter writer = new FileWriter("highestScores.txt", true);
			writer.write(getNewName().getLabel().toString() + " was last seen on " + program.getFloorNum());
			writer.write("haha");
			writer.write("\r\n");   // write new line
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//getNewName().getLabel() + " was last seen on " + program.getFloorNum()
	}
	
	public void openFile() {
		try {
			x = new Formatter("highScores.txt");
		}
		catch(Exception e) {
			System.out.println("Invalid character");
		}
	}
	
	public void addRecords() {
		x.format("s", getNewName().getLabel() + " was last seen on " + program.getFloorNum());
	}
	
	public void closeFile() {
		x.close();
	}

	public void keyPressed(KeyEvent e) 
	{
		System.out.println("user typin something");
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			temp = "A";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_B:
			temp = "B";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_C:
			temp = "C";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_D:
			temp = "D";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_E:
			temp = "E";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_F:
			temp = "F";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_G:
			temp = "G";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_H:
			temp = "H";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_I:
			temp = "I";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_J:
			temp = "J";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_K:
			temp = "K";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_L:
			temp = "L";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_M:
			temp = "M";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_N:
			temp = "N";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_O:
			temp = "O";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_P:
			temp = "P";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_Q:
			temp = "Q";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_R:
			temp = "R";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_S:
			temp = "S";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_T:
			temp = "T";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_U:
			temp = "U";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_V:
			temp = "V";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_W:
			temp = "W";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_X:
			temp = "X";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_Y:
			temp = "Y";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_Z:
			temp = "Z";
			userName.setLabel(userName.getLabel()+temp);
			setName(userName);
			program.add(getNewName());
			break;
		case KeyEvent.VK_ENTER:
			//nameDone();
			System.out.println("user's name should be pushed into the txt file at this point");
			System.out.println(getNewName().getLabel());
			break;
		default:
			break;
		}
	}
	
	public void KeyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			temp = "";
			break;
		case KeyEvent.VK_B:
			temp = "";
			break;
		case KeyEvent.VK_C:
			temp = "";
			break;
		case KeyEvent.VK_D:
			temp = "";
			break;
		case KeyEvent.VK_E:
			temp = "";
			break;
		case KeyEvent.VK_F:
			temp = "";
			break;
		case KeyEvent.VK_G:
			temp = "";
			break;
		case KeyEvent.VK_H:
			temp = "";
			break;
		case KeyEvent.VK_I:
			temp = "";
			break;
		case KeyEvent.VK_J:
			temp = "";
			break;
		case KeyEvent.VK_K:
			temp = "";
			break;
		case KeyEvent.VK_L:
			temp = "";
			break;
		case KeyEvent.VK_M:
			temp = "";
			break;
		case KeyEvent.VK_N:
			temp = "";
			break;
		case KeyEvent.VK_O:
			temp = "";
			break;
		case KeyEvent.VK_P:
			temp = "";
			break;
		case KeyEvent.VK_Q:
			temp = "";
			break;
		case KeyEvent.VK_R:
			temp = "";
			break;
		case KeyEvent.VK_S:
			temp = "";
			break;
		case KeyEvent.VK_T:
			temp = "";
			break;
		case KeyEvent.VK_U:
			temp = "";
			break;
		case KeyEvent.VK_V:
			temp = "";
			break;
		case KeyEvent.VK_W:
			temp = "";
			break;
		case KeyEvent.VK_X:
			temp = "";
			break;
		case KeyEvent.VK_Y:
			temp = "";
			break;
		case KeyEvent.VK_Z:
			temp = "";
			break;
		case KeyEvent.VK_ENTER:
			program.switchToMenu();
			break;
		default:
			break;
		}
	}

}
