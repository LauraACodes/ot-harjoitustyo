package laurassupertetris.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import laurassupertetris.ui.Tetris;


public class Controller {

    int[][] board;
    int sqSize;
    int move;
    int boardWidth; 
    int boardHeight; 
    BorderPane layout = Tetris.layout;
    Block block = Tetris.block;
    Block nextBlock = Tetris.nextBlock;
    Turns turns;
    Text scoreText = Tetris.scoreText;
    int score = Tetris.score;
    Text lineText = Tetris.lineText;    
    int lineCount = Tetris.lineCount;
    Scene gameScene;
    Boolean showTetris = false;
    
    
    //public Controller(int[][] board, , int move, int boardWidth, int boardHeight, BorderPane layout, Block block, Block nextBlock) {
    
    public Controller(int sqSize, int boardWidth, int boardHeight, Scene gscene) {
        this.board = new int[12][24];
        this.sqSize = sqSize;
        this.move = 31;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameScene =gscene;
        this.turns = new Turns(this.sqSize, this.move, this.boardWidth, this.boardHeight, this.board);
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
        
        
    }
    
    public void moveRight(Block block) {
        //onko oikealla tilaa siirtyä huomioiden laudan leveys ja olemassa olevat palikat 
        if (block.a.getX() + move <= boardWidth - sqSize && block.b.getX() + move <= boardWidth - sqSize
                && block.c.getX() + move <= boardWidth - sqSize && block.d.getX() + move <= boardWidth - sqSize
                && board[((int) block.a.getX() / sqSize) + 1][(int) block.a.getY() / sqSize] == 0
                && board[((int) block.b.getX() / sqSize) + 1][(int) block.b.getY() / sqSize] == 0
                && board[((int) block.c.getX() / sqSize) + 1][(int) block.c.getY() / sqSize] == 0
                && board[((int) block.d.getX() / sqSize) + 1][(int) block.d.getY() / sqSize] == 0) {

            // jos on tilaa, kaikki osat siirtyy yhden oikealle
            partRight(block.a);
            partRight(block.b);
            partRight(block.c);
            partRight(block.d);
        }
        // ja jos ei ole tilaa, niin ei tee mitään
    }

    public void moveLeft(Block block) {
        //onko vasemmalla tilaa siirtyä huomioiden laudan leveys ja olemassa olevat palikat 
        if (block.a.getX() - move >= 0 && block.b.getX() - move >= 0
                && block.c.getX() - move >= 0 && block.d.getX() - move >= 0
                && board[((int) block.a.getX() / sqSize) - 1][(int) block.a.getY() / sqSize] == 0
                && board[((int) block.b.getX() / sqSize) - 1][(int) block.b.getY() / sqSize] == 0
                && board[((int) block.c.getX() / sqSize) - 1][(int) block.c.getY() / sqSize] == 0
                && board[((int) block.d.getX() / sqSize) - 1][(int) block.d.getY() / sqSize] == 0) {

            // jos on tilaa, kaikki osat siirtyy yhden vasemmalle
            partLeft(block.a);
            partLeft(block.b);
            partLeft(block.c);
            partLeft(block.d);
        }
        // ja jos ei ole tilaa, niin ei tee mitään        
    }

    public void moveDown(Block bl) {
        bl.a.setY(bl.a.getY() + move);
        bl.b.setY(bl.b.getY() + move);
        bl.c.setY(bl.c.getY() + move);
        bl.d.setY(bl.d.getY() + move);
        score++;
        scoreText.setText("SCORE: " + score);
        lineText.setText("LINES: " + lineCount);
    }
    
    public boolean downOk(Block bl) {
        boolean downOk = false;
        // tämä if katsoo, estääkö joku blockin pudottamisen alaspäin, 
        // ja jos estää, palikka asetetaan boardille ja palautetaan false
        if (bl.a.getY() == boardHeight - sqSize || bl.b.getY() == boardHeight - sqSize
                || bl.c.getY() == boardHeight - sqSize || bl.d.getY() == boardHeight - sqSize
                || fullA(bl) || fullB(bl) || fullC(bl) || fullD(bl)) {
            board[(int) bl.a.getX() / sqSize][(int) bl.a.getY() / sqSize] = 1;
            board[(int) bl.b.getX() / sqSize][(int) bl.b.getY() / sqSize] = 1;
            board[(int) bl.c.getX() / sqSize][(int) bl.c.getY() / sqSize] = 1;
            board[(int) bl.d.getX() / sqSize][(int) bl.d.getY() / sqSize] = 1;
            downOk = true;
        }
        return downOk;
    }
    
        
    //seuraavissa liikutetaan yhtä osasta jos laudan rajat ei tule vastaan
    public void partDown(Rectangle part) {
        if (part.getY() + move < boardHeight) {
            part.setY(part.getY() + move);
        }
    }

    public void partRight(Rectangle part) {
        if (part.getX() + move <= boardWidth - sqSize) {
            part.setX(part.getX() + move);
        }
    }

    public void partLeft(Rectangle part) {
        if (part.getX() - move >= 0) {
            part.setX(part.getX() - move);
        }
    }

    public void partUp(Rectangle part) {
        if (part.getY() - move >= 0) {
            part.setY(part.getY() - move);
        }
    }
    
    //seuraavat palauttaa true jos tarkasteltavan blockin osan alla on täyttä
    public boolean fullA(Block block) {
        return (board[(int) block.a.getX() / sqSize][(int) block.a.getY() / sqSize + 1] == 1);
    }

    public boolean fullB(Block block) {
        return (board[(int) block.b.getX() / sqSize][(int) block.b.getY() / sqSize + 1] == 1);
    }

    public boolean fullC(Block block) {
        return (board[(int) block.c.getX() / sqSize][(int) block.c.getY() / sqSize + 1] == 1);
    }

    public boolean fullD(Block block) {
        return (board[(int) block.d.getX() / sqSize][(int) block.d.getY() / sqSize + 1] == 1);
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
            }   while (lines.size() > 0);
        }
        /*Block next = nextBlock;
        nextBlock = new Block();
        block = next;
        layout.getChildren().addAll(next.a, next.b, next.c, next.d);
        moveOnKeyPress(next);*/
    }

    public void moveOnKeyPress(Block bl) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        moveRight(bl);
                        break;
                    case DOWN:
                        if (downOk(bl)) {
                            removeRows();
                        } else {
                            moveDown(bl);

                        }
                        break;
                    case LEFT:
                        moveLeft(bl);
                        break;
                    case UP:
                        moveTurn(bl);
                        break;
                }
            }
        });
    }
    
}