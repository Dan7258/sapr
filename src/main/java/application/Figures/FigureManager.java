package application.Figures;

import java.util.ArrayList;

import application.Primitives.DotP;
import application.Primitives.LineP;
import application.Figures.FigureEnum.Figures;
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
            case DOTF:
                DotF dot = new DotF(coord[0], coord[1], 6, Color.BLACK);
                listFigures.add(dot);
                figureRender.render(dot);
                break;
            case SEGMENT:
                Segment lineF = new Segment(coord[0], coord[1], coord[2], coord[3], 6, 1, Color.BLACK);
                listFigures.add(lineF);
                figureRender.render(lineF);
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
