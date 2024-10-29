package application;

import java.util.Optional;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

import application.Figures.FigureManager;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        TextInputDialog dialog = new TextInputDialog("Имя файла");
        dialog.setTitle("Сохранение файла");
        dialog.setHeaderText("Введите имя файла без расширения:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            String nameFile = name + ".grb";
            try (FileWriter writer = new FileWriter(nameFile)) {
                gson.toJson(figureManager.getPreparingData(), writer);
                System.out.println("Файл успешно сохранен как " + nameFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}