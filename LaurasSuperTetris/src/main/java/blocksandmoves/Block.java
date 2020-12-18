
package blocksandmoves;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.Ui;
/**
 * Luokka Block luo tetriksen palikat eli Blockit.
 * Block koostuu neljästä Rectanglesta.
 * Blockilla on väri ja nimi.
 * 
 */
public class Block {
    public Rectangle a;
    public Rectangle b;
    public Rectangle c;
    public Rectangle d;
    Color color;
    private String name;

    public static int sqSize = 31;
    public static int boardWidth = sqSize * 12;
    public static int boardHeigth = sqSize * 24;
    /**
     * Konstruktori luo Blockille neljä osaa.
     * Tåmän jälkeen konstruktori kutsuu eri metodeja joiden avulla mm. blockin tyyppi valikoituu.
     */    
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
    /**
     * Metodi valitsee satunnaisesti minkäniminen eli muotoinen Blockista tulee.
     * 
     * @return satunnaisesti valittu nimi.
     */    
    public String chooseBlock() {
        int rand = (int) (Math.random() * 7);
        String bName = "";
     
        if (rand == 0) { 
            bName = "square"; 
        }
        if (rand == 1) {
            bName = "line";
        }
        if (rand == 2) {
            bName = "ess";
        }
        if (rand == 3) {
            bName = "enn";
        }
        if (rand == 4) {
            bName = "dude";
        }
        if (rand == 5) {
            bName = "jei";
        }
        if (rand == 6) {
            bName = "ell";
        }
        
        return bName;
    }

    /**
     * Metodi asettaa syötteenä annetun blockin nimen perusteella blockin neljä osaa pelilaudan koordinaatistoon.
     * 
     * @param bName luotavan blokin nimi joka blockin muodon eli osasten paikan
     */
    public void positionBlock(String bName) {
        if (bName == "square") {
            positionSquare();
        } 
        if (bName == "line") {
            positionLine();
        } 
        if (bName == "ess") {
            positionEss();
        }         
        if (bName == "enn") {
            positionEnn();
        }
        if (bName == "dude") {
            positionDude();
        }
        if (bName == "jei") {
            positionJei();
        }
        if (bName == "ell") {
            positionEll();
        }
    }
    
    public void positionSquare() {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2);
            c.setX(boardWidth / 2 - sqSize);
            c.setY(sqSize);
            d.setX(boardWidth / 2);
            d.setY(sqSize);        
    }
        
    public void positionLine() {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2);
            c.setX(boardWidth / 2 + sqSize);
            d.setX(boardWidth / 2 + 2 * sqSize);       
    }
        
    public void positionEss() {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2);
            c.setY(sqSize);
            d.setX(boardWidth / 2);
            d.setY(sqSize * 2);        
    }
        
    public void positionEnn() {
            a.setX(boardWidth / 2);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2);
            c.setY(sqSize);
            d.setX(boardWidth / 2 - sqSize);
            d.setY(sqSize * 2);        
    }
        
    public void positionDude() {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2);
            c.setY(sqSize);
            d.setX(boardWidth / 2 - sqSize);
            d.setY(sqSize * 2);        
    }
        
    public void positionJei() {
            a.setX(boardWidth / 2);
            b.setX(boardWidth / 2);
            b.setY(sqSize);
            c.setX(boardWidth / 2 - sqSize);
            c.setY(sqSize * 2);
            d.setX(boardWidth / 2);
            d.setY(sqSize * 2);        
    }
        
    public void positionEll() {
            a.setX(boardWidth / 2 - sqSize);
            b.setX(boardWidth / 2 - sqSize);
            b.setY(sqSize);
            c.setX(boardWidth / 2 - sqSize);
            c.setY(sqSize * 2);
            d.setX(boardWidth / 2);
            d.setY(sqSize * 2);        
    }
    /**
     * Metodi valitsee blockin värin.
     * Kaikki tietynmuotoiset blockit ovat tietyn värisiä.
     * 
     * @param bName blockin nimi
     * 
     * @return blocin värin Color.VÄRI -muodossa.
     */
    public Color selectColor(String bName) {
        if (bName == "square") {
            return Color.CORAL;
        } 
        if (bName == "line") {
            return Color.HOTPINK;
        } 
        if (bName == "ess") {
            return Color.LIGHTGREEN;
        }         
        if (bName == "enn") {
            return Color.LIMEGREEN;
        }
        if (bName == "dude") {
            return Color.GOLD;
        }
        if (bName == "jei") {
            return Color.DARKMAGENTA;
        }
        if (bName == "ell") {
            return Color.DEEPSKYBLUE;
        }
        return Color.BLACK;
    }

    /**
     * Metodi värjää palikan kaikki neljä osaa parametrina saadulla värillä.
     * @param color 
     */
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
