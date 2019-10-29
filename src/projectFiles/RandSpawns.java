package projectFiles;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

//this is a class that is to only be filled with hard-coded randomized spawns
//separate from Map for the sake of making Map look less ugly
//only to be called from Map

public class RandSpawns {
	//instance variables
	private ArrayList <Map> randomSpawn;
	private Map coords; 
	
	RandSpawns()
	{
		coords = new Map(0,0);
	}
	
	//stores random spawn values into an array list
	public void UHaulStorage() 
	{
		//for testing purposes
		int x =(int)Math.random(), y = (int)Math.random();
		coords = new Map(x,y);
		
	}
	
	//returns the random spawn values from the array list
	public ArrayList<Map> IHaveUHaulMembership() 
	{
		System.out.println("All values: ");
		return randomSpawn;
	}
	
	//for the sake of testing stuff
	public static void Test() 
	{
		RandSpawns testing = new RandSpawns();
		testing.UHaulStorage();
		testing.IHaveUHaulMembership();
	}
	
	
	public static void main(String args[]) 
	{
		RandSpawns test = new RandSpawns();
		test.Test();
	}
}
