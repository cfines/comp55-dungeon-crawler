package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import removeLater.GButton;

public class pausePane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GButton pauseButton;
	private GButton resumeButton;
	private GLabel pauseLabel;
	
	///////DEVELOPER_MODE_LABELS_AND_BUTTONS/////////////////
	private ArrayList<GButton> buttonArr = new ArrayList<GButton>();
	private GLabel baseFloor;
	private GLabel osvaldoomFloor;
	private GLabel shadowRealm;
	private GLabel lucinaDomain;
	private GLabel schtanlyParable;
	private GLabel bombFloor;
	private GButton BF_R1 = new GButton("R1", 0, 0, 50, 50);
	private GButton BF_RKey = new GButton("KEY", 0, 0, 50, 50);
	private GButton BF_RBoss = new GButton("BOSS", 0, 0, 50, 50);
	private GButton OF_R1 = new GButton("R1", 0, 0, 50, 50);
	private GButton OF_RKey = new GButton("KEY", 0, 0, 50, 50);
	private GButton OF_Boss = new GButton("BOSS", 0, 0, 50, 50);
	private GButton SR_R1 = new GButton("R1", 0, 0, 50, 50);
	private GButton SR_RKey = new GButton("KEY", 0, 0, 50, 50);
	private GButton SR_RBoss = new GButton("BOSS", 0, 0, 50, 50);
	private GButton LD_R1 = new GButton("R1", 0, 0, 50, 50);
	private GButton LD_RKey = new GButton("KEY", 0, 0, 50, 50);
	private GButton LD_RBoss = new GButton("BOSS", 0, 0, 50, 50);
	private GButton SP_R1 = new GButton("R1", 0, 0, 50, 50);
	private GButton SP_RKey = new GButton("KEY", 0, 0, 50, 50);
	private GButton SP_Boss = new GButton("BOSS", 0, 0, 50, 50);
	private GButton BOMB_R1 = new GButton("R1", 0, 0, 50, 50);
	private GButton BOMB_Bomb1 = new GButton("BOMB1", 0, 0, 50, 50);
	private GButton BOMB_Bomb2 = new GButton("BOMB2", 0, 0, 50, 50);
	private GButton BOMB_Bomb3 = new GButton("BOMB3", 0, 0, 50, 50);
	
	
	public pausePane(MainApplication app){
		this.program = app;
		pauseButton = new GButton("Quit", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 130, 150, 50);
		resumeButton = new GButton("Resume", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 190, 150, 50);
		pauseLabel = new GLabel("PAUSED", program.WINDOW_WIDTH / 2 - 135 ,85);
		pauseLabel.setColor(Color.red);
		pauseLabel.setFont("Arial-Bold-64");
		
		
		
	}

	@Override
	public void showContents() {
		program.add(pauseButton);
		program.add(pauseLabel);
		program.add(resumeButton);
		
		if(program.getDeveloperMode()) {
			for(int i = 0; i < buttonArr.size(); i++) {
				program.add(buttonArr.get(i));
			}
		}
		
	}

	@Override
	public void hideContents() {
		program.remove(pauseButton);
		program.remove(pauseLabel);
		program.remove(resumeButton);
		
		if(program.getDeveloperMode()) {
			for(int i = 0; i < buttonArr.size(); i++) {
				program.remove(buttonArr.get(i));
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			program.noLongerPaused();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == pauseButton) {
			program.switchToMenu();
		} else if (obj == resumeButton) {
			program.noLongerPaused();
		}
	}
	
	
	
}
