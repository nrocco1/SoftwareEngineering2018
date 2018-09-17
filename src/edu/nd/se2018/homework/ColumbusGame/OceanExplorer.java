package edu.nd.se2018.homework.ColumbusGame;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class OceanExplorer extends Application {
	boolean[][] islandMap;
	Pane root;
	final int dimensions = 25;
	final int islandCount = 25;
	final int scalingFactor = 20;
	Image shipImage;
	ImageView shipImageView;
	OceanMap oceanMap;
	Scene scene;
	Ship ship;
	PirateShip pirate1;
	Image pirateImage1;
	ImageView pirateImageView1;
	PirateShip pirate2;
	Image pirateImage2;
	ImageView pirateImageView2;
	
	@Override
	public void start(Stage oceanStage) throws Exception{
		oceanMap = new OceanMap(dimensions, islandCount);
		//islandMap = oceanMap.getMap();
		
		root = new AnchorPane();
		drawMap();
		
		ship = new Ship(oceanMap);
		loadShipImage();
		
		pirate1 = new PirateShip(oceanMap);
		pirate2 = new PirateShip(oceanMap);
		loadPirateShipImages();
		
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);
		
		scene = new Scene(root, 500, 500);
		oceanStage.setTitle("Christopher Columbus");
		oceanStage.setScene(scene);
		oceanStage.show();
		startSailing();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void drawMap() {
		this.oceanMap.drawMap(this.root.getChildren(), this.scalingFactor);
	}
	
	public void loadPirateShipImages() {
		this.pirateImage1 = new Image("file:///Users/NickRocco/eclipse-workspace/ColumbusGame/src/application/pirateship.gif", 20, 20, true, true);
		this.pirateImage2 = new Image("file:///Users/NickRocco/eclipse-workspace/ColumbusGame/src/application/pirateship.gif", 20, 20, true, true);
		this.pirateImageView1 = new ImageView(pirateImage1);
		this.pirateImageView2 = new ImageView(pirateImage2);
		pirateImageView1.setX(this.pirate1.getPirateLocation().getX()*this.scalingFactor);
		pirateImageView1.setY(this.pirate1.getPirateLocation().getY()*this.scalingFactor);
		pirateImageView2.setX(this.pirate2.getPirateLocation().getX()*this.scalingFactor);
		pirateImageView2.setY(this.pirate2.getPirateLocation().getY()*this.scalingFactor);
		root.getChildren().add(pirateImageView1);
		root.getChildren().add(pirateImageView2);
	}
	
	public void loadShipImage() {
		this.shipImage = new Image("file:///Users/NickRocco/eclipse-workspace/ColumbusGame/src/application/ColumbusShip.png", 20, 20, true, true);
		this.shipImageView = new ImageView(shipImage);
		shipImageView.setX(this.ship.getShipLocation().getX()*this.scalingFactor);
		shipImageView.setY(this.ship.getShipLocation().getY()*this.scalingFactor);
		root.getChildren().add(shipImageView);
	}
	
	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				switch (ke.getCode()) {
					case RIGHT:
						ship.goEast();
						break;
					case LEFT:
						ship.goWest();
						break;
					case UP:
						ship.goNorth();
						break;
					case DOWN:
						ship.goSouth();
						break;
					default:
						break;
				}
				shipImageView.setX(ship.getShipLocation().getX()*scalingFactor);
				shipImageView.setY(ship.getShipLocation().getY()*scalingFactor);
				pirateImageView1.setX(pirate1.getPirateLocation().getX()*scalingFactor);
				pirateImageView1.setY(pirate1.getPirateLocation().getY()*scalingFactor);
				pirateImageView2.setX(pirate2.getPirateLocation().getX()*scalingFactor);
				pirateImageView2.setY(pirate2.getPirateLocation().getY()*scalingFactor);
			}
		});
	}
}
