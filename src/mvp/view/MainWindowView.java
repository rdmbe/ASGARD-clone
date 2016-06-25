/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetColumn;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author Eki
 */
public class MainWindowView extends BorderPane {
    
    public Button newBtn;
    public Button openBtn;
    public Button saveBtn;
    public Button saveAsBtn;
    public Button undoBtn;
    public Button redoBtn;
    public Button cutBtn;
    public Button copyBtn;
    public Button pasteBtn;
    
    public MenuItem newItem;
    public MenuItem openDataItem;
    public MenuItem openProjectItem;
    public MenuItem saveItem;
    public MenuItem saveAsItem;
    public MenuItem exitItem;
    
    public MenuItem gwrItem;
    public MenuItem lmItem;
    public MenuItem glmItem;
    
    public TextField coordinateField = getCoordinateField();
    public TextField functionField = getFunctionField();
    
    public SpreadsheetView spreadsheet = getSpreadsheet();
        
    public MainWindowView(){
        initMainWindow();
    }
    
    private void initMainWindow(){
        HBox centerHBox = new HBox(coordinateField,getFunctionImage(),functionField);
        centerHBox.setAlignment(Pos.CENTER_LEFT);
        centerHBox.setSpacing(5);
        centerHBox.setHgrow(functionField, Priority.ALWAYS);
        centerHBox.setPadding(new Insets(5, 5, 5, 5));
        centerHBox.setStyle("-fx-background-color: white;"+
                "-fx-border-width: 0.3 0 0 0");
        
        VBox topVBox = new VBox(getMenuBar(),getToolBar(),centerHBox);
        topVBox.setStyle("-fx-background-color: #cce5ff;");
        
        VBox centerVBox = new VBox(spreadsheet);
        centerVBox.setVgrow(spreadsheet,Priority.ALWAYS);
        centerVBox.setPadding(new Insets(5,0,0,0));
        centerVBox.setStyle("-fx-background-color: rgba(17,129,175,100);");

        HBox bottomHBox = new HBox(getStatusLabel());
        bottomHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomHBox.setPrefHeight(24);
        bottomHBox.setPadding(new Insets(0, 5, 0, 0));
        bottomHBox.setStyle("-fx-background-color: rgba(17,129,175,100);");
        
        this.setTop(topVBox);
        this.setCenter(centerVBox);
        this.setBottom(bottomHBox);
    }
    
    private MenuBar getMenuBar(){
        Menu fileMenu = getFileMenu();
        Menu editMenu = getEditMenu();
        Menu analyzeMenu = getAnalyzeMenu();
        Menu helpMenu = getHelpMenu();
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, analyzeMenu, helpMenu);
 
