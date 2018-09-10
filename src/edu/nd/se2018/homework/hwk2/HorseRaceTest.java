package edu.nd.se2018.homework.hwk2;
import org.junit.Test;

public class HorseRaceTest {
	@Test
	public void test() {
		Race race = new Race();
		race.addHorse("Secretariat", 0, 20, new EarlySprintStrategy());
		race.addHorse("American Pharaoh", 1, 25, new SlowStartStrategy());
		race.addHorse("Justify", 2, 30, new EarlySprintStrategy());
		race.addHorse("Affirmed", 3, 35, new SlowStartStrategy());
		race.addHorse("Seattle Slew", 4, 40, new SteadyRunStrategy());
		race.startRace();
		assert (race.getWinner() == "Seattle Slew");

		Race race2 = new Race();
		race2.addHorse("Nick", 0, 100, new EarlySprintStrategy());
		race2.addHorse("Kevin", 1, 75, new SlowStartStrategy());
		race2.addHorse("Katie", 2, 120, new EarlySprintStrategy());
		race2.addHorse("Sam", 3, 85, new SlowStartStrategy());
		race2.addHorse("Jess", 4, 110, new SteadyRunStrategy());
		race2.startRace();
		assert (race2.getWinner() == "Katie");
		
		Race race3 = new Race();
		race3.addHorse("Hart", 0, 50, new EarlySprintStrategy());
		race3.addHorse("Harold", 1, 50, new SlowStartStrategy());
		race3.addHorse("Harry", 2, 50, new SteadyRunStrategy());
		race3.addHorse("Heather", 3, 49, new SlowStartStrategy());
		race3.addHorse("Hector", 4, 49, new SteadyRunStrategy());
		race3.startRace();
		assert (race3.getWinner() == "Hart");
	}
}
