package edu.nd.se2018.homework.hwk2;

public class Main {
	
	public static void main(String[] args) {
		Race race = new Race();
		race.addHorse("Secretariat", 0, 90, new EarlySprintStrategy());
		race.addHorse("American Pharaoh", 1, 95, new SlowStartStrategy());
		race.addHorse("Justify", 2, 100, new EarlySprintStrategy());
		race.addHorse("Affirmed", 3, 105, new SlowStartStrategy());
		race.addHorse("Seattle Slew", 4, 110, new SteadyRunStrategy());
		race.startRace();
	}
	
}
