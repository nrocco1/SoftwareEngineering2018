package ChipsChallenge;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGame extends Application {

	Pane root;
	int dimensions = 25;
	Grid grid;
	int width = 1000;
	int height = 625;
	int scale = 25;
	Scene scene;
	Chip chip;
	Monster monster1, monster2, monster3, monster4, monster5;
	Image chipImage, monster1Image, monster2Image, monster3Image, monster4Image, monster5Image;
	ImageView chipImageView, monster1IV, monster2IV, monster3IV, monster4IV, monster5IV;
	ObservableList<Node> ObservableList;
	boolean end = false;
	int level = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage stage = primaryStage;
		grid = new Grid(this.level);
		chip = new Chip(grid);
		root = new AnchorPane();
		ObservableList = root.getChildren();
		grid.drawGrid(ObservableList, scale);
		monster1 = new Monster(grid, chip);
		monster2 = new Monster(grid, chip);
		monster3 = new Monster(grid, chip);
		monster4 = new Monster(grid, chip);
		monster5 = new Monster(grid, chip);
		chip.addObserver(monster1);
		chip.addObserver(monster2);
		chip.addObserver(monster3);
		chip.addObserver(monster4);
		chip.addObserver(monster5);
	
		chipImage = new Image("file:chip/textures/chipDown.PNG", 25, 25, true, true);
		chipImageView = new ImageView(chipImage);
		chipImageView.setX(chip.getChipLocation().x*scale); 
		chipImageView.setY(chip.getChipLocation().y*scale);
		root.getChildren().add(chipImageView);
		
		monster1Image = new Image("file:chip/textures/bugUp.png", 25, 25, true, true);
		monster1IV = new ImageView(monster1Image);
		monster1IV.setX(monster1.getMonsterLocation().x*scale); 
		monster1IV.setY(monster1.getMonsterLocation().y*scale);
		root.getChildren().add(monster1IV);
		
		monster2Image = new Image("file:chip/textures/bugUp.png", 25, 25, true, true);
		monster2IV = new ImageView(monster2Image);
		monster2IV.setX(monster2.getMonsterLocation().x*scale); 
		monster2IV.setY(monster2.getMonsterLocation().y*scale);
		root.getChildren().add(monster2IV);
		
		monster3Image = new Image("file:chip/textures/bugUp.png", 25, 25, true, true);
		monster3IV = new ImageView(monster3Image);
		monster3IV.setX(monster3.getMonsterLocation().x*scale); 
		monster3IV.setY(monster3.getMonsterLocation().y*scale);
		root.getChildren().add(monster3IV);
		
		monster4Image = new Image("file:chip/textures/bugUp.png", 25, 25, true, true);
		monster4IV = new ImageView(monster4Image);
		monster4IV.setX(monster4.getMonsterLocation().x*scale); 
		monster4IV.setY(monster4.getMonsterLocation().y*scale);
		root.getChildren().add(monster4IV);
		
		monster5Image = new Image("file:chip/textures/bugUp.png", 25, 25, true, true);
		monster5IV = new ImageView(monster5Image);
		monster5IV.setX(monster5.getMonsterLocation().x*scale); 
		monster5IV.setY(monster5.getMonsterLocation().y*scale);
		root.getChildren().add(monster5IV);
		
		Text title = new Text(715, 100, "CHIPS CHALLENGE");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
		title.setFill(Color.GREEN);
		root.getChildren().add(title);
		
		Text rules = new Text(715, 150, "Collect all of the keys and avoid monsters to advance!");
		rules.setFont(Font.font ("Verdana", 10));
		rules.setFill(Color.GREEN);
		root.getChildren().add(rules);
		
		Text track = new Text(715, 200, "Keys collected:");
		track.setFont(Font.font ("Verdana", FontWeight.BOLD, 10));
		track.setFill(Color.GREEN);
		root.getChildren().add(track);
		
		scene = new Scene(root, width, height);
		stage.setScene(scene);
		stage.setTitle("Chip's Challenge");
		stage.show();
		startGame(stage);	
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	private void startGame(Stage stage) {
	
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()){
					case RIGHT: 
						chip.goEast();
						root.getChildren().remove(chipImageView);
						right right = new right();
						chipImageView = right.abstractMovement();
						grid.checkGrid(ObservableList, chip.getChipLocation().x, chip.getChipLocation().y);
						grid.boardGrid[chip.getChipLocation().x][chip.getChipLocation().y] = 3;
						break; 
					case LEFT:
						chip.goWest();
						root.getChildren().remove(chipImageView);
						left left = new left();
						chipImageView = left.abstractMovement();
						grid.checkGrid(ObservableList, chip.getChipLocation().x, chip.getChipLocation().y);
						grid.boardGrid[chip.getChipLocation().x][chip.getChipLocation().y] = 3;
						break; 
					case UP:
						chip.goNorth();
						root.getChildren().remove(chipImageView);
						up up = new up();
						chipImageView = up.abstractMovement();
						grid.checkGrid(ObservableList, chip.getChipLocation().x, chip.getChipLocation().y);
						grid.boardGrid[chip.getChipLocation().x][chip.getChipLocation().y] = 3;
						break; 
					case DOWN:
						chip.goSouth();
						root.getChildren().remove(chipImageView);
						down down = new down();
						chipImageView = down.abstractMovement();
						grid.checkGrid(ObservableList, chip.getChipLocation().x, chip.getChipLocation().y);
						grid.boardGrid[chip.getChipLocation().x][chip.getChipLocation().y] = 3;
						break;
					case ESCAPE:
						end = true;
						root.getChildren().clear();
						stage.close();
						break;
					default:
						break;			
					}
					if (end == false) {
						chipImageView.setX(chip.getChipLocation().x*scale); 
						chipImageView.setY(chip.getChipLocation().y*scale);
						root.getChildren().add(chipImageView);
					
						root.getChildren().remove(monster1IV);
						monster1IV.setX(monster1.getMonsterLocation().x*scale); 
						monster1IV.setY(monster1.getMonsterLocation().y*scale);
						root.getChildren().add(monster1IV);
					
						root.getChildren().remove(monster2IV);
						monster2IV.setX(monster2.getMonsterLocation().x*scale); 
						monster2IV.setY(monster2.getMonsterLocation().y*scale);
						root.getChildren().add(monster2IV);
						
						root.getChildren().remove(monster3IV);
						monster3IV.setX(monster3.getMonsterLocation().x*scale); 
						monster3IV.setY(monster3.getMonsterLocation().y*scale);
						root.getChildren().add(monster3IV);
					
						root.getChildren().remove(monster4IV);
						monster4IV.setX(monster4.getMonsterLocation().x*scale); 
						monster4IV.setY(monster4.getMonsterLocation().y*scale);
						root.getChildren().add(monster4IV);
						
						root.getChildren().remove(monster5IV);
						monster5IV.setX(monster5.getMonsterLocation().x*scale); 
						monster5IV.setY(monster5.getMonsterLocation().y*scale);
						root.getChildren().add(monster5IV);
						
						if (chip.getChipLocation().x == grid.finalxLocation && chip.getChipLocation().y == grid.finalyLocation) {
							Text level2 = new Text(775, 390, "LEVEL 2");
							Text win = new Text(775, 450, "WINNER");
							if (level == 0) {
								stage.close();
								level += 1;
								setLevel(level);
								try {
									end = false;
									start(stage);
								} catch (Exception e) {
									e.printStackTrace();
								}
								level2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
								level2.setFill(Color.RED);
								root.getChildren().add(level2);
							}
							else {
								root.getChildren().remove(level2);
								win.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
								win.setFill(Color.RED);
								root.getChildren().add(win);
								root.getChildren().remove(chipImageView);
								end = true;
							}
						}
						if ((chip.getChipLocation().x == monster1.getMonsterLocation().x && chip.getChipLocation().y == monster1.getMonsterLocation().y) || (chip.getChipLocation().x == monster2.getMonsterLocation().x && chip.getChipLocation().y == monster2.getMonsterLocation().y) || (chip.getChipLocation().x == monster3.getMonsterLocation().x && chip.getChipLocation().y == monster3.getMonsterLocation().y) || (chip.getChipLocation().x == monster4.getMonsterLocation().x && chip.getChipLocation().y == monster4.getMonsterLocation().y) || (chip.getChipLocation().x == monster5.getMonsterLocation().x && chip.getChipLocation().y == monster5.getMonsterLocation().y)) {
							Text loss = new Text(700, 500, "A monster caught you!");
							loss.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
							loss.setFill(Color.RED);
							root.getChildren().add(loss);
							end = true;
						}	
					}
				}	
		});	
	}
}
