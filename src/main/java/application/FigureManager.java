package application;

import java.util.ArrayList;
import application.FigureEnum.Figures;
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
                figureRender.render(dot);
                break;
            case LINE:

                break;
        }
    }

    public void changeCoordinate() {

    }

    public void deleteFigure() {

    }
}
