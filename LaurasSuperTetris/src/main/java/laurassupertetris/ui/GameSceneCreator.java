package laurassupertetris.ui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    Button pauseButton = Controller.pauseButton;
    AnimationTimer timer = Controller.timer;
    
    public static Scene gameScene;

    // Controlleri & Game
    public static Controller controller = Tetris.controller;
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
        lineText.setText("LINE: " + lineC);
    }
    
    public void gameOver() {
        Text gameO = new Text("GAME OVER");
        gameO.setFill(Color.RED);
        gameO.setStyle("-fx-font: 70 arimo;");
        gameO.setY(250);
        gameO.setX(10);
        layout.getChildren().add(gameO);
    }
}

