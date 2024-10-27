package application;

import application.Figures.Figure;
import application.Figures.FigureManager;
import application.Figures.Ring;
import javafx.scene.layout.AnchorPane;


public class Zoom {
    
    private AnchorPane workingArea;
    private CoordinateSystem coordinateSystem;
    private FigureManager figureManager;
    private double scaleFactor = 1;

    public Zoom(AnchorPane workingArea, FigureManager figureManager, CoordinateSystem coordinateSystem) {
        this.workingArea = workingArea;
        this.coordinateSystem = coordinateSystem;
        this.figureManager = figureManager;

        workingArea.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            scaleFactor = (deltaY > 0) ? 1.1 : 0.9; // Увеличиваем или уменьшаем масштаб
            double mouseX = event.getX();
            double mouseY = event.getY();
            zoomEnable(scaleFactor, mouseX, mouseY);
        });
    }

    public void zoomEnable(double scaleFactor, double mouseX, double mouseY) {
        if((coordinateSystem.getScale() > 5 && scaleFactor != 0.9) || (coordinateSystem.getScale() < 0.03 && scaleFactor != 1.1)) {
            return;
        }
        coordinateSystem.updateScale(coordinateSystem.getScale()*scaleFactor);
        double[] axesCoordinates = coordinateSystem.getCoordinate();
        double[] newAxesCoordinates = new double[axesCoordinates.length];
        for(int i = 0; i < axesCoordinates.length; i+=2) {
            newAxesCoordinates[i] = (axesCoordinates[i] - mouseX) * scaleFactor + mouseX;
            newAxesCoordinates[i+1] = (axesCoordinates[i+1] - mouseY) * scaleFactor + mouseY;
        }
        coordinateSystem.setCoordinate(newAxesCoordinates);
        for(Figure figure : figureManager.getListFigures()) {
            double[] coordinates = figure.getCoordinate();
            double[] newCoordinates = new double[coordinates.length];
            for(int i = 0; i < coordinates.length; i+=2) {
                newCoordinates[i] = (coordinates[i] - mouseX) * scaleFactor + mouseX;
                newCoordinates[i+1] = (coordinates[i+1] - mouseY) * scaleFactor + mouseY;
            }
            figure.setCoordinate(newCoordinates);
        }
        
    }
    

}
