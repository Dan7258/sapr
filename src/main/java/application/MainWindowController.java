package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab btn2D;

    @FXML
    private Tab btn3D;

    @FXML
    private Button btnCircle;

    @FXML
    private AnchorPane btnDot;

    @FXML
    private Button btnLine;

    @FXML
    private Tab btnProperties;

    @FXML
    private Button btnRectangle;

    @FXML
    private AnchorPane settingArea;

    @FXML
    private Slider slider;

    @FXML
    private AnchorPane workingArea;

    @FXML
    private Label mouseCoordinates;


    private double mouseX;
    private double mouseY;
    private double translateX;
    private double translateY;
    private Line xAxis;
    private Line yAxis;


    @FXML
    void initialize() {
        assert btn2D != null : "fx:id=\"btn2D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btn3D != null : "fx:id=\"btn3D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnCircle != null : "fx:id=\"btnCircle\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnDot != null : "fx:id=\"btnDot\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnLine != null : "fx:id=\"btnLine\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnProperties != null : "fx:id=\"btnProperties\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnRectangle != null : "fx:id=\"btnRectangle\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert settingArea != null : "fx:id=\"settingArea\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert mouseCoordinates != null : "fx:id=\"mouseCoordinates\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert slider != null : "fx:id=\"slider\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert workingArea != null : "fx:id=\"workingArea\" was not injected: check your FXML file 'mainWindow.fxml'.";

    
        createAxes();
        enablePanning();
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

    private void enablePanning() {
        workingArea.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            translateX = workingArea.getTranslateX();
            translateY = workingArea.getTranslateY();
        });

        workingArea.setOnMouseDragged(event -> {
            double offsetX = event.getSceneX() - mouseX;
            double offsetY = event.getSceneY() - mouseY;
            workingArea.setTranslateX(translateX + offsetX);
            workingArea.setTranslateY(translateY + offsetY);
        });
    }

    private void showMouseCoordinates() {
        workingArea.setOnMouseMoved(event-> {
            int x = (int)event.getX();
            int y = (int)event.getY();
            mouseCoordinates.setText(String.format("X: %d, Y: %d", x - (int)(yAxis.getEndX()) , -y + (int)(xAxis.getEndY())));
    });
    }   

}
