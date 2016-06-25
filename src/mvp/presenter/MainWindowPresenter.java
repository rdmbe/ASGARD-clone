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
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.model.Gwr;
import mvp.model.Lm;
import mvp.view.GWRAnalyzeView;
import mvp.view.LMAnalyzeView;
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
    private final MainWindowView view;
    private final Data data = new Data();
    private final Gwr gwr = new Gwr();
    private final Lm lm = new Lm();
    
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
        view.glmItem.setOnAction(e -> showLMDialog());
        view.gwrItem.setOnAction(e -> showGWRDialog());
        
        
        view.spreadsheet.getSelectionModel().getSelectedCells().addListener((Observable observable) -> {
            for(TablePosition cell:view.spreadsheet.getSelectionModel().getSelectedCells()){
                view.coordinateField.setText(toAlphabetic(cell.getColumn())+""+(cell.getRow()+1));
                view.functionField.setText(view.spreadsheet.getGrid().getRows().get(cell.getRow()).get(cell.getColumn()).getText());
            }
        });
    }
    
    private void showOpenDataDialog(){
        OpenDataDialogView odview = new OpenDataDialogView(data);
        OpenDataDialogPresenter presenter = new OpenDataDialogPresenter(odview, view,data);
        Stage stage = new Stage();
        Scene scene = new Scene(odview,500,300);
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
        GWRAnalyzeView gwrAnalyzeView = new GWRAnalyzeView(data,gwr);
        GWRAnalyzePresenter gwrAnalyzePresenter = new GWRAnalyzePresenter(gwrAnalyzeView,view,data,gwr);
        Stage stage = new Stage();
        Scene scene = new Scene(gwrAnalyzeView,500,500);
        stage.setScene(scene);
        stage.setTitle("GWR Analyze");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
      private void showLMDialog(){
        LMAnalyzeView lmAnalyzeView = new LMAnalyzeView(data,lm);
        LMAnalyzePresenter lmAnalyzePresenter = new LMAnalyzePresenter(lmAnalyzeView,view,data,lm);
        Stage stage = new Stage();
        Scene scene = new Scene(lmAnalyzeView,500,500);
        stage.setScene(scene);
        stage.setTitle("GLM Analyze");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    private String toAlphabetic(int i) {
        if( i<0 ) {
            return "-"+toAlphabetic(-i-1);
        }
        int quot = i/26;
        int rem = i%26;
        char letter = (char)((int)'A' + rem);
        if( quot == 0 ) {
            return ""+letter;
        } else {
            return toAlphabetic(quot-1) + letter;
        }
    }
    
}
