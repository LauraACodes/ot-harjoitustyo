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
/**
 * Luokka vastaa käyttöliittymän näyttämisestä pelaajalle.
 * Toistaiseksi valmiina on vain pelinäkymä. 
 * Jatkossa tästä löytyy myös valinnat muiden pelin scenejen näyttämiseksi.
 * 
 */
public class Tetris extends Application {

    // Controlleri & Game
    public static Controller controller;
    public boolean game = false;


    /**
     * Pelin käynnistävä metodi luo pelissä tarvittavat osat kutsumalla muiden 
     * luokkien metodeita: pelilaudan, controllerin ja timerin sekä
     * käynnistää pelin.
     * @param stage
     * @throws Exception 
     */
      
    @Override
    public void start(Stage stage) throws Exception {

    /*    gameboard = new Gameboard();
        gameScene = gameboard.getGameScene();
        
        controller = new Controller(sqSize, boardWidth, boardHeight, gameScene);
    /*    
        Block startBlock = nextBlock;
        controller.moveOnKeyPress(startBlock);
        block = startBlock;
        nextBlock = new Block();
    */  
        controller = new Controller(stage);
        stage.setScene(controller.getScene());
        stage.setTitle("LaurasSuperTetris");
        stage.show();

    /*    AnimationTimer timer = new TetrisTimer();
        timer.start();*/
    }
    

    public static void main(String[] args) {
        launch(Tetris.class);
    }
}
