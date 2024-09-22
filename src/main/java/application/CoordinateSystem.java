package application;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class CoordinateSystem {


    private Line xAxis;
    private Line yAxis;
    private AnchorPane workingArea;
    private Label mouseCoordinates;
    
    public CoordinateSystem(AnchorPane workingArea, Label mouseCoordinates) {
        this.workingArea = workingArea;
        this.mouseCoordinates = mouseCoordinates;
        createAxes();
        showMouseCoordinates();
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



}