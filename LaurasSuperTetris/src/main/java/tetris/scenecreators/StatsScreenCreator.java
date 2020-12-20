
package tetris.scenecreators;

import tetris.controls.TetrisDao;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tetris.controls.Controller;
import tetris.ui.Ui;

/**
 * Luokka vastaa statistiikkasivun luomisesta.
 * @author andlaura
 */ 

public class StatsScreenCreator {
    
    public static Controller controller = Ui.controller;
    public static String playerName;
    public static int playerID;
    public static VBox statsLayout;
    public static Scene statsScene;
    
    public static VBox scoreBox;    
    public static StackPane playerNameBox;
    public static StackPane latestScoreBox;
    public static StackPane topScoreBox;
    public static StackPane playedGamesBox;
    
    public static Button playButton;
    public static TetrisDao dao = Controller.dao;    
    ElementGenerator eGenerator = new ElementGenerator();
    
    /**
     * Luokan konstruktori luo statistiikkaScenen parametrinä saadun 
     * pelaajan tiedoilla.
     * @param playerName 
     */
    public StatsScreenCreator(String playerName) {
        this.playerName = playerName;
        playerID = dao.getPlayerID(playerName);
        createStatsScreen();
    }
    
    /**
     * Metodi vasta statistiikkasivun kokoamisesta.
     */
    public void createStatsScreen() {
        playerNameBox = createNameBox();     
        latestScoreBox = createLatestScoreBox();
        topScoreBox = createTopScoreBox();
        playedGamesBox = createPlayedGamesBox();
        
        scoreBox = new VBox(10);
        scoreBox.getChildren().addAll(latestScoreBox, topScoreBox, playedGamesBox);
        
        playButton = eGenerator.createButton("NEW GAME", 150, 40, Color.LIGHTSKYBLUE);
        playButton.setOnAction(start -> {
            controller.startNewGame();
        });           
        
        statsLayout = eGenerator.createVBox(50, 20, Color.STEELBLUE);
        statsLayout.getChildren().addAll(playerNameBox, scoreBox, playButton);
    
        statsScene = new Scene(statsLayout, 522, 744);
    }
    /**
     * Statistiikkasivun kokoajan apumetodi. 
     * Hakee tarvittavia tietoja tietokannasta.
     * @return 
     */
    public StackPane createPlayedGamesBox() {
        StackPane playedGamesB = new StackPane();
        
        Rectangle rectangle = eGenerator.createRectangle(400, 70, Color.GOLD, Color.GOLD);
        
        int playedGames = dao.getPlayerNrOfGames(playerID);        
        Text playedGamesHead = eGenerator.createText("Nr of played games: " + playedGames, Color.BLACK, 20, 0, 0);
        
        playedGamesB.getChildren().addAll(rectangle, playedGamesHead);
        
        return playedGamesB;
    }    
    /**
     * Statistiikkasivun kokoajan apumetodi. 
     * Hakee tarvittavia tietoja tietokannasta.
     * @return 
     */
    public StackPane createTopScoreBox() {
        StackPane topScoreB = new StackPane();
        
        Rectangle rectangle = eGenerator.createRectangle(400, 70, Color.CORAL, Color.CORAL);
        
        int topScoreInt = dao.getPlayerTopScore(playerID);        
        Text topScoreHead = eGenerator.createText("Top score: " + topScoreInt, Color.WHITE, 20, 0, 0);
      
        int topScoreRank = dao.getRank(topScoreInt);
        Text topScoreRes = eGenerator.createText("(Ranking: " + topScoreRank + ")", Color.WHITE, 15, 0, 0);

        VBox topScore = eGenerator.createVBox(5, 20, Color.TRANSPARENT);
        topScore.getChildren().addAll(topScoreHead, topScoreRes);
        
        topScoreB.getChildren().addAll(rectangle, topScore);
        
        return topScoreB;
    }
    /**
     * Statistiikkasivun kokoajan apumetodi. 
     * Hakee tarvittavia tietoja tietokannasta.
     * @return 
     */
    public StackPane createLatestScoreBox() {
        StackPane latestScoreB = new StackPane();
        
        Rectangle rectangle = eGenerator.createRectangle(400, 70, Color.HOTPINK, Color.HOTPINK);
        
        VBox latScore = eGenerator.createVBox(5, 20, Color.TRANSPARENT);  
        int latScoreInt = controller.getScore();        
        Text latScoreHead = eGenerator.createText("Latest score: " + latScoreInt, Color.WHITE, 20, 0, 0);
        int latScoreRank = dao.getRank(latScoreInt);
        Text latScoreRes = eGenerator.createText("(Ranking: " + latScoreRank + ")", Color.WHITE, 15, 0, 0);   
        latScore.getChildren().addAll(latScoreHead, latScoreRes);
        
        latestScoreB.getChildren().addAll(rectangle, latScore);
        
        return latestScoreB;
    }    
    /**
     * Statistiikkasivun kokoajan apumetodi. 
     * Luo pelaajan nimen sisältävän blokin.
     * @return 
     */    
    public StackPane createNameBox() {
        StackPane nameBox = new StackPane();
        
        Rectangle rectangle = eGenerator.createRectangle(400, 70, Color.LIMEGREEN, Color.LIMEGREEN);     
        Text nameText = eGenerator.createText("Player: " + playerName, Color.WHITE, 30, 0, 0);
        
        ObservableList list = nameBox.getChildren();
        list.addAll(rectangle, nameText);
        
        return nameBox;
    }
   
    public Scene getStatsScene() {
        return statsScene;
    }
}
