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
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Handler {
    private AnchorPane area2d;
    private AnchorPane workingArea;
    private FigureManager figureManager;
    private Form form;
    private CoordinateSystem coordinateSystem;
    private boolean createMode = false;
    private Node chooseFigure;
    private Button chooseButton;
    
    PauseTransition pause = new PauseTransition(Duration.millis(170));

    public Handler(AnchorPane area2d, AnchorPane workingArea, FigureManager figureManager, CoordinateSystem coordinateSystem, Form form) {
        this.area2d = area2d;
        this.workingArea = workingArea;
        this.figureManager = figureManager;
        this.form = form;
        this.coordinateSystem = coordinateSystem;
        checkButtons();
        checkEvents();
        checkchooseFigure();
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
                        createByTwoPoints((Button)node, Figures.SEGMENT);
                        break;
                        case "btnRectangle":
                        createByTwoPoints((Button)node, Figures.RECTANGLE);
                        break;
                        case "btnCircle":
                        createByTwoPoints((Button)node, Figures.RING);
                        break;
                    }
                }
            });
        }
    }

    private void checkEvents() {
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY && !createMode && event.getTarget() != workingArea) {
                pause.play();
                pause.setOnFinished(e -> {
                    if(event.getClickCount() == 2 ) {
                        figureManager.changeColor((Node)event.getTarget(), Color.GREEN);
                        figureManager.manageSettings((Node)event.getTarget());
                    }
                    if(event.getClickCount() == 1 ) {
                        System.out.println(event);
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
                figureManager.setDefaultColor(target);
                workingArea.getScene().setOnKeyPressed(null);
            }
        });
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY || event.getButton() == MouseButton.PRIMARY) {
                figureManager.setDefaultColor(target);
                workingArea.getScene().setOnKeyPressed(null);
            }
        });
    } 

    private void checkchooseFigure() {
        workingArea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() != MouseButton.MIDDLE) {
                if(chooseFigure == null) {
                    chooseFigure = (Node)event.getTarget();
                }
                if(event.getTarget() != chooseFigure && chooseFigure != null) {
                    if(chooseFigure != workingArea) {
                        figureManager.setDefaultColor(chooseFigure);
                    }
                    chooseFigure = (Node)event.getTarget();
                } 
            }
            
        });

    }

    private void createModeEnable(Button button) {
        chooseButton = button;
        button.setText("Выбрано");
        createMode = true;
        form.deleteFormCoord();
    }

    private void createModeDisable(Button button) {
        if (button == null) {
            return;
        }
        switch (button.getId()) {
            case "btnDot":
            button.setText("Точка");
            break;
            case "btnLine":
            button.setText("Линия");
            break;
            case "btnRectangle":
            button.setText("Прямоугольник");
            break;
            case "btnCircle":
            button.setText("Окружность");
            break;
        }
        createMode = false;
        form.deleteFormCoord();
        workingArea.setOnMouseClicked(null);
    }

    private void btnDotIsPressed(Button button) {
        createModeDisable(chooseButton);
        createModeEnable(button);
        form.createFormCoord("0", "0", "Введите координаты: ");
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double[] coord = new double[2];
                coord = coordinateSystem.getRelativeCoordinate((double)event.getX(), (double)event.getY());
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(coord), Figures.DOTF);
                form.deleteFormCoord();
                form.createFormCoord("0", "0", "Введите координаты: ");
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                createModeDisable(button);
            }
        });
        
        workingArea.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                double[] coordinates = form.getDataFromForm();
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(coordinates), Figures.DOTF);
                form.deleteFormCoord();
                form.createFormCoord("0", "0", "Введите координаты: ");
            }
        });
    }
    

    private void createByTwoPoints(Button button, Figures type) {
        createModeDisable(chooseButton);
        createModeEnable(button);
        double[] coord = new double[4];
        AtomicInteger i = new AtomicInteger(0);
        handleCoordinateInput(button, coord, i, type);
    }
    
    private void handleCoordinateInput(Button button, double[] coord, AtomicInteger i, Figures type) {
        if (i.get() >= 4) {
            if (createMode) {
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(coord), type);
                i.set(0);
            }
        }
        form.deleteFormCoord();
        if (i.get() == 0) {
            String text = type == Figures.RING ? "Введите координаты центра: " : "Введите координаты левой границы: ";
            form.createFormCoord("0", "0", "0", "0", text);
        } else {
            String text = type == Figures.RING ? "Введите радиус через координаты: " : "Введите координаты правой границы: ";
            form.createFormCoord("0", "0", "0", "0", text);
        }
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double[] coordm = choiceCoordinate(event);                
                coord[i.get()] = coordm[0];
                coord[i.get() + 1] = coordm[1];
                i.addAndGet(2);
                handleCoordinateInput(button, coord, i, type); 
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                createModeDisable(button);
                i.set(4);
                return;
            }
        });
        workingArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                double[] data = form.getDataFromForm();
                parseData(coord, data, i);
                i.addAndGet(2);
                handleCoordinateInput(button, coord, i, type);                 
            }
        });
    }

    private double[] choiceCoordinate(MouseEvent event) {
        double[] coordm = new double[2];
        if(event.getTarget() instanceof Circle) {
            Circle circle = (Circle)event.getTarget();
            coordm = coordinateSystem.getRelativeCoordinate((double)circle.getCenterX(), (double)circle.getCenterY());
        } else {
            coordm = coordinateSystem.getRelativeCoordinate((double)event.getX(), (double)event.getY());
        }
        return coordm;
    }

    private void parseData(double[] coord, double[] data, AtomicInteger i) {
        if(data[2] == 0 && data[3] == 0) {
            coord[i.get()] = data[0];
            coord[i.get() + 1] = data[1];
        } else {
            if(i.get()==0) {
                double x = 0 + (double)(Math.cos(data[3] * Math.PI / 180) * data[2]);
                double y = 0 + (double)(Math.sin(data[3] * Math.PI / 180) * data[2]);
                coord[i.get()] = x;
                coord[i.get() + 1] = y;
            } else {
                double x = coord[0] + (double)(Math.cos(data[3] * Math.PI / 180) * data[2]);
                double y = coord[1] + (double)(Math.sin(data[3] * Math.PI / 180) * data[2]);
                coord[i.get()] = x;
                coord[i.get() + 1] = y;
            }
        }
    }
    
}
