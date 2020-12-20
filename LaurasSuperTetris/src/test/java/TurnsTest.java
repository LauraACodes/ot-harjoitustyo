
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
//    Block nextBlock = new Block();
    
    public TurnsTest() {
    }
    
    @Before
    public void setUp() {
        board = new int[12][24];
        block = new Block();
        turns = new Turns(31, 31, 31 * 12, 31 * 24);
        turns.setBoard(board);
        /*moves = new Moves(31, 31, 31 * 12, 31 * 24);
        moves.moveDown(block);
        moves.moveDown(block);
        moves.moveDown(block);*/
    }    
    
    @Test
    public void afterFirstTurnAXIsRight() {
        String name = block.getName();
        int aX;
        
        if (name.equals("square")) {
            aX = 155;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            aX = 155;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            aX = 155+62;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            aX = 186+31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            aX = 155+31+31;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            aX = 186+31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            aX = 155;
        } else {
            aX = 0;
        }
        assertEquals(aX, (int) block.getA().getX());
    }
    @Test
    public void afterFirstTurnAYIsRight() {
        String name = block.getName();
        int aY;
        
        if (name.equals("square")) {
            aY = 0;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            aY = 0;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            aY = 0;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            aY = 0 + 31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);            
            aY = 0;
        } else if (name.equals("jei")) {
            turns.turnJei(block);            
            aY = 0 + 31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            aY = 0 + 31;
        } else {
            aY = 0;
        }
        assertEquals(aY, (int) block.getA().getY());
    }  
    
    @Test
    public void afterFirstTurnBXIsRight() {
        String name = block.getName();
        int bX;
        
        if (name.equals("square")) {
            bX = 186;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            bX = 186;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            bX = 155+31;
        } else if (name.equals("enn")) {
            turns.turnEll(block);
            bX = 155+31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            bX = 155+31;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            bX = 186;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            bX = 155;
        } else {
            bX = 0;
        }
        assertEquals(bX, (int) block.getB().getX());
    }
    @Test
    public void afterFirstTurnBYIsRight() {
        String name = block.getName();
        int bY;
        
        if (name.equals("square")) {
            bY = 0;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            bY = 0;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            bY = 31 - 31;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            bY = 31 - 31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            bY = 31 - 31;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            bY = 31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            bY = 31;
        } else {
            bY = 0;
        }
        assertEquals(bY, (int) block.getB().getY());
    }  
    
    @Test
    public void afterSecondTurnAXIsRight() {
        String name = block.getName();
        int aX;
        
        if (name.equals("square")) {
            aX = 155;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            turns.turnLine(block);            
            aX = 155;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            turns.turnEss(block);
            aX = 155+62;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            turns.turnEnn(block);
            aX = 186+31-31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            turns.turnDude(block);            
            aX = 155+62;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            turns.turnJei(block);
            aX = 186+31-31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            turns.turnEll(block);
            aX = 155+31-31;
        } else {
            aX = 0;
        }
        assertEquals(aX, (int) block.getA().getX());
    } 
    
    @Test
    public void afterSecondTurnAYIsRight() {
        String name = block.getName();      
        int aY;
        
        if (name.equals("square")) {
            aY = 0;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            turns.turnLine(block);  
            aY = 0;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            turns.turnEss(block);  
            aY = 0 + 62;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            turns.turnEnn(block);  
            aY = 0 + 31 + 31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            turns.turnDude(block);  
            aY = 0 + 62;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            turns.turnJei(block);  
            aY = 0 + 31 + 31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            turns.turnEll(block);  
            aY = 0 + 31 + 31;
        } else {
            aY = 0;
        }
        assertEquals(aY, (int) block.getA().getY());
    }  

    @Test
    public void afterThirdTurnAXIsRight() {
        String name = block.getName();      
        int aX;
        
        if (name.equals("square")) {
            aX = 155;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            turns.turnLine(block);  
            turns.turnLine(block);              
            aX = 155;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            turns.turnEss(block);  
            turns.turnEss(block);              
            aX = 155+62-62;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            turns.turnEnn(block);  
            turns.turnEnn(block);              
            aX = 186+31-31-31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            turns.turnDude(block);  
            turns.turnDude(block);              
            aX = 155+62-62;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            turns.turnJei(block);  
            turns.turnJei(block);              
            aX = 186+31-31-31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            turns.turnEll(block);  
            turns.turnEll(block);              
            aX = 155+31-31-31;
        } else {
            aX = 0;
        }
        assertEquals(aX, (int) block.getA().getX());
    } 
    
    @Test
    public void afterThirdTurnAYIsRight() {
        String name = block.getName();
        int aY;
        
        if (name.equals("square")) {
            aY = 0;
        } else if (name.equals("line")) {
            turns.turnLine(block);
            turns.turnLine(block);  
            turns.turnLine(block);  
            aY = 0;
        } else if (name.equals("ess")) {
            turns.turnEss(block);
            turns.turnEss(block);  
            turns.turnEss(block);  
            aY = 0 + 62;
        } else if (name.equals("enn")) {
            turns.turnEnn(block);
            turns.turnEnn(block);  
            turns.turnEnn(block);  
            aY = 0 + 31 + 31 - 31;
        } else if (name.equals("dude")) {
            turns.turnDude(block);
            turns.turnDude(block);  
            turns.turnDude(block);  
            aY = 0 + 62 + 93;
        } else if (name.equals("jei")) {
            turns.turnJei(block);
            turns.turnJei(block);  
            turns.turnJei(block);   
            aY = 0 + 31 + 31 - 31;
        } else if (name.equals("ell")) {
            turns.turnEll(block);
            turns.turnEll(block);  
            turns.turnEll(block);  
            aY = 0 + 31 + 31 - 31;
        } else {
            aY = 0;
        }
        assertEquals(aY, (int) block.getA().getY());
    }       
}
