package laurassupertetris.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import laurassupertetris.controller.Block;
import laurassupertetris.controller.Controller;
/**
 * Luokka TetrisTimer ohjaa eli tehdittaa blockien putoamista eli koko pelilautaa.
 * 
 */

public class TetrisTimer extends AnimationTimer {

    private long lastUpdate;
    private long startSpeed = 150000000;
    Controller controller = Tetris.controller;


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
    /**
     * Metodi tarkkailee pelilaudan täyttymistä.
     * Peli päättyy, mikäli palikan osat ovat olleet pelilaudan ylimpänä (Y=0) kaksi kierrosta.
     * Kun putoava palikka putoaa pohjalle tai edellisen kerroksen päälle, metodi kutsuu controlleria
     * selvittämään, tuleeko pelilaudalta putsata rivejä. Jos pitää, controlleri hoitaa sen ja 
     * tämä metori tuo pelilaudalle uuden palikan.
     */
    /* 
    public void handleTetris() {

        if (block.a.getY() == 0 || block.b.getY() == 0 || block.c.getY() == 0 || block.d.getY() == 0) {
            timeOnTop++;
        } else {
            timeOnTop = 0;
        }
        if (timeOnTop == 2) {
            gameOver();
        }
        if (timeOnTop == 15) {
            System.exit(0);
        }
        if (game) {
            //huom! tää ensimmäinen palauttaa true, jos sinne EI mahdu.
            if (controller.downOk(block)) {
                controller.removeRows();
                //tehdään uusi blockki ja lisätään se sceneen

                Block next = nextBlock;
                nextBlock = new Block();
                block = next;
                layout.getChildren().addAll(next.a, next.b, next.c, next.d);
                controller.moveOnKeyPress(next);
            } else {
                controller.moveDown(block);
            }
        }
    }
    /**
     * Metodi päättää pelin, eli tuo GAME OVER tekstin pelilaudalle.
     */


}
