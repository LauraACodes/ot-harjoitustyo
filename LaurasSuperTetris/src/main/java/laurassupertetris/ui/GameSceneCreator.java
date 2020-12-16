package laurassupertetris.ui;

import Scores.TetrisDao;
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
import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;


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
    public static Controller controller = Tetris.controller;
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
        
        scoreText = new Text("SCORE: " + score);
        scoreText.setStyle("-fx-font: 20 LucidaConsole;");
        scoreText.setY(50);
        scoreText.setX(boardWidth + 10);
        scoreText.setFill(Color.WHITE);
       
        lineText = new Text("LINES: " + lineCount);
        lineText.setStyle("-fx-font: 20 LucidaConsole;");
        lineText.setY(100);
        lineText.setX(boardWidth + 10);
        lineText.setFill(Color.WHITE);
        
        Text nameText = new Text(name);
        nameText.setStyle("-fx-font: 20 LucidaConsole;");
        nameText.setY(150);
        nameText.setX(boardWidth + 10);
        nameText.setFill(Color.WHITE);   
        /*
        StackPane buttonPlace = new StackPane();
        buttonPlace.getChildren().add(pauseButton);
        
        pauseButton.setOnAction(start -> {
            System.out.println("pause");
        });**/
        
        layout.getChildren().addAll(line, scoreText, lineText, nameText);
      //  layout.setRight(buttonPlace);
        // tehdään ensimmäinen palikka, laitetaan se layoutiin ja sceneen.
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
        System.out.println("tuli gohon");
        updateDB();
        
        StackPane gameOver = new StackPane();
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(400);
        rectangle.setHeight(200);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);  
        rectangle.setFill(Color.CRIMSON);
        rectangle.setStroke(Color.WHITE);
        

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
        
        HBox buttonBox = new HBox(30);
        buttonBox.getChildren().addAll(toStatsButton, newGameButton);   
        buttonBox.setStyle("-fx-font: 20 LucidaConsole;");
        buttonBox.setAlignment(Pos.CENTER);
        
        VBox GO = new VBox(20);
        Text gameO = new Text("GAME OVER");
        gameO.setFill(Color.WHITE);
        gameO.setStyle("-fx-font: 40 LucidaConsole;");
        gameO.setY(250);
        gameO.setX(10);
        int endScore = controller.getScore();
        int lineScore = controller.getLineCount();
        Text scoreText = new Text("Score: " + endScore + ", Lines: " + lineScore);
        scoreText.setFill(Color.WHITE);
        scoreText.setStyle("-fx-font: 20 LucidaConsole;"); 
        GO.getChildren().addAll(gameO, scoreText, buttonBox);
        GO.setAlignment(Pos.CENTER);
        
        ObservableList list = gameOver.getChildren();
        list.addAll(rectangle, GO);
        //gameOver.setAlignment(Pos.CENTER);
        layout.setCenter(gameOver);
    }
}

