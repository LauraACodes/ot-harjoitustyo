
package tetris.blocksandmoves;

import tetris.controls.Controller;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static tetris.controls.Controller.lineCount;
import static tetris.controls.Controller.lineText;
import static tetris.controls.Controller.score;
import static tetris.controls.Controller.scoreText;

public class Moves {
    
    int sqSize;
    int move;
    int boardWidth; 
    int boardHeight; 
    int[][] board = Controller.board;
    
    Text scoreText = Controller.scoreText; 
    Text lineText = Controller.lineText;    
    int score = Controller.score; 
    int lineCount = Controller.lineCount;      
    

    public Moves(int sqS, int move, int boardWidth, int boardHeight) {
        this.sqSize = sqS;
        this.move = move;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
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
    /**
     * Metodi liikuttaa koko palikkaa vasemmalle jos mahdollista.
     * If-lause tarkistaa ensin, voiko kaikki blockin osat liikkua yhden muuvin 
     * verran vasemmalle pelilaudalla. Jos voi, siirtometodia kutsutaan jokaisen 
     * blockin osan kohdalla. 
     * 
     * @param block liikuteltava Blokki 
     */
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
    /**
     * Metodi tarkastaa, estääkö jokin blockin pudottamisen alaspäin.
     * Jos estää, blocki asetetaan pelilaudalle ja palautetaan false.
     * @param bl liikuteltava Blokki
     * @return palauttaa epäloogisesti false, jos blockin voi pudottaa ja true jos ei voi.  
     */
    public boolean downOk(Block bl) {
        boolean downOk = false;
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
    
    /**
     * Metodi liikuttaa koko palikkaa alas, lisää pisteitä ja päivittää pistelaskun.
     * Metodin kutsumista edeltää tarkistus siitä, voiko osia liikuttaa. 
     * 
     * @param block liikuteltava Blokki 
     */
    public void moveDown(Block bl) {
        bl.a.setY(bl.a.getY() + move);
        bl.b.setY(bl.b.getY() + move);
        bl.c.setY(bl.c.getY() + move);
        bl.d.setY(bl.d.getY() + move);
    }
    /**
     * Metodi liikuttaa yhtä Blockin osaa alaspäin jos laudan rajat eivät tule vastaan.
     * @param part liikuteltava Blockin osa. 
     */        
    public void partDown(Rectangle part) {
        if (part.getY() + move <= boardHeight) {
            part.setY(part.getY() + move);
        }
    }
    /**
     * Metodi liikuttaa yhtä Blockin osaa oikealle jos laudan rajat eivät tule vastaan.
     * @param part liikuteltava Blockin osa. 
     */ 
    public void partRight(Rectangle part) {
        if (part.getX() + move <= boardWidth - sqSize) {
            part.setX(part.getX() + move);
        }
    }
    /**
     * Metodi liikuttaa yhtä Blockin osaa vasemmalle jos laudan rajat eivät tule vastaan.
     * @param part liikuteltava Blockin osa. 
     */ 
    public void partLeft(Rectangle part) {
        if (part.getX() - move >= 0) {
            part.setX(part.getX() - move);
        }
    }
    /**
     * Metodi liikuttaa yhtä Blockin osaa ylöspäin jos laudan rajat eivät tule vastaan.
     * @param part liikuteltava Blockin osa. 
     */ 
    public void partUp(Rectangle part) {
        if (part.getY() - move >= 0) {
            part.setY(part.getY() - move);
        }
    }

    /**
     * Metodi palauttaa true, jos tarkasteltavan blockin A osan alla on täyttä.
     * 
     * @param block
     * @return true, jos täyttä, false jos on tilaa.
     */       

    public boolean fullA(Block block) {
        return (board[(int) block.a.getX() / sqSize][(int) block.a.getY() / sqSize + 1] == 1);
    }
    /**
     * Metodi palauttaa true, jos tarkasteltavan blockin B osan alla on täyttä.
     * 
     * @param block
     * @return true, jos täyttä, false jos on tilaa.
     */  
    public boolean fullB(Block block) {
        return (board[(int) block.b.getX() / sqSize][(int) block.b.getY() / sqSize + 1] == 1);
    }
    /**
     * Metodi palauttaa true, jos tarkasteltavan blockin C osan alla on täyttä.
     * 
     * @param block
     * @return true, jos täyttä, false jos on tilaa.
     */  
    public boolean fullC(Block block) {
        return (board[(int) block.c.getX() / sqSize][(int) block.c.getY() / sqSize + 1] == 1);
    }
    /**
     * Metodi palauttaa true, jos tarkasteltavan blockin D osan alla on täyttä.
     * 
     * @param block
     * @return true, jos täyttä, false jos on tilaa.
     */  
    public boolean fullD(Block block) {
        return (board[(int) block.d.getX() / sqSize][(int) block.d.getY() / sqSize + 1] == 1);
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    
}
