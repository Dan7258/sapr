package application;

import application.Figures.FigureManager;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Panning {


    private AnchorPane workingArea;


    private FigureManager figureManager;
    private CoordinateSystem coordinateSystem;

    private double oldMouseCoordX;
    private double oldMouseCoordY;

    public Panning(AnchorPane workingArea, FigureManager figureManager, CoordinateSystem coordinateSystem) {
        this.workingArea = workingArea;
        this.figureManager = figureManager;
        this.coordinateSystem = coordinateSystem;
        enablePanning();
    }

    private void enablePanning() {
        workingArea.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            oldMouseCoordX = (double)event.getX();
            oldMouseCoordY = (double)event.getY();
            coordinateSystem.updatePosition();
            figureManager.updatePosition();
            
        });
    
        workingArea.setOnMouseDragged(event -> {
            if(event.isMiddleButtonDown() ) {
                double deltaX = event.getX() - oldMouseCoordX;
                double deltaY = event.getY() - oldMouseCoordY;

                coordinateSystem.setPosition(deltaX, deltaY);
                figureManager.changePosition(deltaX, deltaY);
            }            
        });
    }
    
}
