package application.Figures;

import java.util.ArrayList;

import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.LineP;
import application.Primitives.Primitive;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Rectangle extends Figure {
    private CoordinateSystem coordinateSystem;
    private DotP dotP1;
    private DotP dotP2;
    private DotP dotP3;
    private DotP dotP4;
    private LineP lineP1;
    private LineP lineP2;
    private LineP lineP3;
    private LineP lineP4;
    private Figures type = Figures.RECTANGLE;
    private Control[] settings;
    private Double[] typeLine = new Double[]{};
  
    public Rectangle(double x1, double y1, double x2, double y2, int radius, double width, Color color, CoordinateSystem coordinateSystem) {
        dotP1 = new DotP(x1, y1, radius, color);
        dotP2 = new DotP(x1, y2, radius, color);
        dotP3 = new DotP(x2, y2, radius, color);
        dotP4 = new DotP(x2, y1, radius, color);
        lineP1 = new LineP(x1, y1, x1, y2, width, color);
        lineP2 = new LineP(x1, y2, x2, y2, width, color);
        lineP3 = new LineP(x2, y2, x2, y1, width, color);
        lineP4 = new LineP(x2, y1, x1, y1, width, color);
        this.coordinateSystem = coordinateSystem;
    }
    @Override
    public PreparingData preparingData() {
        double[] relativeCoord = coordinateSystem.getRelativeCoordinate(getCoordinate());
        return new PreparingData(type, getColor().toString(), relativeCoord, getRadius(), getWidth(), this.typeLine);
    }
    
    @Override
    public void regenerateLink() {
        this.dotP1 = new DotP(getCoordinate()[0], getCoordinate()[1], getRadius(), getColor());
        this.dotP2 = new DotP(getCoordinate()[0], getCoordinate()[3], getRadius(), getColor());
        this.dotP3 = new DotP(getCoordinate()[2], getCoordinate()[3], getRadius(), getColor());
        this.dotP4 = new DotP(getCoordinate()[2], getCoordinate()[1], getRadius(), getColor());
        this.lineP1 = new LineP(getCoordinate()[0], getCoordinate()[1], getCoordinate()[0], getCoordinate()[3], getWidth(), getColor());
        this.lineP2 = new LineP(getCoordinate()[0], getCoordinate()[3], getCoordinate()[2], getCoordinate()[3], getWidth(), getColor());
        this.lineP3 = new LineP(getCoordinate()[2], getCoordinate()[3], getCoordinate()[2], getCoordinate()[1], getWidth(), getColor());
        this.lineP4 = new LineP(getCoordinate()[2], getCoordinate()[1], getCoordinate()[0], getCoordinate()[1], getWidth(), getColor());
    }

    @Override
    public double[] getCoordinate() {
        double[] newCoord = new double[4];
        newCoord[0] = dotP1.getCoordinate()[0];
        newCoord[1] = dotP2.getCoordinate()[1];
        newCoord[2] = dotP3.getCoordinate()[0];
        newCoord[3] = dotP4.getCoordinate()[1];
        return newCoord;
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
        dotP1.setCoordinate(new double[]{newCoordinates[0], newCoordinates[1]});
        dotP2.setCoordinate(new double[]{newCoordinates[0], newCoordinates[3]});
        dotP3.setCoordinate(new double[]{newCoordinates[2], newCoordinates[3]});
        dotP4.setCoordinate(new double[]{newCoordinates[2], newCoordinates[1]});
        lineP1.setCoordinate(new double[]{newCoordinates[0], newCoordinates[1], newCoordinates[0], newCoordinates[3]});
        lineP2.setCoordinate(new double[]{newCoordinates[0], newCoordinates[3], newCoordinates[2], newCoordinates[3]});
        lineP3.setCoordinate(new double[]{newCoordinates[2], newCoordinates[3], newCoordinates[2], newCoordinates[1]});
        lineP4.setCoordinate(new double[]{newCoordinates[2], newCoordinates[1], newCoordinates[0], newCoordinates[1]});
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        dotP1.changePosition(deltaX, deltaY);  
        dotP2.changePosition(deltaX, deltaY);
        dotP3.changePosition(deltaX, deltaY);
        dotP4.changePosition(deltaX, deltaY);
        lineP1.changePosition(deltaX, deltaY);
        lineP2.changePosition(deltaX, deltaY);
        lineP3.changePosition(deltaX, deltaY);
        lineP4.changePosition(deltaX, deltaY);
    }

    @Override
    public void updatePosition() {
        dotP1.updatePosition();
        dotP2.updatePosition();
        dotP3.updatePosition();
        dotP4.updatePosition();
        lineP1.updatePosition();
        lineP2.updatePosition();
        lineP3.updatePosition();
        lineP4.updatePosition();
    }

    @Override
    public Color getColor() {
        return lineP1.getColor();
    }

    @Override
    public void setColor(Color color) {
        dotP1.setColor(color);
        dotP2.setColor(color);
        dotP3.setColor(color);
        dotP4.setColor(color);
        lineP1.setColor(color);
        lineP2.setColor(color);
        lineP3.setColor(color);
        lineP4.setColor(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        dotP1.setRadius(radius);
        dotP2.setRadius(radius);
        dotP3.setRadius(radius);
        dotP4.setRadius(radius);
    }

    public int getRadius() {
        return (int)dotP1.getRadius();
    }

    public void setWidth(double width) {
        lineP1.setWidth(width);
        lineP2.setWidth(width);
        lineP3.setWidth(width);
        lineP4.setWidth(width);
    }

    public double getWidth() {
        return lineP1.getWidth();
    }

    public void setTypeLine(Double[] typeLine) {
        this.typeLine = typeLine;
        ((Line)lineP1.getLink()).getStrokeDashArray().setAll(this.typeLine);
        ((Line)lineP2.getLink()).getStrokeDashArray().setAll(this.typeLine);
        ((Line)lineP3.getLink()).getStrokeDashArray().setAll(this.typeLine);
        ((Line)lineP4.getLink()).getStrokeDashArray().setAll(this.typeLine);
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        dotP1.setLink(node.get(0));
        dotP2.setLink(node.get(1));
        dotP3.setLink(node.get(2));
        dotP4.setLink(node.get(3));
        lineP1.setLink(node.get(4));
        lineP2.setLink(node.get(5));
        lineP3.setLink(node.get(6));
        lineP4.setLink(node.get(7));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(dotP1);
        list.add(dotP2);
        list.add(dotP3);
        list.add(dotP4);
        list.add(lineP1);
        list.add(lineP2);
        list.add(lineP3);
        list.add(lineP4);
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
        if(((Line)lineP1.getLink()).getStrokeDashArray().isEmpty()) {
            comboBox.setValue("──────");
        } else {
            double[] dashArray = ((Line)lineP1.getLink()).getStrokeDashArray().stream().mapToDouble(Double::doubleValue).toArray();
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
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP1.getCoordinate())[0])),
            new Label("Y1: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP1.getCoordinate())[1])),
            new Label("X2: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP3.getCoordinate())[0])),
            new Label("Y2: "), 
            new TextField(Double.toString(coordinateSystem.getRelativeCoordinate(dotP3.getCoordinate())[1])),
            new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
            new Label("Тип линий: "), 
            comboBox,
            new Label("толщина линий: "), 
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
            x2 = dotP3.getCoordinate()[0];
        }
        if(((TextField)settings[8]).getText()!="") {
            y2 = Double.parseDouble(((TextField)settings[8]).getText()); 
            y2 = coordinateSystem.getAbsoluteCoordinate(new double[]{0,y2})[1];
        } else {
            y2 = dotP3.getCoordinate()[1];
        }
        if(((TextField)settings[10]).getText()!="") {
            radius = Integer.parseInt(((TextField)settings[10]).getText()) > 0 ? Integer.parseInt(((TextField)settings[10]).getText()) : 1; 
        } else {
            radius = (int)dotP1.getRadius();
        }
        if(((TextField)settings[12]).getText()!="") {
            color = Color.web(((TextField)settings[12]).getText());
        } else {
            color = dotP1.getColor();
        }
        switch ((String)(((ComboBox)settings[14]).getValue())) {
            case "──────":
                this.typeLine = new Double[]{};
                break;
            case "─ ─ ─ ─ ─":
                this.typeLine = new Double[]{10.0, 10.0};
                break;
            case "─·─·─·─·─":
                this.typeLine = new Double[]{15.0, 10.0, 1.0, 8.0};
                break;
        }
        ((Line)lineP1.getLink()).getStrokeDashArray().setAll(this.typeLine);
        ((Line)lineP2.getLink()).getStrokeDashArray().setAll(this.typeLine);
        ((Line)lineP3.getLink()).getStrokeDashArray().setAll(this.typeLine);
        ((Line)lineP4.getLink()).getStrokeDashArray().setAll(this.typeLine);
        
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
