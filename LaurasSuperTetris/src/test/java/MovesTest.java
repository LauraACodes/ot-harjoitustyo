import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.blocksandmoves.Block;
import tetris.blocksandmoves.Moves;

public class MovesTest {
    
    Moves moves;
    int[][] board;
    Block block;
    
    public MovesTest() {
    }
   
    @Before
    public void setUp() {
        board = new int[12][24];
        block = new Block();
        moves = new Moves(31, 31, 31 * 12, 31 * 24);
        moves.setBoard(board);
    }
    
    @Test
    public void downOkReturnFalseWhenBoardIsEmpty() {
        assertEquals(false, moves.downOk(block));
    }   
    
    @Test
    public void downOkReturnTrueWhenBlockCantMove() {
        Rectangle a = block.getA();
        a.setY(713);
        assertEquals(true, moves.downOk(block));
    }        

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
        
        moves.moveRight(block);
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
        
        moves.moveLeft(block);
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
    
    @Test
    public void moveDownMovesBlockDown() {
        Rectangle a = block.getA();
        int aY = (int) a.getY();
        Rectangle b = block.getB();
        int bY = (int) b.getY();
        Rectangle c = block.getC();
        int cY = (int) c.getY();
        Rectangle d = block.getD();
        int dY = (int) d.getY();
        
        moves.moveDown(block);
        aY += 31;
        bY += 31;
        cY += 31;
        dY += 31;

        Rectangle aN = block.getA();
        int aYN = (int) a.getY();
        Rectangle bN = block.getB();
        int bYN = (int) b.getY();
        Rectangle cN = block.getC();
        int cYN = (int) c.getY();
        Rectangle dN = block.getD();
        int dYN = (int) d.getY();
        
        boolean isSame = false;
        if (aY == aYN && bY == bYN && cY == cYN && dY == dYN) {
            isSame=true;
        }
        assertTrue(isSame);
    }
}
