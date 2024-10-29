package application;

import java.io.Serializable;
import java.util.ArrayList;
import application.Figures.Figure;

public class ProjectData implements Serializable{
    private ArrayList<Figure> listFigures;
    private static final long serialVersionUID = 1L;
    public ProjectData(ArrayList<Figure> listFigures) {
        this.listFigures = listFigures;
    }

    public ArrayList<Figure> getListFigures() {
        return this.listFigures;
    }
    public void setListFigures(ArrayList<Figure> listFigures) {
        this.listFigures = listFigures;
    }
}
