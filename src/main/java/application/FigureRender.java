package application;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class FigureRender {
    
    private AnchorPane workingArea;

    public FigureRender(AnchorPane workingArea) {
        this.workingArea = workingArea;
    }
    public void render(Figure figure) {
        switch (figure.getType()) {
            case DOT:
                workingArea.getChildren().add(new Circle(figure.getCoordinate()[0], figure.getCoordinate()[1], 6, figure.getColor()));
                break;
            case LINE:

                break;
        }
    }
}
