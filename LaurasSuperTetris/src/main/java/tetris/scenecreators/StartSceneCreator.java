
package tetris.scenecreators;

import tetris.controls.TetrisDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tetris.controls.Controller;


public class StartSceneCreator {
    
    public static Controller controller;
    public static Button startButton;
    public static Button statsButton;
    public static Scene startScene;
    public static VBox startLayout;
    public static StackPane heading;
    public static StackPane playerNameBox;
    public static StackPane rankBox;
    public static TextField nameField;
    public static String playerName;
    public static GridPane rankList;
    public static TetrisDao dao = Controller.dao;
    ElementGenerator eGenerator = new ElementGenerator();
    
    public StartSceneCreator(Controller controller) {
        this.controller = controller;
        heading = createHeading();
        playerNameBox = createNameBox();
        rankBox = createRankPane();

        startLayout = new VBox(50);

        startLayout.getChildren().addAll(heading, playerNameBox, rankBox);
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    
        startScene = new Scene(startLayout, 522, 744);
    }
    
     
    public StackPane createNameBox() {
        StackPane playerNameB = new StackPane();
        
        Text player = eGenerator.createText("Player:", Color.BLACK, 20, 0, 0);
        nameField = new TextField("Anonymous");
        HBox namesHB = eGenerator.createHBox(20, 20);
        namesHB.getChildren().addAll(player, nameField);

        HBox buttonBox = eGenerator.createHBox(30, 20);
        createStartB();
        createStatsB();
        buttonBox.getChildren().addAll(startButton, statsButton);   
      
        VBox textAndButtons = eGenerator.createVBox(20, 20, Color.TRANSPARENT);
        textAndButtons.getChildren().addAll(namesHB, buttonBox);      

        Rectangle rectangle = eGenerator.createRectangle(400, 160, Color.GOLD, Color.GOLD);  
        
        ObservableList list = playerNameB.getChildren();
        list.addAll(rectangle, textAndButtons);        
        
        return playerNameB;
    }

    public void createStartB() {
        startButton = eGenerator.createButton("START", 150, 40, Color.CORAL);    
        startButton.setOnAction(start -> {
            playerName = nameField.getText();
            if (!dao.doesPlayerExist(playerName)) {
                dao.insertPlayer(playerName);
            }
            this.controller.startGame();  
        });
    }
    
    public void createStatsB() {
        statsButton = eGenerator.createButton("TO STATS", 150, 40, Color.CORAL); 
        statsButton.setOnAction(start -> {
            playerName = nameField.getText();
            controller.toStats();
        });  
    }
    
    public StackPane createHeading() {
        StackPane heading = new StackPane();
        
        Rectangle rectangle = createRectangle(400, 70);
        rectangle.setFill(Color.CORAL);
        rectangle.setStroke(Color.CORAL);  
        
        Text headText = new Text("Laura's Super Tetris");
        headText.setStyle("-fx-font: 40 LucidaConsole;");
        headText.setFill(Color.WHITE);
        
        ObservableList list = heading.getChildren();
        list.addAll(rectangle, headText);
        
        return heading;
    }
    
    public Rectangle createRectangle(double width, double height) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);  
        return rectangle;
    }
    
    public StackPane createRankPane() {
        StackPane ranks = new StackPane();
        rankList = eGenerator.createGridP(18, 30, 5);
        Rectangle rectangle = eGenerator.createRectangle(400, 240, Color.LIGHTGREEN, Color.LIGHTGREEN);
              
        Text headText = new Text("Tetris Top 5:");
        headText.setStyle("-fx-font: 30 LucidaConsole;");
        headText.setFill(Color.BLACK);
        
        addLabelsToRankPane();
        addDaoInfoToRankPane();

        VBox headAndScores = eGenerator.createVBox(15, 20, Color.TRANSPARENT);
        headAndScores.getChildren().addAll(headText, rankList);
        
        ObservableList list = ranks.getChildren();
        list.addAll(rectangle, headAndScores);        
        return ranks;
    }
    
    public void addLabelsToRankPane() {
        Label ind1 = new Label("1.");
        Label ind2 = new Label("2.");
        Label ind3 = new Label("3.");
        Label ind4 = new Label("4.");
        Label ind5 = new Label("5.");

        rankList.addColumn(0, ind1, ind2, ind3, ind4, ind5);        
    }
    
    public void addDaoInfoToRankPane() {
        String[][] top5 = dao.getTop5();
        Label name1 = new Label(top5[0][0]);
        Label name2 = new Label(top5[0][1]);
        Label name3 = new Label(top5[0][2]);
        Label name4 = new Label(top5[0][3]);
        Label name5 = new Label(top5[0][4]);

        rankList.addColumn(1, name1, name2, name3, name4, name5);
        Label sco1 = new Label(top5[1][0]);
        Label sco2 = new Label(top5[1][1]);
        Label sco3 = new Label(top5[1][2]);
        Label sco4 = new Label(top5[1][3]);
        Label sco5 = new Label(top5[1][4]);

        rankList.addColumn(2, sco1, sco2, sco3, sco4, sco5);        
    }
    
    public Scene getStartScene() {
        return startScene;
    }

    public String getPlayerName() {
        return playerName; 
    }

}
