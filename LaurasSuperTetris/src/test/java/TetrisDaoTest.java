import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import tetris.controls.TetrisDao;


public class TetrisDaoTest {  
  
    TetrisDao dao;
    
    public TetrisDaoTest() {

    }
    
    @Before
    public void setUpClass() {
        try {
            dao = new TetrisDao("tetrisDB");
            dao.emptyTables("tetrisDB");
        } catch (Exception e) {
        }  
        
        String name = "Piru";
        dao.insertPlayer(name);
         
    }
    
    @Test
    public void insertedPlayerFoundFromDB() {
        boolean foundIntID = false;
        
        int playerID = dao.getPlayerID("Piru");
        
        if (playerID > 0) {
            foundIntID = true;
        }
        
        assertTrue(foundIntID);
    }
    
    @Test
    public void doesntFindNonExistPlayers() {
        boolean foundIntID = dao.doesPlayerExist("Jesse");       
        assertFalse(foundIntID);        
    }
    
    @Test
    public void playerTablesNoOfGamesGetsUpdated() {
        dao.updatePlayerScores("Piru", 50);
        
        dao.updateGames("Piru", 50);
        dao.updatePlayerScores("Piru", 100);
        dao.updateGames("Piru", 100);
        int playerID = dao.getPlayerID("Piru");
        int nrOfGames = dao.getPlayerNrOfGames(playerID);  
        assertEquals(2, nrOfGames);
    }
    
    @Test
    public void playerTablesTopScoreGetsUpdated() {
        dao.updatePlayerScores("Piru", 50);
        dao.updateGames("Piru", 50);
        dao.updatePlayerScores("Piru", 400);
        dao.updateGames("Piru", 400);
        dao.updatePlayerScores("Piru", 100);
        dao.updateGames("Piru", 100);
        int playerID = dao.getPlayerID("Piru");
        int topScore = dao.getPlayerTopScore(playerID);  
        assertEquals(400, topScore);
    }
    
    @Test
    public void findsScoresRankRight() {
        dao.updatePlayerScores("Piru", 50);
        dao.updateGames("Piru", 50);
        dao.updatePlayerScores("Piru", 400);
        dao.updateGames("Piru", 400);
        dao.updatePlayerScores("Piru", 100);
        dao.updateGames("Piru", 100);
        int rank = dao.getRank(100);
        assertEquals(2, rank);
    }
    
    @Test
    public void returnsRightTopOneScore() {
        dao.insertPlayer("Pelsepuubi");
        dao.insertPlayer("HerraSaatana");
        dao.insertPlayer("PääPiru");
        
        dao.updatePlayerScores("Piru", 50);
        dao.updateGames("Piru", 50);
        dao.updatePlayerScores("Piru", 400);
        dao.updateGames("Piru", 400);
        dao.updatePlayerScores("Piru", 100);
        dao.updateGames("Piru", 100);        
        dao.updatePlayerScores("Pelsepuubi", 300000);
        dao.updateGames("Pelsepuubi", 300000);
        dao.updatePlayerScores("Pelsepuubi", 1000);
        dao.updateGames("Pelsepuubi", 1000);
        dao.updatePlayerScores("HerraSaatana", 900);
        dao.updateGames("HerraSaatana", 900);
        dao.updatePlayerScores("PääPiru", 50000);
        dao.updateGames("PääPiru", 50000);              
        dao.updatePlayerScores("PääPiru", 100000);
        dao.updateGames("PääPiru", 100000); 
        
        String[][] top5Results = dao.getTop5();
        String topScore = top5Results[1][0];
        String wantedScore = Integer.toString(300000);
        assertEquals(wantedScore, topScore);
    }
    
    @Test
    public void returnsRightTopOnePlayer() {
        dao.insertPlayer("Pelsepuubi");
        dao.insertPlayer("HerraSaatana");
        dao.insertPlayer("PääPiru");
        
        dao.updatePlayerScores("Piru", 50);
        dao.updateGames("Piru", 50);
        dao.updatePlayerScores("Piru", 400);
        dao.updateGames("Piru", 400);
        dao.updatePlayerScores("Piru", 100);
        dao.updateGames("Piru", 100);        
        dao.updatePlayerScores("Pelsepuubi", 300000);
        dao.updateGames("Pelsepuubi", 300000);
        dao.updatePlayerScores("Pelsepuubi", 1000);
        dao.updateGames("Pelsepuubi", 1000);
        dao.updatePlayerScores("HerraSaatana", 900);
        dao.updateGames("HerraSaatana", 900);
        dao.updatePlayerScores("PääPiru", 50000);
        dao.updateGames("PääPiru", 50000);              
        dao.updatePlayerScores("PääPiru", 100000);
        dao.updateGames("PääPiru", 100000); 
        
        String[][] top5Results = dao.getTop5();
        String topPlayer = top5Results[0][0];
        assertEquals("Pelsepuubi", topPlayer);
    }    
}
