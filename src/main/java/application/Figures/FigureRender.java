package application.Figures;

import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import application.Primitives.Primitive;

public class FigureRender {
    
    private AnchorPane workingArea;

    public FigureRender(AnchorPane workingArea) {
        this.workingArea = workingArea;
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
}
