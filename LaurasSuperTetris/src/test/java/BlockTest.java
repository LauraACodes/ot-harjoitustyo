
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.blocksandmoves.Block;

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
    public void fourCreatedBlocksAreNotAllSimilar() {
        Block block1 = new Block();
        Block block2 = new Block();
        Block block3 = new Block();
        Block block4 = new Block();
        Boolean notSame = true;
        String name = block1.getName();
        if (block1.getName().equals(block2.getName()) && block1.getName().equals(block3.getName()) && block1.getName().equals(block4.getName())) {
            notSame = false;
        }
        assertTrue(notSame);
    }
    
    @Test
    public void colorMatchesName() {
        String name = block.getName();
        Color c = (Color) block.a.getFill();
        Color colorMatch;
        
        if (name.equals("square")) {
            colorMatch = Color.CORAL;
        } else if (name.equals("line")) {
            colorMatch = Color.HOTPINK;
        } else if (name.equals("ess")) {
            colorMatch = Color.LIGHTGREEN;
        } else if (name.equals("enn")) {
            colorMatch = Color.MEDIUMSEAGREEN;
        } else if (name.equals("dude")) {
            colorMatch = Color.GOLD;
        } else if (name.equals("jei")) {
            colorMatch = Color.MEDIUMSLATEBLUE;
        } else if (name.equals("ell")) {
            colorMatch = Color.LIGHTSKYBLUE;
        } else {
            colorMatch = Color.BLACK;
        }
        assertEquals(colorMatch, c);
    }
    
}
