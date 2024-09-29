package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DotFigure {

    private AnchorPane workingArea;
    private AnchorPane settingArea;
    private Button btnDot;
    private CoordinateSystem coordinateSystem;

    TextField textFieldX;
    TextField textFieldY;
    Label label;

    private int[][] listPosDots = new int[0][0];
    private Circle[] listDots = new Circle[0];

    
    public DotFigure(AnchorPane workingArea, AnchorPane settingArea, CoordinateSystem coordinateSystem, Button btnDot) {
        this.workingArea = workingArea;
        this.settingArea = settingArea;
        this.coordinateSystem = coordinateSystem;
        this.btnDot = btnDot;
        btnDotActive();
    }

    private void btnDotActive() {
        btnDot.setOnMouseClicked(event -> {
            btnDot.setText("выбрано");
            create();
        });
    }

    private void create() {
        workingArea.setOnMouseClicked(event -> {
            int x = (int)event.getX();
            int y = (int)event.getY();
            add(x, y);
            btnDot.setText("Точка");
            workingArea.setOnMouseClicked(null);

        });
    }

    private void add(int x, int y) {
        int[][] newListPosDots = new int[listPosDots.length + 1][2];
        for(int i = 0; i < listPosDots.length; i++) {
            newListPosDots[i][0] = listPosDots[i][0];
            newListPosDots[i][1] = listPosDots[i][1]; 
        }

        newListPosDots[newListPosDots.length - 1][0] = x;
        newListPosDots[newListPosDots.length - 1][1] = y;

        listPosDots = newListPosDots;

        Circle[] newListDots = new Circle[listDots.length + 1];
        for(int i = 0; i < listDots.length; i++) {
            newListDots[i] = listDots[i];
        }

        newListDots[newListDots.length - 1] = new Circle(listPosDots[listPosDots.length - 1][0], listPosDots[listPosDots.length - 1][1], 6, Color.BLACK);
        hoverDot(newListDots[newListDots.length - 1], newListPosDots[newListPosDots.length - 1]);

        listDots = newListDots;

        workingArea.getChildren().add(listDots[listDots.length - 1]);
        
    }

    public void updatePosition() {

        for(int i = 0; i < listPosDots.length; i++) {
            listPosDots[i][0] = (int)listDots[i].getCenterX();
            listPosDots[i][1] = (int)listDots[i].getCenterY();
        }
    }

    public void setPosition(double deltaX, double deltaY) {

        for(int i = 0; i < listPosDots.length; i++) {
            listDots[i].setCenterX(listPosDots[i][0] + deltaX);
            listDots[i].setCenterY(listPosDots[i][1] + deltaY);
        }
    }

    private void hoverDot(Circle dot, int[] coord) {
        dot.setOnMouseClicked(event -> {
            if(event.getClickCount() >= 2) {
                changeCoordinate(dot, coord);
                dot.setFill(Color.GREEN);
            }
        });
        dot.setOnMouseEntered(event -> {
            dot.setFill(Color.RED);
        });
        dot.setOnMouseExited(event -> {
            
            if (!dot.getFill().equals(Color.GREEN)) { 
                dot.setFill(Color.BLACK); 
            }
        });
    }

    private void createFormCoord(String x, String y){
        label = new Label();
        label.setLayoutX(20);
        label.setLayoutY(20);
        label.setText("Введите новые координаты:");
        textFieldX = new TextField();
        textFieldX.setPromptText("X:" + x);
        textFieldX.setLayoutX(20);
        textFieldX.setLayoutY(50);
        textFieldX.setPrefWidth(50);
        textFieldY = new TextField();
        textFieldY.setPromptText("Y:" + y);
        textFieldY.setLayoutX(80);
        textFieldY.setLayoutY(50);
        textFieldY.setPrefWidth(50);
        settingArea.getChildren().addAll(textFieldX, textFieldY, label);
    }

    private void deleteFormCoord(){
        settingArea.getChildren().removeAll(textFieldX, textFieldY, label);
    }

    private void changeCoordinate(Circle dot, int[] coord) {
        deleteFormCoord();
        createFormCoord(String.valueOf((int)dot.getCenterX() - coordinateSystem.getYAxisPosition()[2]), String.valueOf(-(int)dot.getCenterY() + coordinateSystem.getXAxisPosition()[3]));
        

        textFieldX.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                if(textFieldX.getText()!="") {
                    coord[0] = Integer.parseInt(textFieldX.getText()) + coordinateSystem.getYAxisPosition()[2]; 
                } else {
                    coord[0] = coord[0]; 
                }
                if(textFieldY.getText()!="") {
                    coord[1] = -Integer.parseInt(textFieldY.getText()) + coordinateSystem.getXAxisPosition()[3];
                } else {
                    coord[1] = coord[1];
                }

                dot.setCenterX(coord[0]);
                dot.setCenterY(coord[1]);
                deleteFormCoord();
                dot.setFill(Color.BLACK);

            }
        });

        textFieldY.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                if(textFieldX.getText()!="") {
                    coord[0] = Integer.parseInt(textFieldX.getText()) + coordinateSystem.getYAxisPosition()[0]; 
                } else {
                    coord[0] = coord[0]; 
                }
                if(textFieldY.getText()!="") {
                    coord[1] = -Integer.parseInt(textFieldY.getText()) + coordinateSystem.getXAxisPosition()[1];
                } else {
                    coord[1] = coord[1];
                }

                dot.setCenterX(coord[0]);
                dot.setCenterY(coord[1]);
                deleteFormCoord();
                dot.setFill(Color.BLACK);

            }
        });

        
    }

}
