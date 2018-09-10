package edu.nd.se2018.homework.hwk2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Race {

	List<Horse> horses = new ArrayList<Horse>();
	private String winner = "";
	
	public Race() {}
	
	public void addHorse(String name, int horseNumber, int maxSpeed, RaceStrategy strategy) {
		Horse h = new Horse(name, horseNumber, maxSpeed, strategy);
		horses.add(h);
	}
	
	public void startRace() {
		runRace();
	}
	
	public void runRace() {
		int allFinished = 0;
		while (allFinished != this.horses.size()) {
			for (Horse h:horses) {
				if (h.getTotalDistance() >= 10) {
					continue;
				}
				RaceStrategy s = h.getStrategy();
				s.update(h);
				if (h.getTotalDistance() > 2 && h.getTotalDistance() <= 3) {
					h.printDistance();
				}
				else if (h.getTotalDistance() > 5 && h.getTotalDistance() <=6) {
					h.printDistance();
				}
				else if (h.getTotalDistance() > 9 && h.getTotalDistance() <= 10) {
					h.printDistance();
				}
				if (h.getTotalDistance() >= 10) {
					allFinished += 1;
				}
				if (h.getTotalDistance() >= 10 && allFinished == 1) {
					this.printWinner(h);
				}
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printWinner(Horse h) {
		String horse = h.getName();
		String s = horse + " has won the race!";
		this.winner = horse;
		System.out.println(s);
	}
	
	public String getWinner() {
		return this.winner;
	}
	
}
