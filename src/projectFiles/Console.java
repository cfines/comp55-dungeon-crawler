package projectFiles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import acm.program.GraphicsProgram;

public class Console extends GraphicsProgram implements KeyListener{
	private Map map;
	private Room room;
	
	public static void main(String[] args) {
		Console cg = new Console();
		cg.playGame();
	}
	
	public void playGame() {
		addKeyListeners();
			
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
}
