package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.Figures.FigureManager;
import application.Figures.FigureRender;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private AnchorPane area2d;

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

    @FXML
    private Label mouseCoordinates1;
    
    CoordinateSystem coordinateSystem;
    Panning panning;
    Form form;
    Scene scene;
    Handler handler;
    FigureRender figureRender;
    FigureManager figureManager;
    Zoom zoom;

    

    @FXML
    void initialize() {
        assert area2d != null : "fx:id=\"area2d\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btn2D != null : "fx:id=\"btn2D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btn3D != null : "fx:id=\"btn3D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnCircle != null : "fx:id=\"btnCircle\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnDot != null : "fx:id=\"btnDot\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnLine != null : "fx:id=\"btnLine\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnProperties != null : "fx:id=\"btnProperties\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnRectangle != null : "fx:id=\"btnRectangle\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert mouseCoordinates != null : "fx:id=\"mouseCoordinates\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert mouseCoordinates1 != null : "fx:id=\"mouseCoordinates1\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert settingArea != null : "fx:id=\"settingArea\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert splitArea != null : "fx:id=\"splitArea\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert workingArea != null : "fx:id=\"workingArea\" was not injected: check your FXML file 'mainWindow.fxml'.";

        coordinateSystem = new CoordinateSystem(workingArea, mouseCoordinates, mouseCoordinates1);
        figureRender = new FigureRender(workingArea, settingArea);
        figureManager = new FigureManager(workingArea, figureRender, coordinateSystem);
        form = new Form(settingArea);
        handler = new Handler(area2d, workingArea, figureManager, coordinateSystem, form);
        panning = new Panning(workingArea, figureManager, coordinateSystem);
        zoom = new Zoom(workingArea, coordinateSystem);
        
    }
    

}
