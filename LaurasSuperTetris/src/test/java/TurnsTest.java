
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;
import laurassupertetris.controller.Turns;
import laurassupertetris.ui.Gameboard;
import laurassupertetris.ui.Tetris;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TurnsTest {
    
    Controller controller;
    int[][] board;
    Block block = new Block();
//    Block nextBlock = new Block();
    Turns turns;
    
    public TurnsTest() {
        board = new int[12][24];
        controller = new Controller(31, 372, 744);
    }
    
    @Test
    public void afterFirstTurnAXIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        int aX;
        
        if (name.equals("square")) {
            aX = 155;
        } else if (name.equals("line")) {
            aX = 155;
        } else if (name.equals("ess")) {
            aX = 155+62;
        } else if (name.equals("enn")) {
            aX = 186+31;
        } else if (name.equals("dude")) {
            aX = 155+31+31;
        } else if (name.equals("jei")) {
            aX = 186+31;
        } else if (name.equals("ell")) {
            aX = 155+31;
        } else {
            aX = 0;
        }
        assertEquals(aX, (int) block.getA().getX());
    }
    @Test
    public void afterFirstTurnAYIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        int aY;
        
        if (name.equals("square")) {
            aY = 0;
        } else if (name.equals("line")) {
            aY = 0;
        } else if (name.equals("ess")) {
            aY = 0;
        } else if (name.equals("enn")) {
            aY = 0+31;
        } else if (name.equals("dude")) {
            aY = 0;
        } else if (name.equals("jei")) {
            aY = 0+31;
        } else if (name.equals("ell")) {
            aY = 0+31;
        } else {
            aY = 0;
        }
        assertEquals(aY, (int) block.getA().getY());
    }  
    
    @Test
    public void afterFirstTurnBXIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        int bX;
        
        if (name.equals("square")) {
            bX = 186;
        } else if (name.equals("line")) {
            bX = 186;
        } else if (name.equals("ess")) {
            bX = 155+31;
        } else if (name.equals("enn")) {
            bX = 155+31;
        } else if (name.equals("dude")) {
            bX = 155+31;
        } else if (name.equals("jei")) {
            bX = 186;
        } else if (name.equals("ell")) {
            bX = 155;
        } else {
            bX = 0;
        }
        assertEquals(bX, (int) block.getB().getX());
    }
    @Test
    public void afterFirstTurnBYIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        int bY;
        
        if (name.equals("square")) {
            bY = 0;
        } else if (name.equals("line")) {
            bY = 0;
        } else if (name.equals("ess")) {
            bY = 31-31;
        } else if (name.equals("enn")) {
            bY = 31-31;
        } else if (name.equals("dude")) {
            bY = 31-31;
        } else if (name.equals("jei")) {
            bY = 31;
        } else if (name.equals("ell")) {
            bY = 31;
        } else {
            bY = 0;
        }
        assertEquals(bY, (int) block.getB().getY());
    }  
    
    @Test
    public void afterSecondTurnAXIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        controller.moveTurn(block);
        int aX;
        
        if (name.equals("square")) {
            aX = 155;
        } else if (name.equals("line")) {
            aX = 155;
        } else if (name.equals("ess")) {
            aX = 155+62;
        } else if (name.equals("enn")) {
            aX = 186+31-31;
        } else if (name.equals("dude")) {
            aX = 155+62;
        } else if (name.equals("jei")) {
            aX = 186+31-31;
        } else if (name.equals("ell")) {
            aX = 155+31-31;
        } else {
            aX = 0;
        }
        assertEquals(aX, (int) block.getA().getX());
    } 
    
    @Test
    public void afterSecondTurnAYIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        controller.moveTurn(block);        
        int aY;
        
        if (name.equals("square")) {
            aY = 0;
        } else if (name.equals("line")) {
            aY = 0;
        } else if (name.equals("ess")) {
            aY = 0+62;
        } else if (name.equals("enn")) {
            aY = 0+31+31;
        } else if (name.equals("dude")) {
            aY = 0+62;
        } else if (name.equals("jei")) {
            aY = 0+31+31;
        } else if (name.equals("ell")) {
            aY = 0+31+31;
        } else {
            aY = 0;
        }
        assertEquals(aY, (int) block.getA().getY());
    }  

    @Test
    public void afterThirdTurnAXIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        controller.moveTurn(block);
        controller.moveTurn(block);        
        int aX;
        
        if (name.equals("square")) {
            aX = 155;
        } else if (name.equals("line")) {
            aX = 155;
        } else if (name.equals("ess")) {
            aX = 155+62-62;
        } else if (name.equals("enn")) {
            aX = 186+31-31-31;
        } else if (name.equals("dude")) {
            aX = 155+62-62;
        } else if (name.equals("jei")) {
            aX = 186+31-31-31;
        } else if (name.equals("ell")) {
            aX = 155+31-31-31;
        } else {
            aX = 0;
        }
        assertEquals(aX, (int) block.getA().getX());
    } 
    
    @Test
    public void afterThirdTurnAYIsRight() {
        String name = block.getName();
        controller.moveTurn(block);
        controller.moveTurn(block);        
        controller.moveTurn(block);
        int aY;
        
        if (name.equals("square")) {
            aY = 0;
        } else if (name.equals("line")) {
            aY = 0;
        } else if (name.equals("ess")) {
            aY = 0+62;
        } else if (name.equals("enn")) {
            aY = 0+31+31-31;
        } else if (name.equals("dude")) {
            aY = 0+62;
        } else if (name.equals("jei")) {
            aY = 0+31+31-31;
        } else if (name.equals("ell")) {
            aY = 0+31+31-31;
        } else {
            aY = 0;
        }
        assertEquals(aY, (int) block.getA().getY());
    }       
}
