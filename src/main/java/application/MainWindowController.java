package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.Figures.FigureManager;
import application.Figures.FigureRender;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.MenuItem;
import java.io.Serializable;


public class MainWindowController implements Serializable{
    private static final long serialVersionUID = 1L;

    @FXML
    private transient ResourceBundle resources;

    @FXML
    private transient URL location;

    @FXML
    private transient AnchorPane area2d;

    @FXML
    private transient SplitPane splitArea;

    @FXML
    private transient Tab btn2D;

    @FXML
    private transient Tab btn3D;

    @FXML
    private transient Button btnCircle;

    @FXML
    private transient Button btnDot;

    @FXML
    private transient Button btnLine;

    @FXML
    private transient Button btnPolyline;

    @FXML
    private transient Tab btnProperties;

    @FXML
    private transient Button btnRectangle;

    @FXML
    private transient MenuItem closeFile;

    @FXML
    private transient AnchorPane settingArea;

    @FXML
    private transient Slider slider;

    @FXML
    private transient AnchorPane workingArea;

    @FXML
    private transient Label mouseCoordinates;

    @FXML
    private transient Label mouseCoordinates1;

    @FXML
    private transient MenuItem openFile;

    @FXML
    private transient MenuItem quit;

    @FXML
    private transient MenuItem saveFile;

    @FXML
    private transient Label scale;
    
    CoordinateSystem coordinateSystem;
    Panning panning;
    Form form;
    Scene scene;
    Handler handler;
    FigureRender figureRender;
    FigureManager figureManager;
    Zoom zoom;
    SaveProject saveProject;
    LoadProject loadProject;


    @FXML
    void initialize() {
        assert area2d != null : "fx:id=\"area2d\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btn2D != null : "fx:id=\"btn2D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btn3D != null : "fx:id=\"btn3D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnCircle != null : "fx:id=\"btnCircle\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnDot != null : "fx:id=\"btnDot\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnLine != null : "fx:id=\"btnLine\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnPolyline != null : "fx:id=\"btnPolyline\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnProperties != null : "fx:id=\"btnProperties\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnRectangle != null : "fx:id=\"btnRectangle\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert closeFile != null : "fx:id=\"closeFile\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert mouseCoordinates != null : "fx:id=\"mouseCoordinates\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert mouseCoordinates1 != null : "fx:id=\"mouseCoordinates1\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert openFile != null : "fx:id=\"openFile\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert quit != null : "fx:id=\"quit\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert saveFile != null : "fx:id=\"saveFile\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert scale != null : "fx:id=\"scale\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert settingArea != null : "fx:id=\"settingArea\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert splitArea != null : "fx:id=\"splitArea\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert workingArea != null : "fx:id=\"workingArea\" was not injected: check your FXML file 'mainWindow.fxml'.";

        coordinateSystem = new CoordinateSystem(workingArea, mouseCoordinates, mouseCoordinates1, scale);
        figureRender = new FigureRender(workingArea, settingArea);
        figureManager = new FigureManager(workingArea, figureRender, coordinateSystem);
        form = new Form(settingArea);
        zoom = new Zoom(workingArea, figureManager, coordinateSystem);
        handler = new Handler(area2d, workingArea, settingArea, figureManager, coordinateSystem, form);
        panning = new Panning(workingArea, figureManager, coordinateSystem);
        saveProject = new SaveProject(figureManager, quit, saveFile, closeFile);
        loadProject = new LoadProject(figureManager, openFile);
    }
    

}
