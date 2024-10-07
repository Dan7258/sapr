package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Form {

    private AnchorPane settingArea;

    private TextField textFieldX;
    private TextField textFieldY;
    private TextField textFieldLength;
    private TextField textFieldAngle;
    private Label label;
    private Label labelPolar;

    public Form(AnchorPane settingArea) {
        this.settingArea = settingArea;
    }

    public void createFormCoord(String x, String y, String text){
        createLabel(text);
        createTextField(x, y);
        
    }

    public void createFormCoord(String x, String y, String length, String angle, String text){
        createLabel(text);
        createTextField(x, y);
        createLabelPolar();
        createTextFieldPolar(length, angle);
        
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

    public void createLabelPolar() {
        labelPolar = new Label();
        labelPolar.setLayoutX(20);
        labelPolar.setLayoutY(80);
        labelPolar.setText("Введите полярные координаты:");
        settingArea.getChildren().add(labelPolar);
    }

    public void createTextFieldPolar(String length, String angle) {
        textFieldLength = new TextField();
        textFieldLength.setPromptText("length: " + length);
        textFieldLength.setLayoutX(20);
        textFieldLength.setLayoutY(110);
        textFieldLength.setPrefWidth(100);
        textFieldAngle = new TextField();
        textFieldAngle.setPromptText("∠°: " + angle);
        textFieldAngle.setLayoutX(20);
        textFieldAngle.setLayoutY(140);
        textFieldAngle.setPrefWidth(100);
        settingArea.getChildren().addAll(textFieldLength, textFieldAngle);
    }

    public void deleteLabel() {
        settingArea.getChildren().remove(label);
    }
    public void deleteTextField() {
        settingArea.getChildren().removeAll(textFieldX, textFieldY);
    }

    public void deleteLabelPolar() {
        settingArea.getChildren().remove(labelPolar);
    }
    public void deleteTextFieldPolar() {
        settingArea.getChildren().removeAll(textFieldLength, textFieldAngle);
    }

    public void deleteFormCoord(){
        deleteLabel();
        deleteTextField();
        deleteLabelPolar();
        deleteTextFieldPolar();
        
    }

    public int[] getDataFromForm() {
        int x;
        int y;
        int length;
        int angle;
        if(textFieldX.getText()!="") {
            x = Integer.parseInt(textFieldX.getText()); 
        } else {
            x = Integer.parseInt(textFieldX.getPromptText().split(" ")[1]); 
        }
        if(textFieldY.getText()!="") {
            y = Integer.parseInt(textFieldY.getText());
        } else {
            y = Integer.parseInt(textFieldY.getPromptText().split(" ")[1]);
        }
        if(textFieldLength.getText()!="") {
            length = Integer.parseInt(textFieldLength.getText()); 
        } else {
            length = Integer.parseInt(textFieldLength.getPromptText().split(" ")[1]); 
        }
        if(textFieldAngle.getText()!="") {
            angle = Integer.parseInt(textFieldAngle.getText()); 
        } else {
            angle = Integer.parseInt(textFieldAngle.getPromptText().split(" ")[1]); 
        }
        return new int[] {x, y, length, angle};
    }

    public TextField getTextFieldX() {
        return textFieldX;
    }
    public TextField getTextFieldY() {
        return textFieldY;
    }
    
}
