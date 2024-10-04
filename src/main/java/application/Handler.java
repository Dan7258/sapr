package application;

import application.FigureEnum.Figures;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Handler {
    private AnchorPane area2d;
    private AnchorPane workingArea;
    private CoordinateSystem coordinateSystem;
    private FigureManager figureManager;
    private Form form;


    public Handler(AnchorPane area2d, AnchorPane workingArea, CoordinateSystem coordinateSystem, FigureManager figureManager) {
        this.area2d = area2d;
        this.workingArea = workingArea;
        this.coordinateSystem = coordinateSystem;
        this.figureManager = figureManager;
        check();
    }

    public void check() {
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
    private void btnDotIsPressed(Button button) {
        button.setText("Выбрано");
        workingArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int[] coord = new int[2];
                coord[0] = (int) event.getX();
                coord[1] = (int) event.getY();
                figureManager.createFigure(coord, Figures.DOT);
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                button.setText("Точка");
                workingArea.setOnMouseClicked(null);
            }
        });
    }

}