        menuBar.getStylesheets().add("resources/css/menubar.css");
        return menuBar;
    }
    
    private ToolBar getToolBar(){
        ToolBar toolBar = new ToolBar();
        toolBar.getStylesheets().add("/resources/css/toolbar.css");
        
        newBtn = new Button("",new ImageView("resources/images/new.png"));
        openBtn = new Button("",new ImageView("resources/images/open.png"));
        saveBtn = new Button("",new ImageView("resources/images/save.png"));
        saveAsBtn = new Button("",new ImageView("resources/images/saveas.png"));
        undoBtn = new Button("",new ImageView("resources/images/undo.png"));
        redoBtn = new Button("",new ImageView("resources/images/redo.png"));
        cutBtn = new Button("",new ImageView("resources/images/cut.png"));
        copyBtn = new Button("",new ImageView("resources/images/copy.png"));
        pasteBtn = new Button("",new ImageView("resources/images/paste.png"));
        
        newBtn.setTooltip(new Tooltip("New"));
        openBtn.setTooltip(new Tooltip("Open"));
        saveBtn.setTooltip(new Tooltip("Save"));
        saveAsBtn.setTooltip(new Tooltip("Save As"));
        undoBtn.setTooltip(new Tooltip("Undo"));
        redoBtn.setTooltip(new Tooltip("Redo"));
        cutBtn.setTooltip(new Tooltip("Cut"));
        copyBtn.setTooltip(new Tooltip("Copy"));
        pasteBtn.setTooltip(new Tooltip("Paste"));
        
        toolBar.getItems().addAll(newBtn,openBtn,saveBtn,saveAsBtn,new Separator(),undoBtn,redoBtn,new Separator(),cutBtn,copyBtn,pasteBtn);
        
        toolBar.getStylesheets().add("/resources/css/toolbar.css");
        
        return toolBar;
    }
    
    private TextField getCoordinateField(){
        TextField field = new TextField();
        field.setPrefColumnCount(5);
        field.setAlignment(Pos.CENTER);
        field.getStylesheets().add("resources/css/textbox.css");
        
        return field;
    }
    
    private ImageView getFunctionImage(){
        ImageView image = new ImageView("resources/images/fx.png");
        image.setFitHeight(20);
        image.setFitWidth(20);
        image.setStyle("-fx-background-color:white");
        
        return image;
    }
    
    private TextField getFunctionField(){
        TextField field = new TextField();
        field.getStylesheets().add("resources/css/textbox.css");
        
        return field;
    }
    
    private SpreadsheetView getSpreadsheet(){
        spreadsheet = new SpreadsheetView();
        int rowCount = 1000;
        int columnCount = 100;
        GridBase grid = new GridBase(rowCount, columnCount);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        for (int row = 0; row < grid.getRowCount(); ++row) {
            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
            for (int column = 0; column < grid.getColumnCount(); ++column) {
                list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1,""));
            }
            rows.add(list);
        }
        grid.setRows(rows);
        spreadsheet.setGrid(grid);
        ObservableList<SpreadsheetColumn> listColumn = spreadsheet.getColumns();
                
        for(int i = 0;i < listColumn.size(); i++){
            listColumn.get(i).setPrefWidth(75);
        }
        
        spreadsheet.getStylesheets().add("resources/css/spreadsheetview.css");
        
        return spreadsheet;
    }
    
    private Label getStatusLabel(){
        Label statusLabel = new Label();
        statusLabel.setText("READY");
        statusLabel.setStyle("-fx-text-fill: white;");
        
        return statusLabel;
    }
    
    private Menu getFileMenu(){
        Menu menu = new Menu("FILE");
        Menu openMenu = new Menu("Open",new ImageView(new Image("resources/images/sc_open.png")));
        
        newItem = new MenuItem("New", new ImageView(new Image("resources/images/sc_new.png")));
        openProjectItem = new MenuItem("Project...");
        openDataItem = new MenuItem("Data...");
        saveItem = new MenuItem("Save", new ImageView(new Image("resources/images/sc_save.png")));
        saveAsItem = new MenuItem("Save As", new ImageView(new Image("resources/images/sc_saveas.png")));
        exitItem = new MenuItem("Exit");
        
        KeyCombination kNew = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        KeyCombination kOpen = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        KeyCombination kSave = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        
        newItem.setAccelerator(kNew);
        openDataItem.setAccelerator(kOpen);
        saveItem.setAccelerator(kSave);
        
        openMenu.getItems().addAll(openProjectItem,openDataItem);
        
        menu.getItems().addAll(newItem,openMenu,saveItem,saveAsItem,new SeparatorMenuItem(),exitItem);
        return menu;
    }
    
    private Menu getEditMenu(){
        Menu menu = new Menu("EDIT");
        
        MenuItem undoItem = new MenuItem("Undo", new ImageView(new Image("resources/images/sc_undo.png")));
        MenuItem redoItem = new MenuItem("Redo", new ImageView(new Image("resources/images/sc_redo.png")));
        MenuItem cutItem = new MenuItem("Cut", new ImageView(new Image("resources/images/sc_cut.png")));
        MenuItem copyItem = new MenuItem("Copy", new ImageView(new Image("resources/images/sc_copy.png")));
        MenuItem pasteItem = new MenuItem("Paste", new ImageView(new Image("resources/images/sc_paste.png")));
        
        KeyCombination kUndo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        KeyCombination kRedo = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
        KeyCombination kCut = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
        KeyCombination kCopy = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
        KeyCombination kPaste = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
        
        undoItem.setAccelerator(kUndo);
        redoItem.setAccelerator(kRedo);
        cutItem.setAccelerator(kCut);
        copyItem.setAccelerator(kCopy);
        pasteItem.setAccelerator(kPaste);
        
        menu.getItems().addAll(undoItem,redoItem,new SeparatorMenuItem(),cutItem,copyItem,pasteItem);
        return menu;
    }
    
    private Menu getAnalyzeMenu(){
         Menu menu = new Menu("ANALYZE");
        
        Menu globalItem = new Menu("Global Model");
        Menu localItem = new Menu("Local Model");
        lmItem = new MenuItem("Linear Regression ...");
        glmItem = new MenuItem("Generalized Linear Model ...");
        gwrItem = new MenuItem("Geographically Weighted Regression ...");
        
        globalItem.getItems().addAll(lmItem,glmItem);
        localItem.getItems().addAll(gwrItem);
        
        //glmItem.getItems().addAll(logItem,poiItem,nbItem);
        
        menu.getItems().addAll(globalItem,localItem);
        return menu;
    }
    
    private Menu getHelpMenu(){
        Menu menu = new Menu("HELP");
        
        MenuItem helpItem = new MenuItem("Help");
        MenuItem aboutItem = new MenuItem("About");
        
        KeyCombination kHelp = new KeyCodeCombination(KeyCode.F1);
        
        helpItem.setAccelerator(kHelp);
        
        menu.getItems().addAll(helpItem,aboutItem);
        return menu;
    }
    
    public void drawTable(ObservableList<String>listHeader,GridBase grid){
        spreadsheet.setGrid(grid);
        spreadsheet.getGrid().getColumnHeaders().setAll(listHeader);
    }
    
}
