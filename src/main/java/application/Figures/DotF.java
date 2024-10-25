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
    private DotP dotP;
    private Figures type = Figures.DOTF;
    private Control[] settings;
    
    public DotF(double x, double y, int radius, Color color, CoordinateSystem coordinateSystem) {
        dotP = new DotP(x, y, radius, color);
        this.coordinateSystem = coordinateSystem;
    }

    @Override
    public double[] getCoordinate() {
        return dotP.getCoordinate();
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
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
        return dotP.getColor();
    }

    @Override
    public void setColor(Color color) {
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

    @Override
    public void takeParamFromSettings() {
        double x;
        double y;
        int radius; 
        Color color;

        if(((TextField)settings[2]).getText()!="") {
            x = Integer.parseInt(((TextField)settings[2]).getText()); 
            x = coordinateSystem.getAbsoluteCoordinate(new double[]{x,0})[0];
        } else {
            x = dotP.getCoordinate()[0];
        }
        if(((TextField)settings[4]).getText()!="") {
            y = Integer.parseInt(((TextField)settings[4]).getText()); 
            y = coordinateSystem.getAbsoluteCoordinate(new double[]{0,y})[1];
        } else {
            y = dotP.getCoordinate()[1];
        }
        if(((TextField)settings[6]).getText()!="") {
            radius = Integer.parseInt(((TextField)settings[6]).getText()); 
        } else {
            radius = dotP.getRadius();
        }
        if(((TextField)settings[8]).getText()!="") {
            color = Color.web(((TextField)settings[8]).getText());
        } else {
            color = dotP.getColor();
        }
        dotP.setCoordinate(new double[]{x,y});
        dotP.setColor(color);
        //System.out.println("h ");
        dotP.setRadius(radius);
        //System.out.println("base color: " + dotP.getColor());
    }

    @Override
    public void setSettings() {
        settings = new Control[]
        {
            new Label("Координаты: "), 
            new Label("X: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP.getCoordinate()[0], dotP.getCoordinate()[1])[0])),
            new Label("Y: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP.getCoordinate()[0], dotP.getCoordinate()[1])[1])),
            new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
        };
    }
}
