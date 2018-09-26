package edu.nd.sarec.railwaycrossing.model.vehicles;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;

import edu.nd.sarec.railwaycrossing.model.infrastructure.gate.CrossingGate;
import edu.nd.sarec.railwaycrossing.view.CarImageSelector;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents Car object
 * @author jane
 *
 */
public class Car extends Observable implements IVehicle, Observer{
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private double leadCarY = -1;  // Current Y position of car directly infront of this one
	private double speed = 0.5;
	private boolean crossing = false;
	Set<Car> belowTracks = new HashSet<Car>();
		
	/**
	 * Constructor
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 */
	public Car(int x, int y){
		this.currentX = x;
		this.currentY = y;
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());

		Random rand = new Random();
		int n = rand.nextInt(5)+1;
		if (n == 1 && getVehicleX() == 791) {
			crossing = true;
		}
	}
		
	@Override
	public Node getImageView() {
		return ivCar;
	}
	
	public boolean gateIsClosed(){
		return gateDown;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	public double getVehicleY(){
		return currentY;
	}
	
	public void move(){
		boolean canMove = true; 
		
		if (getVehicleY() > 550 && getVehicleX() < 391) {
			belowTracks.add(this);
		}
		
		// First case.  Car is at the front of the stopping line.
		if (gateDown && getVehicleY() < 430 && getVehicleY()> 390)
			canMove = false;
		
		// Second case. Car is too close too other car.
		if (leadCarY != -1  && getDistanceToLeadCar() < 50)
			canMove = false;
		
		if (canMove && !crossing){
			currentY+=speed;
			ivCar.setY(currentY);
		}
		else if (canMove && crossing) {
			if (getVehicleY() > 650) {
				if (getVehicleX() < 391) {
					crossing = false;
				}
				else {
					deleteObservers();
					currentX-=speed;
					ivCar.setX(currentX);
				}
				
			}
			else {
				if (belowTracks.isEmpty()) {
					currentY+=speed;
					ivCar.setY(currentY);
				}
			}
		}
		
		if (!crossing) {
			setChanged();
			notifyObservers();
		}
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setGateDownFlag(boolean gateDown){
		this.gateDown = gateDown;
	}
	
	public boolean offScreen(){
		if (currentY > 1020)
			return true;
		else
			return false;			
	}
		
	public void reset(){
		currentY = originalY;
	}
	
	public double getDistanceToLeadCar(){
		return Math.abs(leadCarY-getVehicleY());
	}
	
	public void removeLeadCar(){
		leadCarY = -1;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car){
			leadCarY = (((Car)o).getVehicleY());
			if (leadCarY > 1020)
				leadCarY = -1;
		}
			
		if (o instanceof CrossingGate){
			CrossingGate gate = (CrossingGate)o;
			if(gate.getTrafficCommand()=="STOP")			
				gateDown = true;
			else
				gateDown = false;
					
		}				
	}	
}
