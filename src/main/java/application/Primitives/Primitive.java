package application.Primitives;

import application.Primitives.PrimitiveEnum.Primitives;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public abstract class Primitive {
    public abstract int[] getCoordinate();
    public abstract void setCoordinate(int[] newCoordinates);
    public abstract void changePosition(double deltaX, double deltaY);
    public abstract Color getColor();
    public abstract void setColor(Color color);
    public abstract void setLink(Node node);
    public abstract Node getLink();
    public abstract void updatePosition();
    public abstract Primitives getType();
}
