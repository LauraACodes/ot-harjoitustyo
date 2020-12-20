package tetris.ui;

import javafx.application.Application;
import tetris.controls.Controller;
import javafx.stage.Stage;
/**
 * Luokka vastaa käyttöliittymän näyttämisestä pelaajalle.
 * 
 */
public class Ui extends Application {

    // Controlleri & Game
    public static Controller controller;
    public boolean game = false;


    /**
     * Pelin käynnistävä metodi luo controllerin kokoamana ja hallinnoimaan 
     * pelissä tarvittavia osia. Controllerin luotuaan metodi hakee ensimmäisen
     * Scenen ja näyttää sen pelaajalle.
     * @param stage
     * @throws Exception 
     */
      
    @Override
    public void start(Stage stage) throws Exception {

        controller = new Controller(stage);
        stage.setScene(controller.getScene());
        stage.setTitle("LaurasSuperTetris");
        stage.show();

    }
    
    public static void main(String[] args) {
        launch(Ui.class);
    }
}
