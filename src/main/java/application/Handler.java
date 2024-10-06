package application;

import java.util.concurrent.atomic.AtomicInteger;
import application.Figures.FigureManager;
import application.Figures.FigureEnum.Figures;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Handler {
    private AnchorPane area2d;
    private AnchorPane workingArea;
    private FigureManager figureManager;
    private Form form;
    private boolean createModeEnable = false;
    
    PauseTransition pause = new PauseTransition(Duration.millis(170));

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
                        btnLineIsPressed((Button)node);
                        break;
                    }
                }
                
            });
        }
    }

    private void checkEvents() {
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY && !createModeEnable && event.getTarget() != workingArea) {
                pause.play();
                pause.setOnFinished(e -> {
                    if(event.getClickCount() == 2 ) {
                        figureManager.changeColor((Node)event.getTarget(), Color.GREEN);
                    }
                    if(event.getClickCount() == 1 ) {
                        figureManager.changeColor((Node)event.getTarget(), Color.BLUE);
                        checkDeleteButton((Node)event.getTarget());
                    }
                    
                });  
                
            }
            
        });
        

    }

    private void checkDeleteButton(Node target) {
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                figureManager.deleteFigure(target);
                workingArea.getScene().setOnKeyPressed(null);
            } else {
                figureManager.changeColor(target, Color.BLACK);
                workingArea.getScene().setOnKeyPressed(null);
            }
        });
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                figureManager.changeColor(target, Color.BLACK);
                workingArea.getScene().setOnKeyPressed(null);
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
                figureManager.createFigure(coord, Figures.DOTF);
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
                figureManager.createFigure(coordinates, Figures.DOTF);
                form.updateTextField("0", "0");
            }
        });
    }

    private void btnLineIsPressed(Button button) {
        button.setText("Выбрано");
        createModeEnable = true;
        int[] coord = new int[4];
        AtomicInteger i = new AtomicInteger(0);
        handleCoordinateInput(button, coord, i);
    }
    
    private void handleCoordinateInput(Button button, int[] coord, AtomicInteger i) {
        if (i.get() >= 4) {
            if (createModeEnable) {
                figureManager.createFigure(coord, Figures.SEGMENT);
                i.set(0);
            }
        }
    
        form.deleteFormCoord();
        if (i.get() == 0) {
            form.createFormCoord("0", "0", "Введите координаты левой границы: ");
        } else {
            form.createFormCoord("0", "0", "Введите координаты правой границы: ");
        }
    
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                coord[i.get()] = (int) event.getX();
                coord[i.get() + 1] = (int) event.getY();
                i.addAndGet(2);
                handleCoordinateInput(button, coord, i); 
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                button.setText("Линия");
                workingArea.setOnMouseClicked(null);
                createModeEnable = false;
                form.deleteFormCoord();
                i.set(4);
                return;
            }
        });
    
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                int[] coordinates = form.getDataFromForm();
                coord[i.get()] = coordinates[0];
                coord[i.get() + 1] = coordinates[1];
                i.addAndGet(2);
                handleCoordinateInput(button, coord, i);                 
            }
        });
    }
}
