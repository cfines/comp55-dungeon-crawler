package starter;

public class Enemy {
	private Stats enemyStats;
	private int moveSpeedStat;
	private double powerStat;
	private ElementType enemyType;
	private double dx, dy;
	
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
	
	public void setDX(double dx) {
		this.dx = dx;
	}
	
	public void setDY(double dy) {
		this.dy = dy;
	}
	
	public double getCoordX() {
		return enemyStats.getCoordX();
	}
	
	public double getCoordY() {
		return enemyStats.getCoordY();
	}
	
	public void tick() {
		
		enemyStats.setCoordX(enemyStats.getCoordX() + dx);
		enemyStats.setCoordY(enemyStats.getCoordY() + dy);
	}
	
	public static void main(String[] args) {
		Enemy derp = new Enemy();
		derp.enemyStats = new Stats(5, 5, 5, 5, 5, 5);
		derp.setDX(5);
		derp.setDY(5);
		System.out.println("X: " + derp.getCoordX() + " Y: " + derp.getCoordY());
		//Checking if tick updates enemy location
		while(1<5) {
			derp.tick();
			System.out.println("X: " + derp.getCoordX() + " Y: " + derp.getCoordY());
		}
		
	}
}
