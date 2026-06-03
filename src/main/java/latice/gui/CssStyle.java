package latice.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CssStyle {
	// Theme constants
    private static final String themeBlue = "#0C10E3";
    private static final String themeSoftFawn = "#D1B17C";
    private static final String themeAntiqueWhite = "#F8E6D2";
    
	public static void resultPageCss(Button closeBtn, Button mainBtn, Button replayBtn, VBox middleVbox, VBox topVbox) {
        // Container styles
        middleVbox.setStyle("-fx-background-color: #262421; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #4A4846; -fx-border-width: 2;");
        topVbox.setStyle("-fx-background-color: #3C3A38; -fx-background-radius: 8;");
        
        // action buttons base style
        String actionBtnStyle = "-fx-background-color: #5C8A8A; " +
                                "-fx-text-fill: white; " +
                                "-fx-font-size: 16px; " +
                                "-fx-font-weight: bold; " +
                                "-fx-padding: 10 25 10 25; " +
                                "-fx-background-radius: 6; " +
                                "-fx-cursor: hand;";
        
        mainBtn.setStyle(actionBtnStyle);
        replayBtn.setStyle(actionBtnStyle);
        
        // close button style
        String closeBtnStyle = "-fx-background-color: transparent; " +
                               "-fx-text-fill: #999999; " +
                               "-fx-font-size: 18px; " +
                               "-fx-font-weight: bold; " +
                               "-fx-cursor: hand; " +
                               "-fx-padding: 2 8 2 8;";
                               
        closeBtn.setStyle(closeBtnStyle);
        
        // hover effects
        mainBtn.setOnMouseEntered(e -> mainBtn.setStyle(actionBtnStyle + " -fx-background-color: #70A3A3;"));
        mainBtn.setOnMouseExited(e -> mainBtn.setStyle(actionBtnStyle));
        
        replayBtn.setOnMouseEntered(e -> replayBtn.setStyle(actionBtnStyle + " -fx-background-color: #70A3A3;"));
        replayBtn.setOnMouseExited(e -> replayBtn.setStyle(actionBtnStyle));
        
        closeBtn.setOnMouseEntered(e -> closeBtn.setStyle(closeBtnStyle + " -fx-text-fill: white; -fx-background-color: #E53935; -fx-background-radius: 4;"));
        closeBtn.setOnMouseExited(e -> closeBtn.setStyle(closeBtnStyle));
    }
	
	public static void gameSceneCss(Label currentTurn, Label iconTurn, VBox turnBadge, Label lblPoolTilesNumber, Label iconPool, VBox poolBadge, Label scorePlayer1, Label scorePlayer2, Label currentPlayer, Button exchangeAllTilesBtn, Button buyANewActionBtn, Button endTurnBtn, BorderPane gameLayout, HBox opponentRack, VBox scoreBadge, VBox playerBadge) {
		// TODO refacto this method into multiple I guess
		currentTurn.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: " + themeBlue + ";");
        iconTurn.setStyle("-fx-font-size: 20px;");
        turnBadge.setStyle("-fx-background-color: " + themeAntiqueWhite + "; -fx-background-radius: 50; -fx-padding: 15; -fx-border-color: " + themeBlue + "; -fx-border-width: 3; -fx-border-radius: 50;");
        
        lblPoolTilesNumber.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: " + themeBlue + ";");
        iconPool.setStyle("-fx-font-size: 22px;");
        poolBadge.setStyle("-fx-background-color: " + themeAntiqueWhite + "; -fx-background-radius: 12; -fx-padding: 10 20; -fx-border-color: " + themeSoftFawn + "; -fx-border-width: 3; -fx-border-radius: 12;");
        
        scorePlayer1.setStyle("-fx-text-fill: " + themeBlue + "; -fx-font-size: 16px; -fx-font-weight: bold;");
        scorePlayer2.setStyle("-fx-text-fill: " + themeBlue + "; -fx-font-size: 16px; -fx-font-weight: bold;");
        currentPlayer.setStyle("-fx-text-fill: " + themeBlue + "; -fx-font-weight: bold; -fx-font-size: 18px;");
                
        scoreBadge.setStyle("-fx-background-color: " + themeAntiqueWhite + "; -fx-background-radius: 12; -fx-padding: 15; -fx-border-color: " + themeSoftFawn + "; -fx-border-width: 3; -fx-border-radius: 12;");
        playerBadge.setStyle("-fx-background-color: " + themeAntiqueWhite + "; -fx-background-radius: 12; -fx-padding: 10; -fx-border-color: " + themeBlue + "; -fx-border-width: 3; -fx-border-radius: 12;");
 
        String actionBtnStyle = "-fx-background-color: " + themeSoftFawn + "; -fx-text-fill: " + themeBlue + "; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 6; -fx-cursor: hand; -fx-border-color: " + themeBlue + "; -fx-border-radius: 5;";
        exchangeAllTilesBtn.setStyle(actionBtnStyle);
        buyANewActionBtn.setStyle(actionBtnStyle);
        
        String endTurnBtnStyle = "-fx-background-color: " + themeBlue + "; -fx-text-fill: " + themeAntiqueWhite + "; -fx-font-weight: bold; -fx-padding: 8 30; -fx-background-radius: 6; -fx-cursor: hand;"; 
        endTurnBtn.setStyle(endTurnBtnStyle);
       
        gameLayout.setStyle("-fx-background-color: linear-gradient(to bottom right, " + themeBlue + ", " + themeSoftFawn + ", " + themeAntiqueWhite + ");");

        opponentRack.setStyle("-fx-background-color: " + themeBlue + "; -fx-background-radius: 8; -fx-border-color: " + themeAntiqueWhite + "; -fx-border-width: 3; -fx-border-radius: 8; -fx-min-width: 450; -fx-max-width: 450; -fx-min-height: 70;");
        
        // hover effects
        applyHoverEffect(exchangeAllTilesBtn);
        applyHoverEffect(buyANewActionBtn);
        applyHoverEffect(endTurnBtn);
    }
	
	public static void applyHoverEffect(Button targetButton) {
	    double defaultScale = 1.0;
	    double hoveredScale = 1.05;

	    targetButton.setOnMouseEntered(event -> {
	        // apply hovered scale
	        targetButton.setScaleX(hoveredScale);
	        targetButton.setScaleY(hoveredScale);
	    });

	    targetButton.setOnMouseExited(event -> {
	        // revert to default scale
	        targetButton.setScaleX(defaultScale);
	        targetButton.setScaleY(defaultScale);
	    });
	}
}
