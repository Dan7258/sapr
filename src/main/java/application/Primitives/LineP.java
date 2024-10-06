package application.Primitives;

import application.Primitives.PrimitiveEnum.Primitives;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineP extends Primitive {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    private double width;
    private Line line;
    private Primitives type = Primitives.LINE;
    
    public LineP(int x1, int y1,int x2, int y2, double width, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.color = color;
    }

    @Override
    public int[] getCoordinate() {
        int[] coordinates = {x1, y1, x2, y2};
        return coordinates;
    }

    @Override
    public void setCoordinate(int[] newCoordinates) {
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
        this.x1 = (int)line.getStartX();
        this.y1 = (int)line.getStartY();
        this.x2 = (int)line.getEndX();
        this.y2 = (int)line.getEndY();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
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
