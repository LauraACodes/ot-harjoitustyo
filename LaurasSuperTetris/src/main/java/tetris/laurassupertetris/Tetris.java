package tetris.laurassupertetris;

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
    public static final int sqSize = 31;
    public static final int move = 31;
    public static int boardWidth = sqSize * 12;
    public static int boardHeight = sqSize * 24;
    public static int[][] board = new int[12][24];
    public static int topRow = 0;
    private static boolean game = true;

    // Asetteluun liittyvät
    public static Block block; 
    public static Block nextBlock;
    public static BorderPane layout; 
    public static Scene gameScene;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // taytetaan lauta nollilla
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
        
        // tehdään ensimmäinen palikka, laitetaan se layoutiin ja sceneen.
        nextBlock = new Block();
        Block startBlock = nextBlock;
        
        //
        layout = new BorderPane();
        layout.getChildren().addAll(startBlock.a, startBlock.b, startBlock.c, startBlock.d);
        gameScene = new Scene(layout, boardWidth, boardHeight);        
        /*
        Button start = new Button("Start");
        BorderPane layout = new BorderPane();
        layout.setCenter(start);
              
        Scene scene = new Scene(layout, 100, 300);
        */
        
        moveOnKeyPress(startBlock);
        block = startBlock;
        nextBlock = new Block();
        
        stage.setTitle("LaurasSuperTetris");
        stage.setScene(gameScene);
        stage.show();   
        
        Timer fall = createTimer();
    }
    
    public void moveOnKeyPress(Block bl) {
        layout.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        moveRight(bl);
                        break;
                    case DOWN:
                        if (downOk(bl)) {
                            removeRows(layout);
                            //tehdään uusi blockki ja lisätään se sceneen
                            Block next = nextBlock;
                            nextBlock = new Block();
                            block = next;
                            layout.getChildren().addAll(next.a, next.b, next.c, next.d);
                            moveOnKeyPress(next);
                        } else {
                            //Miksi tässä muka tarttis olla ehtoa..??
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
    
  
    
    public Timer createTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (block.a.getY() == 0 || block.b.getY() == 0 || 
                                block.c.getY() == 0 || block.d.getY() == 0 ) 
                            topRow++;
                        else
                            topRow = 0;

                        if (topRow == 2) {
                            //Game Over
                            game = false;
                        }
                        if (topRow == 15) {
                            System.exit(0);
                        }
                        if (game) {
                            if (downOk(block)) {
                                removeRows(layout);
                                //tehdään uusi blockki ja lisätään se sceneen
                                Block next = nextBlock;
                                nextBlock = new Block();
                                block = next;
                                //layout.getChildren().addAll(next.a, next.b, next.c, next.d);
                                layout.getChildren().addAll(next.a, next.b, next.c, next.d);
                                moveOnKeyPress(next);
                            } else {
                                //Miksi tässä muka tarttis olla ehtoa..??
                                moveDown(block);
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 300);
        return timer;
    }
        static boolean downOk(Block bl) {
        boolean downOk = false;
        // tämä if katsoo, estääkö joku blockin pudottamisen alaspäin, 
        // ja jos estää, palikka asetetaan boardille ja palautetaan true
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


    
    public static void moveDown(Block bl) {
        bl.a.setY(bl.a.getY() + move);
        bl.b.setY(bl.b.getY() + move);
        bl.c.setY(bl.c.getY() + move);
        bl.d.setY(bl.d.getY() + move);
    }
    
    public static void moveRight(Block block) {
        
    }

    public static void moveLeft(Block block) {
        
    }
    
    public static void moveTurn(Block block) {
        
    }
    
    //seuraavat palauttaa true jos tarkasteltavan blockin osan alla on täyttä
    public static boolean fullA(Block block) {
        return (board[(int) block.a.getX() / sqSize][ (int) block.a.getY() / sqSize + 1] == 1);
    }
    
    public static boolean fullB(Block block) {
        return (board[(int) block.b.getX() / sqSize][ (int) block.b.getY() / sqSize + 1] == 1);
    }
    
    public static boolean fullC(Block block) {
        return (board[(int) block.c.getX() / sqSize][ (int) block.c.getY() / sqSize + 1] == 1);
    }
    
    public static boolean fullD(Block block) {
        return (board[(int) block.d.getX() / sqSize][ (int) block.d.getY() / sqSize + 1] == 1);
    }    
    
    //seuraavissa liikutetaan yhtä osasta jos laudan rajat ei tule vastaan
    public void partDown(Rectangle part) {
        if (part.getY() + move < boardHeight) {
            part.setY(part.getY() + move);
        }
    }
    public void partRight(Rectangle part) {
        if (part.getX() + move < boardWidth - sqSize) {
            part.setX(part.getX() + move);
        }
    }
    public void partLeft(Rectangle part) {
        if (part.getX() - move >= 0) {
            part.setX(part.getX() - move);
        }
    }


    public static void removeRows(BorderPane pane) {
        ArrayList<Node> parts = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newParts = new ArrayList<Node>();
        int full = 0;
        //katsotaan mitkä rivit täysiä
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    full++;
                }
            }
            if (full == board.length) {
                lines.add(i + lines.size());
            }
            full = 0;
        }
        //Tuhotaan se rivi;
        while (lines.size() > 0) {
            for (Node node : layout.getChildren()) {
                if (node instanceof Rectangle) {
                    parts.add(node);
                }
                //Tähän tulisi pisteet rivin tyhjennyksestä
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
        }
    }
    public static void main(String[] args) {
        launch(Tetris.class);
    }  
}
