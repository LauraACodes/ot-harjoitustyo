
package tetris.controls;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Luokassa luodaan pelin käyttämä tietokanta sekä käytetään tietokantaa.
 * @author andlaura
 */
public class TetrisDao {
    
    private String dbName;
    private String defName;
    /**
     * Luokan konstruktorissa määritellään tietokannan nimi annetun
     * parametrin mukaan. Tämän jälkene kutsutaan toista metodia luomaan tk.
     * @param defName
     * @throws SQLException 
     */
    public TetrisDao(String defName) throws SQLException {
        this.defName = defName;
        this.dbName = "jdbc:sqlite:" + defName;
        initDb();
    }
    /**
     * Metodi tarkastaa onko nimetty tietokanta jo olemassa ja jos ei ole, luo tietokannan.
     */    
    public void initDb() {
        if (new File(defName).exists()) {
            return;
        }
               
        try (Connection connection = DriverManager.getConnection(dbName);
                Statement stmt = connection.createStatement()) {
            stmt.execute(createPlayersTblSql());
            stmt.execute(createGamesTblSql());
            connection.close();
        } catch (SQLException e) {
        }
    }
    /**
     * Luokka palvelee muita metodeita vastaamalla yhteyden luomisesta tk:hon. 
     * @return 
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbName);
        } catch (SQLException e) {
        }
        return conn;
    }
    /**
     * Metodi vie parametrinä annetun nimen tietokantaan.
     * @param name 
     */
    public void insertPlayer(String name) {
        String sql = "INSERT INTO players(name) VALUES(?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
        }
    }
    /**
     * Pelin loputtua metodi päivittää Player-tauluun valitut tulokset: mm.
     * kasvattaa pelattujen pelien määrää, päivittää tarvittaessa topScoren.
     * @param name pelaajan nimi
     * @param score uusin pelitulos
     */
    public void updatePlayerScores(String name, int score) {
        int playerID = getPlayerID(name);
        int topScore = getPlayerTopScore(playerID);
        int nrOfGames = getPlayerNrOfGames(playerID);
        
        if (score >= topScore) {
            topScore = score;
        }
        
        nrOfGames++;
        
        String sql = "UPDATE players SET topScore = ? , nrOfGames = ? WHERE playerID = ?";      
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, topScore);
            pstmt.setInt(2, nrOfGames);
            pstmt.setInt(3, playerID);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
        }
    }
    /**
     * Pelin loputtua metodi lisää Games-tauluun uusimman tuloksen.
     * @param name pelaajan nimi
     * @param score uusin pelitulos
     */
    public void updateGames(String name, int score) {
        int playerID = getPlayerID(name);
        String sql = "INSERT INTO games(playerID, score) VALUES(?, ?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerID);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
        }
    }    
    /**
     * Metodi etsii games-taulusta parametrina annetun tuloksen sijoitusta.
     * @param score tulos, jonka sijoutusta etsitään
     * @return annetun tuloksen sijoitus
     */
    public int getRank(int score) {
        ArrayList<Integer> list = new ArrayList<>();
        
        String sql = "SELECT score FROM games";
        
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt("score"));
            }
            conn.close();
        } catch (SQLException e) {
        }
        
        Collections.sort(list, Collections.reverseOrder());

        int rank = findRank(list, score);
        
        return rank;
    }
    /**
     * GetRank-metodin apumetodi. Etsii annetulta listalta annetun tuloksen 
     * sijoituksen.
     * @param list
     * @param score
     * @return 
     */
    public int findRank(ArrayList<Integer> list, int score) {
        int rank = 0;
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == score) {
                rank = i + 1;
            }
        } 
        
        return rank;
    }
    /**
    * Metodi palauttaa taulukon, jossa on kaikista pelatuista peleistä top5 
    * tulokset ja pelanneiden nimet
    * @return 
    */
    public String[][] getTop5() {
        String[][] top5 = createTable();
        
        String sql = "SELECT games.score AS score, players.name AS name FROM games JOIN players ON players.playerID=games.playerID ORDER BY score DESC LIMIT 5";    
        
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            int i = 0;
            while (rs.next()) {
                top5[1][i] = rs.getInt("score") + "";
                top5[0][i] = rs.getString("name");
                i++;
            }
            
            conn.close();
        } catch (SQLException e) {
        }
               
        return top5;
    }
    
    /**
     * getTop5 metodin apumetodi, alustaa tyhjän taulukon.
     * @return 
     */
    public String[][] createTable() {
        String[][] top5 = new String[2][5];
        for (int i = 0; i < 5; i++) {
            top5[0][i] = " ";
            top5[1][i] = " ";
        }
        return top5;
    }
    /**
     * Etsii player-taulusta pelaajan idn annetun nimen perusteella.
     * @param name
     * @return pelaajan id
     */
    public int getPlayerID(String name) {
        int id = 0;
        
        String sql = "SELECT playerID FROM Players WHERE name= ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            id = rs.getInt(1);        
            conn.close();
        } catch (SQLException e) {
        }  
        
        return id;
    }
    /**
     * Etsii player-taulusta pelaajan parhaimman tuloksen.
     * @param id
     * @return paras tulos
     */
    public int getPlayerTopScore(int id) {
        int score = 0;
        
        String sql = "SELECT topScore FROM Players WHERE playerID= ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            score = rs.getInt(1);        
            conn.close();
        } catch (SQLException e) {
        }  
        
        return score;
    }
    /**
     * Etsi player-taulusta pelaajan pelaamien pelien lukumäärän.
     * @param id
     * @return pelattujen pelien lkm
     */
    public int getPlayerNrOfGames(int id) {
        int nrOfGames = 0;
        
        String sql = "SELECT nrOfGames FROM Players WHERE playerID= ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            nrOfGames = rs.getInt(1);        
            conn.close();
        } catch (SQLException e) {
        }  
        
        return nrOfGames;
    }
    /**
     * Metodi tarkastaa onko player-tietokannassa jo parametrinä annettua pelaajaa.
     * @param name
     * @return true jos pelaaja löytyy
     */
    public boolean doesPlayerExist(String name) {
        boolean exist = false;
        
        String sql = "SELECT COUNT(*) FROM Players WHERE name= ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            int findings = rs.getInt(1);
            if (findings > 0) {
                exist = true;
            }
            conn.close();
        } catch (SQLException e) {
        }
        
        return exist;
    }
    /**
     * Apumetodi player-taulun luomista varten tarvittavasta sql-stringistä-
     * @return 
     */
    public String createPlayersTblSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " playerID integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " topScore integer DEFAULT 0,\n"
                + " nrOfGames integer DEFAULT 0\n"
                + ");";
        return sql;
    }
    /**
     * Apumetodi games-taulun luomista varten tarvittavasta sql-stringistä-
     * @return 
     */
    public String createGamesTblSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Games (\n"
                + " gameID integer PRIMARY KEY,\n"
                + " playerID integer REFERENCES players,\n"
                + " score integer DEFAULT 0\n"
                + ");";
        return sql;
    }
    
    //Testausta varten
    public void emptyTables(String defName) {
        
        String sql1 = "DELETE FROM Games";
        String sql2 = "DELETE FROM Players";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql1);
            stmt.execute(sql2);
            conn.close();
        } catch (SQLException e) {
        }
    }
}
