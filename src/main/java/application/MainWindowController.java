package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane btnDot;

    @FXML
    private Button btnLine;

    @FXML
    void initialize() {
        assert btn2D != null : "fx:id=\"btn2D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btn3D != null : "fx:id=\"btn3D\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnDot != null : "fx:id=\"btnDot\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnLine != null : "fx:id=\"btnLine\" was not injected: check your FXML file 'mainWindow.fxml'.";

    }

}
