package application;

import application.FigureEnum.Figures;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Dot extends Figure{
    private int x;
    private int y;
    private int radius;
    private Color color;
    private Circle circle;
    private Figures type = Figures.DOT;
    
    public Dot(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public int[] getCoordinate() {
        int[] coordinates = {x, y};
        return coordinates;
    }

    @Override
    public void setCoordinate(int[] newCoordinates) {
        this.x = newCoordinates[0];
        this.y = newCoordinates[1];
        circle.setCenterX(newCoordinates[0]);
        circle.setCenterY(newCoordinates[1]);
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        circle.setCenterX(this.x + deltaX);
        circle.setCenterY(this.y + deltaY);
    }

    @Override
    public void updatePosition() {
        this.x = (int)circle.getCenterX();
        this.y = (int)circle.getCenterY();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        circle.setFill(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    @Override
    public void setLink(Node node) {
        this.circle = (Circle) node;
    }

    @Override
    public Node getLink() {
        return circle;
    }

    
}
