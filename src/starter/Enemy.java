package starter;

public class Enemy {
	private Coordinates startUp;
	private Stats enemyStats;
	private int moveSpeedStat;
	private double powerStat;
	private ElementType enemyType;
	private int dx, dy;
	
	Enemy(){};
	
	Enemy (int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y, ElementType bossType)
	{
		startUp = new Coordinates(input_x, input_y);
		enemyStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 5;
		powerStat = 5;
		this.enemyType = bossType;
	}
	
	public ElementType getEnemyType() 
	{
		return enemyType;
	}
	
	public Stats getEnemyStats() {
		return enemyStats;
	}
	
	public void setStartX(int x) {
		startUp.setX(x);
	}
	
	public void setStartY(int y) {
		startUp.setY(y);
	}
	
	public int getCoordX() {
		return startUp.getX();
	}
	
	public int getCoordY() {
		return startUp.getY();
	}
	
	public void tick() {
		enemyStats.setCoordX(getCoordX() + 5);
		enemyStats.setCoordY(getCoordY() + 5);
		setStartX(getCoordX() + 5);
		setStartY(getCoordY() + 5);
	}
	
	public static void main(String[] args) {
		Enemy derp = new Enemy(5,5,5,5,5,5, ElementType.FIRE);
		derp.setStartX(10);
		derp.setStartY(5);
		System.out.println("X: " + derp.getCoordX() + " Y: " + derp.getCoordY());
		//Checking if tick updates enemy location
		while(1<5) {
			derp.tick();
			System.out.println("X: " + derp.getCoordX() + " Y: " + derp.getCoordY());
		}
	}
}
