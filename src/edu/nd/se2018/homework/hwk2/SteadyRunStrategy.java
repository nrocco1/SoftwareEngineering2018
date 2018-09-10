package edu.nd.se2018.homework.hwk2;

public class SteadyRunStrategy extends RaceStrategy {

	public void update(Horse horse) {
		float percent = (float) 0.8;
		int speed = horse.getMaxSpeed();
		float ran = percent * ((float)speed/100);
		horse.updateTotalDistance(ran);
	}
	
}
