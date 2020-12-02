


import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;

public class ControllerTest {
    
    int[][] board;
    Block block;
    Controller controller;
    
    public ControllerTest() {
    }
    
    @Before
    public void setUp() {
        board = new int[12][24];
        block = new Block();
        controller = new Controller(31, 372, 744, new BorderPane(), block, new Block());
    }    
    
    @Test
    public void downOkReturnFalseWhenBoardIsEmpty() {
        assertEquals(false, controller.downOk(block));
    }   
    
    @Test
    public void downOkReturnTrueWhenBlockCantMove() {
        Rectangle a = block.getA();
        a.setY(713);
        assertEquals(true, controller.downOk(block));
    }       
    
    @Test
    public void moveDownMovesSquareADown() {
        controller.moveDown(block);
        assertEquals(31, (int) block.getA().getY());
    }
    
    //Nämä 2 seuraavaa voisi varmaan tehdä kätevämminkin..
    @Test
    public void moveRightMovesBlockRight() {
        Rectangle a = block.getA();
        int aX = (int) a.getX();
        Rectangle b = block.getB();
        int bX = (int) b.getX();
        Rectangle c = block.getC();
        int cX = (int) c.getX();
        Rectangle d = block.getD();
        int dX = (int) d.getX();
        
        controller.moveRight(block);
        aX += 31;
        bX += 31;
        cX += 31;
        dX += 31;

        Rectangle aN = block.getA();
        int aXN = (int) a.getX();
        Rectangle bN = block.getB();
        int bXN = (int) b.getX();
        Rectangle cN = block.getC();
        int cXN = (int) c.getX();
        Rectangle dN = block.getD();
        int dXN = (int) d.getX();
        
        boolean isSame = false;
        if (aX == aXN && bX == bXN && cX == cXN && dX == dXN) {
            isSame=true;
        }
        assertTrue(isSame);
    }
    
    @Test
    public void moveLeftMovesBlockLeft() {
        Rectangle a = block.getA();
        int aX = (int) a.getX();
        Rectangle b = block.getB();
        int bX = (int) b.getX();
        Rectangle c = block.getC();
        int cX = (int) c.getX();
        Rectangle d = block.getD();
        int dX = (int) d.getX();
        
        controller.moveLeft(block);
        aX -= 31;
        bX -= 31;
        cX -= 31;
        dX -= 31;

        Rectangle aN = block.getA();
        int aXN = (int) a.getX();
        Rectangle bN = block.getB();
        int bXN = (int) b.getX();
        Rectangle cN = block.getC();
        int cXN = (int) c.getX();
        Rectangle dN = block.getD();
        int dXN = (int) d.getX();
        
        boolean isSame = false;
        if (aX == aXN && bX == bXN && cX == cXN && dX == dXN) {
            isSame=true;
        }
        assertTrue(isSame);
    }
    

}
