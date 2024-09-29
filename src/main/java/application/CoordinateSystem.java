package application;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class CoordinateSystem {


    private Line xAxis;
    private Line yAxis;
    private int[] xAxisPosition = new int[4];
    private int[] yAxisPosition = new int[4];
    private AnchorPane workingArea;
    private Label mouseCoordinates;
    
    public CoordinateSystem(AnchorPane workingArea, Label mouseCoordinates) {
        this.workingArea = workingArea;
        this.mouseCoordinates = mouseCoordinates;
        createAxes();
        showMouseCoordinates();
        
    } 


    private void createAxes() {
        xAxisPosition[0] = 0;
        xAxisPosition[1] = 850;
        xAxisPosition[2] = (int)workingArea.getPrefWidth();;
        xAxisPosition[3] = 850;

        yAxisPosition[0] = 50;
        yAxisPosition[1] = 0;
        yAxisPosition[2] = 50;
        yAxisPosition[3] = (int)workingArea.getPrefHeight();


        xAxis = new Line(xAxisPosition[0], xAxisPosition[1], xAxisPosition[2], xAxisPosition[3]);
        xAxis.setStyle("-fx-stroke: black;");
        yAxis = new Line(yAxisPosition[0], yAxisPosition[1], yAxisPosition[2], yAxisPosition[3]);
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


    public void updatePosition() {
        xAxisPosition[1] = (int)xAxis.getStartY();
        xAxisPosition[2] = (int)xAxis.getEndX();
        xAxisPosition[3] = (int)xAxis.getEndY();

        yAxisPosition[0] = (int)yAxis.getStartX();
        yAxisPosition[2] = (int)yAxis.getEndX();
        yAxisPosition[3] = (int)yAxis.getEndY();
    }

    public void setPosition(double deltaX, double deltaY) {
        xAxis.setStartY(xAxisPosition[1] + deltaY);
        xAxis.setEndY(xAxisPosition[3] + deltaY);

        yAxis.setStartX(yAxisPosition[0] + deltaX);
        yAxis.setEndX(yAxisPosition[2] + deltaX);
    }
}