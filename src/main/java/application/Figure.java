package application;

import javafx.scene.paint.Color;
import application.FigureEnum.Figures;

public abstract class Figure {
    public abstract int[] getCoordinate();
    public abstract void setCoordinate(int[] newCoordinates);
    public abstract Color getColor();
    public abstract void setColor(Color color);
    public abstract Figures getType();
}
