
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

public class ElementGenerator {
    
    public Text createText(String tText, Color fill, int fontSize, int x, int y) {
        Text text = new Text(tText);
        text.setFill(fill);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        text.setStyle(ftext);
        text.setX(x);
        text.setY(y);
        return text;
    }
  
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
    
    public Button createButton(String text, int width, int height, Color backgroudColor) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        button.setBackground(new Background(new BackgroundFill(backgroudColor, CornerRadii.EMPTY, Insets.EMPTY)));
        return button;
    }
    
    public HBox createHBox(int inc, int fontSize) {
        HBox hbox = new HBox(inc);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        hbox.setStyle(ftext);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    
    public VBox createVBox(int inc, int fontSize, Color backgroudColor) {
        VBox vbox = new VBox(inc);
        String ftext = "-fx-font: " + fontSize + " LucidaConsole;";
        vbox.setStyle(ftext);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(backgroudColor, CornerRadii.EMPTY, Insets.EMPTY)));
        return vbox;
    }    
    
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
