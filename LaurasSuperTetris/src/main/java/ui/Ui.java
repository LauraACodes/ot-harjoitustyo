package ui;

import javafx.application.Application;
import Controls.Controller;
import javafx.stage.Stage;
/**
 * Luokka vastaa käyttöliittymän näyttämisestä pelaajalle.
 * Toistaiseksi valmiina on vain pelinäkymä. 
 * Jatkossa tästä löytyy myös valinnat muiden pelin scenejen näyttämiseksi.
 * 
 */
public class Ui extends Application {

    // Controlleri & Game
    public static Controller controller;
    public boolean game = false;


    /**
     * Pelin käynnistävä metodi luo pelissä tarvittavat osat kutsumalla muiden 
     * luokkien metodeita: pelilaudan, controllerin ja timerin sekä
     * käynnistää pelin.
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
