package edu.nd.se2018.homework.hwk2;

public class SlowStartStrategy extends RaceStrategy {

	public void update(Horse horse) {
		float percent = 0;
		if (horse.getTotalDistance() <= 6) {
			percent = (float).75;
		}
		else if (horse.getTotalDistance() > 6 && horse.getTotalDistance() <= 9) {
			percent = (float) .90;
		}
		else {
			percent = (float) 1;
		}
		int speed = horse.getMaxSpeed();
		float ran = percent * ((float)speed/100);
		horse.updateTotalDistance(ran);
	}
	
}
