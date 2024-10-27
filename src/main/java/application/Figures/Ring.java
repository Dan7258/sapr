package application.Figures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.PolylineP;
import application.Primitives.Primitive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class Ring extends Figure{
    private CoordinateSystem coordinateSystem;
    private DotP dotP;
    private PolylineP polylineP;
    private Figures type = Figures.RING;
    private Control[] settings;
    private Map<String, ObservableList<Double>> ringStyles = new HashMap<>(); 
    private double mainRadius;
    private double scale = 1;
    private int numPoints = 100;
    
    public Ring(double x1, double y1, double x2, double y2, int radius, double width, Color color, CoordinateSystem coordinateSystem) {
        dotP = new DotP(x1, y1, radius, color);
        this.coordinateSystem = coordinateSystem;
        this.scale = coordinateSystem.getScale();
        this.mainRadius = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / this.scale;
        this.polylineP = new PolylineP(width, color);
        this.polylineP.removeLastPoint();
        generateRing(x1, y1);
        ringStyles.put("──────" , FXCollections.observableArrayList());
        ringStyles.put("─ ─ ─ ─ ─" ,FXCollections.observableArrayList(10.0, 10.0));
        ringStyles.put("─·─·─·─·─" ,  FXCollections.observableArrayList(15.0, 10.0, 1.0, 8.0));
    }

    private void generateRing(double centerX, double centerY) {
        for (int i = 0; i <= this.numPoints; i++) {
            double angle = 2 * Math.PI * i / this.numPoints;
            double x = centerX + this.mainRadius * this.scale * Math.cos(angle);
            double y = centerY + this.mainRadius * this.scale * Math.sin(angle);
            polylineP.addMultiplePoints(new double[]{x,y});;
        }
    }

    @Override
    public double[] getCoordinate() {
        double[] coordinate = Arrays.copyOf(dotP.getCoordinate(), dotP.getCoordinate().length + polylineP.getCoordinate().length);
        System.arraycopy(polylineP.getCoordinate(), 0, coordinate,dotP.getCoordinate().length, polylineP.getCoordinate().length);
        return coordinate;
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
        this.scale = coordinateSystem.getScale();
        dotP.setCoordinate(Arrays.copyOfRange(newCoordinates, 0, 2));
        polylineP.setCoordinate(Arrays.copyOfRange(newCoordinates, 2, newCoordinates.length));
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        dotP.changePosition(deltaX, deltaY); 
        polylineP.changePosition(deltaX, deltaY); 
    }

    @Override
    public void updatePosition() {
        dotP.updatePosition();
        polylineP.updatePosition();
    }

    @Override
    public Color getColor() {
        return dotP.getColor();
    }

    @Override
    public void setColor(Color color) {
        dotP.setColor(color);
        polylineP.setColor(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        dotP.setRadius(radius);
    }

    public void setMainRadius(double newRadius) {
        this.mainRadius = newRadius;
        polylineP.removeAllPoint();
        generateRing(dotP.getCoordinate()[0], dotP.getCoordinate()[1]);
    }

    public int getRadius() {
        return (int)dotP.getRadius();
    }
    public double getMainRadius() {
        return this.mainRadius;
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        dotP.setLink(node.get(0));
        polylineP.setLink(node.get(1));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(dotP);
        list.add(polylineP);
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

        if(((Polyline)polylineP.getLink()).getStrokeDashArray().isEmpty()) {
            comboBox.setValue("──────");
        } else {
            double[] dashArray = ((Polyline)polylineP.getLink()).getStrokeDashArray().stream().mapToDouble(Double::doubleValue).toArray();
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
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP.getCoordinate()[0], dotP.getCoordinate()[1])[0])),
            new Label("Y1: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP.getCoordinate()[0], dotP.getCoordinate()[1])[1])),
            new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Основной радиус: "), 
            new TextField(Double.toString(getMainRadius())),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
            new Label("Тип линий: "), 
            comboBox,
            new Label("толщина линии: "), 
            new TextField(Double.toString(polylineP.getWidth())),
        };
    }

    @Override
    public void takeParamFromSettings() {
        this.scale = coordinateSystem.getScale();
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
            mainRadius = Double.parseDouble(((TextField)settings[8]).getText()); 
        } else {
            mainRadius = getMainRadius();
        }
        if(((TextField)settings[10]).getText()!="") {
            color = Color.web(((TextField)settings[10]).getText());
        } else {
            color = getColor();
        }
        ((Polyline)polylineP.getLink()).getStrokeDashArray().setAll(ringStyles.get(((ComboBox)settings[12]).getValue()));
        if(((TextField)settings[14]).getText()!="") {
            width = Double.parseDouble(((TextField)settings[14]).getText()) > 0 ? Double.parseDouble(((TextField)settings[14]).getText()) : 1; 
        } else {
            width = ((Polyline)polylineP.getLink()).getStrokeWidth();
        }
        polylineP.setWidth(width);
        dotP.setCoordinate(new double[]{x,y});
        setColor(color);
        setRadius(radius);
        setMainRadius(mainRadius);
    }
}
