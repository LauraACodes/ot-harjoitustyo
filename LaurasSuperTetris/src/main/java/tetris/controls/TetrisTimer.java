package tetris.controls;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tetris.blocksandmoves.Block;
import tetris.ui.Ui;
/**
 * Luokka TetrisTimer ohjaa eli tehdittaa blockien putoamista eli koko pelilautaa.
 * 
 */

public class TetrisTimer extends AnimationTimer {

    private long lastUpdate;
    private long startSpeed = 150000000;

    Controller controller = Ui.controller;

    /**
     * Timerin handle metodi vastaa palikoiden putoamisnopeudesta ja
     * putoamistahdin kiihdyttämisestä.
     * Varsinainen työ tehdään kun handle-metodi kutsuu handleTetris-metodia.
     * @param now 
     */
    @Override
    public void handle(long now) {
        
        if (now - lastUpdate >= startSpeed) {
            controller.handleTime();
            lastUpdate = now;
            startSpeed -= 5000;
        }
    }
 

}
