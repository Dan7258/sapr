package application.Primitives;

import application.Primitives.PrimitiveEnum.Primitives;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DotP extends Primitive{
    private double x;
    private double y;
    private double radius;
    private Color color;
    private Circle circle;
    private Primitives type = Primitives.DOT;
    
    public DotP(double x, double y, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.circle = new Circle(x,y, radius, color); 
    }

    public DotP(double x, double y, double radius, Color color, double width) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.circle = new Circle(x,y, radius, Color.TRANSPARENT);
        this.circle.setStroke(color);
        this.circle.setStrokeWidth(width);
        this.circle.setId("Ring");
    }

    @Override
    public double[] getCoordinate() {
        double[] coordinates = {x, y};
        return coordinates;
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
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
        this.x = (double)circle.getCenterX();
        this.y = (double)circle.getCenterY();
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

    public void setRadius(double radius) {
        this.radius = radius;
        circle.setRadius(radius);
    }

    public double getRadius() {
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
