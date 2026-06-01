package latice.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CssStyle {
	
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
}
