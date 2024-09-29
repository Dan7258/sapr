package application;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class CoordinateSystem {


    private Line xAxis;
    private Line yAxis;
    private double[] xAxisPosition = new double[4];
    private double[] yAxisPosition = new double[4];
    private AnchorPane workingArea;
    private Label mouseCoordinates;

    private int oldMouseCoordX;
    private int oldMouseCoordY;
    
    public CoordinateSystem(SplitPane splitArea, AnchorPane workingArea, Label mouseCoordinates) {
        this.workingArea = workingArea;
        this.mouseCoordinates = mouseCoordinates;
        createAxes();
        showMouseCoordinates();
        enablePanning();
        
    } 


    private void createAxes() {
        xAxisPosition[0] = 0;
        xAxisPosition[1] = 850;
        xAxisPosition[2] = workingArea.getPrefWidth();;
        xAxisPosition[3] = 850;

        yAxisPosition[0] = 50;
        yAxisPosition[1] = 0;
        yAxisPosition[2] = 50;
        yAxisPosition[3] = workingArea.getPrefHeight();


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

    private void enablePanning() {
        workingArea.setOnMousePressed(event -> {
            if(event.isPrimaryButtonDown() && event.isSecondaryButtonDown()) {
                oldMouseCoordX = (int)event.getSceneX();
                oldMouseCoordY = (int)event.getSceneY();
                
                xAxisPosition[1] = xAxis.getStartY();
                xAxisPosition[2] = xAxis.getEndX();
                xAxisPosition[3] = xAxis.getEndY();

                yAxisPosition[0] = yAxis.getStartX();
                yAxisPosition[2] = yAxis.getEndX();
                yAxisPosition[3] = yAxis.getEndY();
            }
        });
    
        workingArea.setOnMouseDragged(event -> {
            if(event.isPrimaryButtonDown() && event.isSecondaryButtonDown()) {
                double deltaX = event.getSceneX() - oldMouseCoordX;
                double deltaY = event.getSceneY() - oldMouseCoordY;

                xAxis.setStartY(xAxisPosition[1] + deltaY);
                xAxis.setEndY(xAxisPosition[3] + deltaY);

                yAxis.setStartX(yAxisPosition[0] + deltaX);
                yAxis.setEndX(yAxisPosition[2] + deltaX);
                
            }            
        });
    }

    public double[] getXAxesCoord() {
        return xAxisPosition;
    }

    public double[] getYAxesCoord() {
        return yAxisPosition;
    }
}