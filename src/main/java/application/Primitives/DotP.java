package application.Primitives;

import java.util.ArrayList;

import application.Primitives.PrimitiveEnum.Primitives;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DotP extends Primitive{
    private int x;
    private int y;
    private int radius;
    private Color color;
    private Circle circle;
    private Primitives type = Primitives.DOT;
    
    public DotP(int x, int y, int radius, Color color) {
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
    public Primitives getType() {
        return type;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        circle.setRadius(radius);
    }

    public int getRadius() {
        return this.radius;
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
