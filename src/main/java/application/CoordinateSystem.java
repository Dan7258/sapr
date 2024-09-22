package application;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class CoordinateSystem {


    private Line xAxis;
    private Line yAxis;
    private SplitPane splitArea;
    private AnchorPane workingArea;
    private Label mouseCoordinates;

    private int oldMouseCoordX;
    private int oldMouseCoordY;
    private int oldAreaPositionX;
    private int oldAreaPositionY;
    
    public CoordinateSystem(SplitPane splitArea, AnchorPane workingArea, Label mouseCoordinates) {
        this.workingArea = workingArea;
        this.mouseCoordinates = mouseCoordinates;
        this.splitArea = splitArea;
        createAxes();
        showMouseCoordinates();
        enablePanning();
    } 


    private void createAxes() {
        double width = workingArea.getPrefWidth();
        double height = workingArea.getPrefHeight();

        xAxis = new Line(0, 850, width, 850);
        xAxis.setStyle("-fx-stroke: black;");
        yAxis = new Line(50, 0, 50, height);
        yAxis.setStyle("-fx-stroke: black;");

        workingArea.getChildren().addAll(xAxis, yAxis);
    }

    private void showMouseCoordinates() {
        workingArea.setOnMouseMoved(event-> {
            int x = (int)event.getX();
            int y = (int)event.getY();
            mouseCoordinates.setText(String.format("X: %d, Y: %d", x - (int)(yAxis.getEndX()) , -y + (int)(xAxis.getEndY())));
        });
    } 

    private void enablePanning() {

        workingArea.setOnMousePressed(event -> {
            if(event.isPrimaryButtonDown() && event.isSecondaryButtonDown()) {
                oldMouseCoordX = (int)event.getSceneX();
                oldMouseCoordY = (int)event.getSceneY();
                oldAreaPositionX = (int)workingArea.getTranslateX();
                oldAreaPositionY = (int)workingArea.getTranslateY();
            }

            
        });

        workingArea.setOnMouseDragged(event -> {
            if(event.isPrimaryButtonDown() && event.isSecondaryButtonDown()) {
                double deltaX = event.getSceneX() - oldMouseCoordX;
                double deltaY = event.getSceneY() - oldMouseCoordY;
                workingArea.setTranslateX(oldAreaPositionX + deltaX);
                workingArea.setTranslateY(oldAreaPositionY + deltaY);
                
                
            }

            
        });

    }

}