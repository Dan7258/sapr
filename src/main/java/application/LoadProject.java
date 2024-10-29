package application;

import java.util.Scanner;

import application.Figures.FigureManager;
import javafx.scene.control.MenuItem;
import java.io.Serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadProject implements Serializable{
    private static final long serialVersionUID = 1L;
    private ProjectData projectData;
    private FigureManager figureManager;
    private MenuItem openFile;

    public LoadProject(FigureManager figureManager, MenuItem openFile) {
        this.figureManager = figureManager;
        this.openFile = openFile;
        checkPress(openFile);
    }

    private void checkPress(MenuItem openFile) {
        openFile.setOnAction( event -> {
            openFile();
        });
    }

    private void openFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла с расширением:");
        String nameFile = scanner.nextLine();
        try (FileInputStream fileIn = new FileInputStream(nameFile);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            projectData = (ProjectData) in.readObject();
            //figureManager.setListFigures(projectData.getListFigures());
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("ProjectData class not found");
            c.printStackTrace();
        }
    }
}
