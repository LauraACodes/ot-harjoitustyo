
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tetris.blocksandmoves.Block;
import tetris.controls.Controller;
import tetris.blocksandmoves.Turns;
import tetris.scenecreators.GameSceneCreator;
import tetris.ui.Ui;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.blocksandmoves.Moves;

public class TurnsTest {
    
    Turns turns;
    Moves moves;
    int[][] board;
    Block block;
    
    public TurnsTest() {
    }
    
    @Before
    public void setUp() {
        board = new int[12][24];
        turns = new Turns(31, 31, 31 * 12, 31 * 24);
        turns.setBoard(board);
        moves = new Moves(31, 31, 31 * 12, 31 * 24);
    }    
    
    @Test
    public void afterFirsTurnLineCoordsAreRight() {
        block = new Block("line");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnLine(block);
        
        int aX = 155 + 31 + 31;
        int aY = 93 - 31 - 31;
        int bX = 186 + 31;
        int bY = 93 - 31;
        int cX = 217;
        int cY = 93; 
        int dX = 248 - 31;
        int dY = 93 + 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
    
    @Test
    public void afterSecondTurnLineCoordsAreRight() {
        block = new Block("line");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnLine(block);
        turns.turnLine(block);
        
        int aX = 155 + 31 + 31 + 31 + 31;
        int aY = 93 - 31 - 31 + 31 + 31;
        int bX = 186 + 31 + 31;
        int bY = 93 - 31 + 31;
        int cX = 217;
        int cY = 93; 
        int dX = 248 - 31 - 31;
        int dY = 93 + 31 - 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
    
    @Test
    public void afterTrirdTurnLineCoordsAreRight() {
        block = new Block("line");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnLine(block);
        turns.turnLine(block);
        turns.turnLine(block);
        
        int aX = 155 + 31 + 31 + 31 + 31 - 31;
        int aY = 93 - 31 - 31 + 31 + 31 + 31;
        int bX = 186 + 31 + 31;
        int bY = 93 - 31 + 31;
        int cX = 217 + 31;
        int cY = 93 - 31; 
        int dX = 248 - 31 - 31 + 31 + 31;
        int dY = 93 + 31 - 31 - 31 - 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    } 
        
    @Test
    public void afterFourthTurnLineCoordsAreRight() {
        block = new Block("line");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnLine(block);
        turns.turnLine(block);
        turns.turnLine(block);
        turns.turnLine(block);
        
        int aX = 155 + 31 + 31 + 31 + 31 - 31 - 31;
        int aY = 93 - 31 - 31 + 31 + 31 + 31 - 31;
        int bX = 186 + 31 + 31;
        int bY = 93 - 31 + 31;
        int cX = 217 + 31 + 31;
        int cY = 93 - 31 + 31; 
        int dX = 248 - 31 - 31 + 31 + 31 + 31 + 31;
        int dY = 93 + 31 - 31 - 31 - 31 + 31 + 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }   
    
       
    @Test
    public void afterFirsTurnEssCoordsAreRight() {
        block = new Block("ess");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEss(block);
        
        int aX = 155 + 62;
        int aY = 93;
        int bX = 155 + 31;
        int bY = 93 + 31 - 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 186 - 31;
        int dY = 93 + 62 - 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
    
    @Test
    public void afterSecondTurnEssCoordsAreRight() {
        block = new Block("ess");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEss(block);
        turns.turnEss(block);
        
        int aX = 155 + 62;
        int aY = 93 + 62;
        int bX = 155 + 31 + 31;
        int bY = 93 + 31 - 31 + 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 186 - 31 + 31;
        int dY = 93 + 62 - 31 - 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterThirdTurnEssCoordsAreRight() {
        block = new Block("ess");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEss(block);
        turns.turnEss(block);
        turns.turnEss(block);
        
        int aX = 155 + 62 - 31 - 31;
        int aY = 93 + 62;
        int bX = 155 + 31 + 31 - 31;
        int bY = 93 + 31 - 31 + 31 + 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 186 - 31 + 31 + 31;
        int dY = 93 + 62 - 31 - 31 + 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }      

    @Test
    public void afterFourthTurnEssCoordsAreRight() {
        block = new Block("ess");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEss(block);
        turns.turnEss(block);
        turns.turnEss(block);
        turns.turnEss(block);
        
        int aX = 155 + 62 - 31 - 31;
        int aY = 93 + 62 - 31 - 31;
        int bX = 155 + 31 + 31 - 31 - 31;
        int bY = 93 + 31 - 31 + 31 + 31 - 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 186 - 31 + 31 + 31 - 31;
        int dY = 93 + 62 - 31 - 31 + 31 + 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }   

           
    @Test
    public void afterFirsTurnEnnCoordsAreRight() {
        block = new Block("enn");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEnn(block);
        
        int aX = 186 + 31;
        int aY = 93 + 31;
        int bX = 155 + 31;
        int bY = 93 + 31 - 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155;
        int dY = 93 + 62 - 31 - 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
    
    @Test
    public void afterSecondTurnEnnCoordsAreRight() {
        block = new Block("enn");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEnn(block);
        turns.turnEnn(block);
        
        int aX = 186 + 31 - 31;
        int aY = 93 + 31 + 31;
        int bX = 155 + 31 + 31;
        int bY = 93 + 31 - 31 + 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155 + 31 + 31;
        int dY = 93 + 62 - 31 - 31;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
    
    @Test
    public void afterThirdTurnEnnCoordsAreRight() {
        block = new Block("enn");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEnn(block);
        turns.turnEnn(block);
        turns.turnEnn(block);
        int aX = 186 + 31 - 31 - 31;
        int aY = 93 + 31 + 31 - 31;
        int bX = 155 + 31 + 31 - 31;
        int bY = 93 + 31 - 31 + 31 + 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155 + 31 + 31;
        int dY = 93 + 62 - 31 - 31 + 62;
               
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    
    @Test
    public void afterFourthTurnEnnCoordsAreRight() {
        block = new Block("enn");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnEnn(block);
        turns.turnEnn(block);
        turns.turnEnn(block);
        turns.turnEnn(block);
        
        int aX = 186 + 31 - 31 - 31 + 31;
        int aY = 93 + 31 + 31 - 31 - 31;
        int bX = 155 + 31 + 31 - 31 - 31;
        int bY = 93 + 31 - 31 + 31 + 31 - 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155 + 31 + 31 - 62;
        int dY = 93 + 62 - 31 - 31 + 62;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
            
    @Test
    public void afterFirstTurnDudeCoordsAreRight() {
        block = new Block("dude");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnDude(block);
        
        int aX = 155 + 62;
        int aY = 93;
        int bX = 155 + 31;
        int bY = 93 + 31 - 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155;
        int dY = 93 + 62 - 62;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterSecondTurnDudeCoordsAreRight() {
        block = new Block("dude");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnDude(block);
        turns.turnDude(block);
        
        int aX = 155 + 62;
        int aY = 93 + 62;
        int bX = 155 + 31 + 31;
        int bY = 93 + 31 - 31 + 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155 + 62;
        int dY = 93 + 62 - 62;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterThirdTurnDudeCoordsAreRight() {
        block = new Block("dude");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnDude(block);
        turns.turnDude(block);
        turns.turnDude(block);
        
        int aX = 155 + 62 -62;
        int aY = 93 + 62;
        int bX = 155 + 31 + 31 - 31;
        int bY = 93 + 31 - 31 + 31 + 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155 + 62;
        int dY = 93 + 62 - 62 + 62;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterFourthTurnDudeCoordsAreRight() {
        block = new Block("dude");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);
        
        turns.turnDude(block);
        turns.turnDude(block);
        turns.turnDude(block);
        turns.turnDude(block);
        
        int aX = 155 + 62 -62;
        int aY = 93 + 62 - 62;
        int bX = 155 + 31 + 31 - 31 - 31;
        int bY = 93 + 31 - 31 + 31 + 31 - 31;
        int cX = 186;
        int cY = 93 + 31; 
        int dX = 155 + 62 - 62;
        int dY = 93 + 62 - 62 + 62;
        
        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY() && 
                bX == (int) block.b.getX() && bY == block.b.getY() &&
                cX == (int) block.c.getX() && cY == block.c.getY() &&
                dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterFirstTurnJeiCoordsAreRight() {
        block = new Block("jei");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnJei(block);

        int aX = 186 + 31;
        int aY = 93 + 31;
        int bX = 186;
        int bY = 93 + 31;
        int cX = 155;
        int cY = 93 + 62 - 62;
        int dX = 186 - 31;
        int dY = 93 + 62 - 31;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterSecondTurnJeiCoordsAreRight() {
        block = new Block("jei");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnJei(block);
        turns.turnJei(block);
        
        int aX = 186 + 31 - 31;
        int aY = 93 + 31 + 31;
        int bX = 186;
        int bY = 93 + 31;
        int cX = 155 + 62;
        int cY = 93 + 62 - 62;
        int dX = 186 - 31 + 31;
        int dY = 93 + 62 - 31 - 31;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterThirdTurnJeiCoordsAreRight() {
        block = new Block("jei");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnJei(block);
        turns.turnJei(block);
        turns.turnJei(block);
        
        int aX = 186 + 31 - 31 - 31;
        int aY = 93 + 31 + 31 - 31;
        int bX = 186;
        int bY = 93 + 31;
        int cX = 155 + 62;
        int cY = 93 + 62 - 62 + 62;
        int dX = 186 - 31 + 31 + 31;
        int dY = 93 + 62 - 31 - 31 + 31;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterFourthTurnJeiCoordsAreRight() {
        block = new Block("jei");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnJei(block);
        turns.turnJei(block);
        turns.turnJei(block);
        turns.turnJei(block);
        
        int aX = 186 + 31 - 31 - 31 + 31;
        int aY = 93 + 31 + 31 - 31 -31;
        int bX = 186;
        int bY = 93 + 31;
        int cX = 155 + 62 - 62;
        int cY = 93 + 62 - 62 + 62;
        int dX = 186 - 31 + 31 + 31 - 31;
        int dY = 93 + 62 - 31 - 31 + 31 + 31;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterFirstTurnEllCoordsAreRight() {
        block = new Block("ell");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnEll(block);

        int aX = 155 + 31;
        int aY = 93 + 31;
        int bX = 155;
        int bY = 93 + 31;
        int cX = 155 - 31;
        int cY = 93 + 62 - 31; 
        int dX = 186 - 62;
        int dY = 93 + 62;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterSecondTurnEllCoordsAreRight() {
        block = new Block("ell");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnEll(block);
        turns.turnEll(block);
        
        int aX = 155 + 31 - 31;
        int aY = 93 + 31 + 31;
        int bX = 155;
        int bY = 93 + 31;
        int cX = 155 - 31 + 31;
        int cY = 93 + 62 - 31 - 31; 
        int dX = 186 - 62;
        int dY = 93 + 62 - 62;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }

    @Test
    public void afterThirdTurnEllCoordsAreRight() {
        block = new Block("ell");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnEll(block);
        turns.turnEll(block);
        turns.turnEll(block);
        
        int aX = 155 + 31 - 31 - 31;
        int aY = 93 + 31 + 31 - 31;
        int bX = 155;
        int bY = 93 + 31;
        int cX = 155 - 31 + 31 + 31;
        int cY = 93 + 62 - 31 - 31 + 31; 
        int dX = 186 - 62 + 62;
        int dY = 93 + 62 - 62;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
    
    @Test
    public void afterFourthTurnEllCoordsAreRight() {
        block = new Block("ell");
        //pudotetaan, jotta palikka mahtuu kääntymään
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);

        turns.turnEll(block);
        turns.turnEll(block);
        turns.turnEll(block);
        turns.turnEll(block);
        
        int aX = 155 + 31 - 31 - 31 + 31;
        int aY = 93 + 31 + 31 - 31 - 31;
        int bX = 155;
        int bY = 93 + 31;
        int cX = 155 - 31 + 31 + 31 - 31;
        int cY = 93 + 62 - 31 - 31 + 31 + 31; 
        int dX = 186 - 62 + 62;
        int dY = 93 + 62 - 62 + 62;

        boolean areSame = false;

        if (aX == (int) block.a.getX() && aY == block.a.getY()
                && bX == (int) block.b.getX() && bY == block.b.getY()
                && cX == (int) block.c.getX() && cY == block.c.getY()
                && dX == (int) block.d.getX() && dY == block.d.getY()) {
            areSame = true;
        }
        assertTrue(areSame);
    }
  
}
