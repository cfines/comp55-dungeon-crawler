package projectFiles;

public class Boss {
	private Stats bossStats;
	private ElementType bossType;
	
	Boss(int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y, ElementType bossType){
		bossStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		this.bossType = bossType;
	}
	
}
