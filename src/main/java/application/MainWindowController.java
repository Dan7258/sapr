package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.SplitPane;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane splitArea;

    @FXML
    private Tab btn2D;

    @FXML
    private Tab btn3D;

    @FXML
    private Button btnCircle;

    @FXML
    private Button btnDot;

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
    
    CoordinateSystem coordinateSystem;
    DotFigure dotFigure;
    Panning panning;

    @FXML
    void initialize() {
        assert splitArea != null : "fx:id=\"SplitArea\" was not injected: check your FXML file 'mainWindow.fxml'.";
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

        dotFigure = new DotFigure(workingArea, btnDot);
        coordinateSystem = new CoordinateSystem(workingArea, mouseCoordinates);

        panning = new Panning(workingArea, dotFigure, coordinateSystem);
        
        
    }

}
