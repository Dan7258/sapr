package application.Figures;

import java.util.ArrayList;
import application.Primitives.Primitive;
import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class FigureManager {
    private AnchorPane workingArea;
    private FigureRender figureRender;
    private CoordinateSystem coordinateSystem;

    private ArrayList<Figure> listFigures = new ArrayList<>();

    public FigureManager(AnchorPane workingArea, FigureRender figureRender, CoordinateSystem coordinateSystem) {
        this.workingArea = workingArea;
        this.figureRender = figureRender;
        this.coordinateSystem = coordinateSystem;
    }

    public void createFigure(int[] coord, Figures type) {
        switch (type) {
            case DOTF:
                DotF dot = new DotF(coord[0], coord[1], 6, Color.BLACK, coordinateSystem);
                listFigures.add(dot);
                figureRender.render(dot);
                break;
            case SEGMENT:
                Segment lineF = new Segment(coord[0], coord[1], coord[2], coord[3], 6, 2, Color.BLACK);
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

    public void manageSettings(Node node) {
        openSettings(node);
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                closeSettings(node);
                workingArea.getScene().setOnKeyPressed(null);
            } 
        });
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            closeSettings(node);
            workingArea.getScene().setOnKeyPressed(null);
        });
    }

    public void openSettings(Node node) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figureRender.renderSettings(figure);
        }
         
    }
    public void closeSettings(Node node) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figureRender.deleteSettings(figure);
            changeColor(node, Color.BLACK);
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
            for(Primitive primitive: figure.getLink())
            if(primitive.getLink() == node) {
                return figure;
            }
        }
        return null;
    }
}
