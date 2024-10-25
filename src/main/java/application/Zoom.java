package application;

import application.Figures.Figure;
import application.Figures.FigureManager;
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
        for(Figure figure : figureManager.getListFigures()) {
            double[] coordinates = figure.getCoordinate();
            double x1 = coordinates[0];
            double y1 = coordinates[1];
            double x2 = coordinates[2];
            double y2 = coordinates[3];
    
            // Вычисляем новые координаты с учетом коэффициента масштабирования
            double newX1 = (double) ((x1 - mouseX) * scaleFactor + mouseX);
            double newY1 = (double) ((y1 - mouseY) * scaleFactor + mouseY);
            double newX2 = (double) ((x2 - mouseX) * scaleFactor + mouseX);
            double newY2 = (double) ((y2 - mouseY) * scaleFactor + mouseY);
    
            // Устанавливаем новые координаты
            figure.setCoordinate(new double[]{newX1, newY1, newX2, newY2});
        }
    }
    

}
