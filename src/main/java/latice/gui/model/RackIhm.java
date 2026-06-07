package latice.gui.model;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import latice.gui.controller.GameController;
import latice.model.Player;
import latice.model.Rack;
import latice.model.tile.Tile;
import latice.util.ImageLoader;
import latice.util.ImagePath;
//TODO see if an interface for the IHMs class would be good
public class RackIhm extends GridPane{
	Image cellBackgroundImg;

	ArrayList<Tile> tilesRack;
	
	public RackIhm() {
		Rack rack = new Rack();
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
	public void updateRackTiles(Player player) {
		Rack rack = player.rack();
		tilesRack = rack.getTiles();
		Image image;
		ImageView cellImgView;
		
		// Reset the ihm rack
		this.getChildren().clear();
		
		for (int tileIndex = 0; tileIndex < rack.maxTiles(); tileIndex++) {
			cellImgView = new ImageView(cellBackgroundImg);
			this.add(cellImgView, tileIndex, 0);
		}
		
		Tile currentTile;
		
		for (int tileIndex = 0; tileIndex < tilesRack.size(); tileIndex++) {
			currentTile = tilesRack.get(tileIndex);
			if (currentTile == null) {
				image = ImageLoader.loadImageSafe(ImagePath.RACK_CELL.imagePath(), 90, 90);
			} else {
				image = ImageLoader.loadImageSafe(currentTile.path(), 90, 90);
			}
			
			cellImgView = new ImageView(image);
			this.add(cellImgView, tileIndex, 0);
		}
	}
	
	public void highlightTile(Integer selectedTileIndex, boolean enable) {
		if (selectedTileIndex != null) {
			for (Node childNode : this.getChildren()) {
				if (childNode instanceof ImageView) {
					Integer currentColumnIndex = GridPane.getColumnIndex(childNode);
					if (currentColumnIndex != null && currentColumnIndex.equals(selectedTileIndex)) {
						if (enable) {
							// apply white drop shadow to simulate a border
							childNode.setStyle("-fx-effect: dropshadow(three-pass-box, white, 8, 0.8, 0, 0);");
						} else {
							childNode.setStyle("");
						}
						
					}
				}
			}
		}
	}
	
	public void clearHighlights() {
		for (Node childNode : this.getChildren()) {
			if (childNode instanceof ImageView) {
				childNode.setStyle("");
			}
		}
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
