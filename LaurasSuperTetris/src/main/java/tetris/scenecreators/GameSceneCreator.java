package tetris.scenecreators;

import tetris.controls.TetrisDao;
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
import tetris.blocksandmoves.Block;
import tetris.controls.Controller;
import tetris.ui.Ui;


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
    ElementGenerator eGenerator = new ElementGenerator();
    
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
        
        scoreText = eGenerator.createText("SCORE: " + score, Color.WHITE, 20, boardWidth + 10, 50);
        lineText = eGenerator.createText("LINES: " + lineCount, Color.WHITE, 20, boardWidth + 10, 100);
        Text nameText = eGenerator.createText(name, Color.WHITE, 20, boardWidth + 10, 150);
        
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
        Rectangle gORectangle = eGenerator.createRectangle(400, 200, Color.CRIMSON, Color.WHITE);

        Text gameO = eGenerator.createText("GAME OVER", Color.WHITE, 40, 10, 250);
        int endScore = controller.getScore();
        int lineScore = controller.getLineCount();
        Text scoreText = eGenerator.createText("Score: " + endScore + ", Lines: " + lineScore, Color.WHITE, 20, 10, 250);
        HBox buttonBox = createGOButtonBox();

        VBox gO = new VBox(20);
        gO.getChildren().addAll(gameO, scoreText, buttonBox);
        gO.setAlignment(Pos.CENTER);
        
        ObservableList list = gameOver.getChildren();
        list.addAll(gORectangle, gO);

        layout.setCenter(gameOver);
    }
    
    public HBox createGOButtonBox() {
        HBox buttonBox = new HBox(30);
        
        Button newGameButton = eGenerator.createButton("NEW GAME", 150, 40, Color.CORAL);
        newGameButton.setOnAction(start -> {
            controller.startNewGame();
        });
        
        Button toStatsButton = eGenerator.createButton("TO STATS", 150, 40, Color.CORAL);
        toStatsButton.setOnAction(start -> {
            controller.toStats();
        });              
        
        buttonBox.getChildren().addAll(toStatsButton, newGameButton);   
        buttonBox.setStyle("-fx-font: 20 LucidaConsole;");
        buttonBox.setAlignment(Pos.CENTER);
        
        return buttonBox;
    }
    

}

