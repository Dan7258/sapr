package application;

import javafx.scene.Node;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class LineFigure {

    private AnchorPane workingArea;
    private Button btnLine;
    private CoordinateSystem coordinateSystem;
    private Form form;

    private ArrayList<int[]> listPosLines = new ArrayList<>();
    private ArrayList<Node[]> listLines = new ArrayList<>();

    private boolean isRightMousePressed = false;
    private boolean createModeEnable = false;
    private boolean coordReceived = false;

    public LineFigure(AnchorPane workingArea, CoordinateSystem coordinateSystem, Button btnLine, Form form) {
        this.workingArea = workingArea;
        this.coordinateSystem = coordinateSystem;
        this.btnLine = btnLine;
        this.form = form;
        btnLineActive();
    }

    private void createModeEnableOn() {
        createModeEnable = true;
        btnLine.setText("выбрано");
    }
    private void createModeEnableOff() {
        createModeEnable = false;
        btnLine.setText("Линия");
    }

    private void btnLineActive() {
        btnLine.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                createModeEnableOn();
                create();
            }
        });
    }

    private void create() {
        int[] lineCoord = new int[4];
        for(int i = 0; i < 4; i+=2) {
            createModeEnableOn();
            form.deleteFormCoord();
            if(i == 0) {
                form.createFormCoord("0", "0", "Введите координаты первой границы: ");
            } else {
                form.createFormCoord("0", "0", "Введите координаты второй границы: ");
            }
            
            int[] coordinates = new int[2];
            workingArea.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    coordinates[0] = (int) event.getX();
                    coordinates[1] = (int) event.getY();
                    coordReceived = true;
                    createModeEnableOff();
                    workingArea.setOnMouseClicked(null);
                    form.deleteFormCoord();
                }
            });

            form.getTextFieldX().setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER) {
                    int[] newCoordinates = form.getDataFromForm();
                    coordinates[0] = newCoordinates[0];
                    coordinates[1] = newCoordinates[1];
                    coordReceived = true;
                    workingArea.setOnMouseClicked(null);
                    createModeEnableOff();
                    form.deleteFormCoord();
                }
            });

            form.getTextFieldY().setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER) {
                    int[] newCoordinates = form.getDataFromForm();
                    coordinates[0] = newCoordinates[0];
                    coordinates[1] = newCoordinates[1];
                    coordReceived = true;
                    workingArea.setOnMouseClicked(null);
                    createModeEnableOff();
                    form.deleteFormCoord();
                }
            });
            if(coordReceived) {
                lineCoord[i] = coordinates[0];
                lineCoord[i+1] = coordinates[1];
                coordReceived = false;
            }
        }
        add(lineCoord[0], lineCoord[1], lineCoord[2], lineCoord[3]);
        
        
    }
    private void add(int x1, int y1, int x2, int y2) {
        listPosLines.add(new int[]{x1, y1, x2, y2});

        listLines.add(new Node[]{new Circle(x1, y1, 6, Color.BLACK), new Circle(x2, y2, 6, Color.BLACK), new Line(x1, y1, x2, y2)});

        workingArea.getChildren().addAll(listLines.get(listLines.size() - 1));

        hoverLine(listLines.get(listLines.size() - 1), listPosLines.get(listPosLines.size() - 1));
        
    }
    private void hoverLine(Node[] line, int[] posDot) {
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
    
}
