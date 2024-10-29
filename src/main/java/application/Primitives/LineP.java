package application.Primitives;

import application.Primitives.PrimitiveEnum.Primitives;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineP extends Primitive {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private String color;
    private double width;
    private Line line;
    private Primitives type = Primitives.LINE;
    
    public LineP(double x1, double y1,double x2, double y2, double width, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.color = color.toString();
        this.line = new Line(x1, y1, x2, y2);
        setColor(color);
        setWidth(width);
    }

    @Override
    public double[] getCoordinate() {
        double[] coordinates = {x1, y1, x2, y2};
        return coordinates;
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
        this.x1 = newCoordinates[0];
        this.y1 = newCoordinates[1];
        this.x2 = newCoordinates[2];
        this.y2 = newCoordinates[3];
        line.setStartX(newCoordinates[0]);
        line.setStartY(newCoordinates[1]); 
        line.setEndX(newCoordinates[2]); 
        line.setEndY(newCoordinates[3]); 
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        line.setStartX(this.x1 + deltaX);
        line.setStartY(this.y1 + deltaY); 
        line.setEndX(this.x2 + deltaX); 
        line.setEndY(this.y2 + deltaY);
    }

    @Override
    public void updatePosition() {
        this.x1 = (double)line.getStartX();
        this.y1 = (double)line.getStartY();
        this.x2 = (double)line.getEndX();
        this.y2 = (double)line.getEndY();
    }

    @Override
    public Color getColor() {
        return Color.valueOf(color);
    }

    @Override
    public void setColor(Color color) {
        this.color = color.toString();
        line.setStroke(color);
    }

    public void setWidth(double width) {
        this.width = width;
        line.setStrokeWidth(width);
    }

    public double getWidth() {
        return this.width;
    }

    @Override
    public Primitives getType() {
        return type;
    }

    @Override
    public void setLink(Node node) {
        this.line = (Line) node;
    }

    @Override
    public Node getLink() {
        return line;
    }
    
}
