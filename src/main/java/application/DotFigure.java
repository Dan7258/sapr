package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DotFigure {

    private AnchorPane workingArea;
    private Button btnDot;
    private CoordinateSystem coordinateSystem;
    private Form form;



    private ArrayList<int[]> listPosDots = new ArrayList<>();
    private ArrayList<Circle> listDots = new ArrayList<>();

    private boolean isRightMousePressed = false;
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
        listPosDots.add(new int[]{x, y});

        listDots.add(new Circle(x, y, 6, Color.BLACK));

        workingArea.getChildren().add(listDots.get(listDots.size() - 1));

        hoverDot(listDots.get(listDots.size() - 1), listPosDots.get(listPosDots.size() - 1));
        
    }

    private void hoverDot(Circle dot, int[] posDot) {
        dot.setOnMouseClicked(event -> {
            if(event.getClickCount() >= 2 && event.getButton() == MouseButton.PRIMARY) {
                dot.setFill(Color.GREEN);
                changeCoordinate(dot);
                
            }
            if(event.getButton() == MouseButton.PRIMARY) {
                dot.setFill(Color.BLUE);
                isRightMousePressed = true;
                checkDeleteButton(dot, posDot);
            }
            if(event.getButton() == MouseButton.SECONDARY && createModeEnable) {

            }
        });
        dot.setOnMouseEntered(event -> {
            dot.setFill(Color.RED);
        });
        dot.setOnMouseExited(event -> {
            
            if (!dot.getFill().equals(Color.GREEN) && !dot.getFill().equals(Color.BLUE)) { 
                dot.setFill(Color.BLACK); 
            }
        });
    }

    private void checkDeleteButton(Circle dot, int[] posDot) {
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.DELETE || !isRightMousePressed) {
                isRightMousePressed = false;
                dot.setFill(Color.BLACK); 
                
            } else {
                deleteDot(dot, posDot);
                isRightMousePressed = false;
            }
        });
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.SECONDARY) {
                isRightMousePressed = false;
                dot.setFill(Color.BLACK); 
                
            }
        });
    } 

    private void deleteDot(Circle dot, int[] posDot) {
        
        listDots.remove(dot);
        listPosDots.remove(posDot);
        workingArea.getChildren().remove(dot);
    }
    

    public void updatePosition() {

        for(int i = 0; i < listPosDots.size(); i++) {
            listPosDots.get(i)[0] = (int)listDots.get(i).getCenterX();
            listPosDots.get(i)[1] = (int)listDots.get(i).getCenterY();
        }
    }

    public void setPosition(double deltaX, double deltaY) {

        for(int i = 0; i < listPosDots.size(); i++) {
            listDots.get(i).setCenterX(listPosDots.get(i)[0] + deltaX);
            listDots.get(i).setCenterY(listPosDots.get(i)[1] + deltaY);
        }
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
