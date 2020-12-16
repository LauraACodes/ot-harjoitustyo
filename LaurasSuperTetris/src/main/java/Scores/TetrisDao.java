
package Scores;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TetrisDao {
    
    private String dbName;
    private String defName;

    public TetrisDao(String defName) throws SQLException {
        this.defName = defName;
        this.dbName = "jdbc:sqlite:" + defName;
        initDb();
    }
    
    public void initDb() {
        if (new File(defName).exists()) {
            return;
        }
               
        try (Connection connection = DriverManager.getConnection(dbName);
                Statement stmt = connection.createStatement()) {
            stmt.execute(createPlayersTblSql());
            stmt.execute(createGamesTblSql());
            connection.close();
            System.out.println("kaikki mahikset, että db onnistuttiin luomaan");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }
    
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbName);
        } catch (SQLException e) {
            System.out.println("db connection failed");
        }
        return conn;
    }
    
    public void insertPlayer(String name) {
        String sql = "INSERT INTO players(name) VALUES(?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("insert player failed");
        }
    }
    
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
            System.out.println("playerupdate onnistui");
        } catch (SQLException e) {
            System.out.println("playerupdate failed");
        }
    }
    
    public void updateGames(String name, int score) {
        int playerID = getPlayerID(name);
        String sql = "INSERT INTO games(playerID, score) VALUES(?, ?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerID);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            conn.close();
            System.out.println("gameupdate onnistui");
        } catch (SQLException e) {
            System.out.println("gamesupdate failed");
        }
    }    
    
    public int getRank(int score) {
        ArrayList<Integer> list = new ArrayList<>();
        int rank = 0;
        
        String sql = "SELECT score FROM games";
        
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(rs.getInt("score"));
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("rank failed");
        }
        
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == score) {
                rank = i+1;
            }
        }
        
        return rank;
    }

    
    public String[][] getTop5() {
        String[][] top5 = new String[2][5];
        for (int i=0; i<5; i++) {
            top5[0][i] = " ";
            top5[1][i] = " ";
        }
        
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
            System.out.println("gettin top5 scores failed");
        }
               
        return top5;
    }
    
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
            System.out.println("ei löydä playeridtä");
        }  
        
        return id;
    }

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
            System.out.println("ei löydä topScorea");
        }  
        
        return score;
    }

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
            System.out.println("ei löydä nrOfGamesia");
        }  
        
        return nrOfGames;
    }


 
    
    public boolean doesPlayerExist(String name) {
        boolean exist = false;
        
        String sql = "SELECT COUNT(*) FROM Players WHERE name= ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            int findings = rs.getInt(1);
            System.out.println("löysi;" + findings);
            if (findings > 0) {
                exist = true;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("tietokantahaussa onglelma");
        }
        
        return exist;
    }
    
    public String createPlayersTblSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " playerID integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " topScore integer DEFAULT 0,\n"
                + " nrOfGames integer DEFAULT 0\n"
                + ");";
        return sql;
    }

    public String createGamesTblSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Games (\n"
                + " gameID integer PRIMARY KEY,\n"
                + " playerID integer REFERENCES players,\n"
                + " score integer DEFAULT 0\n"
                + ");";
        return sql;
    }
}
