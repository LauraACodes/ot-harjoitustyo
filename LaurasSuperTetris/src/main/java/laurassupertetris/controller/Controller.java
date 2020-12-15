package laurassupertetris.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import laurassupertetris.ui.GameSceneCreator;
import laurassupertetris.ui.StartSceneCreator;
import laurassupertetris.ui.Tetris;
import laurassupertetris.ui.TetrisTimer;

/**
 * Controller luokka vastaa näppäimistön kuuntelusta sekä palikoiden
 * siirtämisestä pelilaudalla.
 *
 */
public class Controller {

    StartSceneCreator startScene;
    GameSceneCreator gameboard;

    public static BorderPane layout;
    public static Block block;
    public static Block nextBlock;

    public static Text scoreText;
    public static Text lineText;
    public static int score;
    public static int lineCount;
    public static Button pauseButton;

    public static int timeOnTop;
    public static boolean game;

    public static int[][] board;
    int sqSize = 31;
    int move = 31;
    int boardWidth = 31 * 12;
    int boardHeight = 31 * 24;

    Turns turns;
    Moves moves;

    Scene gameScene;
    Stage stage;

    Boolean showTetris = false;
    public static AnimationTimer timer;

    /**
     * Luokan konstruktori lukitsee tarvittavat muuttujat, luo Turns-olion
     * hoitamaan blockien käännökset sekä alustaa pelilaudan.
     *
     * @param sqSize Blockin osan koko (neliön sivu)
     * @param boardWidth Pelilaudan leveys
     * @param boardHeight Pelilaudan pituus
     * @param gscene pelilaudan Scene
     */
    public Controller(Stage stage) {
        board = new int[12][24];
        startScene = new StartSceneCreator(this);
        this.stage = stage;

        layout = new BorderPane();
        block = new Block();
        nextBlock = new Block();

        scoreText = new Text();
        lineText = new Text();
        score = 0;
        lineCount = 0;
        pauseButton = new Button("Pause");

        timeOnTop = 0;

        for (int[] row : board) {
            Arrays.fill(row, 0);
        }

        turns = new Turns(this.sqSize, this.move, this.boardWidth, this.boardHeight, this.board);
        moves = new Moves(this.sqSize, this.move, this.boardWidth, this.boardHeight, this.board);


    }

    public Scene getScene() {
        return startScene.getStartScene();
    }

    public void startGame() {
        
        gameboard = new GameSceneCreator(startScene.getPlayerName());
        gameScene = gameboard.getGameScene();
        Block startBlock = nextBlock;
        moveOnKeyPress(startBlock);
        block = startBlock;
        nextBlock = new Block();
        game = true;

        stage.setScene(gameScene);
        stage.show();

        timer = new TetrisTimer();
        timer.start();
    }

    public void moveOnKeyPress(Block bl) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        moves.moveRight(bl);
                        break;
                    case DOWN:
                        if (moves.downOk(bl)) {
                            removeRows();
                        } else {
                            moves.moveDown(bl);
                        }
                        break;
                    case LEFT:
                        moves.moveLeft(bl);
                        break;
                    case UP:
                        moveTurn(bl);
                        break;
                }
            }
        });
    }

    public void prepareNextScreen(Block bl) {
        if (moves.downOk(bl)) {
            removeRows();
            Block next = nextBlock;
            nextBlock = new Block();
            block = next;
            layout.getChildren().addAll(next.a, next.b, next.c, next.d);
            moveOnKeyPress(next);
        } else {
            moves.moveDown(bl);
            score++; 
            gameboard.addPoints(score, lineCount);
        }
    }

    public void moveTurn(Block block) {
        String name = block.getName();
        if (name.equals("line")) {
            this.turns.turnLine(block);
        }
        if (name.equals("ess")) {
            this.turns.turnEss(block);
        }
        if (name.equals("enn")) {
            this.turns.turnEnn(block);
        }
        if (name.equals("dude")) {
            this.turns.turnDude(block);
        }
        if (name.equals("jei")) {
            this.turns.turnJei(block);
        }
        if (name.equals("ell")) {
            this.turns.turnEll(block);
        }
    }

    public void handleTime() {

        if (block.a.getY() == 0 || block.b.getY() == 0 || block.c.getY() == 0 || block.d.getY() == 0) {
            timeOnTop++;
        } else {
            timeOnTop = 0;
        }
        if (timeOnTop == 2) {
            gameboard.gameOver();
            game = false;
        }
        if (timeOnTop == 15) {
            System.exit(0);
        }
        if (game) {
            prepareNextScreen(block);
        }
    }

    /**
     * Metodi päättää pelin, eli tuo GAME OVER tekstin pelilaudalle.
     */


    public void removeRows() {
        ArrayList<Node> parts = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newParts = new ArrayList<Node>();
        int full = 0;
        //katsotaan mitkä rivit täysiä
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == 1) {
                    full++;
                }
            }
            if (full == board.length) {
                lines.add(i);
            }
            full = 0;
        }
        Text tetrisText = new Text("T E T R I S");
        tetrisText.setFill(Color.RED);
        tetrisText.setStyle("-fx-font: 70 arimo;");
        tetrisText.setY(250);
        tetrisText.setX(10);

        if (showTetris) {

            Node jep = layout.getCenter();
            layout.getChildren().remove(jep);
            showTetris = false;
        }

        //Tuhotaan se rivi / rivit;
        if (lines.size() > 0) {
            if (lines.size() == 4) {
                layout.setCenter(tetrisText);
                showTetris = true;
            }
            do {
                lineCount++;
                for (Node node : layout.getChildren()) {

                    if (node instanceof Rectangle) {
                        parts.add(node);
                    }

                }
                for (Node node : parts) {
                    Rectangle n = (Rectangle) node;
                    if (n.getY() == lines.get(0) * sqSize) {
                        board[(int) n.getX() / sqSize][(int) n.getY() / sqSize] = 0;
                        layout.getChildren().remove(node);
                    } else {
                        newParts.add(node);
                    }
                }

                for (Node node : newParts) {
                    Rectangle n = (Rectangle) node;
                    if (n.getY() < lines.get(0) * sqSize) {
                        board[(int) n.getX() / sqSize][(int) n.getY() / sqSize] = 0;
                        n.setY(n.getY() + sqSize);
                    }
                }
                lines.remove(0);
                parts.clear();
                newParts.clear();
                for (Node node : layout.getChildren()) {
                    if (node instanceof Rectangle) {
                        parts.add(node);
                    }
                }
                for (Node node : parts) {
                    Rectangle n = (Rectangle) node;
                    try {
                        board[(int) n.getX() / sqSize][(int) n.getY() / sqSize] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                parts.clear();
            } while (lines.size() > 0);
        }
    }
 
}
