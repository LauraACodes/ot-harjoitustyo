
package laurassupertetris.ui;

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
import laurassupertetris.controller.Controller;


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
    
    public StartSceneCreator(Controller controller) {
        this.controller = controller;
        
        heading = createHeading();
        playerNameBox = createNameBox();
        rankBox = createRankPane();

        startLayout = new VBox(50);

        startLayout.getChildren().addAll(heading, playerNameBox, rankBox);
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    
        startScene = new Scene(startLayout, 522, 744, Color.AQUA);
    }
    
     
    public StackPane createNameBox() {
        StackPane playerNameB = new StackPane();
        
        VBox testing = new VBox(20);
        HBox hbox = new HBox(20);
        Text player = new Text("Player:");
        player.setFill(Color.BLACK);
        nameField = new TextField();
        nameField.setText("Anonymous");

        hbox.getChildren().addAll(player, nameField);
        hbox.setStyle("-fx-font: 20 LucidaConsole;");
        hbox.setAlignment(Pos.CENTER);
        
        startButton = new Button("START");
        startButton.setPrefSize(150,40);
        startButton.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
               
        startButton.setOnAction(start -> {
            playerName = nameField.getText();
            this.controller.startGame();  
        });

        statsButton = new Button("TO STATS");
        statsButton.setPrefSize(150,40);
        statsButton.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
       
        HBox buttonBox = new HBox(30);
        buttonBox.getChildren().addAll(startButton, statsButton);   
        buttonBox.setStyle("-fx-font: 20 LucidaConsole;");
        buttonBox.setAlignment(Pos.CENTER);
        
        
        VBox textAndButtons = new VBox(20);
        textAndButtons.getChildren().addAll(hbox, buttonBox);
        
        textAndButtons.setAlignment(Pos.CENTER);
        Rectangle rectangle = createRectangle(400, 160);  
        
        rectangle.setFill(Color.GOLD);
        rectangle.setStroke(Color.GOLD);
        
        ObservableList list = playerNameB.getChildren();
        list.addAll(rectangle, textAndButtons);        
        
        return playerNameB;
    }

    public StackPane createHeading() {
        StackPane heading = new StackPane();
        
        Rectangle rectangle = createRectangle(400, 70);
        rectangle.setFill(Color.CORAL);
        rectangle.setStroke(Color.CORAL);
        
        Text headText = new Text("Laura's Super Tetris");
        headText.setStyle("-fx-font: 40 LucidaConsole;");
        headText.setFill(Color.WHITE);
        //headText.setStyle("-fx-text-inner-color: red;");
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

        Rectangle rectangle = createRectangle(400, 240);
        rectangle.setFill(Color.LIGHTGREEN);
        rectangle.setStroke(Color.LIGHTGREEN);
        
        VBox headAndScores = new VBox(15);
        
        Text headText = new Text("Tetris Top 5:");
        headText.setStyle("-fx-font: 20 LucidaConsole;");
        headText.setFill(Color.BLACK);
        
        GridPane pane = new GridPane();
        Label ind1 = new Label("1.");
        Label ind2 = new Label("2.");
        Label ind3 = new Label("3.");
        Label ind4 = new Label("4.");
        Label ind5 = new Label("5.");

        pane.addColumn(0, ind1, ind2, ind3, ind4, ind5);
        Label name1 = new Label("name1");
        Label name2 = new Label("name2");
        Label name3 = new Label("name3");
        Label name4 = new Label("name4");
        Label name5 = new Label("name5");

        pane.addColumn(1, name1, name2, name3, name4, name5);
        Label sco1 = new Label("1000000000");
        Label sco2 = new Label("100000000");
        Label sco3 = new Label("10000000");
        Label sco4 = new Label("1000000");
        Label sco5 = new Label("100000");

        pane.addColumn(2, sco1, sco2, sco3, sco4, sco5);
        pane.setStyle("-fx-font: 18 LucidaConsole;");
  
        pane.setHgap(30);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        headAndScores.getChildren().addAll(headText, pane);
        headAndScores.setAlignment(Pos.CENTER);
        
        ObservableList list = ranks.getChildren();
        list.addAll(rectangle, headAndScores);        
        return ranks;
    }
    public Scene getStartScene() {
        return startScene;
    }

    public String getPlayerName() {
        return playerName; 
    }

}
