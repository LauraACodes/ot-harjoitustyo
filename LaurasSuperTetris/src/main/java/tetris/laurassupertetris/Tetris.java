package tetris.laurassupertetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Tetris extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button start = new Button("Start");
        BorderPane layout = new BorderPane();
        layout.setCenter(start);
        
        Scene scene = new Scene(layout, 100, 300);
        
        stage.setTitle("LaurasSuperTetris");
        stage.setScene(scene);
        stage.show();       
    }
    
    public static void main(String[] args) {
        launch(Tetris.class);
    }    
}
