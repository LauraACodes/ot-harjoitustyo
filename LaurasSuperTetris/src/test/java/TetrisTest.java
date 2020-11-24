/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.laurassupertetris.Block;
import tetris.laurassupertetris.Tetris;

public class TetrisTest {
    
    int[][] board;
    Block block;
    
    public TetrisTest() {
        board = new int[12][24];
        block = new Block();
    }

    @Test
    public void downOkReturnFalseWhenBoardIsEmpty() {
        assertEquals(false, Tetris.downOk(block));
    }
    //kun palikka on neliö
    @Test
    public void moveDownMovesSquareADown() {
        Tetris.moveDown(block);
        assertEquals(31, (int) block.getA().getY());
    }
    /*
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
*/

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
