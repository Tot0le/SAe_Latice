package latice.gui.model;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class RackIhm extends GridPane{
	Image cellImg;
	
	ImageView cellImg1View;
	ImageView cellImg2View;
	ImageView cellImg3View;
	ImageView cellImg4View;
	ImageView cellImg5View;
	
	public RackIhm() {
		cellImg = new Image(getClass().getResource("/images/rack_cell.png").toExternalForm());
		
		cellImg1View = new ImageView(cellImg);
		cellImg2View = new ImageView(cellImg);
		cellImg3View = new ImageView(cellImg);
		cellImg4View = new ImageView(cellImg);
		cellImg5View = new ImageView(cellImg);
		
		GridPane.setColumnIndex(cellImg1View, 0);
		GridPane.setColumnIndex(cellImg2View, 1);
		GridPane.setColumnIndex(cellImg3View, 2);
		GridPane.setColumnIndex(cellImg4View, 3);
		GridPane.setColumnIndex(cellImg5View, 4);
		
		this.getChildren().addAll(cellImg1View,cellImg2View,cellImg3View,cellImg4View,cellImg5View);
		
		
		// Set the rack in the middle
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        
        //debug color
		this.setStyle("-fx-background-color:#00ff00; -fx-opacity:1;"); 
		
		this.setPadding(new Insets(10));
		this.setHgap(10);
	}
	
	public void updateRackTiles() {
		//TODO updateRackTile method
	}
}
