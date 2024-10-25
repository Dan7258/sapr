package application;

import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class CoordinateSystem {


    private Line xAxis;
    private Line yAxis;
    private double[] xAxisPosition = new double[4];
    private double[] yAxisPosition = new double[4];
    private AnchorPane workingArea;
    private Label mouseCoordinates;
    private Label mouseCoordinates1;
    private Label scale;
    private double scaleFactor = 1.0;
    
    public CoordinateSystem(AnchorPane workingArea, Label mouseCoordinates, Label mouseCoordinates1, Label scale) {
        this.workingArea = workingArea;
        this.mouseCoordinates = mouseCoordinates;
        this.mouseCoordinates1 = mouseCoordinates1;
        this.scale = scale;
        createAxes();
        showMouseCoordinates();
        showScale();
        
    } 


    private void createAxes() {
        xAxisPosition[0] = 0;
        xAxisPosition[1] = 850;
        xAxisPosition[2] = (double)workingArea.getPrefWidth();;
        xAxisPosition[3] = 850;

        yAxisPosition[0] = 50;
        yAxisPosition[1] = 0;
        yAxisPosition[2] = 50;
        yAxisPosition[3] = (double)workingArea.getPrefHeight();

        xAxis = new Line(xAxisPosition[0], xAxisPosition[1], xAxisPosition[2], xAxisPosition[3]);
        xAxis.setStyle("-fx-stroke: black;");
        yAxis = new Line(yAxisPosition[0], yAxisPosition[1], yAxisPosition[2], yAxisPosition[3]);
        yAxis.setStyle("-fx-stroke: black;");

        workingArea.getChildren().addAll(xAxis, yAxis);
    }

    public void updateScale(double newScaleFactor) {
        this.scaleFactor = newScaleFactor;
    }
    public double getScale() {
        return this.scaleFactor;
    }

    private void showMouseCoordinates() {
        workingArea.setOnMouseMoved(e-> {
            double x = e.getX();
            double y = e.getY();
            int angle = (int)(180 * Math.atan2(-y + (xAxis.getEndY()), x - (yAxis.getEndX())) / Math.PI);
            double length = (double)Math.sqrt(Math.pow(x - (double)(yAxis.getEndX()), 2) + Math.pow(y - (double)(xAxis.getEndY()), 2));
            mouseCoordinates1.setText(String.format("length: %.2f, ∠°: %d", length / scaleFactor , angle )); 
            mouseCoordinates.setText(String.format("X: %.2f, Y: %.2f", (x - yAxis.getEndX()) / scaleFactor, (-y + xAxis.getEndY()) / scaleFactor));
        });
    } 
    private void showScale() {
        scale.setText(String.format("scale: %.2f", scaleFactor));
        workingArea.addEventHandler(ScrollEvent.SCROLL, event -> {
            scale.setText(String.format("scale: %.2f", scaleFactor));
        });
    }

    public double[] getRelativeCoordinate(double x, double y) {
        return new double[]{(x - yAxisPosition[2]) / scaleFactor,(-y + xAxisPosition[3]) / scaleFactor};
    }

    public double[] getAbsoluteCoordinate(double[] coord) {
        for(int i = 0; i < coord.length;i+=2) {
            coord[i] = coord[i] * scaleFactor + yAxisPosition[0];
            coord[i+1] = -coord[i+1] * scaleFactor + xAxisPosition[1];
        }
        return coord;
    }


    public void updatePosition() {
        xAxisPosition[1] = (double)xAxis.getStartY();
        xAxisPosition[3] = (double)xAxis.getEndY();

        yAxisPosition[0] = (double)yAxis.getStartX();
        yAxisPosition[2] = (double)yAxis.getEndX();
    }

    public void setPosition(double deltaX, double deltaY) {
        xAxis.setStartY(xAxisPosition[1] + deltaY);
        xAxis.setEndY(xAxisPosition[3] + deltaY);

        yAxis.setStartX(yAxisPosition[0] + deltaX);
        yAxis.setEndX(yAxisPosition[2] + deltaX);
    }

    public void setCoordinate(double[] newAxesCoordinate) {

        xAxis.setStartY(newAxesCoordinate[1]);
        xAxis.setEndY(newAxesCoordinate[3]);
        yAxis.setStartX(newAxesCoordinate[0]);
        yAxis.setEndX(newAxesCoordinate[2]);

        xAxisPosition[1] = newAxesCoordinate[1];
        xAxisPosition[3] = newAxesCoordinate[3];

        yAxisPosition[0] = newAxesCoordinate[0];
        yAxisPosition[2] = newAxesCoordinate[2];
    }

    public double[] getCoordinate() {
        return new double[]{yAxis.getStartX(), xAxis.getStartY(), yAxis.getEndX(), xAxis.getEndY()};
    }

    public double[] getXAxisPosition() {
        return xAxisPosition;
    }
    public double[] getYAxisPosition() {
        return yAxisPosition;
    }
}