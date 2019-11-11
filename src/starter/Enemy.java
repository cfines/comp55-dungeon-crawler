package starter;

public class Enemy {
	private Coordinates startUp;
	private Stats enemyStats;
	private int moveSpeedStat;
	private double powerStat;
	private ElementType enemyType;
	private int x, y;
	
	Enemy(){};
	
	Enemy (int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y, ElementType bossType)
	{
		enemyStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 5;
		powerStat = 5;
		this.enemyType = bossType;
	}
	
	public ElementType getEnemyType() 
	{
		return enemyType;
	}
	
	public void setStartX(int x) {
		startUp.setX(x);
	}
	
	public void setStartY(int y) {
		startUp.setY(y);
	}
	
	public double getCoordX() {
		return startUp.getX();
	}
	
	public double getCoordY() {
		return startUp.getY();
	}
	
	public void tick() {
		setStartX(5);
		setStartY(5);
	}
	
	public static void main(String[] args) {
		Enemy derp = new Enemy();
		derp.enemyStats = new Stats(5, 5, 5, 5, 5, 5);
		System.out.println("X: " + derp.getCoordX() + " Y: " + derp.getCoordY());
		//Checking if tick updates enemy location
		while(1<5) {
			derp.tick();
			System.out.println("X: " + derp.getCoordX() + " Y: " + derp.getCoordY());
		}
	}
}
