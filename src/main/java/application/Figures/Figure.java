package application.Figures;

import java.util.ArrayList;

import application.Figures.FigureEnum.Figures;
import application.Primitives.Primitive;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public abstract class Figure {
    public abstract int[] getCoordinate();
    public abstract void setCoordinate(int[] newCoordinates);
    public abstract void changePosition(double deltaX, double deltaY);
    public abstract Color getColor();
    public abstract void setColor(Color color);
    public abstract void setLink(ArrayList<Node> node);
    public abstract ArrayList<Primitive> getLink();
    public abstract void updatePosition();
    public abstract Figures getType();
}
