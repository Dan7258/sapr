package application;

import application.Figures.FigureManager;
import application.Figures.Segment;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;


public class Zoom {
    
    private AnchorPane workingArea;
    private CoordinateSystem coordinateSystem;
    private FigureManager figureManager;

    public Zoom(AnchorPane workingArea, FigureManager figureManager, CoordinateSystem coordinateSystem) {
        this.workingArea = workingArea;
        this.coordinateSystem = coordinateSystem;
        this.figureManager = figureManager;

        workingArea.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            double scaleFactor = (deltaY > 0) ? 1.1 : 0.9; // Увеличиваем или уменьшаем масштаб
            //zoomEnable(segment, scaleFactor);
        });
    }

    public void zoomEnable(Segment segment, double scaleFactor) {
        int[] coordinates = segment.getCoordinate();
        int x1 = coordinates[0];
        int y1 = coordinates[1];
        int x2 = coordinates[2];
        int y2 = coordinates[3];

        // Вычисляем центр линии
        double centerX = (x1 + x2) / 2.0;
        double centerY = (y1 + y2) / 2.0;

        // Вычисляем новые координаты с учетом коэффициента масштабирования
        int newX1 = (int) ((x1 - centerX) * scaleFactor + centerX);
        int newY1 = (int) ((y1 - centerY) * scaleFactor + centerY);
        int newX2 = (int) ((x2 - centerX) * scaleFactor + centerX);
        int newY2 = (int) ((y2 - centerY) * scaleFactor + centerY);

        // Устанавливаем новые координаты
        segment.setCoordinate(new int[]{newX1, newY1, newX2, newY2});
    }

}
