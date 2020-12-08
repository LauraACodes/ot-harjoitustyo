package laurassupertetris.ui;

import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tetris extends Application {

    // Laudan perusasiat
    public static int timeOnTop = 0;
    public static final int sqSize = 31;
    public static int boardWidth = sqSize * 12;
    public static int boardHeight = sqSize * 24;
    public static Gameboard gameboard;

    // Asetteluun liittyvät
    public static Block block = new Block();
    public static Block nextBlock = new Block();
    public static BorderPane layout = new BorderPane();
    public static Scene gameScene;
    
    //Pisteidenlasku
    public static Text scoreText;
    public static int score = 0;
    public static Text lineText;    
    public static int lineCount = 0;

    // Controlleri & Game
    public static Controller controller;
    public static boolean game = true;

    @Override
    public void start(Stage stage) throws Exception {

        gameboard = new Gameboard();
        gameScene = gameboard.getGameScene();
        
        controller = new Controller(sqSize, boardWidth, boardHeight, gameScene);
        
        Block startBlock = nextBlock;
        controller.moveOnKeyPress(startBlock);
        block = startBlock;
        nextBlock = new Block();
      
        stage.setTitle("LaurasSuperTetris");
        stage.setScene(gameScene);
        stage.show();

        AnimationTimer timer = new TetrisTimer();
        timer.start();
    }

    public static void main(String[] args) {
        launch(Tetris.class);
    }
}
