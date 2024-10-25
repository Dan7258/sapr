package application.Figures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.LineP;
import application.Primitives.Primitive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Segment extends Figure {
    private CoordinateSystem coordinateSystem;
    private DotP dotP1;
    private DotP dotP2;
    private LineP lineP;
    private Figures type = Figures.SEGMENT;
    private Control[] settings;
    private Map<String, ObservableList<Double>> lineStyles = new HashMap<>(); 
  
    public Segment(double x1, double y1,double x2, double y2, int radius, double width, Color color, CoordinateSystem coordinateSystem) {
        dotP1 = new DotP(x1, y1, radius, color);
        dotP2 = new DotP(x2, y2, radius, color);
        lineP = new LineP(x1, y1, x2, y2, width, color);
        this.coordinateSystem = coordinateSystem;
        lineStyles.put("──────" , FXCollections.observableArrayList());
        lineStyles.put("─ ─ ─ ─ ─" ,FXCollections.observableArrayList(10.0, 10.0));
        lineStyles.put("─·─·─·─·─" ,  FXCollections.observableArrayList(15.0, 10.0, 1.0, 8.0));
    }

    @Override
    public double[] getCoordinate() {
        return lineP.getCoordinate();
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
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
        return lineP.getColor();
    }

    @Override
    public void setColor(Color color) {
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
        return settings;
    }

    @Override
    public void setSettings() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
            "──────",
            "─ ─ ─ ─ ─",
            "─·─·─·─·─"
        );
        if(((Line)lineP.getLink()).getStrokeDashArray().isEmpty()) {
            comboBox.setValue("──────");
        } else {
            double[] dashArray = ((Line)lineP.getLink()).getStrokeDashArray().stream().mapToDouble(Double::doubleValue).toArray();
            if(dashArray.length == 2) {
                comboBox.setValue( "─ ─ ─ ─ ─");
            } else {
                comboBox.setValue("─·─·─·─·─");
            }
        }
        
        settings = new Control[]
        {
            new Label("Координаты: "), 
            new Label("X1: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP1.getCoordinate()[0], dotP1.getCoordinate()[1])[0])),
            new Label("Y1: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP1.getCoordinate()[0], dotP1.getCoordinate()[1])[1])),
            new Label("X2: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP2.getCoordinate()[0], dotP2.getCoordinate()[1])[0])),
            new Label("Y2: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP2.getCoordinate()[0], dotP2.getCoordinate()[1])[1])),
            new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
            new Label("Тип линии: "), 
            comboBox,
            new Label("толщина линии: "), 
            new TextField(Double.toString(getWidth())),
        };
    }

    @Override
    public void takeParamFromSettings() {
        double x1;
        double y1;
        double x2;
        double y2;
        int radius; 
        Color color;
        double width;
                   
        if(((TextField)settings[2]).getText()!="") {
            x1 = Double.parseDouble(((TextField)settings[2]).getText()); 
            x1 = coordinateSystem.getAbsoluteCoordinate(new double[]{x1,0})[0];
        } else {
            x1 = dotP1.getCoordinate()[0];
        }
        if(((TextField)settings[4]).getText()!="") {
            y1 = Double.parseDouble(((TextField)settings[4]).getText()); 
            y1 = coordinateSystem.getAbsoluteCoordinate(new double[]{0,y1})[1];
        } else {
            y1 = dotP1.getCoordinate()[1];
        }
        if(((TextField)settings[6]).getText()!="") {
            x2 = Double.parseDouble(((TextField)settings[6]).getText()); 
            x2 = coordinateSystem.getAbsoluteCoordinate(new double[]{x2,0})[0];
        } else {
            x2 = dotP2.getCoordinate()[0];
        }
        if(((TextField)settings[8]).getText()!="") {
            y2 = Double.parseDouble(((TextField)settings[8]).getText()); 
            y2 = coordinateSystem.getAbsoluteCoordinate(new double[]{0,y2})[1];
        } else {
            y2 = dotP2.getCoordinate()[1];
        }
        if(((TextField)settings[10]).getText()!="") {
            radius = Integer.parseInt(((TextField)settings[10]).getText()) > 0 ? Integer.parseInt(((TextField)settings[10]).getText()) : 1; 
        } else {
            radius = dotP1.getRadius();
        }
        if(((TextField)settings[12]).getText()!="") {
            color = Color.web(((TextField)settings[12]).getText());
        } else {
            color = dotP1.getColor();
        }
        ((Line)lineP.getLink()).getStrokeDashArray().setAll(lineStyles.get(((ComboBox)settings[14]).getValue()));
        if(((TextField)settings[16]).getText()!="") {
            width = Double.parseDouble(((TextField)settings[16]).getText()) > 0 ? Double.parseDouble(((TextField)settings[16]).getText()) : 1; 
        } else {
            width = getWidth();
        }

        setCoordinate(new double[]{x1,y1,x2,y2});
        setColor(color);
        setRadius(radius);
        setWidth(width);
        
    }
    
}
