package application.Figures;

import java.util.ArrayList;
import java.util.Arrays;

import application.Primitives.Primitive;
import application.CoordinateSystem;
import application.Figures.FigureEnum.Figures;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;


public class FigureManager {
    private AnchorPane workingArea;
    private FigureRender figureRender;
    private CoordinateSystem coordinateSystem;

    private ArrayList<Figure> listFigures = new ArrayList<>();
    private ArrayList<PreparingData> preparingData = new ArrayList<>();

    public FigureManager(AnchorPane workingArea, FigureRender figureRender, CoordinateSystem coordinateSystem) {
        this.workingArea = workingArea;
        this.figureRender = figureRender;
        this.coordinateSystem = coordinateSystem;
    }

    public ArrayList<Figure> getListFigures() {
        return listFigures;
    }

    public ArrayList<PreparingData> getPreparingData() {
        preparingData.clear();
        for(Figure figure : listFigures) {
            preparingData.add(figure.preparingData());
        }
        return preparingData;
    }

    public void setListFigures(ArrayList<PreparingData> listData) {
        figureRender.eraseAll(this.listFigures);
        this.listFigures.clear();
        double[] absoluteCoord;
        for(PreparingData data : listData) {
            switch (data.getType()) {
                case DOTF:
                    absoluteCoord = coordinateSystem.getAbsoluteCoordinate(data.getCoordinate());
                    DotF dot = new DotF(absoluteCoord[0], absoluteCoord[1], data.getRadius(), data.getColor(), coordinateSystem);
                    listFigures.add(dot);
                    figureRender.render(dot);
                    break;
                case SEGMENT:
                    absoluteCoord = coordinateSystem.getAbsoluteCoordinate(data.getCoordinate());
                    Segment segment = new Segment(absoluteCoord[0], absoluteCoord[1], absoluteCoord[2], absoluteCoord[3], data.getRadius(), data.getWidth(), data.getColor(), coordinateSystem);
                    segment.setTypeLine(data.getTypeLine());
                    listFigures.add(segment);
                    figureRender.render(segment);
                    break;
                case RECTANGLE:
                    absoluteCoord = coordinateSystem.getAbsoluteCoordinate(data.getCoordinate());
                    Rectangle rectangle = new Rectangle(absoluteCoord[0], absoluteCoord[1], absoluteCoord[2], absoluteCoord[3], data.getRadius(), data.getWidth(), data.getColor(), coordinateSystem);
                    rectangle.setTypeLine(data.getTypeLine());
                    listFigures.add(rectangle);
                    figureRender.render(rectangle);
                    break;
                case RING:
                    absoluteCoord = coordinateSystem.getAbsoluteCoordinate(data.getCoordinate());
                    Ring ring = new Ring(absoluteCoord[0], absoluteCoord[1], absoluteCoord[2], absoluteCoord[3], data.getRadius(), data.getWidth(), data.getColor(), coordinateSystem);
                    ring.setTypeLine(data.getTypeLine());
                    listFigures.add(ring);
                    figureRender.render(ring);
                    break;
                case POLYLINEF:
                    absoluteCoord = coordinateSystem.getAbsoluteCoordinate(data.getCoordinate());
                    PolylineF polylineF = new PolylineF(absoluteCoord[0], absoluteCoord[1], absoluteCoord[2], absoluteCoord[3], data.getWidth(), data.getColor(), coordinateSystem);
                    polylineF.addMultiplePoints(Arrays.copyOfRange(absoluteCoord, 4, absoluteCoord.length));
                    polylineF.setTypeLine(data.getTypeLine());
                    listFigures.add(polylineF);
                    figureRender.render(polylineF);
                    break;
                
            }
        }
    }



    public void createFigure(double[] coord, Figures type, boolean addPoint) {
        switch (type) {
            case DOTF:
                DotF dot = new DotF(coord[0], coord[1], 6, Color.BLACK, coordinateSystem);
                listFigures.add(dot);
                figureRender.render(dot);
                break;
            case SEGMENT:
                Segment segment = new Segment(coord[0], coord[1], coord[2], coord[3], 6, 2, Color.BLACK, coordinateSystem);
                listFigures.add(segment);
                figureRender.render(segment);
                break;
            case RECTANGLE:
                Rectangle rectangle = new Rectangle(coord[0], coord[1], coord[2], coord[3], 6, 2, Color.BLACK, coordinateSystem);
                listFigures.add(rectangle);
                figureRender.render(rectangle);
                break;
            case RING:
                Ring ring = new Ring(coord[0], coord[1], coord[2], coord[3], 6, 2, Color.BLACK, coordinateSystem);
                listFigures.add(ring);
                figureRender.render(ring);
                break;
            case POLYLINEF:
                if(addPoint) {
                    ((PolylineF)listFigures.get(listFigures.size()-1)).addMultiplePoints(new double[]{coord[coord.length-2], coord[coord.length-1]});
                    break;
                }
                PolylineF polylineF = new PolylineF(coord[0], coord[1], coord[2], coord[3], 2, Color.BLACK, coordinateSystem);
                listFigures.add(polylineF);
                figureRender.render(polylineF);
                break;
        }
    }

    public void changeColor(Node node, Color color) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            for(Primitive primitive : figure.getLink()) {
                if(primitive.getLink() instanceof Circle) {
                    ((Circle)primitive.getLink()).setFill(color);
                }
                if(primitive.getLink() instanceof Line || primitive.getLink() instanceof Polyline) {
                    ((Shape)primitive.getLink()).setStroke(color);
                }
            }
        }  
    }

    public void setDefaultColor(Node node) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figure.setColor(figure.getColor());
        }  
    }

    public void manageSettings(Node node) {
        openSettings(node);
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                updateParamFigure(node);
                closeSettings(node);
                workingArea.getScene().setOnKeyPressed(null);
            } 
            if (event.getCode() == KeyCode.ESCAPE) {
                closeSettings(node);
                setDefaultColor(node);
                workingArea.getScene().setOnKeyPressed(null);
            } 
        });
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() != MouseButton.MIDDLE) {
                closeSettings(node);
                setDefaultColor(node);
                workingArea.getScene().setOnKeyPressed(null);
            }
        });
    }

    public void openSettings(Node node) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figure.setSettings();
            figureRender.renderSettings(figure);
        }
    }
    public void closeSettings(Node node) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figureRender.deleteSettings(figure);
        }
    }

    public void updateParamFigure(Node node) {
        Figure figure = searchFigure(node);
        if(figure != null) {
            figure.takeParamFromSettings();
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
