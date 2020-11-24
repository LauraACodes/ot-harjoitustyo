
package laurassupertetris.controller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import laurassupertetris.ui.Tetris;

public class Block {
    public Rectangle a;
    public Rectangle b;
    public Rectangle c;
    public Rectangle d;
    Color color;
    private String name;

    public static int sqSize = Tetris.sqSize;
    public static int boardWidth = Tetris.boardWidth;
    public static int boardHeigth = Tetris.boardHeight;
    
    public Block() {
        //luo palikan osat
        a = new Rectangle(sqSize - 1, sqSize - 1);
        b = new Rectangle(sqSize - 1, sqSize - 1);
        c = new Rectangle(sqSize - 1, sqSize - 1);
        d = new Rectangle(sqSize - 1, sqSize - 1);

        //NELIO - laittaa palikat paikoilleen koordinaatistossa
        a.setX(boardWidth / 2 - sqSize);
        b.setX(boardWidth / 2);
        c.setX(boardWidth / 2 - sqSize);
        c.setY(sqSize);
        d.setX(boardWidth / 2);        
        d.setY(sqSize);
        name = "square";
        color = Color.BLUE;
        
        // Varjataan palikan osat valitulla varilla
        a.setFill(color);
        b.setFill(color);
        c.setFill(color);
        d.setFill(color);

    }
    
    public String getName() {
        return this.name;
    }
    
    //Testiä varten
    public Rectangle getA() {
        return this.a;
    }
    public Rectangle getB() {
        return this.b;
    }    
    public Rectangle getC() {
        return this.c;
    }
    public Rectangle getD() {
        return this.d;
    }
    
}