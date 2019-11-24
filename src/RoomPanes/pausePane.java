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
	private ArrayList<GLabel> labelArr = new ArrayList<GLabel>();
	private GLabel baseFloor = new GLabel("BASE", 10 ,97);
	private GLabel osvaldoomFloor = new GLabel("OSVALDOOM", 10, 172);
	private GLabel shadowRealm = new GLabel("SHADOW_REALM", 10, 247);
	private GLabel lucinaDomain = new GLabel("PEPPA_PIG", 10, 322);
	private GLabel schtanlyParable = new GLabel("SCHTANLY_PARABLE", 10, 397);
	private GLabel bombFloor = new GLabel("BOMB_FLOOR", 10, 472);
	private GButton BF_R1 = new GButton("R1", 10, 100, 50, 50);
	private GButton BF_RKey = new GButton("KEY", 65, 100, 50, 50);
	private GButton BF_RBoss = new GButton("BOSS", 120, 100, 50, 50);
	private GButton OF_R1 = new GButton("R1", 10, 175, 50, 50);
	private GButton OF_RKey = new GButton("KEY", 65, 175, 50, 50);
	private GButton OF_RBoss = new GButton("BOSS", 120, 175, 50, 50);
	private GButton SR_R1 = new GButton("R1", 10, 250, 50, 50);
	private GButton SR_RKey = new GButton("KEY", 65, 250, 50, 50);
	private GButton SR_RBoss = new GButton("BOSS", 120, 250, 50, 50);
	private GButton LD_R1 = new GButton("R1", 10, 325, 50, 50);
	private GButton LD_RKey = new GButton("KEY", 65, 325, 50, 50);
	private GButton LD_RBoss = new GButton("BOSS", 120, 325, 50, 50);
	private GButton SP_R1 = new GButton("R1", 10, 400, 50, 50);
	private GButton SP_RKey = new GButton("KEY", 65, 400, 50, 50);
	private GButton SP_RBoss = new GButton("BOSS", 120, 400, 50, 50);
	private GButton BOMB_R1 = new GButton("R1", 10, 475, 50, 50);
	private GButton BOMB_Bomb1 = new GButton("BOMB1", 65, 475, 50, 50);
	private GButton BOMB_Bomb2 = new GButton("BOMB2", 120, 475, 50, 50);
	private GButton BOMB_Bomb3 = new GButton("BOMB3", 175, 475, 50, 50);
	
	
	public pausePane(MainApplication app){
		this.program = app;
		pauseButton = new GButton("Quit", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 130, 150, 50);
		resumeButton = new GButton("Resume", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 190, 150, 50);
		pauseLabel = new GLabel("PAUSED", program.WINDOW_WIDTH / 2 - 135 ,85);
		pauseLabel.setColor(Color.red);
		pauseLabel.setFont("Arial-Bold-64");
		
		baseFloor.setColor(Color.white);
		osvaldoomFloor.setColor(Color.cyan);
		shadowRealm.setColor(Color.black);
		lucinaDomain.setColor(Color.orange);
		schtanlyParable.setColor(Color.gray);
		bombFloor.setColor(Color.red);
		labelArr.add(baseFloor);
		labelArr.add(osvaldoomFloor);
		labelArr.add(shadowRealm);
		labelArr.add(lucinaDomain);
		labelArr.add(schtanlyParable);
		labelArr.add(bombFloor);
		
		for(int i = 0; i < labelArr.size(); i++) {
			labelArr.get(i).setFont("Arial-Bold-20");
		}
		
		buttonArr.add(BF_R1);
		buttonArr.add(BF_RKey);
		buttonArr.add(BF_RBoss);
		buttonArr.add(OF_R1);
		buttonArr.add(OF_RKey);
		buttonArr.add(OF_RBoss);
		buttonArr.add(SR_R1);
		buttonArr.add(SR_RKey);
		buttonArr.add(SR_RBoss);
		buttonArr.add(LD_R1);
		buttonArr.add(LD_RKey);
		buttonArr.add(LD_RBoss);
		buttonArr.add(SP_R1);
		buttonArr.add(SP_RKey);
		buttonArr.add(SP_RBoss);
		buttonArr.add(BOMB_R1);
		buttonArr.add(BOMB_Bomb1);
		buttonArr.add(BOMB_Bomb2);
		buttonArr.add(BOMB_Bomb3);
		
		
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
			for(int i = 0; i < labelArr.size(); i++) {
				program.add(labelArr.get(i));
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
			for(int i = 0; i < labelArr.size(); i++) {
				program.remove(labelArr.get(i));
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
