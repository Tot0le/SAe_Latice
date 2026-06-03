package latice.gui.view;

import java.net.URL;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import latice.gui.CssStyle;
import latice.gui.controller.GameBtnController;
import latice.gui.controller.GameController;
import latice.gui.controller.LabelController;
import latice.gui.controller.MenuBtnController;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.StandartPool;
import latice.util.MusicPath;


public class GameScene extends Scene { //TODO add a resign button
	private GameBoard gameboard;
	private GameBoardIhm visualGameboard;
	private MenuBtnController menuBtnController;
	private GameBtnController gameBtnController;
	private VBox vBoxBottom;
	private VBox vBoxLeft;
	private HBox hBoxBottom;
	private StackPane root;
	private BorderPane gameLayout;
	private BorderPane overlayLayout;
	private Group rackIhmEmplacement;
	private Button endTurnBtn;
	private Button exchangeAllTilesBtn;
//	private Button exchangeTilesBtn;
	private Button confirmBtn;
	private Button buyANewActionBtn;
	private ArrayList<Label> scoreLabels; //TODO if the name is to long do something (the score is unreadable)
	private ArrayList<String> usernames;
	private Label currentPlayerLbl;
	private Label endMessageLbl;
	private Label currentTurnLbl;  // TODO first currentTurnLbl update
	private Label lblPoolTilesNumber;
	
	private static final double SIDE_PANEL_WIDTH = 350.0;
	
