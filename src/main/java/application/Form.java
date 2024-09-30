package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Form {

    private AnchorPane settingArea;
    private CoordinateSystem coordinateSystem;

    private TextField textFieldX;
    private TextField textFieldY;
    private Label label;

    public Form(AnchorPane settingArea, CoordinateSystem coordinateSystem) {
        this.settingArea = settingArea;
        this.coordinateSystem = coordinateSystem;
    }

    public void createFormCoord(String x, String y, String text){
        createLabel(text);
        createTextField(x, y);
        
    }
    public void createLabel(String text) {
        label = new Label();
        label.setLayoutX(20);
        label.setLayoutY(20);
        label.setText(text);
        settingArea.getChildren().add(label);
    }
    public void createTextField(String x, String y) {
        textFieldX = new TextField();
        textFieldX.setPromptText("X: " + x);
        textFieldX.setLayoutX(20);
        textFieldX.setLayoutY(50);
        textFieldX.setPrefWidth(50);
        textFieldY = new TextField();
        textFieldY.setPromptText("Y: " + y);
        textFieldY.setLayoutX(80);
        textFieldY.setLayoutY(50);
        textFieldY.setPrefWidth(50);
        settingArea.getChildren().addAll(textFieldX, textFieldY);
    }

    public void deleteLabel() {
        settingArea.getChildren().remove(label);
    }
    public void deleteTextField() {
        settingArea.getChildren().removeAll(textFieldX, textFieldY);
    }

    public void deleteFormCoord(){
        deleteLabel();
        deleteTextField();
    }

    public int[] getDataFromForm() {
        int x;
        int y;
        if(textFieldX.getText()!="") {
            x = Integer.parseInt(textFieldX.getText()) + coordinateSystem.getYAxisPosition()[0]; 
        } else {
            x = Integer.parseInt(textFieldX.getPromptText().split(" ")[1]) + coordinateSystem.getYAxisPosition()[0]; 
        }
        if(textFieldY.getText()!="") {
            y = -Integer.parseInt(textFieldY.getText()) + coordinateSystem.getXAxisPosition()[1];
        } else {
            y = -Integer.parseInt(textFieldY.getPromptText().split(" ")[1]) + coordinateSystem.getXAxisPosition()[1];
        }
        return new int[] {x, y};
    }

    public TextField getTextFieldX() {
        return textFieldX;
    }
    public TextField getTextFieldY() {
        return textFieldY;
    }
    
}
