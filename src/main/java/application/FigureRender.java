package application;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class FigureRender {
    
    private AnchorPane workingArea;

    public FigureRender(AnchorPane workingArea) {
        this.workingArea = workingArea;
    }
    public Node render(Figure figure) {
        Node node = new Node(){};
        switch (figure.getType()) {
            case DOT:
                Circle circle = new Circle(figure.getCoordinate()[0], figure.getCoordinate()[1], 6, figure.getColor());
                workingArea.getChildren().add(circle);
                return circle;
            case LINE:

                break;
        }
        return node;
    }

    public void erase(Figure figure) {
        workingArea.getChildren().remove(figure.getLink());
    }
}
