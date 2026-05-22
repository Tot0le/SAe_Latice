package latice.gui.model;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import latice.model.Rack;
import latice.model.tile.Tile;
import latice.util.ImageLoader;
import latice.util.ImagePath;

public class RackIhm extends GridPane{
	Image cellImg;
	
	ImageView cellImg1View;
	ImageView cellImg2View;
	ImageView cellImg3View;
	ImageView cellImg4View;
	ImageView cellImg5View;

	Rack rack;
	
	ArrayList<Tile> tiles;
	
	public RackIhm(Rack rack) {
		this.rack = rack;
		
		this.cellImg = ImageLoader.loadImageSafe(ImagePath.RACK_CELL.imagePath(), 90, 90);
		
		this.cellImg1View = new ImageView(cellImg);
		this.cellImg2View = new ImageView(cellImg);
		this.cellImg3View = new ImageView(cellImg);
		this.cellImg4View = new ImageView(cellImg);
		this.cellImg5View = new ImageView(cellImg);
		
		GridPane.setColumnIndex(this.cellImg1View, 0);
		GridPane.setColumnIndex(this.cellImg2View, 1);
		GridPane.setColumnIndex(this.cellImg3View, 2);
		GridPane.setColumnIndex(this.cellImg4View, 3);
		GridPane.setColumnIndex(this.cellImg5View, 4);
		
		this.getChildren().addAll(this.cellImg1View,this.cellImg2View,this.cellImg3View,this.cellImg4View,this.cellImg5View);
		
		
		// Set the rack in the middle
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        
        //debug color
		//this.setStyle("-fx-background-color:#00ff00; -fx-opacity:1;"); 
        
		this.setStyle("-fx-background-color:#0a10ad; -fx-opacity:1;"); 
		
		this.setPadding(new Insets(10));
		this.setHgap(10);
	}
	
	public void updateRackTiles() {
		tiles = this.rack.getTiles();
		ArrayList<Image> images = new ArrayList<>();
		Image image;
		
		for (int i = 0; i < this.rack.tilesAmounts(); i++) {
			image = ImageLoader.loadImageSafe(tiles.get(i).path().imagePath(), 90, 90);
			images.add(i, image);
		}
		
		System.out.println("rack tiles amounts : " + this.rack.tilesAmounts());
		System.out.println("image amount : " + images.size());
		
		if (this.rack.tilesAmounts() >= 1) {
			this.cellImg1View.setImage(images.get(0));
		}
		if (this.rack.tilesAmounts() >= 2) {
			this.cellImg2View.setImage(images.get(1));
		}
		if (this.rack.tilesAmounts() >= 3) {
			this.cellImg3View.setImage(images.get(2));
		}
		if (this.rack.tilesAmounts() >= 4) {
			this.cellImg4View.setImage(images.get(3));
		}
		if (this.rack.tilesAmounts() >= 5) {
			this.cellImg5View.setImage(images.get(4));
		}
	}
}
