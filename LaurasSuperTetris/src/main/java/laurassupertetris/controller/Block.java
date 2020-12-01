
package laurassupertetris.controller;

import java.util.Random;
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

        name = chooseBlock();
        positionBlock(name);
        color = selectColor(name);
        fillBlockWithColor(color);

    }
    //arpoo minkälainen blokki tehdään. Onko tällainen if-muotoilu sallittu?
    public String chooseBlock() {
        int rand = (int) (Math.random()*7);
        String bName = "";
     
        if (rand == 0) {bName = "square";}
        if (rand == 1) {bName = "line";}
        if (rand == 2) {bName = "ess";}
        if (rand == 3) {bName = "enn";}
        if (rand == 4) {bName = "dude";}
        if (rand== 5) {bName = "jei";}
        if (rand == 6) {bName = "ell";}
        
        return bName;
    }
    //asettaa kunkin blockin osan paikoilleen koordinaatistoon
    public void positionBlock(String bName) {
        if (bName == "square") {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2);
            c.setX(boardWidth / 2 - sqSize);
            c.setY(sqSize);
            d.setX(boardWidth / 2);
            d.setY(sqSize);
        } 
        if (bName == "line") {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2);
            c.setX(boardWidth / 2 + sqSize);
            d.setX(boardWidth / 2 + 2 * sqSize);
        } 
        if (bName == "ess") {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2);
            c.setY(sqSize);
            d.setX(boardWidth / 2);
            d.setY(sqSize * 2);
        }         
        if (bName == "enn") {
            a.setX(boardWidth / 2);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2);
            c.setY(sqSize);
            d.setX(boardWidth / 2 - sqSize);
            d.setY(sqSize * 2);
        }
        if (bName == "dude") {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2);
            c.setY(sqSize);
            d.setX(boardWidth / 2 - sqSize);
            d.setY(sqSize * 2);
        }
        if (bName == "jei") {
            a.setX(boardWidth / 2);
            b.setX(boardWidth / 2);
            b.setY(sqSize);
            c.setX(boardWidth / 2 - sqSize);
            c.setY(sqSize * 2);
            d.setX(boardWidth / 2);
            d.setY(sqSize * 2);
        }
        if (bName == "ell") {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2 - sqSize);
            c.setY(sqSize * 2);
            d.setX(boardWidth / 2);
            d.setY(sqSize * 2);
        }
    }

    public Color selectColor(String bName) {
        if (bName == "square") {
            return Color.BLUE;
        } 
        if (bName == "line") {
            return Color.CHOCOLATE;
        } 
        if (bName == "ess") {
            return Color.FIREBRICK;
        }         
        if (bName == "enn") {
            return Color.LAVENDER;
        }
        if (bName == "dude") {
            return Color.SILVER;
        }
        if (bName == "jei") {
            return Color.AQUA;
        }
        if (bName == "ell") {
            return Color.GREENYELLOW;
        }
        return Color.BLACK;
    }
    
    public void fillBlockWithColor(Color color) {
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
