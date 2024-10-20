package application.Figures;

import java.util.ArrayList;

import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.Primitive;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DotF extends Figure{
    
    private CoordinateSystem coordinateSystem;
    private Color color;
    private DotP dotP;
    private Figures type = Figures.DOTF;
    private Control[] settings;
    
    public DotF(int x, int y, int radius, Color color, CoordinateSystem coordinateSystem) {
        this.color = color;
        dotP = new DotP(x, y, radius, color);
        this.coordinateSystem = coordinateSystem;
        setSettings();
    }

    @Override
    public int[] getCoordinate() {
        return dotP.getCoordinate();
    }

    @Override
    public void setCoordinate(int[] newCoordinates) {
        dotP.setCoordinate(newCoordinates);
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        dotP.changePosition(deltaX, deltaY);  
    }

    @Override
    public void updatePosition() {
        dotP.updatePosition();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        dotP.setColor(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        dotP.setRadius(radius);
    }

    public int getRadius() {
        return dotP.getRadius();
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        dotP.setLink(node.get(0));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(dotP);
        return list;
    }
    @Override
    public Control[] getSettings() {
        return settings;
    }

    private void setSettings() {
        settings = new Control[]
        {
            new Label("Координаты: "), 
            new Label("X: "), 
            new TextField(Integer.toString(coordinateSystem.getRelativeCoordinate(dotP.getCoordinate()[0], dotP.getCoordinate()[1])[0])),
            new Label("Y: "), 
            new TextField(Integer.toString(coordinateSystem.getRelativeCoordinate(dotP.getCoordinate()[0], dotP.getCoordinate()[1])[1])),
            new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
        };
    }
}
