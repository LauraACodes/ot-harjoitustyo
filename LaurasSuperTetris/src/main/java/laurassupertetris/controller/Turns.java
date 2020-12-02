
package laurassupertetris.controller;

import javafx.scene.shape.Rectangle;

public class Turns {
    
    int sqSize;
    int move;
    int boardWidth; 
    int boardHeight; 
    int[][] board;
    
    /*Tästä eteenpäin metodit kääntää kutakin palikkatyyppiä.
    Näiden iffeissä tsekataan vain ne mitä muuttuu, ei niitä ruutuja, joissa on jo jokin kyseisen palikan osa!
    Jokaisen iffin ensimmäinen ehto kertoo missä asennossa palikka on ja katsoo loput ehdot sen mukaan,
    miten mikäkin palikan osan pitäisi liikkua.*/
    public Turns(int sqS, int move, int boardWidth, int boardHeight, int[][] board) {
        this.sqSize = sqS;
        this.move = move;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.board = board;
        
    }
    
    public void turnLine(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    

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
        } else if (a.getY() < b.getY() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY() + 2 * sqSize)
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
        } else if (a.getX() > b.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
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
        } else if (a.getY() > b.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
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

    public void turnEss(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        
        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            partRight(a);
            partRight(a);
            partRight(b);
            partUp(b);
            partLeft(d);
            partUp(d);
        } else if (b.getY() < c.getY() && isTurnOk((int) a.getX(), (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            partDown(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partRight(d);
            partUp(d);
        } else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            partLeft(a);
            partLeft(a);
            partLeft(b);
            partDown(b);
            partRight(d);
            partDown(d);
        } else if (b.getY() > c.getY() && isTurnOk((int) a.getX(), (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            partUp(a);
            partUp(a);
            partLeft(b);
            partUp(b);
            partLeft(d);
            partDown(d);
        }
    }
    
    public void turnEnn(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    

        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            partRight(a);
            partDown(a);
            partRight(b);
            partUp(b);
            partUp(d);
            partUp(d);
        } else if (b.getY() < c.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            partLeft(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partRight(d);
            partRight(d);
        } else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            partLeft(a);
            partUp(a);
            partLeft(b);
            partDown(b);
            partDown(d);
            partDown(d);
        } else if (b.getY() > c.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            partRight(a);
            partUp(a);
            partLeft(b);
            partUp(b);
            partLeft(d);
            partLeft(d);
        }
    }
    
    public void turnDude(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        
        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            partRight(a);
            partRight(a);
            partRight(b);
            partUp(b);
            partUp(d);
            partUp(d);
        } else if (b.getY() < c.getY() && isTurnOk((int) a.getX(), (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            partDown(a);
            partDown(a);
            partRight(b);
            partDown(b);
            partRight(d);
            partRight(d);
        } else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            partLeft(a);
            partLeft(a);
            partLeft(b);
            partDown(b);
            partDown(d);
            partDown(d);
        } else if (b.getY() > c.getY() && isTurnOk((int) a.getX(), (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            partUp(a);
            partUp(a);
            partLeft(b);
            partUp(b);
            partLeft(d);
            partLeft(d);
        }
    }
    
    public void turnJei(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;    
        
        if (b.getY() < d.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX(), (int) c.getY() - 2 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            partRight(a);
            partDown(a);
            partUp(c);
            partUp(c);
            partLeft(d);
            partUp(d);
        } else if (b.getX() > d.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 2 * sqSize, (int) c.getY())
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            partLeft(a);
            partDown(a);
            partRight(c);
            partRight(c);
            partRight(d);
            partUp(d);
        } else if (b.getY() > d.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX(), (int) c.getY() + 2 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            partLeft(a);
            partUp(a);
            partDown(c);
            partDown(c);
            partRight(d);
            partDown(d);
        } else if (b.getX() < d.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() - 2 * sqSize, (int) c.getY())
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
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
        
        if (b.getY() < c.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() - 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            partRight(a);
            partDown(a);
            partLeft(c);
            partUp(c);
            partLeft(d);
            partLeft(d);
        } else if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            partLeft(a);
            partDown(a);
            partRight(c);
            partUp(c);
            partUp(d);
            partUp(d);
        } else if (b.getY() > c.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            partLeft(a);
            partUp(a);
            partRight(c);
            partDown(c);
            partRight(d);
            partRight(d);
        } else if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() - 1 * sqSize, (int) c.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            partRight(a);
            partUp(a);
            partLeft(c);
            partDown(c);
            partDown(d);
            partDown(d);
        }
    }
    
    public boolean isTurnOk(int x, int y) {
        boolean turnOk = true;
        //tuleeko X tai Y-koordinaatin rajat vastaan. ja sen jälkeen katsoo onko alla jotain.
        if (x > boardWidth | x < 0 | y > boardHeight | y < 0) {
            turnOk = false;

        } else if (board[x / sqSize][y / sqSize] == 1) {
            turnOk = false;
        }
        
        return turnOk;
    }

    //seuraavissa liikutetaan yhtä osasta jos laudan rajat ei tule vastaan
    public void partDown(Rectangle part) {
        part.setY(part.getY() + move);
    }

    public void partRight(Rectangle part) {
        part.setX(part.getX() + move);
    }

    public void partLeft(Rectangle part) {
        part.setX(part.getX() - move);
    }

    public void partUp(Rectangle part) {
        part.setY(part.getY() - move);
    }    
}