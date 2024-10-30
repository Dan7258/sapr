package application.Figures;

import application.Figures.FigureEnum.Figures;
import javafx.scene.paint.Color;

public class PreparingData {
    private Figures type;
    private String color;
    private double[] coordinate;
    private double radius;
    private double width;
    private Double[] typeLine;
    public PreparingData(Figures type, String color, double[] coordinate, double radius) {
        this.type = type;
        this.color = color;
        this.coordinate = coordinate;
        this.radius = radius;
    }   

    public PreparingData(Figures type, String color, double[] coordinate, double radius, double width, Double[] typeLine) {
        this.type = type;
        this.color = color;
        this.coordinate = coordinate;
        this.radius = radius;
        this.width = width;
        this.typeLine = typeLine;
    }  

    public Color getColor() {
        return Color.valueOf(this.color);
    }
    
    public Figures getType() {
        return type;
    }
    public int getRadius() {
        return (int)radius;
    }
    public double[] getCoordinate() {
        return coordinate;
    }
    public double getWidth() {
        return this.width;
    }

    public Double[] getTypeLine() {
        return this.typeLine;
    }

}
