package application.Figures;

import java.util.ArrayList;
import java.util.Arrays;

import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.LineP;
import application.Primitives.Primitive;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Segment extends Figure {

    private Color color;
    private DotP dotP1;
    private DotP dotP2;
    private LineP lineP;
    private Figures type = Figures.SEGMENT;
    
    public Segment(int x1, int y1,int x2, int y2, int radius, double width, Color color) {
        this.color = color;
        dotP1 = new DotP(x1, y1, radius, color);
        dotP2 = new DotP(x2, y2, radius, color);
        lineP = new LineP(x1, y1, x2, y2, width, color);
    }

    @Override
    public int[] getCoordinate() {
        return lineP.getCoordinate();
    }

    @Override
    public void setCoordinate(int[] newCoordinates) {
        dotP1.setCoordinate(Arrays.copyOfRange(newCoordinates, 0, 2));
        dotP2.setCoordinate(Arrays.copyOfRange(newCoordinates, 2, 4));
        lineP.setCoordinate(newCoordinates);
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        dotP1.changePosition(deltaX, deltaY);  
        dotP2.changePosition(deltaX, deltaY);
        lineP.changePosition(deltaX, deltaY);
    }

    @Override
    public void updatePosition() {
        dotP1.updatePosition();
        dotP2.updatePosition();
        lineP.updatePosition();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        dotP1.setColor(color);
        dotP2.setColor(color);
        lineP.setColor(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        dotP1.setRadius(radius);
        dotP2.setRadius(radius);
    }

    public int getRadius() {
        return dotP1.getRadius();
    }

    public void setWidth(double width) {
        lineP.setWidth(width);
    }

    public double getWidth() {
        return lineP.getWidth();
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        dotP1.setLink(node.get(0));
        dotP2.setLink(node.get(1));
        lineP.setLink(node.get(2));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(dotP1);
        list.add(dotP2);
        list.add(lineP);
        return list;
    }
    @Override
    public Control[] getSettings() {
        return null;
    }




    public void takeParamFromSettings() {
        int x;
        int y;
        int radius; 
        Color color;

    }
    
}
