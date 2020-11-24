package tetris.laurassupertetris;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class Controller {

    int[][] board;
    int sqSize;
    int move;
    int boardWidth; 
    int boardHeight; 
    BorderPane layout;
    Block block;
    Block nextBlock;
    
    public Controller(int[][] board, int sqSize, int move, int boardWidth, int boardHeight, BorderPane layout, Block block, Block nextBlock) {
        this.board = board;
        this.sqSize = sqSize;
        this.move = move;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.layout = layout;
        this.block = block;
        this.nextBlock = nextBlock;
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
    
    public void moveDown(Block bl) {
        if (downOk(bl)) {
            removeRows(layout);
            //tehdään uusi blockki ja lisätään se sceneen
            Block next = nextBlock;
            nextBlock = new Block();
            block = next;
            layout.getChildren().addAll(next.a, next.b, next.c, next.d);
            Tetris.moveOnKeyPress(next);
        } else {              
            bl.a.setY(bl.a.getY() + move);
            bl.b.setY(bl.b.getY() + move);
            bl.c.setY(bl.c.getY() + move);
            bl.d.setY(bl.d.getY() + move);
        }
    }
    
    public boolean downOk(Block bl) {
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

    public void removeRows(BorderPane pane) {
        ArrayList<Node> parts = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newParts = new ArrayList<Node>();
        int full = 0;
        //katsotaan mitkä rivit täysiä
        System.out.println(board[0].length);
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
        //Tuhotaan se rivi;
        if (lines.size() > 0 ) 
            do {
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
        } while (lines.size() >0);
        
    }
}