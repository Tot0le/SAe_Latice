package latice.gui.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import latice.gui.controller.GameController;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Position;
import latice.model.tile.Tile;
import latice.util.ImageLoader;
import latice.util.ImagePath;

public class GameBoardIhm extends GridPane {
	private GameBoard gameboard;
	private Integer tileSize = 100;
	private final HashSet<Position> tilesPlacedPlacement;

	public GameBoardIhm(GameBoard gameboard) {
		this.gameboard = gameboard;
		this.tilesPlacedPlacement = new HashSet<>();
		
		Image image;
		ArrayList<Position> positions = (ArrayList<Position>) Factory.createAllPositions(gameboard.length());

		for (Position position : positions) {
			image = this.chooseRightBackgroundImage(position);

			ImageView currentImageViewBackground = new ImageView(image);
			configureTilesImages(currentImageViewBackground);
			this.add(currentImageViewBackground, position.column(), position.row());
		}
		this.setHgap(1);
		this.setVgap(1);

		// Set the max size to be able to move it freely
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		this.setStyle("-fx-background-color:#053367; -fx-opacity:1;"); // navy blue font
		this.setPadding(new Insets(15));

	}
	
	public Image chooseRightBackgroundImage(Position position) {
		Image image;
		Image backgroundImage = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SEA.imagePath(), tileSize, tileSize);
		if (gameboard.isSunAt(position)) {
			image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SUN.imagePath(), backgroundImage);
		} else if (gameboard.isMoonAt(position)) {
			image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MOON.imagePath(), backgroundImage);
		} else {
			image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SEA.imagePath(), backgroundImage);
		}
		return image;
	}


	public void bindController(GameController gameController) {
		this.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			Node clickedNode = (Node) event.getTarget();
			if (clickedNode instanceof ImageView) {
				Integer columnIndex = GridPane.getColumnIndex(clickedNode);
				Integer rowIndex = GridPane.getRowIndex(clickedNode);
				if (columnIndex != null && rowIndex != null) {
					gameController.handleTilePlacement(new Position(rowIndex, columnIndex));
				}
			}
		});
	}
	
	/**
	 * Methods must be called when something change on the board
	 */
	public void update() {
		ImageView currentImageView;
		
		Map<Position, Tile> tiles = gameboard.tiles();
		Image image;
		// check if a position is not in the gameboard anymore  
		for (Position posOfaTilePlaced: tilesPlacedPlacement) {
			if (! tiles.containsKey(posOfaTilePlaced)) {
				Position notATileHereAnymore = posOfaTilePlaced;
				image = this.chooseRightBackgroundImage(posOfaTilePlaced);

				currentImageView = new ImageView(image);
				
				// parameters of the imageview
				configureTilesImages(currentImageView);

				// add the image at the right position
				this.tilesPlacedPlacement.remove(notATileHereAnymore);
				this.add(currentImageView, notATileHereAnymore.column(), notATileHereAnymore.row());
			}
		}
		
		
		for (Position position : tiles.keySet()) {
			// this get the path by getting the path in the ImagePath
			image = ImageLoader.loadImageSafe(tiles.get(position).path().imagePath(), tileSize, tileSize);
			currentImageView = new ImageView(image);

			// parameters of the imageview
			configureTilesImages(currentImageView);

			// add the image at the right position
			this.tilesPlacedPlacement.add(position); // usefull later if a tile is push
			this.add(currentImageView, position.column(), position.row());
		}
	}
	
	public void configureTilesImages(ImageView imgView) {
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
		imgView.setPreserveRatio(false);
	}
}
