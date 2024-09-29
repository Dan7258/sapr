package application;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DotFigure {

    private AnchorPane workingArea;
    private Button btnDot;

    private int[][] listPosDots = new int[0][0];
    private Circle[] listDots = new Circle[0];

    
    public DotFigure(AnchorPane workingArea, Button btnDot) {
        this.workingArea = workingArea;
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

        newListDots[newListDots.length - 1] = new Circle(listPosDots[listPosDots.length - 1][0], listPosDots[listPosDots.length - 1][1], 5, Color.BLACK);

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
}
