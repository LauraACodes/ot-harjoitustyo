
package tetris.scenecreators;

import static tetris.scenecreators.GameSceneCreator.controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
/**
 * Luokka toimii SceneCreatoreide apuna luoden niiden käyttöön mm. Textejä,
 * Rectangleja ja Buttoneita.
 * @author andlaura
 */
public class ElementGenerator {
    /**
     * metodi luo annettujen parametrien mukaisen Text -olion pyytävän 
     * Scenen käyttöön.
     * @param tText - tekstiin tuleva teksti
     * @param fill - väri Color. -muodossa
     * @param fontSize 
     * @param x - x-koordinaatti, 0 jos ei tarvi
     * @param y - y-koordinaatti, 0 jos ei tarvi
     * @return 
     */
    public Text createText(String tText, Color fill, int fontSize, int x, int y) {
        Text text = new Text(tText);
        text.setFill(fill);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        text.setStyle(ftext);
        text.setX(x);
        text.setY(y);
        return text;
    }
    /**
     * metodi luon annettujen parametrien mukaisen Rectangle -olion pyytävän 
     * Scenen käyttöön.
     * @param width
     * @param height
     * @param fill
     * @param stroke - reunaviivan väri
     * @return 
     */
    public Rectangle createRectangle(int width, int height, Color fill, Color stroke) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);  
        rectangle.setFill(fill);
        rectangle.setStroke(stroke);
        return rectangle;
    }
    /**
     * metodi luon annettujen parametrien mukaisen Button -olion pyytävän 
     * Scenen käyttöön.
     * @param text
     * @param width
     * @param height
     * @param backgroudColor
     * @return 
     */
    public Button createButton(String text, int width, int height, Color backgroudColor) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        button.setBackground(new Background(new BackgroundFill(backgroudColor, CornerRadii.EMPTY, Insets.EMPTY)));
        return button;
    }
    /**
     * metodi luon annettujen parametrien mukaisen HBox -olion pyytävän 
     * Scenen käyttöön.
     * @param inc
     * @param fontSize
     * @return 
     */
    public HBox createHBox(int inc, int fontSize) {
        HBox hbox = new HBox(inc);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        hbox.setStyle(ftext);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    /**
     * metodi luon annettujen parametrien mukaisen VBox -olion pyytävän 
     * Scenen käyttöön.
     * @param inc
     * @param fontSize
     * @return 
     */   
    public VBox createVBox(int inc, int fontSize, Color backgroudColor) {
        VBox vbox = new VBox(inc);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        vbox.setStyle(ftext);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(backgroudColor, CornerRadii.EMPTY, Insets.EMPTY)));
        return vbox;
    }    
    /**
     * metodi luon annettujen parametrien mukaisen GridPane-olion pyytävän 
     * Scenen käyttöön.
     * @param inc
     * @param fontSize
     * @return 
     */    
    public GridPane createGridP(int fontSize, int hgap, int vgap) {
        GridPane gridP = new GridPane();
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";        
        gridP.setStyle(ftext);
        gridP.setHgap(hgap);
        gridP.setVgap(vgap);
        gridP.setAlignment(Pos.CENTER);     
        return gridP;
    }
}