	public GameScene(ArrayList<String> usernames, MenuBtnController menuBtnController) {
		super(new StackPane(), 1920, 1080);
		
		this.currentTurnLbl = new Label("1");
		this.lblPoolTilesNumber = new Label("");
		this.usernames = usernames;
		this.gameboard = new GameBoard();
		this.visualGameboard = new GameBoardIhm(this.gameboard);
		this.menuBtnController = menuBtnController;
		
		this.vBoxBottom = new VBox();
		this.hBoxBottom = new HBox();
		this.rackIhmEmplacement = new Group();
		
		this.root = (StackPane) this.getRoot();
		
		gameLayout = new BorderPane();
		
		overlayLayout = new BorderPane();
		overlayLayout.setVisible(false);
		
		this.root.getChildren().add(gameLayout);
		this.root.getChildren().add(overlayLayout);
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		VBox vboxTop = new VBox();
		
		// decorative opponent rack
		HBox opponentRack = new HBox();
		
		vboxTop.getChildren().addAll(opponentRack);
		vboxTop.setAlignment(Pos.TOP_CENTER);
		vboxTop.setPadding(new Insets(20, 10, 10, 10));
		
		vboxTop.setSpacing(15);
		
		// right panel setup (scores and currentPlayer)
		Label scorePlayer1 = new Label("Score " + usernames.get(0) + " : 0");
		Label scorePlayer2 = new Label("Score " + usernames.get(1) + " : 0");
		currentPlayerLbl = new Label();
		
//		scorePlayer1.setWrapText(true);
		scorePlayer1.setTooltip(new javafx.scene.control.Tooltip("Score " + usernames.get(0) + " : 0")); //TODO modify them when score change, also 1/2 chance that Player 1 & player2 are reversed
		scorePlayer2.setTooltip(new javafx.scene.control.Tooltip("Score " + usernames.get(1) + " : 0"));
		currentPlayerLbl.setWrapText(true);
		
		VBox vboxRight = new VBox();
		vboxRight.setPrefWidth(SIDE_PANEL_WIDTH);
		vboxRight.setAlignment(Pos.TOP_CENTER);
		vboxRight.setPadding(new Insets(30));
		vboxRight.setSpacing(15);
		
		// containers for the score and player rectangle background
		VBox scoreBadge = new VBox(scorePlayer1, scorePlayer2);
		scoreBadge.setAlignment(Pos.CENTER);
		scoreBadge.setSpacing(10);
		
		VBox playerBadge = new VBox(currentPlayerLbl);
		playerBadge.setAlignment(Pos.CENTER);
		
		vboxRight.getChildren().addAll(scoreBadge, playerBadge);
		
		// music implementation and starting
		String musicFile = MusicPath.MUSIC_INGAME.getPath();  
		URL resourceUrl = RulesScene.class.getResource(musicFile);
	    Media sound = new Media(resourceUrl.toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		
		// left panel setup (turns and Pool badges)
		Label iconTurn = new Label("⏳");
		VBox turnBadge = new VBox(iconTurn, currentTurnLbl);
		turnBadge.setAlignment(Pos.CENTER);
		turnBadge.setMaxWidth(80);
		
		Label iconPool = new Label("🀄"); // TODO change this emoji into a better tile (it's a majong tile)
		VBox poolBadge = new VBox(iconPool, lblPoolTilesNumber);
		poolBadge.setAlignment(Pos.CENTER);
		
		vBoxLeft = new VBox();
		vBoxLeft.setPrefWidth(SIDE_PANEL_WIDTH);
		vBoxLeft.setAlignment(Pos.TOP_CENTER);
		vBoxLeft.setPadding(new Insets(30));
		vBoxLeft.setSpacing(30);
		vBoxLeft.getChildren().addAll(turnBadge);
		
		scoreLabels = new ArrayList<>();
		scoreLabels.add(scorePlayer1);
		scoreLabels.add(scorePlayer2);
		
		gameLayout.setRight(vboxRight);
		
		gameLayout.setLeft(vBoxLeft);
		
		gameLayout.setTop(vboxTop);
		
		gameLayout.setCenter(visualGameboard);
		
		endTurnBtn = new Button("End Turn");
		exchangeAllTilesBtn = new Button("Exchange All Tiles");
		buyANewActionBtn = new Button("Buy an action.");
		
		buyANewActionBtn.setDisable(true);
		
		hBoxBottom.getChildren().addAll(exchangeAllTilesBtn, buyANewActionBtn);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setSpacing(50);
		
		// Layout bottom elements with AnchorPane to keep rack centered and pool on right
		AnchorPane rackAndPoolPane = new AnchorPane();
		HBox rackCenterContainer = new HBox(rackIhmEmplacement);
		rackCenterContainer.setAlignment(Pos.CENTER);
		
		// force the rackCenterContainer HBox to extends itself to the maximum to the left and to the right
		AnchorPane.setLeftAnchor(rackCenterContainer, 0.0);
		AnchorPane.setRightAnchor(rackCenterContainer, 0.0);
		
		// place the anchor top to it's top
		AnchorPane.setTopAnchor(rackCenterContainer, 0.0);
		// place the rack 20 pixels higher to form a small 20px margin bottom
		AnchorPane.setBottomAnchor(rackCenterContainer, 20.0);
		
		// place the poolBadge to be 100 pixels to the left relative to the right & to be 30 pixels up relative to the bottom
		AnchorPane.setRightAnchor(poolBadge, 100.0);
		AnchorPane.setBottomAnchor(poolBadge, 30.0);
		
		rackAndPoolPane.getChildren().addAll(rackCenterContainer, poolBadge);
		
		vBoxBottom.getChildren().addAll(endTurnBtn, hBoxBottom, rackAndPoolPane);
		vBoxBottom.setAlignment(Pos.CENTER);
		vBoxBottom.setSpacing(15);
		
		// apply CSS
		CssStyle.gameSceneCss(currentTurnLbl, iconTurn, turnBadge, lblPoolTilesNumber, iconPool, poolBadge, scorePlayer1, scorePlayer2, currentPlayerLbl, exchangeAllTilesBtn, buyANewActionBtn, endTurnBtn, gameLayout, opponentRack, scoreBadge, playerBadge);

		gameLayout.setBottom(vBoxBottom);
		
		BorderPane.setAlignment(vBoxBottom, Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(vboxTop, Pos.TOP_CENTER);
	}
	
	public void launchGame() {
		Pool standartPool = new StandartPool();
		Referee.shuffle(standartPool);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Referee.dealTheCards(standartPool, poolPlayer1, poolPlayer2);
		
		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();

		Player player1 = new Player(usernames.get(0), 0, rackPlayer1, poolPlayer1, gameboard);
		Player player2 = new Player(usernames.get(1), 0, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		playerList = (ArrayList<Player>) Referee.randomChoosePlayerOrder(playerList);
		// The game can now start
		
		Referee referee = new Referee(gameboard, playerList);
		LabelController lblController = new LabelController(referee, scoreLabels, currentPlayerLbl, endMessageLbl, lblPoolTilesNumber, currentTurnLbl);
		gameBtnController = new GameBtnController(referee, exchangeAllTilesBtn, buyANewActionBtn, endTurnBtn);
		GameController gameController = new GameController(this.gameboard, this.visualGameboard, referee, this, lblController, menuBtnController, gameBtnController);
		
		// the end turn button now listen
		endTurnBtn.setOnAction(event -> {
			gameController.endTurnBtnHandler();
			
		});
		
		// the exchangeAllTilesBtn now listen
		exchangeAllTilesBtn.setOnAction(event -> {
			gameController.exchangeAllTilesBtnHandler();
		});
		
		// the exchangeAllTilesBtn now listen
		buyANewActionBtn.setOnAction(event -> {
			gameController.buyANewActionBtnHandler();
		});
	}

	public void lockBoardRackAndBtns(boolean lockEndTurn) {
		this.visualGameboard.setMouseTransparent(true);
		this.rackIhmEmplacement.setMouseTransparent(true);
		
		this.gameBtnController.disableButtons(lockEndTurn);
	}
	
	public Group rackEmplacement() {
		return this.rackIhmEmplacement;
	}
	
	public BorderPane gameLayout() {
		return gameLayout;
	}

	public void setGameLayout(BorderPane gameLayout) {
		this.gameLayout = gameLayout;
	}

	public BorderPane overlayLayout() {
		return overlayLayout;
	}

	public void setOverlayLayout(BorderPane overlayLayout) {
		this.overlayLayout = overlayLayout;
		this.root.getChildren().remove(overlayLayout);
		this.overlayLayout.setVisible(true);
		this.root.getChildren().add(this.overlayLayout);
	}
}
