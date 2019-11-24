package RoomPanes;

import java.util.Formatter;

public class CreateTextfile {
	private Formatter x;
	public void openFile() {
		try {
			x = new Formatter("highScores.txt");
		}
		catch(Exception e) {
			System.out.println("Invalid character");
		}
	}
	
	public void addRecords() {
		x.format("s", "");
	}
	
	public void closeFile() {
		x.close();
	}
}
