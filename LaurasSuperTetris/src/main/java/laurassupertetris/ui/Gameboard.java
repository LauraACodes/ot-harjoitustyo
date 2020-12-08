package laurassupertetris.ui;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;
import static laurassupertetris.ui.Tetris.block;
import static laurassupertetris.ui.Tetris.boardHeight;
import static laurassupertetris.ui.Tetris.boardWidth;
import static laurassupertetris.ui.Tetris.controller;
import static laurassupertetris.ui.Tetris.gameScene;
import static laurassupertetris.ui.Tetris.layout;
import static laurassupertetris.ui.Tetris.lineCount;
import static laurassupertetris.ui.Tetris.lineText;
import static laurassupertetris.ui.Tetris.nextBlock;
import static laurassupertetris.ui.Tetris.score;
import static laurassupertetris.ui.Tetris.scoreText;

public class Gameboard {

    public static int topRow = 0;
    public static final int sqSize = 31;
    public static int boardWidth = sqSize * 12;
    public static int boardHeight = sqSize * 24;

    // Asetteluun liittyvät
    public static Block block = Tetris.block;
    public static Block nextBlock = Tetris.nextBlock;
    //public static Block startBlock;
    public static BorderPane layout = Tetris.layout;
    public static Scene gameScene;

    // Controlleri & Game
    public static Controller controller = Tetris.controller;
    private static boolean game = true;

    public Gameboard() {

        createGameScene();

    }

    public void createGameScene() {
        
        Line line = new Line(boardWidth, 0, boardWidth, boardHeight);
        line.setFill(Color.CHOCOLATE);
        
        scoreText = new Text("SCORE: " + score);
        scoreText.setStyle("-fx-font: 15 arimo;");
        scoreText.setY(50);
        scoreText.setX(boardWidth + 10);
        scoreText.setFill(Color.GREY);
       
        lineText = new Text("LINES: " + lineCount);
        lineText.setStyle("-fx-font: 15 arimo;");
        lineText.setY(100);
        lineText.setX(boardWidth + 10);
        lineText.setFill(Color.GREY);
        
        layout.getChildren().addAll(line, scoreText, lineText);
        // tehdään ensimmäinen palikka, laitetaan se layoutiin ja sceneen.
        Block startBlock = nextBlock;

        layout.getChildren().addAll(startBlock.a, startBlock.b, startBlock.c, startBlock.d);
        gameScene = new Scene(layout, boardWidth + 150, boardHeight);
   
    }
    
    public Scene getGameScene(){
        /*Block startBlock = nextBlock;
        controller.moveOnKeyPress(startBlock);
        block = startBlock;
        nextBlock = new Block();*/
        return gameScene;
    }
     
}

