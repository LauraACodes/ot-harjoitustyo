
package laurassupertetris.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import laurassupertetris.controller.Controller;


public class StartSceneCreator {
    
    public static Controller controller;
    public static Button startButton;
    public static Scene startScene;
    public static VBox startLayout;
    
    public StartSceneCreator(Controller controller) {
        this.controller = controller;
        Text tetris = new Text("Laura's Super Tetris");
        startButton = new Button("START");
        
        startButton.setOnAction(start -> {
            this.controller.startGame();  
        });
        
        startLayout = new VBox(10);
        startLayout.getChildren().addAll(tetris, startButton);
        
        startScene = new Scene(startLayout, 522, 744);
    }

    public Scene getStartScene() {
        return startScene;
    }


}
