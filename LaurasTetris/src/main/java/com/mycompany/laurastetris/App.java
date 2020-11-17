package com.mycompany.laurastetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    public static final int sqSize = 20;
    public static final int Move = 20;
    public static int maxWidth = 10 * sqSize;
    public static int maxHeight = 20 * sqSize;
    public static int[][] board = new int[maxWidth / sqSize][maxHeight / sqSize];

    
    @Override
    public void start(Stage stage) {

        Pane view = new Pane();      
        Scene scene = new Scene(view, maxWidth, maxHeight);
        
        stage.setTitle("LaurasTetris");        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(App.class);
    }

}