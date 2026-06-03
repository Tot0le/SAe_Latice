package latice.gui.controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import latice.gui.view.GameScene;
import latice.gui.view.MainMenu;
import latice.gui.view.RulesScene;
import latice.gui.view.SettingGameScene;
import javafx.scene.media.MediaPlayer;

public class MenuBtnController {
		
		private Stage primaryStage ;
		private SettingGameScene settingScene;
		private ArrayList<String> usernames;
		private MediaPlayer mediaPlayer;
		
		public MenuBtnController(Stage primaryStage, MediaPlayer mediaPlayer) {
	        this.primaryStage = primaryStage;
	        this.mediaPlayer = mediaPlayer;
	    }

		public void launchBtnHandler() {
	    	if(this.mediaPlayer != null) {
				this.mediaPlayer.stop();
			}
			this.usernames = settingScene.getUsernames();
			if (!usernames.isEmpty()) {
				GameScene gameScene = new GameScene(usernames, this);
	    		
	    		primaryStage.setScene(gameScene);
	    		gameScene.launchGame();
			}
			
		}

		// handler of play button is starting a was scene with gameScene
		public void playBtnHandler() {
			if(this.mediaPlayer != null) {
				this.mediaPlayer.stop();
			}
        	settingScene = new SettingGameScene(this);
    		
    		primaryStage.setScene(settingScene);
	    }
		
		// Button back goes back to main menu
		public void backBtnHandler() {
			if(this.mediaPlayer != null) {
				this.mediaPlayer.stop();
			}
			MainMenu mainMenu = new MainMenu(primaryStage);

			primaryStage.setScene(mainMenu);
			primaryStage.setTitle("Game starting window");
		}
		
//		handler of quit button is exiting the window
	    public void quitBtnHandler() {
	            	primaryStage.close();
	            }

	    public void rulesBtnHandler() {
        	if(this.mediaPlayer != null) {
				this.mediaPlayer.stop();
				}
	            RulesScene rulesScene = new RulesScene(primaryStage);
	            primaryStage.setScene(rulesScene);
	            }
	    
	    public void setSettingGameScene(SettingGameScene settingScene, MediaPlayer mediaPlayer) {
	    	if(this.mediaPlayer != null) {
				this.mediaPlayer.stop();
			}
	    	this.settingScene = settingScene;
	    	this.mediaPlayer = mediaPlayer;

	    }
	    
	    public Stage primaryStage() {
	    	return primaryStage;
	    }
	    
	    public ArrayList<String> usernames() {
	    	return usernames;
	    }
	    
	    public void setMediaPlayer(MediaPlayer mediaPlayer) {
	        this.mediaPlayer = mediaPlayer;
	    }
	    public MediaPlayer mediaPlayer() {
	    	return mediaPlayer;
	    }
	}
