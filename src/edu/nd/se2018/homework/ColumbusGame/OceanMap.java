package edu.nd.se2018.homework.ColumbusGame;

import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap {

	boolean[][] oceanGrid = new boolean[25][25];
	int dimensions = 25;
	int scalingFactor;
	int islandCount;
	
	
	public OceanMap(int dimensions, int islandCount) {
		this.dimensions = dimensions;
		this.islandCount = islandCount;
	}
	
	public void drawMap(ObservableList<Node> root, int scale) {
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				root.add(rect);
				oceanGrid[x][y] = false;
			}
		}
		
		for (int i = 0; i < this.islandCount; i++) {
			Random rand = new Random();
			int x = rand.nextInt(24);
			int y = rand.nextInt(24);
			if (x == 5 && y == 5) {
				i--;
				continue;
			}
			insertIsland(root, scale, x, y);
		}
	}
	
	public void insertIsland(ObservableList<Node >root, int scale, int x, int y) {
		Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.GREEN);
		root.add(rect);
		oceanGrid[x][y] = true;
	}
	
}
