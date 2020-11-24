
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import laurassupertetris.controller.Block;

public class BlockTest {
    
    Block block;
    
    public BlockTest() {
        block = new Block();
    }
    
    @Test
    public void blockExists() {
        assertTrue(block != null);
    }    
    
    @Test
    public void nameIsSquare() {
        assertEquals("square", block.getName());
    }
    
    @Test
    public void colorIsBlue() {
        assertEquals(Color.BLUE, block.getA().getFill());
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
