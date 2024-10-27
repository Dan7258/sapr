package application.Primitives;

import java.util.ArrayList;

import application.Primitives.PrimitiveEnum.Primitives;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class PolylineP extends Primitive{
    ArrayList<Double> pointsList = new ArrayList<>();
    private Color color;
    private double width;
    private Polyline polyline;
    private Primitives type = Primitives.POLYLINE;
    
    public PolylineP(double x1, double y1,double x2, double y2, double width, Color color) {
        this.width = width;
        this.color = color;
        this.polyline = new Polyline();
        setWidth(width);
        setColor(color);
        addMultiplePoints(new double[]{x1,y1,x2,y2});
    }

    public PolylineP(double width, Color color) {
        this.width = width;
        this.color = color;
        this.polyline = new Polyline();
        setWidth(width);
        setColor(color);
    }

    public void addMultiplePoints(double[] listPoints) {
        for(double point : listPoints) {
            addPoint(point);
        }
    }

    private void addPoint(double point) {
        this.polyline.getPoints().addAll(point);
        pointsList.add(point);
    }

    public void removeLastPoint() {
        if(pointsList.size() > 2) {
            this.polyline.getPoints().remove(this.polyline.getPoints().size()-1);
            this.polyline.getPoints().remove(this.polyline.getPoints().size()-1);
            pointsList.remove(this.polyline.getPoints().size()-1);
            pointsList.remove(this.polyline.getPoints().size()-1);
        }  
    }
    public void removeAllPoint() {
        this.polyline.getPoints().clear();
        pointsList.clear();
    }

    @Override
    public double[] getCoordinate() {
        double[] coordinates = new double[this.pointsList.size()];
        for(int i = 0; i < this.pointsList.size(); i++) {
            coordinates[i] = this.pointsList.get(i);
        }
        return coordinates;
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
        for(int i = 0; i < this.polyline.getPoints().size(); i++) {
            this.polyline.getPoints().set(i, newCoordinates[i]);
            this.pointsList.set(i, newCoordinates[i]);
        }
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        for(int i = 0; i < this.polyline.getPoints().size(); i+=2) {
            this.polyline.getPoints().set(i, this.pointsList.get(i) + deltaX);
            this.polyline.getPoints().set(i+1, this.pointsList.get(i+1) + deltaY);
        }
    }

    @Override
    public void updatePosition() {
        for(int i = 0; i < this.pointsList.size(); i+=2) {
            this.pointsList.set(i, this.polyline.getPoints().get(i));
            this.pointsList.set(i+1, this.polyline.getPoints().get(i+1));
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        polyline.setStroke(color);
    }

    public void setWidth(double width) {
        this.width = width;
        polyline.setStrokeWidth(width);
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
        this.polyline = (Polyline)node;
    }

    @Override
    public Node getLink() {
        return polyline;
    }
}
