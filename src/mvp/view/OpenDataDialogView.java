/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvp.model.Data;

/**
 *
 * @author Eki
 */
public class OpenDataDialogView extends VBox {
     
    public Label filePathLbl = getFilePathLbl();
    public TextField filePathField = getFilePathFld();
    public Button fileBtn = getFileBtn();
    public Label shapeFilePathLbl = getShapeFilePathLbl();
    public TextField shapeFilePathField = getShapeFilePathFld();
    public Button shapeFileBtn = getShapeFileBtn();
    public CheckBox colHeaderCb = getColHeaderCb();
    public Button okBtn = getOkBtn();
    public Button cancelBtn = getCancelBtn();
    
    private final Data data;
    
    public OpenDataDialogView(Data data){
        this.data = data;
        initOpenDataDialog();
        bindDataToModel();
    }
    
    private void initOpenDataDialog(){
        filePathLbl.setLabelFor(filePathField);
        shapeFilePathLbl.setLabelFor(shapeFilePathField);
        HBox upperTopHBox = new HBox(filePathLbl,filePathField,fileBtn);
        upperTopHBox.setAlignment(Pos.BASELINE_CENTER);
        upperTopHBox.setHgrow(filePathField, Priority.ALWAYS);
        upperTopHBox.setPadding(new Insets(10));
        upperTopHBox.setSpacing(5);
        HBox upperMiddleTopHBox = new HBox(shapeFilePathLbl,shapeFilePathField,shapeFileBtn);
        upperMiddleTopHBox.setAlignment(Pos.BASELINE_CENTER);
        upperMiddleTopHBox.setHgrow(shapeFilePathField, Priority.ALWAYS);
        upperMiddleTopHBox.setPadding(new Insets(10));
        upperMiddleTopHBox.setSpacing(5);
        HBox centerHBox = new HBox(colHeaderCb);
        centerHBox.setSpacing(5);
        centerHBox.setPadding(new Insets(10));
        HBox bottomHBox = new HBox(okBtn,cancelBtn);
        bottomHBox.setSpacing(5);
        bottomHBox.setPadding(new Insets(10));
        bottomHBox.setAlignment(Pos.BOTTOM_RIGHT);
        this.setStyle("-fx-background-color : white;");
        this.getChildren().addAll(upperTopHBox,upperMiddleTopHBox,centerHBox,bottomHBox);
        this.setSpacing(5);
    }
    
    public void enterField(String path){
        filePathField.setText(path);
    }
    
    public void enterShpField(String shpPath){
        shapeFilePathField.setText(shpPath);
    }
    
    private Label getFilePathLbl(){
        Label label = new Label();
        label.setText("File Path   : ");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private TextField getFilePathFld(){
        TextField field = new TextField();
        field.setPromptText("Enter the file path here");
        field.getStylesheets().add("resources/css/textfield.css");
        
        return field;
    }
    
    private Button getFileBtn(){
        Button btn = new Button();
        btn.setText("...");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    private Label getShapeFilePathLbl(){
        Label label = new Label();
        label.setText("Shapefile  : ");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private TextField getShapeFilePathFld(){
        TextField field = new TextField();
        field.setPromptText("Enter the shapefile path here");
        field.getStylesheets().add("resources/css/textfield.css");
        
        return field;
    }
    
    private Button getShapeFileBtn(){
        Button btn = new Button();
        btn.setText("...");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    private CheckBox getColHeaderCb(){
        CheckBox cb = new CheckBox();
        cb.setText("Use Column Header");
        cb.getStylesheets().add("resources/css/checkbox.css");
        
        return cb;
    }
    
    private Button getOkBtn(){
        Button btn = new Button();
        btn.setText("OK");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    private Button getCancelBtn(){
        Button btn = new Button();
        btn.setText("Cancel");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    private void bindDataToModel(){
        filePathField.textProperty().bindBidirectional(data.getPathProperty());
    }
    
    public void closeStage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
    
}
