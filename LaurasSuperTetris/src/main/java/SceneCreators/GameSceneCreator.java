package SceneCreators;

import Controls.TetrisDao;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import blocksandmoves.Block;
import Controls.Controller;
import ui.Ui;


/**
 * Luokka Gameboard luo pelin pelilaudan.
 * 
 */
public class GameSceneCreator {

    public static int topRow = 0;
    public static final int SQSIZE = 31;
    public static int boardWidth = SQSIZE  * 12;
    public static int boardHeight = SQSIZE  * 24;

    // Asetteluun liittyvät
    Block block = Controller.block;
    Block nextBlock = Controller.nextBlock;
    BorderPane layout = Controller.layout;
    
    Text scoreText = Controller.scoreText; 
    Text lineText = Controller.lineText;    
    int score = Controller.score; 
    int lineCount = Controller.lineCount;   
    AnimationTimer timer = Controller.timer;
    
    public static Scene gameScene;

    // Controlleri & Game
    public static Controller controller = Ui.controller;
    public static TetrisDao dao = Controller.dao;
    private static boolean game = true;
    /** Luokan konstruktorin tehtävä on kutsua toista metodia luomaan 
     * varsinaisen pelilaudan.
     * 
     */
    public GameSceneCreator(String name) {
        createGameScene(name);
    }
    /** 
     * Metodi luo pelilaudan: Viivan erottamaan pisteiden laskua sekä
     * pistelaskussa tarvittavat sisällöt/nodet. 
     * Luodut pelilaudan osat kootaan gameSceneksi.
     */
    public void createGameScene(String name) {
        Line line = new Line(boardWidth, 0, boardWidth, boardHeight);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(5);
        
        scoreText = createText("SCORE: " + score, Color.WHITE, 20, boardWidth + 10, 50);
        lineText = createText("LINES: " + lineCount, Color.WHITE, 20, boardWidth + 10, 100);
        Text nameText = createText(name, Color.WHITE, 20, boardWidth + 10, 150);
        
        layout.getChildren().addAll(line, scoreText, lineText, nameText);

        Block startBlock = nextBlock;

        layout.getChildren().addAll(startBlock.a, startBlock.b, startBlock.c, startBlock.d);
        layout.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        gameScene = new Scene(layout, boardWidth + 150, boardHeight);
   
    }
    
    public Scene getGameScene() {
        return gameScene;
    }
    
    public void addPoints(int sco, int lineC) {
        scoreText.setText("SCORE: " + sco);
        lineText.setText("LINES: " + lineC);
    }
    
    public void updateDB() {
        dao.updatePlayerScores(controller.getPlayerName(), controller.getScore());
        dao.updateGames(controller.getPlayerName(), controller.getScore());
    }
    
    public void gameOver() {
        updateDB();
       
        StackPane gameOver = new StackPane();
        Rectangle gORectangle = createGORec();

        Text gameO = createText("GAME OVER", Color.WHITE, 40, 10, 250);
        int endScore = controller.getScore();
        int lineScore = controller.getLineCount();
        Text scoreText = createText("Score: " + endScore + ", Lines: " + lineScore, Color.WHITE, 20, 10, 250);
        HBox buttonBox = createGOButtonBox();

        VBox GO = new VBox(20);
        GO.getChildren().addAll(gameO, scoreText, buttonBox);
        GO.setAlignment(Pos.CENTER);
        
        ObservableList list = gameOver.getChildren();
        list.addAll(gORectangle, GO);

        layout.setCenter(gameOver);
    }
    
    public Text createText(String tText, Color fill, int fontSize, int x, int y) {
        Text text = new Text(tText);
        text.setFill(fill);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        text.setStyle(ftext);
        text.setX(x);
        text.setY(y);
        return text;
    }    
    
    public Rectangle createGORec() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(400);
        rectangle.setHeight(200);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);  
        rectangle.setFill(Color.CRIMSON);
        rectangle.setStroke(Color.WHITE);
        return rectangle;
    }
    
    public HBox createGOButtonBox() {
        HBox buttonBox = new HBox(30);
        
        Button newGameButton = new Button("NEW GAME");
        newGameButton.setPrefSize(150,40);
        newGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        newGameButton.setOnAction(start -> {
            controller.startNewGame();
        });
        
        Button toStatsButton = new Button("TO STATS");
        toStatsButton.setPrefSize(150,40);
        toStatsButton.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        toStatsButton.setOnAction(start -> {
            controller.toStats();
        });              
        
        buttonBox.getChildren().addAll(toStatsButton, newGameButton);   
        buttonBox.setStyle("-fx-font: 20 LucidaConsole;");
        buttonBox.setAlignment(Pos.CENTER);
        
        return buttonBox;
    }
    

}

