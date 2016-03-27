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

/**
 *
 * @author Eki
 */
public class OpenDataDialogView extends VBox {
     
    public Label filePathLbl = getFilePathLbl();
    public TextField filePathField = getFilePathFld();
    public CheckBox colHeaderCb = getColHeaderCb();
    public Button fileBtn = getFileBtn();
    public Button okBtn = getOkBtn();
    public Button cancelBtn = getCancelBtn();
    
    public OpenDataDialogView(){
        initOpenDataDialog();
//        bindDataModel();
    }
    
    private void initOpenDataDialog(){
        filePathLbl.setLabelFor(filePathField);
        HBox topHBox = new HBox(filePathLbl,filePathField,fileBtn);
        topHBox.setAlignment(Pos.BASELINE_CENTER);
        topHBox.setHgrow(filePathField, Priority.ALWAYS);
        topHBox.setPadding(new Insets(10));
        topHBox.setSpacing(5);
        HBox centerHBox = new HBox(colHeaderCb);
        centerHBox.setSpacing(5);
        centerHBox.setPadding(new Insets(10));
        HBox bottomHBox = new HBox(okBtn,cancelBtn);
        bottomHBox.setSpacing(5);
        bottomHBox.setPadding(new Insets(10));
        bottomHBox.setAlignment(Pos.BOTTOM_RIGHT);
        this.setStyle("-fx-background-color : white;");
        this.getChildren().addAll(topHBox,centerHBox,bottomHBox);
        this.setSpacing(5);
    }
    
    public void enterField(String path){
        filePathField.setText(path);
    }
    
    private Label getFilePathLbl(){
        Label label = new Label();
        label.setText("File Path : ");
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
    
//    private void bindDataModel(){
//        filePathField.textProperty().bindBidirectional(model.filePathProperty());
//        colHeaderCb.selectedProperty().bindBidirectional(model.usingColumnHeaderProperty());
//    }
    
    public void closeStage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
    
}
