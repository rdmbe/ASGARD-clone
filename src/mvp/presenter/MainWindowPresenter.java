/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.presenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvp.view.GWRAnalyzeView;
import mvp.view.MainWindowView;
import mvp.view.OpenDataDialogView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

/**
 *
 * @author Eki
 */
public class MainWindowPresenter {
    private MainWindowView view;
    
    public MainWindowPresenter(MainWindowView view){
        this.view = view;
        attachEvents();
    }
    
    private void attachEvents(){
        view.openBtn.setOnAction(e -> showOpenDataDialog());
        view.saveAsBtn.setOnAction(e -> showSaveDialog());
        
        view.openDataItem.setOnAction(e -> showOpenDataDialog());
        view.saveAsItem.setOnAction(e -> showSaveDialog());
        view.exitItem.setOnAction(e -> Platform.exit());
        
        view.gwrItem.setOnAction(e -> showGWRDialog());
    }
    
    private void showOpenDataDialog(){
        OpenDataDialogView odview = new OpenDataDialogView();
        OpenDataDialogPresenter presenter = new OpenDataDialogPresenter(odview, view);
        Stage stage = new Stage();
        Scene scene = new Scene(odview,500,150);
        stage.setScene(scene);
        stage.setTitle("Open Data");
        stage.getIcons().add(new Image("resources/images/sc_open.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    private void showSaveDialog(){
        FileChooser fileDialog = new FileChooser();
        fileDialog.setTitle("Save As File");
        fileDialog.setInitialFileName("untitled.xlsx");
        fileDialog.setInitialDirectory(new File("C:\\"));
        
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        File file = fileDialog.showSaveDialog(stage);
    }
    
    private void showGWRDialog(){
        GWRAnalyzeView gwrAnalyzeView = new GWRAnalyzeView();
        GWRAnalyzePresenter gwrAnalyzePresenter = new GWRAnalyzePresenter(gwrAnalyzeView);
        Stage stage = new Stage();
        Scene scene = new Scene(gwrAnalyzeView,500,500);
        stage.setScene(scene);
        stage.setTitle("GWR Analyze");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
}
