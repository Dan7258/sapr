package application.Figures;

import java.util.ArrayList;

import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.PolylineP;
import application.Primitives.Primitive;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class PolylineF extends Figure{
    private CoordinateSystem coordinateSystem;
    private PolylineP polylineP;
    private DotP dotP;
    private Figures type = Figures.POLYLINEF;
    private Control[] settings;
    private Double[] typeLine = new Double[]{};
  
    public PolylineF(double x1, double y1,double x2, double y2, double width, Color color, CoordinateSystem coordinateSystem) {
        polylineP = new PolylineP(x1, y1, x2, y2, width, color);
        dotP = new DotP(x1, y1, width + 2, color);
        this.coordinateSystem = coordinateSystem;
    }
    @Override
    public PreparingData preparingData() {
        double[] relativeCoord = coordinateSystem.getRelativeCoordinate(getCoordinate());
        return new PreparingData(type, getColor().toString(), relativeCoord, getRadius(), getWidth(), this.typeLine);
    }

    @Override
    public void regenerateLink() {
        this.dotP = new DotP(dotP.getCoordinate()[0], dotP.getCoordinate()[1], getRadius(), getColor());
        this.polylineP = new PolylineP(polylineP.getCoordinate()[0], polylineP.getCoordinate()[1], polylineP.getCoordinate()[2], polylineP.getCoordinate()[3], getWidth(), getColor());
    }

    public void addMultiplePoints(double[] listPoints) {
        polylineP.addMultiplePoints(listPoints);
    }
    public void removeLastPoint() {
        polylineP.removeLastPoint();;
    }

    @Override
    public double[] getCoordinate() {
        return polylineP.getCoordinate();
    }

    @Override
    public void setCoordinate(double[] newCoordinates) {
        polylineP.setCoordinate(newCoordinates);
        dotP.setCoordinate(new double[]{newCoordinates[0], newCoordinates[1]});
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        polylineP.changePosition(deltaX, deltaY);
        dotP.changePosition(deltaX, deltaY);
    }

    @Override
    public void updatePosition() {
        polylineP.updatePosition();
        dotP.updatePosition();
    }

    @Override
    public Color getColor() {
        return polylineP.getColor();
    }

    @Override
    public void setColor(Color color) {
        polylineP.setColor(color);
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
        return (int)dotP.getRadius();
    }

    public void setWidth(double width) {
        polylineP.setWidth(width);
    }

    public double getWidth() {
        return polylineP.getWidth();
    }

    public void setTypeLine(Double[] typeLine) {
        this.typeLine = typeLine;
        ((Polyline)polylineP.getLink()).getStrokeDashArray().setAll(this.typeLine);
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        polylineP.setLink(node.get(0));
        dotP.setLink(node.get(1));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(polylineP);
        list.add(dotP);
        return list;
    }

    @Override
    public Control[] getSettings() {
        return settings;
    }

    @Override
    public void setSettings() {
        ComboBox<String> comboBox1 = new ComboBox<>();
        ComboBox<String> comboBox2 = new ComboBox<>();
        Button btnAdd = new Button("Добавить");
        Button btnDel = new Button("Удалить");
        comboBox1.getItems().addAll(
            "──────",
            "─ ─ ─ ─ ─",
            "─·─·─·─·─"
        );
        comboBox2.getItems().addAll(
            "Да",
            "Нет"
        );
        comboBox2.setValue("Нет");
        ComboBox<String> comboBox3 = new ComboBox<>();
        comboBox3.getItems().addAll(
            "Да",
            "Нет"
        );
        comboBox3.setValue("Нет");

        if(((Polyline)polylineP.getLink()).getStrokeDashArray().isEmpty()) {
            comboBox1.setValue("──────");
        } else {
            double[] dashArray = ((Polyline)polylineP.getLink()).getStrokeDashArray().stream().mapToDouble(Double::doubleValue).toArray();
            if(dashArray.length == 2) {
                comboBox1.setValue("─ ─ ─ ─ ─");
            } else {
                comboBox1.setValue("─·─·─·─·─");
            }
        }
        
        settings = new Control[]
        {   new Label("Радиус: "), 
            new TextField(Integer.toString(getRadius())),
            new Label("Цвет: "), 
            new TextField(getColor().toString()),
            new Label("Тип линии: "), 
            comboBox1,
            new Label("толщина линии: "), 
            new TextField(Double.toString(getWidth())),
            new Label("Добавить точку? "),
            comboBox2, 
            new Label("X: "), 
            new TextField("0"),
            new Label("Y: "), 
            new TextField("0"),
            btnAdd,
            new Label("Удалить последнюю точку? "),
            comboBox3,
            btnDel,
        };
        checkAddBtn(btnAdd);
        checkDelBtn(btnDel);
    }

    private void checkAddBtn(Button btnAdd) {
        btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                double x;
                double y;
                if(((TextField)settings[11]).getText()!="") {
                    x = Double.parseDouble(((TextField)settings[11]).getText()); 
                    x = coordinateSystem.getAbsoluteCoordinate(new double[]{x,0})[0];
                } else {
                    x = 0;
                }
                if(((TextField)settings[13]).getText()!="") {
                    y = Double.parseDouble(((TextField)settings[13]).getText()); 
                    y = coordinateSystem.getAbsoluteCoordinate(new double[]{0,y})[1];
                } else {
                    y = 0;
                }
                if(((ComboBox)settings[9]).getValue().equals("Да")) {
                    addMultiplePoints(new double[]{x,y});
                }
            }
        });
    }

    private void checkDelBtn(Button btnDel) {
        btnDel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                if(((ComboBox)settings[16]).getValue().equals("Да")) {
                    removeLastPoint();
                }
            }
        });
    }

    @Override
    public void takeParamFromSettings() {
        Color color;
        double width;
        int radius;

        if(((TextField)settings[1]).getText()!="") {
            radius = Integer.parseInt(((TextField)settings[1]).getText()) > 0 ? Integer.parseInt(((TextField)settings[1]).getText()) : 1; 
        } else {
            radius = (int)dotP.getRadius();
        }           
        if(((TextField)settings[3]).getText()!="") {
            color = Color.web(((TextField)settings[3]).getText());
        } else {
            color = polylineP.getColor();
        }
        switch ((String)(((ComboBox)settings[5]).getValue())) {
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
        ((Polyline)polylineP.getLink()).getStrokeDashArray().setAll(this.typeLine);
        if(((TextField)settings[7]).getText()!="") {
            width = Double.parseDouble(((TextField)settings[7]).getText()) > 0 ? Double.parseDouble(((TextField)settings[7]).getText()) : 1; 
        } else {
            width = getWidth();
        }
        setRadius(radius);
        setColor(color);
        setWidth(width);
    }
}
