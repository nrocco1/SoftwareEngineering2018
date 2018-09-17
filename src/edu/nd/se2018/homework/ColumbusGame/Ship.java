package edu.nd.se2018.homework.ColumbusGame;

import java.util.Observable;

import javafx.scene.effect.Light.Point;

public class Ship extends Observable {
	
	Point currentLocation;
	OceanMap oceanMap;
	
	public Ship(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		this.currentLocation = new Point();
		currentLocation.setX(5);
		currentLocation.setY(5);
	}
	
	public Point getShipLocation() {
		return currentLocation;
	}
	
	public void goEast() {
		if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()+1][(int) this.currentLocation.getY()] != true) {
			this.currentLocation.setX(this.currentLocation.getX()+1);
		}
		setChanged();
		notifyObservers();
	}
	
	public void goWest() {
		if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()-1][(int) this.currentLocation.getY()] != true) {
			this.currentLocation.setX(this.currentLocation.getX()-1);
		}
		setChanged();
		notifyObservers();
	}
	
	public void goNorth() {
		if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()][(int) this.currentLocation.getY()-1] != true) {
			this.currentLocation.setY(this.currentLocation.getY()-1);
		}
		setChanged();
		notifyObservers();
	}
	
	public void goSouth() {
		if (this.oceanMap.oceanGrid[(int) this.currentLocation.getX()][(int) this.currentLocation.getY()+1] != true) {
			this.currentLocation.setY(this.currentLocation.getY()+1);
		}
		setChanged();
		notifyObservers();
	}

}
