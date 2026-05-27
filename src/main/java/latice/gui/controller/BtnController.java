package latice.gui.controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import latice.gui.view.GameScene;
import latice.gui.view.RulesScene;

public class BtnController {
		
		private Stage primaryStage ;
		
		public BtnController(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	    }
		
		// handler of play button is starting a was scene with gameScene
		public EventHandler<MouseEvent> playBtnHandler() {
	        return new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	        		GameScene gameScene = new GameScene();
	        		Scene scene = gameScene.createGameScene();

	        		primaryStage.setScene(scene);
	        		gameScene.launchGame();
	            }
	        };
	    }
//		handler of quit button is exiting the window
	    public EventHandler<MouseEvent> quitBtnHandler() {
	        return new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            		
	            	primaryStage.close();
	            }
	        };
	    }
	    
	    public EventHandler<MouseEvent> rulesBtnHandler() {
	        return new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	
	            	RulesScene rulesScene = new RulesScene();
	            	Scene scene = rulesScene.createRulesScene(primaryStage);
	            	
	            	primaryStage.setScene(scene);
	            }
	        };
	    }
	}
