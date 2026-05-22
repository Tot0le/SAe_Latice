package latice.gui.model;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import latice.gui.controller.GameController;
import latice.model.Rack;
import latice.model.tile.Tile;
import latice.util.ImageLoader;
import latice.util.ImagePath;

public class RackIhm extends GridPane{
	Image cellImg;
	
	ImageView cellImgView;
	
	Rack rack;
	
	ArrayList<Tile> tiles;
	
	public RackIhm(Rack rack) {
		this.rack = rack;
		
		this.cellImg = ImageLoader.loadImageSafe(ImagePath.RACK_CELL.imagePath(), 90, 90);
		
		for (int i = 0; i < 5; i++) {
			cellImgView = new ImageView(cellImg);
			this.add(cellImgView, i, 0);
		}
		
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
		
		for (int i = 0; i < this.rack.tilesAmounts(); i++) {
			cellImgView = new ImageView(images.get(i));
			this.add(cellImgView, i, 0);
		}
		
		// Debug prints
		//System.out.println("rack tiles amounts : " + this.rack.tilesAmounts());
		//System.out.println("image amount : " + images.size());
	}

	public void bindController(GameController gameController) {
		this.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			//TODO 
		});
	}
}
