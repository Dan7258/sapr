package application;

import java.util.ArrayList;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import application.Figures.FigureManager;
import application.Figures.PreparingData;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import java.io.FileReader;
import java.io.IOException;

public class LoadProject {
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
    TextInputDialog dialog = new TextInputDialog("Имя файла");
    dialog.setTitle("Открытие файла");
    dialog.setHeaderText("Введите имя файла без расширения:");
    
    Optional<String> result = dialog.showAndWait();

    result.ifPresent(name -> {
        String nameFile = name + ".grb";
        Gson gson = new Gson();
        
        try (FileReader reader = new FileReader(nameFile)) {
            Type figureListType = new TypeToken<ArrayList<PreparingData>>(){}.getType();
            figureManager.setListFigures(gson.fromJson(reader, figureListType));
            System.out.println("file open " + nameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
}

}
