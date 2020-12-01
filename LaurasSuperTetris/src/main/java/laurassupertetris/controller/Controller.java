package laurassupertetris.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    //public Controller(int[][] board, , int move, int boardWidth, int boardHeight, BorderPane layout, Block block, Block nextBlock) {
    
    public Controller(int sqSize, int boardWidth, int boardHeight, BorderPane layout, Block block, Block nextBlock) {
        this.board = new int[12][24];
        this.sqSize = sqSize;
        this.move = 31;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.layout = layout;
        this.block = block;
        this.nextBlock = nextBlock;
        
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
            turnLine(block);
        }

        if (name.equals("ess")) {
            turnEss(block);
        }
        
        if (name.equals("enn")) {
            turnEnn(block);
        }
        
        if (name.equals("dude")) {
            turnDude(block);
        }
        
        if (name.equals("jei")) {
            turnJei(block);
        }

        if (name.equals("ell")) {
            turnEll(block);
        }    
    }
    
    /*Tästä eteenpäin metodit kääntää kutakin palikktyyppiä.
    Näiden iffeissä tsekataan vain ne mitä muuttuu, ei niitä ruutuja, joissa on jo jokin kyseisen palikan osa!
    Jokaisen iffin ensimmäinen ehto kertoo missä asennossa palikka on ja katsoo loput ehdot sen mukaan,
    miten mikäkin palikan osan pitäisi liikkua.*/
    
    public void turnLine(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        //starttiasennosta ensimmäiseen pystyyn
        if (a.getX() < b.getX() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            partRight(a);
            partRight(a);
            partUp(a);
            partUp(a);
            partUp(b);
            partRight(b);
            partLeft(d);
            partDown(d);
        } //asento2 - ensimmaisestä pystystä toiseen vaakaan
        else if (a.getY() < b.getY() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            partRight(a);
            partRight(a);
            partDown(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partLeft(d);
            partUp(d);
        } //asento3 - toisesta vaa'asta toiseen pystyyn
        else if (a.getX() > b.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY() - 2 * sqSize)) {
            partLeft(a);
            partDown(a);
            partRight(c);
            partUp(c);
            partRight(d);
            partRight(d);
            partUp(d);
            partUp(d);
        } //asento4 - toista pystystä alkuasentoon
        else if (a.getY() > b.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY() + 2 * sqSize)) {
            partLeft(a);
            partUp(a);
            partRight(c);
            partDown(c);
            partRight(d);
            partRight(d);
            partDown(d);
            partDown(d);
        }
    }
    
    public void turnEnn(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        //starttiasennosta ensimmäiseen pystyyn
        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            System.out.println("ensimmainen starttas");
            partRight(a);
            partDown(a);
            partRight(b);
            partUp(b);
            partUp(d);
            partUp(d);
        } //asento2 - ensimmaisestä pystystä toiseen vaakaan
        else if (b.getY() < c.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            System.out.println("toinen starttas");
            partLeft(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partRight(d);
            partRight(d);
        } //asento3 - toisesta vaa'asta toiseen pystyyn
        else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            System.out.println("kolmas starttas");
            partLeft(a);
            partUp(a);
            partLeft(b);
            partDown(b);
            partDown(d);
            partDown(d);
        } //asento4 - toista pystystä alkuasentoon
        else if (b.getY() > c.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            System.out.println("neljäs starttas");
            partRight(a);
            partUp(a);
            partLeft(b);
            partUp(b);
            partLeft(d);
            partLeft(d);
        }
    }

    public void turnEss(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        //starttiasennosta ensimmäiseen pystyyn
        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            System.out.println("ensimmainen starttas");
            partRight(a);
            partRight(a);
            partRight(b);
            partUp(b);
            partLeft(d);
            partUp(d);
        } //asento2 - ensimmaisestä pystystä toiseen vaakaan
        else if (b.getY() < c.getY() && isTurnOk((int) a.getX(), (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            System.out.println("toinen starttas");
            partDown(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partRight(d);
            partUp(d);
        } //asento3 - toisesta vaa'asta toiseen pystyyn
        else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            System.out.println("kolmas starttas");
            partLeft(a);
            partLeft(a);
            partLeft(b);
            partDown(b);
            partRight(d);
            partDown(d);
        } //asento4 - toista pystystä alkuasentoon
        else if (b.getY() > c.getY() && isTurnOk((int) a.getX(), (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            System.out.println("neljäs starttas");
            partUp(a);
            partUp(a);
            partLeft(b);
            partUp(b);
            partLeft(d);
            partDown(d);
        }
    }
  
    public void turnJei(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        //starttiasennosta ensimmäiseen pystyyn
        if (b.getY() < d.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX(), (int) c.getY() - 2 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            System.out.println("ensimmainen starttas");
            partRight(a);
            partDown(a);
            partUp(c);
            partUp(c);
            partLeft(d);
            partUp(d);
        } //asento2 - ensimmaisestä pystystä toiseen vaakaan
        else if (b.getX() > d.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 2 * sqSize, (int) c.getY())
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            System.out.println("toinen starttas");
            partLeft(a);
            partDown(a);
            partRight(c);
            partRight(c);
            partRight(d);
            partUp(d);
        } //asento3 - toisesta vaa'asta toiseen pystyyn
        else if (b.getY() > d.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX(), (int) c.getY() + 2 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            System.out.println("kolmas starttas");
            partLeft(a);
            partUp(a);
            partDown(c);
            partDown(c);
            partRight(d);
            partDown(d);
        } //asento4 - toista pystystä alkuasentoon
        else if (b.getX() < d.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() - 2 * sqSize, (int) c.getY())
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            System.out.println("neljäs starttas");
            partRight(a);
            partUp(a);
            partLeft(c);
            partLeft(c);
            partLeft(d);
            partDown(d);
        }
    }
  
    public void turnEll(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        //starttiasennosta ensimmäiseen pystyyn
        if (b.getY() < c.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() - 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            System.out.println("ensimmainen starttas");
            partRight(a);
            partDown(a);
            partLeft(c);
            partUp(c);
            partLeft(d);
            partLeft(d);
        } //asento2 - ensimmaisestä pystystä toiseen vaakaan
        else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            System.out.println("toinen starttas");
            partLeft(a);
            partDown(a);
            partRight(c);
            partUp(c);
            partUp(d);
            partUp(d);
        } //asento3 - toisesta vaa'asta toiseen pystyyn
        else if (b.getY() > c.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            System.out.println("kolmas starttas");
            partLeft(a);
            partUp(a);
            partRight(c);
            partDown(c);
            partRight(d);
            partRight(d);
        } //asento4 - toista pystystä alkuasentoon
        else if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() - 1 * sqSize, (int) c.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            System.out.println("neljäs starttas");
            partRight(a);
            partUp(a);
            partLeft(c);
            partDown(c);
            partDown(d);
            partDown(d);
        }
    }
    
    public void turnDude(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        //starttiasennosta ensimmäiseen pystyyn
        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            System.out.println("ensimmainen starttas");
            partRight(a);
            partRight(a);
            partRight(b);
            partUp(b);
            partUp(d);
            partUp(d);
        } //asento2 - ensimmaisestä pystystä toiseen vaakaan
        else if (b.getY() < c.getY() && isTurnOk((int) a.getX(), (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            System.out.println("toinen starttas");
            partDown(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partRight(d);
            partRight(d);
        } //asento3 - toisesta vaa'asta toiseen pystyyn
        else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            System.out.println("kolmas starttas");
            partLeft(a);
            partLeft(a);
            partLeft(b);
            partDown(b);
            partDown(d);
            partDown(d);
        } //asento4 - toista pystystä alkuasentoon
        else if (b.getY() > c.getY() && isTurnOk((int) a.getX(), (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            System.out.println("neljäs starttas");
            partUp(a);
            partUp(a);
            partLeft(b);
            partUp(b);
            partLeft(d);
            partLeft(d);
        }
    }
    
    public boolean isTurnOk(int x, int y) {
        boolean turnOk = true;
        //tuleeko X-koordinaatin rajat vastaan.
        if (x > boardWidth | x < 0) {
            turnOk = false;
        }
        //tuleeko Y-koordinaatin rajat vastaan.
        if (y > boardHeight | y < 0) {
            turnOk = false;
        }
        //onko palikan alla jo jotain.
        if (board[x / sqSize][y / sqSize] == 1) {
            turnOk = false;
        }
        return turnOk;
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
        if (lines.size() > 0) {
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
            }   while (lines.size() > 0);
        }
    }
    
}