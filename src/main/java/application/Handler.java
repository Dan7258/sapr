package application;

import java.util.ArrayList;

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
                        case "btnPolyline":
                        createByTwoPoints((Button)node, Figures.POLYLINEF);
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
            case "btnPolyline":
            button.setText("Полилиния");
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
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(coord), Figures.DOTF, false);
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
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(coordinates), Figures.DOTF, false);
                form.deleteFormCoord();
                form.createFormCoord("0", "0", "Введите координаты: ");
            }
        });
    }

    private double[] converCollectionToArray(ArrayList<Double> coord) {
        double[] convCoord = new double[coord.size()];
        for(int i = 0; i < coord.size();i++) {
            convCoord[i] = coord.get(i);
        }
        return convCoord;
    }
    

    private void createByTwoPoints(Button button, Figures type) {
        createModeDisable(chooseButton);
        createModeEnable(button);
        ArrayList<Double> coord = new ArrayList<>();
        AtomicInteger i = new AtomicInteger(0);
        handleCoordinateInput(button, coord, i, type);
    }
    
    private void handleCoordinateInput(Button button, ArrayList<Double> coord, AtomicInteger i, Figures type) {
        if (i.get() == 4) {
            if (createMode) {
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(converCollectionToArray(coord)), type, false);
                if(type != Figures.POLYLINEF) {
                    i.set(0);
                    coord.clear();
                }
                
            }
        }
        if (i.get() > 4) {
            if (createMode) {
                figureManager.createFigure(coordinateSystem.getAbsoluteCoordinate(converCollectionToArray(coord)), type, true);                
            }
        }
        form.deleteFormCoord();
        if (i.get() == 0) {
            String text = type == Figures.RING ? "Введите координаты центра: " : "Введите координаты левой границы: ";
            text = type == Figures.POLYLINEF ? "Введите координаты первой точки: " : "Введите координаты левой границы: ";
            form.createFormCoord("0", "0", "0", "0", text);
        } else {
            String text = type == Figures.RING ? "Введите радиус через координаты: " : "Введите координаты правой границы: ";
            text = type == Figures.POLYLINEF ? "Введите координаты следующей точки: " : "Введите координаты правой границы: ";
            form.createFormCoord("0", "0", "0", "0", text);
        }
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double[] coordm = choiceCoordinate(event);                
                coord.add(coordm[0]);
                coord.add(coordm[1]);
                i.addAndGet(2);
                handleCoordinateInput(button, coord, i, type); 
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                createModeDisable(button);
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

    private void parseData(ArrayList<Double> coord, double[] data, AtomicInteger i) {
        if(data[2] == 0 && data[3] == 0) {
            coord.add(data[0]);
            coord.add(data[1]);
        } else {
            if(i.get()==0) {
                double x = 0 + (double)(Math.cos(data[3] * Math.PI / 180) * data[2]);
                double y = 0 + (double)(Math.sin(data[3] * Math.PI / 180) * data[2]);
                coord.add(x);
                coord.add(y);
            } else {
                double x = coord.get(coord.size()-2) + (double)(Math.cos(data[3] * Math.PI / 180) * data[2]);
                double y = coord.get(coord.size()-1) + (double)(Math.sin(data[3] * Math.PI / 180) * data[2]);
                coord.add(x);
                coord.add(y);
            }
        }
    }
    
}
