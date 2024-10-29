package application;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import application.Figures.FigureManager;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SaveProject {

    private FigureManager figureManager;
    private transient MenuItem quit;
    private transient MenuItem saveFile;
    private transient MenuItem closeFile;

    public SaveProject(FigureManager figureManager, MenuItem quit, MenuItem saveFile, MenuItem closeFile) {
        this.figureManager = figureManager;
        this.quit = quit;
        this.saveFile = saveFile;
        this.closeFile = closeFile;
        checkPress(quit, saveFile, closeFile);
    }

    private void checkPress(MenuItem quit, MenuItem saveFile, MenuItem closeFile) {
        quit.setOnAction( event -> {
            saveFile();
            Platform.exit();
        });
        saveFile.setOnAction( event -> {
            saveFile();
        });
        closeFile.setOnAction( event -> {
            saveFile();
            Platform.exit();
        });
    }

    public void saveFile() {
        ProjectData projectData = new ProjectData(figureManager.getListFigures());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла без расширения:");
        String nameFile = scanner.nextLine() + ".grb";
        try (FileOutputStream fileOut = new FileOutputStream(nameFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(projectData);
            System.out.println("Project data is saved in " + nameFile);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}