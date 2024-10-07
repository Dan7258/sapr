package application;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class CoordinateSystem {


    private Line xAxis;
    private Line yAxis;
    private int[] xAxisPosition = new int[4];
    private int[] yAxisPosition = new int[4];
    private AnchorPane workingArea;
    private Label mouseCoordinates;
    private Label mouseCoordinates1;
    
    public CoordinateSystem(AnchorPane workingArea, Label mouseCoordinates, Label mouseCoordinates1) {
        this.workingArea = workingArea;
        this.mouseCoordinates = mouseCoordinates;
        this.mouseCoordinates1 = mouseCoordinates1;
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
        workingArea.setOnMouseMoved(e-> {
            int x = (int)e.getX();
            int y = (int)e.getY();
            int angle = (int)(180 * Math.atan2(-y + (int)(xAxis.getEndY()), x - (int)(yAxis.getEndX())) / Math.PI);
            int length = (int)Math.sqrt(Math.pow(x - (int)(yAxis.getEndX()), 2) + Math.pow(y - (int)(xAxis.getEndY()), 2));
            mouseCoordinates1.setText(String.format("length: %d, ∠°: %d", length , angle )); 
            mouseCoordinates.setText(String.format("X: %d, Y: %d", x - (int)(yAxis.getEndX()) , -y + (int)(xAxis.getEndY())));
           
            
        });
    } 

    public int[] getMouseCoordinate(MouseEvent event) {
        return new int[]{(int)event.getX() - yAxisPosition[2],-(int)event.getY() + xAxisPosition[3]};
    }

    public int[] getFormatedCoordinate(int[] coord) {
        for(int i = 0; i < coord.length;i+=2) {
            coord[i] = coord[i] + yAxisPosition[0];
            coord[i+1] = -coord[i+1] + xAxisPosition[1];
        }
        return coord;
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

    public int[] getXAxisPosition() {
        return xAxisPosition;
    }
    public int[] getYAxisPosition() {
        return yAxisPosition;
    }
}