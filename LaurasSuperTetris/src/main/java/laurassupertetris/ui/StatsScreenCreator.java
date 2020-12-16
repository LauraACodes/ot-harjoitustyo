
package laurassupertetris.ui;

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
import laurassupertetris.controller.Controller;

public class StatsScreenCreator {
    
    public static Controller controller = Tetris.controller;
    public static String playerName;
    public static VBox statsLayout;
    public static Scene statsScene;
    
    public static VBox scoreBox;    
    public static StackPane playerNameBox;
    public static StackPane latestScoreBox;
    public static StackPane topScoreBox;
    public static StackPane playedGamesBox;
    
    public static Button playButton;
    
    
    public StatsScreenCreator(String playerName) {
        this.playerName = playerName;
        
        createStatsScreen();

    }
    
    public void createStatsScreen() {
        playerNameBox = createNameBox();     
        latestScoreBox = createLatestScoreBox();
        topScoreBox = createTopScoreBox();
        playedGamesBox = createPlayedGamesBox();
        
        scoreBox = new VBox(10);
        scoreBox.getChildren().addAll(latestScoreBox, topScoreBox, playedGamesBox);
        
        playButton = new Button("NEW GAME");
        playButton.setPrefSize(150,40);
        playButton.setBackground(new Background(new BackgroundFill(Color.DARKMAGENTA, CornerRadii.EMPTY, Insets.EMPTY)));

        playButton.setOnAction(start -> {
            controller.startGame();
        });           
        
        statsLayout = new VBox(50);
        statsLayout.getChildren().addAll(playerNameBox, scoreBox, playButton);
        statsLayout.setAlignment(Pos.CENTER);
        statsLayout.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    
        statsScene = new Scene(statsLayout, 522,744);
    }

    public StackPane createPlayedGamesBox() {
        StackPane playedGamesB = new StackPane();
        
        Rectangle rectangle = createRectangle(400, 70);
        rectangle.setFill(Color.GOLD);
        rectangle.setStroke(Color.GOLD);  
        
        int playedGames = 30;        
        Text playedGamesHead = new Text("Nr of played games: " + playedGames);
        playedGamesHead.setStyle("-fx-font: 20 LucidaConsole;");
        playedGamesHead.setFill(Color.BLACK);
        
        playedGamesB.getChildren().addAll(rectangle, playedGamesHead);
        
        return playedGamesB;
    }    

    public StackPane createTopScoreBox() {
        StackPane topScoreB = new StackPane();
        
        Rectangle rectangle = createRectangle(400, 70);
        rectangle.setFill(Color.CORAL);
        rectangle.setStroke(Color.CORAL);  
        
        VBox topScore = new VBox(5);
        int topScoreInt = 10000000;        
        Text topScoreHead = new Text("Top score: " + topScoreInt);
        topScoreHead.setStyle("-fx-font: 20 LucidaConsole;");
        topScoreHead.setFill(Color.WHITE);
        

        int topScoreRank = 1;
        String topScoreDate = "15.12.2020";

        Text topScoreRes = new Text("(Ranking: " + topScoreRank + ". , " + topScoreDate + ")");
        topScoreRes.setStyle("-fx-font: 15 LucidaConsole;");
        topScoreRes.setFill(Color.WHITE);
        
        topScore.getChildren().addAll(topScoreHead, topScoreRes);
        topScore.setAlignment(Pos.CENTER);
        
        topScoreB.getChildren().addAll(rectangle, topScore);
        
        return topScoreB;
    }

    public StackPane createLatestScoreBox() {
        StackPane latestScoreB = new StackPane();
        
        Rectangle rectangle = createRectangle(400, 70);
        rectangle.setFill(Color.HOTPINK);
        rectangle.setStroke(Color.HOTPINK);  
        
        VBox latScore = new VBox(5);
        int latScoreInt = 10;        
        Text latScoreHead = new Text("Latest score: " + latScoreInt);
        latScoreHead.setStyle("-fx-font: 20 LucidaConsole;");
        latScoreHead.setFill(Color.WHITE);
        

        int latScoreRank = 1;
        String latScoreDate = "15.12.2020";

        Text latScoreRes = new Text("(Ranking: " + latScoreRank + ". , " + latScoreDate + ")");
        latScoreRes.setStyle("-fx-font: 15 LucidaConsole;");
        latScoreRes.setFill(Color.WHITE);
        
        latScore.getChildren().addAll(latScoreHead, latScoreRes);
        latScore.setAlignment(Pos.CENTER);
        
        latestScoreB.getChildren().addAll(rectangle, latScore);
        
        return latestScoreB;
    }    
    public StackPane createNameBox() {
        StackPane nameBox = new StackPane();
        
        Rectangle rectangle = createRectangle(400, 70);
        rectangle.setFill(Color.LIMEGREEN);
        rectangle.setStroke(Color.LIMEGREEN);

        
        Text nameText = new Text("Player: " + playerName);
        nameText.setStyle("-fx-font: 30 LucidaConsole;");
        nameText.setFill(Color.WHITE);
        
        ObservableList list = nameBox.getChildren();
        list.addAll(rectangle, nameText);
        
        return nameBox;
    }
    
    public Rectangle createRectangle(double width, double height) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);  
        return rectangle;
    }    
    public Scene getStatsScene() {
        return statsScene;
    }
}
