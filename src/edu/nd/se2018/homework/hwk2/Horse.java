package edu.nd.se2018.homework.hwk2;

public class Horse {

	private String name = "";
	private int horseNumber = -1;
	private int maxSpeed = -1;
	private RaceStrategy strategy = null;
	private float totalDistance = 0;
	
	public Horse(String name, int horseNumber, int maxSpeed, RaceStrategy strategy) {
		this.name = name;
		this.horseNumber = horseNumber;
		this.maxSpeed = maxSpeed;
		this.strategy = strategy;
	}
	
	public void setStrategy(RaceStrategy strategy) {
		this.strategy = strategy;
	}
	
	public RaceStrategy getStrategy() {
		return this.strategy;
	}
	
	public int getMaxSpeed() {
		return this.maxSpeed;
	}

	public void updateTotalDistance(float distance) {
		this.totalDistance += distance;
	}
	
	public void printDistance() {
		String d = Float.toString(this.totalDistance);
		String s = this.name + " has run a total of " + d + " miles.";
		System.out.println(s);
	}
	
	public float getTotalDistance() {
		return this.totalDistance;
	}
	
	public String getName() {
		return this.name;
	}
	
}
