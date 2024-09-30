package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DotFigure {

    private AnchorPane workingArea;
    private Button btnDot;
    private CoordinateSystem coordinateSystem;
    private Form form;


    private int[][] listPosDots = new int[0][0];
    private Circle[] listDots = new Circle[0];
    private boolean createModeEnable = false;

    
    public DotFigure(AnchorPane workingArea, CoordinateSystem coordinateSystem, Button btnDot, Form form) {
        this.workingArea = workingArea;
        this.coordinateSystem = coordinateSystem;
        this.btnDot = btnDot;
        this.form = form;
        btnDotActive();
    }

    private void createModeEnableOn() {
        createModeEnable = true;
        btnDot.setText("выбрано");
    }
    private void createModeEnableOff() {
        createModeEnable = false;
        btnDot.setText("Точка");
    }

    private void btnDotActive() {
        btnDot.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                createModeEnableOn();
                create();
            }
            
        });
    }

    private void create() {
        createModeEnableOn();
        form.deleteFormCoord();
        form.createFormCoord("0", "0", "Введите координаты: ");
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                add(x, y);
                createModeEnableOff();
                workingArea.setOnMouseClicked(null);
                form.deleteFormCoord();
            }
        });

        form.getTextFieldX().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                int[] coordinates = form.getDataFromForm();
                add(coordinates[0], coordinates[1]);
                workingArea.setOnMouseClicked(null);
                createModeEnableOff();
                form.deleteFormCoord();
            }
        });

        form.getTextFieldY().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                int[] coordinates = form.getDataFromForm();
                add(coordinates[0], coordinates[1]);
                workingArea.setOnMouseClicked(null);
                createModeEnableOff();
                form.deleteFormCoord();
            }
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
        hoverDot(newListDots[newListDots.length - 1]);

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

    private void hoverDot(Circle dot) {
        dot.setOnMouseClicked(event -> {
            if(event.getClickCount() >= 2) {
                changeCoordinate(dot);
                dot.setFill(Color.GREEN);
            }
            if(event.getButton() == MouseButton.SECONDARY && createModeEnable) {

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


    private void changeCoordinate(Circle dot) {
        form.deleteFormCoord();
        form.createFormCoord(String.valueOf((int)dot.getCenterX() - coordinateSystem.getYAxisPosition()[2]), String.valueOf(-(int)dot.getCenterY() + coordinateSystem.getXAxisPosition()[3]), "Введите новые координаты: ");
        

        form.getTextFieldX().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                int [] coord = form.getDataFromForm();

                dot.setCenterX(coord[0]);
                dot.setCenterY(coord[1]);
                form.deleteFormCoord();
                dot.setFill(Color.BLACK);

            }
        });

        form.getTextFieldY().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {

                int [] coord = form.getDataFromForm();

                dot.setCenterX(coord[0]);
                dot.setCenterY(coord[1]);
                form.deleteFormCoord();
                dot.setFill(Color.BLACK);

            }
        });
    }

}
