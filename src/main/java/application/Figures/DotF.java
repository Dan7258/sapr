package application.Figures;

import java.util.ArrayList;

import application.Figures.FigureEnum.Figures;
import application.Primitives.DotP;
import application.Primitives.Primitive;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class DotF extends Figure{
    
    private Color color;
    private DotP dotP;
    private Figures type = Figures.DOTF;
    
    public DotF(int x, int y, int radius, Color color) {
        this.color = color;
        dotP = new DotP(x, y, radius, color);
    }

    @Override
    public int[] getCoordinate() {
        return dotP.getCoordinate();
    }

    @Override
    public void setCoordinate(int[] newCoordinates) {
        dotP.setCoordinate(newCoordinates);
    }

    @Override
    public void changePosition(double deltaX, double deltaY) {
        dotP.changePosition(deltaX, deltaY);  
    }

    @Override
    public void updatePosition() {
        dotP.updatePosition();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        dotP.setColor(color);
    }

    @Override
    public Figures getType() {
        return type;
    }

    public void setRadius(int radius) {
        dotP.setRadius(radius);
    }
    
    @Override
    public void setLink(ArrayList<Node> node) {
        dotP.setLink(node.get(0));
    }

    @Override
    public ArrayList<Primitive> getLink() {
        ArrayList<Primitive> list = new ArrayList<>();
        list.add(dotP);
        return list;
    }
}
