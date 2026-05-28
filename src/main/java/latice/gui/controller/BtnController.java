package latice.gui.controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import latice.gui.view.GameScene;
import latice.gui.view.SettingGameScene;

public class BtnController {
		
		private Stage primaryStage ;
		private SettingGameScene settingScene;
		
		public BtnController(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	    }
		

		
		public void launchBtnHandler() {
			ArrayList<String> usernames = settingScene.getUsernames();
			if (!usernames.isEmpty()) {
				GameScene gameScene = new GameScene(usernames);
	    		Scene scene = gameScene.createGameScene();
	    		
	    		primaryStage.setScene(scene);
	    		gameScene.launchGame();
			}
			
		}
//		// handler of launch button is starting a was scene with gameScene
//		public EventHandler<MouseEvent> launchBtnHandler() {
//	        return new EventHandler<MouseEvent>() {
//	            @Override
//	            public void handle(MouseEvent event) {
//	            	
//	        		GameScene gameScene = new GameScene(usernames);
//	        		Scene scene = gameScene.createGameScene();
//	        		
//	        		primaryStage.setScene(scene);
//	        		gameScene.launchGame();
//	            }
//	        };
//	    }
		
		// handler of play button is starting a was scene with gameScene
		public void playBtnHandler() {
        	SettingGameScene settingScene = new SettingGameScene();
    		Scene scene = settingScene.createSettingScene(this);
    		
    		primaryStage.setScene(scene);
	    }
//		// handler of play button is starting a was scene with gameScene
//		public EventHandler<MouseEvent> playBtnHandler() {
//	        return new EventHandler<MouseEvent>() {
//	            @Override
//	            public void handle(MouseEvent event) {
//	            	SettingGameScene settingScene = new SettingGameScene();
//	        		Scene scene = settingScene.createSettingScene(this);
//	        		
//	        		primaryStage.setScene(scene);
//	            }
//	        };
//	    }
//		handler of quit button is exiting the window
	    public EventHandler<MouseEvent> quitBtnHandler() {
	        return new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            		
	            	primaryStage.close();
	            }
	        };
	    }
	    public void setSettingGameScene(SettingGameScene settingScene) {
	    	this.settingScene = settingScene;
	    }
	}
