package application;

import application.FigureEnum.Figures;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


public class Handler {
    private AnchorPane area2d;
    private AnchorPane workingArea;
    private FigureManager figureManager;
    private Form form;
    private boolean createModeEnable = false;


    public Handler(AnchorPane area2d, AnchorPane workingArea, FigureManager figureManager, Form form) {
        this.area2d = area2d;
        this.workingArea = workingArea;
        this.figureManager = figureManager;
        this.form = form;
        checkButtons();
        checkEvents();
    }

    private void checkButtons() {
        for (Node node : area2d.getChildren()) {
            node.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    switch (node.getId()) {
                        case "btnDot":
                        btnDotIsPressed((Button)node);
                        break;
                        case "btnLine":
                        System.out.println("bbb");
                        break;
                    }
                }
                
            });
        }
    }

    private void checkEvents() {
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY && !createModeEnable && event.getTarget() != workingArea) {
                if(event.getClickCount() == 1 ) {
                    figureManager.changeColor((Node)event.getTarget(), Color.BLUE);
                    checkDeleteButton((Node)event.getTarget());
                }
                if(event.getClickCount() == 2 ) {
                    figureManager.changeColor((Node)event.getTarget(), Color.GREEN);
                }
            }
            
        });

    }

    private void checkDeleteButton(Node target) {
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                figureManager.deleteFigure(target);
            } else {
                figureManager.changeColor(target, Color.BLACK);
            }
        });
    } 

    private void btnDotIsPressed(Button button) {
        button.setText("Выбрано");
        createModeEnable = true;
        form.deleteFormCoord();
        form.createFormCoord("0", "0", "Введите координаты: ");
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int[] coord = new int[2];
                coord[0] = (int) event.getX();
                coord[1] = (int) event.getY();
                figureManager.createFigure(coord, Figures.DOT);
                form.updateTextField("0", "0");
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                button.setText("Точка");
                workingArea.setOnMouseClicked(null);
                createModeEnable = false;
                form.deleteFormCoord();
            }
        });
        workingArea.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                int[] coordinates = form.getDataFromForm();
                figureManager.createFigure(coordinates, Figures.DOT);
                form.updateTextField("0", "0");
            }
        });
    }

}
