package latice.gui.controller;

import java.net.URL;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import latice.gui.view.GameScene;
import latice.gui.view.MainMenu;
import latice.gui.view.RulesScene;
import latice.gui.view.SettingGameScene;
import latice.util.MusicPath;
import javafx.scene.media.Media;
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
			this.setMediaPlayer(mediaPlayer);
			this.usernames = settingScene.getUsernames();
			boolean validUsernames = (!usernames.isEmpty() 
					&& !usernames.get(0).trim().isEmpty()
					&& !usernames.get(0).trim().isEmpty()); 
			if (validUsernames){
				if(this.mediaPlayer != null) {
					this.mediaPlayer.stop();
				}
				GameScene gameScene = new GameScene(usernames, this);
	    		
	    		primaryStage.setScene(gameScene);
	    		
	    		// music implementation and starting here to avoid a loop in GameScene
	    		String musicFile = MusicPath.MUSIC_INGAME.getPath();  
	    		URL resourceUrl = RulesScene.class.getResource(musicFile);
	    	    Media sound = new Media(resourceUrl.toExternalForm());
	    		MediaPlayer mediaPlayer = new MediaPlayer(sound);
	    		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    		mediaPlayer.play();
	    		this.setMediaPlayer(mediaPlayer);
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
			this.setMediaPlayer(mediaPlayer);
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
