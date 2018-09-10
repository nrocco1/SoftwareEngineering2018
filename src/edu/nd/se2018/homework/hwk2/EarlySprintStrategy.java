package edu.nd.se2018.homework.hwk2;

public class EarlySprintStrategy extends RaceStrategy {

	public void update(Horse horse) {
		float percent = 0;
		if (horse.getTotalDistance() <= 2) {
			percent = 1;
		}
		else if (horse.getTotalDistance() > 2) {
			percent = (float) .75;
		}
		int speed = horse.getMaxSpeed();
		float ran = percent * ((float)speed/100);
		horse.updateTotalDistance(ran);
	}
}
