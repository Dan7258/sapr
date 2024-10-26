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
        label = new Label(text);
        label.setLayoutX(20);
        label.setLayoutY(20);
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
        textFieldLength.setPromptText("length(radius): " + length);
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

    public double[] getDataFromForm() {
        double x = 0;
        double y = 0;
        double length = 0;
        int angle = 0;
        if(textFieldX.getText()!="") {
            x = Double.parseDouble(textFieldX.getText()); 
        } else {
            x = Double.parseDouble(textFieldX.getPromptText().split(" ")[1]); 
        }
        if(textFieldY.getText()!="") {
            y = Double.parseDouble(textFieldY.getText());
        } else {
            y = Double.parseDouble(textFieldY.getPromptText().split(" ")[1]);
        }
        if(textFieldLength != null && textFieldLength.getText()!="") {
            length = Double.parseDouble(textFieldLength.getText()); 
        } else if(textFieldLength != null && textFieldLength.getText()==""){
            length = Double.parseDouble(textFieldLength.getPromptText().split(" ")[1]); 
        }
        if(textFieldAngle != null && textFieldAngle.getText()!="") {
            angle = Integer.parseInt(textFieldAngle.getText()); 
        } else if(textFieldAngle != null && textFieldAngle.getText()==""){
            angle = Integer.parseInt(textFieldAngle.getPromptText().split(" ")[1]); 
        }
        return new double[] {x, y, length, angle};
    }


    
}
