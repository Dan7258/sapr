package application.Figures;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import application.Primitives.Primitive;

public class FigureRender {
    
    private AnchorPane workingArea;
    private AnchorPane settingArea;

    public FigureRender(AnchorPane workingArea, AnchorPane settingArea) {
        this.workingArea = workingArea;
        this.settingArea = settingArea;
    }
    public void render(Figure figure) {
        ArrayList<Primitive> primitives = figure.getLink();
        for(Primitive primitive : primitives) {
            workingArea.getChildren().add(primitive.getLink());
        }
    }

    public void erase(Figure figure) {
        ArrayList<Primitive> primitives = figure.getLink();
        for(Primitive primitive : primitives) {
            workingArea.getChildren().remove(primitive.getLink());
        }
    }

    public void eraseAll(ArrayList<Figure> listFigures) {
        for(Figure figure : listFigures) {
            erase(figure);
        }
        
    }

    public void renderSettings(Figure figure) {
        Control[] settings = figure.getSettings();
        int marginTop = 0;
        int marginLeft = 20;
        int hight = 20;
        int width = 100;
        Control preset = null;

        for(Control setting: settings) {
            if (setting instanceof TextField || setting instanceof ComboBox || setting instanceof Button) {
                setting.setPrefWidth(width);
                marginTop += 7 + hight;
            }
            if (setting instanceof Label) {
                marginTop += 15 + hight;
            }
            if(setting instanceof Label && preset instanceof Label) {
                marginTop -= 10;
            } 
            setting.setLayoutX(marginLeft);
            setting.setLayoutY(marginTop);
            settingArea.getChildren().add(setting);
            preset = setting;
            
        }
    }

    public void deleteSettings(Figure figure) {
        settingArea.getChildren().removeAll(figure.getSettings());
    }
}
