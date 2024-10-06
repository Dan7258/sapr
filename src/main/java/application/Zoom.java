package application;

import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;


public class Zoom {
    
    private AnchorPane workingArea;
    private CoordinateSystem coordinateSystem;

    public Zoom(AnchorPane workingArea, CoordinateSystem coordinateSystem) {
        this.workingArea = workingArea;
        this.coordinateSystem = coordinateSystem;
    }

}
