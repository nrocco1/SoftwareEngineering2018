package edu.nd.sarec.railwaycrossing.model.infrastructure.gate;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import edu.nd.sarec.railwaycrossing.model.vehicles.Train;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Context class for Crossing Gate
 * @author jane
 *
 */
public class CrossingGate extends Observable implements Observer{
	
	// Crossing Gate location and its trigger & exit points
	private int anchorX;
	private int anchorY;
	private int movingX;
	private int movingY;
	private int triggerPointR;
	private int exitPointR;
	private int triggerPointL;
	private int exitPointL;

	private IGateState gateClosed;
	private IGateState gateOpen;
	private IGateState gateClosing;
	private IGateState gateOpening;
	private IGateState currentGateState;
	private Line line; 
	private Pane root;
	Set<Train> inRange = new HashSet<>();
	
	String gateName;
	
	public CrossingGate(){}
	
	public CrossingGate(int xPosition, int yPosition, String crossingGate){		
		anchorX = xPosition;
		anchorY = yPosition;
		movingX = anchorX;
		movingY = anchorY-60;
		triggerPointR = anchorX+250;
		exitPointL = anchorX+250;
		exitPointR = anchorX-250;
		triggerPointL = anchorX-250;
		
		// Gate elements
		line = new Line(anchorX, anchorY,movingX,movingY);
		line.setStroke(Color.RED);
	    line.setStrokeWidth(10);
		
		// Gate States
		gateClosed = new GateClosed(this);
		gateOpen = new GateOpen(this);
		gateOpening = new GateOpening(this);
		gateClosing = new GateClosing(this);
		currentGateState = gateOpen;
		gateName = crossingGate;
	}
	
	public Line getGateLine(){
		return line;
	}
	
	public void operateGate(){
		currentGateState.operate();
	}
	
	public void close(){
		if (movingY<anchorY){		
		    movingX+=1;
		    movingY+=1;
			line.setStartX(anchorX);
			line.setStartY(anchorY);
			line.setEndX(movingX);
			line.setEndY(movingY);
		} else {
			currentGateState.gateFinishedOpening();
		}
	}
	
	public void open(){
		if (movingX>anchorX){
			movingX-=1;
			movingY-=1;		
			line.setStartX(anchorX);
			line.setStartY(anchorY);
			line.setEndX(movingX);
			line.setEndY(movingY);
		}  else {
			currentGateState.gateFinishedOpening();
		}
	}
	
	// State getters and setters
	public IGateState getGateClosedState(){
		return gateClosed;
	}
	public IGateState getGateOpenState(){
		return gateOpen;
	}
	public IGateState getGateClosingState(){
		return gateClosing;
	}
	public IGateState getGateOpeningState(){
		return gateOpening;
	}
	
	public void setGateState(IGateState newState){
		currentGateState = newState;
		setChanged();
		notifyObservers();
	}
	
	public String getTrafficCommand(){
		return currentGateState.getTrafficAction();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Train){
			Train train = (Train)o;
			if (train.getVehicleY() == 475) {
				if (train.getVehicleX() < triggerPointR && train.getVehicleX() > exitPointR) {
					inRange.add(train);
					currentGateState.approachStation();
				}
				else {
					inRange.remove(train);
					if (inRange.isEmpty()) {
						currentGateState.leaveStation();
					}
				}
			}
			if (train.getVehicleY() == 525) {
				if (train.getVehicleX() > triggerPointL && train.getVehicleX() < exitPointL) {
					inRange.add(train);
					currentGateState.approachStation();
				}
				else {
					inRange.remove(train);
					if (inRange.isEmpty()) {
						currentGateState.leaveStation();
					}
				}
			}
		}	
	}
}
