package application;

import application.FigureEnum.Figures;
import javafx.scene.paint.Color;

public class Dot extends Figure{
    private int x;
    private int y;
    private int radius;
    private Color color;
    private Figures type = Figures.DOT;
    
    public Dot(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public int[] getCoordinate() {
        int[] coordinates = {x, y};
        return coordinates;
    }

    @Override
    public void setCoordinate(int[] newCoordinates) {
        this.x = newCoordinates[0];
        this.y = newCoordinates[1];
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
}
