package laurassupertetris.ui;

import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Tetris extends Application {

    // Laudan perusasiat
    public static int topRow = 0;
    public static final int sqSize = 31;
    public static int boardWidth = sqSize * 12;
    public static int boardHeight = sqSize * 24;

    // Asetteluun liittyvät
    public static Block block;
    public static Block nextBlock;
    public static BorderPane layout;
    public static Scene gameScene;
    
    // Controlleri & Game
    public static Controller controller;
    private static boolean game = true;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // tehdään ensimmäinen palikka, laitetaan se layoutiin ja sceneen.
        nextBlock = new Block();
        Block startBlock = nextBlock;
        layout = new BorderPane();
        layout.getChildren().addAll(startBlock.a, startBlock.b, startBlock.c, startBlock.d);
        gameScene = new Scene(layout, boardWidth, boardHeight);

        controller = new Controller(sqSize, boardWidth, boardHeight, layout, block, nextBlock);
        
        moveOnKeyPress(startBlock);
        block = startBlock;
        nextBlock = new Block();

        stage.setTitle("LaurasSuperTetris");
        stage.setScene(gameScene);
        stage.show();

        Timer fall = createTimer();
    }

    public void moveOnKeyPress(Block bl) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        controller.moveRight(bl);
                        break;
                    case DOWN:
                        if (controller.downOk(bl)) {
                            controller.removeRows(layout);
                            Block next = nextBlock;
                            nextBlock = new Block();
                            block = next;
                            layout.getChildren().addAll(next.a, next.b, next.c, next.d);
                            moveOnKeyPress(next);
                        } else {
                            controller.moveDown(bl);
                        }                       
                        break;
                    case LEFT:
                        controller.moveLeft(bl);
                        break;
                    case UP:
                        controller.moveTurn(bl);
                        break;
                }
            }
        });
    }

    public Timer createTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (block.a.getY() == 0 || block.b.getY() == 0
                                || block.c.getY() == 0 || block.d.getY() == 0) {
                            topRow++;
                        } else {
                            topRow = 0;
                        }
                        if (topRow == 2) {
                            //Game Over
                            game = false;
                        }
                        if (topRow == 15) {
                            System.exit(0);
                        }
                        if (game) {
                            if (controller.downOk(block)) {
                                controller.removeRows(layout);
                                //tehdään uusi blockki ja lisätään se sceneen
                                Block next = nextBlock;
                                nextBlock = new Block();
                                block = next;
                                layout.getChildren().addAll(next.a, next.b, next.c, next.d);
                                moveOnKeyPress(next);
                            } else {
                                controller.moveDown(block);
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 300);
        return timer;
    }

    public static void main(String[] args) {
        launch(Tetris.class);
    }
}
