package latice.application;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//TODO 
		//code venant d'un TP (exemple)
		Group root = new Group();
		
		Text txt1 = new Text(50,50,"DRAG ME");
		Text txt2 = new Text(300,50,"DROP HERE");
		
		Rectangle rectPremier = new Rectangle(150, 100, Color.DARKBLUE);
		Rectangle rectDeuxieme = new Rectangle(150, 100, Color.DARKGREEN);
		rectPremier.setX(50);
		rectPremier.setY(100);
		rectDeuxieme.setX(300);
		rectDeuxieme.setY(200);
		root.getChildren().addAll(txt1,txt2, rectPremier, rectDeuxieme);
		Scene scene = new Scene(root,500,350);
		scene.setFill(Color.LIGHTGREEN);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}	
}
