package application.Figures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.Primitive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ring extends Figure{
    private CoordinateSystem coordinateSystem;
    private DotP dotP1;
    private DotP dotP2;
    private Figures type = Figures.RING;
    private Control[] settings;
    private Map<String, ObservableList<Double>> ringStyles = new HashMap<>(); 
    private double mainRadius;
    private double scale = 1;
    
    public Ring(double x1, double y1, double x2, double y2, int radius, double width, Color color, CoordinateSystem coordinateSystem) {
        dotP1 = new DotP(x1, y1, radius, color);
        this.mainRadius = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        dotP2 = new DotP(x1, y1, this.mainRadius, color, width);
        this.coordinateSystem = coordinateSystem;
        ringStyles.put("──────" , FXCollections.observableArrayList());
        ringStyles.put("─ ─ ─ ─ ─" ,FXCollections.observableArrayList(10.0, 10.0));
        ringStyles.put("─·─·─·─·─" ,  FXCollections.observableArrayList(15.0, 10.0, 1.0, 8.0));
    }

    @Override
    public double[] getCoordinate() {
        return dotP1.getCoordinate();
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
        dotP1.setCoordinate(newCoordinates);
        dotP2.setCoordinate(newCoordinates);
        // mainRadius = Math.sqrt(Math.pow(newCoordinates[2] - newCoordinates[0], 2) + Math.pow(newCoordinates[3] - newCoordinates[1], 2));
        // dotP2.setRadius(mainRadius);
    }

    

    @Override
    public void changePosition(double deltaX, double deltaY) {
        dotP1.changePosition(deltaX, deltaY); 
        dotP2.changePosition(deltaX, deltaY); 
        // this.x2 += deltaX;
        // this.y2 += deltaY;
    }

    @Override
    public void updatePosition() {
        dotP1.updatePosition();
        dotP2.updatePosition();
        // this.x2 += dotP1.getCoordinate()[0] + this.mainRadius;
        // this.y2 += dotP1.getCoordinate()[1];
    }

    @Override
    public Color getColor() {
        return dotP1.getColor();
    }

    @Override
    public void setColor(Color color) {
        dotP1.setColor(color);
        ((Circle)dotP2.getLink()).setStroke(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        dotP1.setRadius(radius);
    }

    public void setMainRadius(double radius) {
        dotP2.setRadius(radius);
    }

    public void zoom(double newRadius) {
        scale = newRadius/this.mainRadius;
        dotP2.setRadius(newRadius);
    }

    public int getRadius() {
        return (int)dotP1.getRadius();
    }
    public double getMainRadius() {
        return dotP2.getRadius();
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        dotP1.setLink(node.get(0));
        dotP2.setLink(node.get(1));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(dotP1);
        list.add(dotP2);
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

        if(((Circle)dotP2.getLink()).getStrokeDashArray().isEmpty()) {
            comboBox.setValue("──────");
        } else {
            double[] dashArray = ((Circle)dotP2.getLink()).getStrokeDashArray().stream().mapToDouble(Double::doubleValue).toArray();
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
            new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Основной радиус: "), 
            new TextField(Double.toString(getMainRadius() / scale)),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
            new Label("Тип линий: "), 
            comboBox,
            new Label("толщина линии: "), 
            new TextField(Double.toString(((Circle)dotP2.getLink()).getStrokeWidth())),
        };
    }

    @Override
    public void takeParamFromSettings() {
        double x;
        double y;
        int radius;
        double mainRadius; 
        Color color;
        double width;

        if(((TextField)settings[2]).getText()!="") {
            x = Double.parseDouble(((TextField)settings[2]).getText()); 
            x = coordinateSystem.getAbsoluteCoordinate(new double[]{x,0})[0];
        } else {
            x = getCoordinate()[0];
        }
        if(((TextField)settings[4]).getText()!="") {
            y = Double.parseDouble(((TextField)settings[4]).getText()); 
            y = coordinateSystem.getAbsoluteCoordinate(new double[]{0,y})[1];
        } else {
            y = getCoordinate()[1];
        }
        if(((TextField)settings[6]).getText()!="") {
            radius = Integer.parseInt(((TextField)settings[6]).getText()); 
        } else {
            radius = (int)getRadius();
        }
        if(((TextField)settings[8]).getText()!="") {
            mainRadius = Double.parseDouble(((TextField)settings[8]).getText()) * this.scale; 
        } else {
            mainRadius = getMainRadius();
        }
        if(((TextField)settings[10]).getText()!="") {
            color = Color.web(((TextField)settings[10]).getText());
        } else {
            color = getColor();
        }
        ((Circle)dotP2.getLink()).getStrokeDashArray().setAll(ringStyles.get(((ComboBox)settings[12]).getValue()));
        if(((TextField)settings[14]).getText()!="") {
            width = Double.parseDouble(((TextField)settings[14]).getText()) > 0 ? Double.parseDouble(((TextField)settings[14]).getText()) : 1; 
        } else {
            width = ((Circle)dotP2.getLink()).getStrokeWidth();
        }
        setCoordinate(new double[]{x,y});
        setColor(color);
        setRadius(radius);
        setMainRadius(mainRadius);
        ((Circle)dotP2.getLink()).setStrokeWidth(width);
    }
}
