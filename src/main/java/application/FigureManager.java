package application;

import java.util.ArrayList;
import application.FigureEnum.Figures;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public class FigureManager {
    private FigureRender figureRender;

    private ArrayList<Figure> listFigures = new ArrayList<>();

    public FigureManager(FigureRender figureRender) {
        this.figureRender = figureRender;
    }

    public void createFigure(int[] coord, Figures type) {
        switch (type) {
            case DOT:
                Dot dot = new Dot(coord[0], coord[1], 6, Color.BLACK);
                listFigures.add(dot);
                Node node = figureRender.render(dot);
                dot.setLink(node);
                break;
            case LINE:

                break;
        }
    }

    public void changeColor(Node node, Color color) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figure.setColor(color);
        }
        
    }

    public void changePosition(double deltaX, double deltaY) {
        for (Figure figure : listFigures) {
            figure.changePosition(deltaX, deltaY);
        }
    }

    public void updatePosition() {
        for (Figure figure : listFigures) {
            figure.updatePosition();
        }
    }

    public void deleteFigure(Node node) {
        
        Figure figure = searchFigure(node);
        if(figure != null) {
            figureRender.erase(figure);
            listFigures.remove(figure);
        
            System.out.println(listFigures);
        }
        
    }

    private Figure searchFigure(Node node) {
        for(Figure figure : listFigures) {
            if(figure.getLink() == node) {
                return figure;
            }
        }
        return null;
    }
}
