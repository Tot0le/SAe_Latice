package latice.gui.model;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import latice.gui.controller.GameController;
import latice.model.Position;
import latice.model.Rack;
import latice.model.tile.Tile;
import latice.util.ImageLoader;
import latice.util.ImagePath;
//TODO see if an interface for the IHMs class would be good
public class RackIhm extends GridPane{
	Image cellBackgroundImg;

	Rack rack;
	
	ArrayList<Tile> tilesIhm;
	
	public RackIhm(Rack rack) {
		this.rack = rack;
		
		this.cellBackgroundImg = ImageLoader.loadImageSafe(ImagePath.RACK_CELL.imagePath(), 90, 90);
		
		ImageView cellImgView;
		for (int tileIndex = 0; tileIndex < rack.maxTiles(); tileIndex++) {
			cellImgView = new ImageView(cellBackgroundImg);
			this.add(cellImgView, tileIndex, 0);
		}
		
		// Set the rack in the middle
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        
        //debug color
		//this.setStyle("-fx-background-color:#00ff00; -fx-opacity:1;"); 
        
		this.setStyle("-fx-background-color:#0a10ad; -fx-opacity:1;"); 
		
		this.setPadding(new Insets(10));
		this.setHgap(10);
	}
	/**
	 * Methods must be called when something change on the rack
	 */
	public void updateRackTiles() {
		tilesIhm = this.rack.getTiles();
		Image image;
		ImageView cellImgView;
		
		// Reset the ihm rack
		for (int tileIndex = 0; tileIndex < rack.maxTiles(); tileIndex++) {
			cellImgView = new ImageView(cellBackgroundImg);
			this.add(cellImgView, tileIndex, 0);
		}
		
		for (int tileIndex = 0; tileIndex < rack.tilesAmount(); tileIndex++) {
			image = ImageLoader.loadImageSafe(tilesIhm.get(tileIndex).path().imagePath(), 90, 90);
			cellImgView = new ImageView(image);
			this.add(cellImgView, tileIndex, 0);
		}
		
		// Debug prints
		//System.out.println("rack tiles amounts : " + this.rack.tilesAmounts());
		//System.out.println("image amount : " + images.size());
	}

	public void bindController(GameController gameController) {
		this.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			Node clickedNode = (Node) event.getTarget();
			if (clickedNode instanceof ImageView) {
				Integer columnIndex = GridPane.getColumnIndex(clickedNode);
				if (columnIndex != null) {
					gameController.handleTileSelection(columnIndex);
				}
			}
		});
	}
}
