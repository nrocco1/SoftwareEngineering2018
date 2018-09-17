package edu.nd.se2018.homework.Columbusgame;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.effect.Light.Point;

public class PirateShip implements Observer {

	Point currentLocation;
	OceanMap oceanMap;
	Point targetPosition;
	Random rand = new Random();
	int scalingFactor = 20;
	
	public PirateShip(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		this.currentLocation = new Point();
		this.currentLocation.setX(rand.nextInt(24));
		this.currentLocation.setY(rand.nextInt(24));
	}
	
	public Point getPirateLocation() {
		return this.currentLocation;
	}
	
	public void moveShip() {
		if (targetPosition.getX() - currentLocation.getX() > 0) {
			if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()+1][(int) this.currentLocation.getY()] != true) {
				this.currentLocation.setX(this.currentLocation.getX()+1);
			}
		}
		else {
			if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()-1][(int) this.currentLocation.getY()] != true) {
				this.currentLocation.setX(this.currentLocation.getX()-1);
			}
		}
		if (targetPosition.getY() - currentLocation.getY() > 0) {
			if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()][(int) this.currentLocation.getY()+1] != true) {
				this.currentLocation.setY(this.currentLocation.getY()+1);
			}
		}
		else {
			if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()][(int) this.currentLocation.getY()-1] != true) {
				this.currentLocation.setY(this.currentLocation.getY()-1);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof Ship) {
			targetPosition = ((Ship)o).getShipLocation();
			moveShip();
		}
	}
	
}
