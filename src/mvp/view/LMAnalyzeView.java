/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.model.Lm;
import mvp.model.Variable;

/**
 *
 * @author Eki
 */
public class LMAnalyzeView extends HBox{
    
    Label modelLbl = getModelLbl();
    public ComboBox<String> modelCmb;
    Label depVarLbl = getDepVarLbl();
    public TextField depVarFld;
    public Button indVarBtn = getIndVarBtn();
    Label indVarLbl = getIndVarLbl();
    public TextArea indVarArea = getIndVarArea();
    public Button depVarBtn = getDepVarBtn();
    public Button okBtn = getOkBtn();
    public Button cancelBtn = getCancelBtn();
    
    private final Data data;
    private final Lm lm;
    
    public LMAnalyzeView(Data data,Lm lm){
        this.data = data;
        this.lm = lm;
        initLMAnalyze();
        bindDataToModel();
    }
    
    private void initLMAnalyze(){
        modelCmb = getModelCmb();
        depVarFld = getDepVarFld();
        
        VBox leftVBox = new VBox();
        VBox rightVBox = new VBox();
        
        HBox yVarHBox = new HBox();
        HBox xVarHBox = new HBox();
        HBox btnHBox = new HBox();
        
        VBox modelVBox = new VBox();
        modelVBox.setSpacing(5);
        modelVBox.getChildren().addAll(modelLbl,modelCmb);
        
        VBox xCoordVBox = new VBox();
        xCoordVBox.setSpacing(5);
       
        
        VBox yCoordVBox = new VBox();
        yCoordVBox.setSpacing(5);
       
        
        VBox kernelVBox = new VBox();
        kernelVBox.setSpacing(5);
       
        
        VBox bandwidthVBox = new VBox();
        bandwidthVBox.setSpacing(5);
        
        
        VBox selectionVBox = new VBox();
        selectionVBox.setSpacing(5);
        
        
        leftVBox.getChildren().addAll(modelVBox,xCoordVBox,yCoordVBox,
                kernelVBox,bandwidthVBox,selectionVBox);
        leftVBox.setPrefSize(250,600);
        leftVBox.setSpacing(30);
        leftVBox.setStyle("-fx-padding : 10");
        
        yVarHBox.getChildren().addAll(depVarFld,depVarBtn);
        yVarHBox.setAlignment(Pos.BASELINE_LEFT);
        yVarHBox.setSpacing(5);
        xVarHBox.getChildren().addAll(indVarArea,indVarBtn);
        xVarHBox.setAlignment(Pos.TOP_LEFT);
        xVarHBox.setSpacing(5);
        btnHBox.getChildren().addAll(okBtn,cancelBtn);
        btnHBox.setAlignment(Pos.BASELINE_RIGHT);
        btnHBox.setSpacing(5);
        
        VBox yVarVBox = new VBox();
        yVarVBox.setSpacing(5);
        yVarVBox.getChildren().addAll(depVarLbl,yVarHBox);
        
        VBox xVarVBox = new VBox();
        xVarVBox.setSpacing(5);
        xVarVBox.getChildren().addAll(indVarLbl,xVarHBox);
        
        rightVBox.getChildren().addAll(yVarVBox,xVarVBox,btnHBox);
        rightVBox.setPrefSize(250,600);
        rightVBox.setSpacing(30);
        rightVBox.setStyle("-fx-padding : 10");
        
        this.setStyle("-fx-background-color : white");
        this.getChildren().addAll(leftVBox,rightVBox);
    }
    
    private Label getModelLbl(){
        Label label = new Label("Model :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ComboBox<String> getModelCmb(){
        ObservableList<String>list = FXCollections.observableArrayList("Logistic","Negative-Binomial","Poisson");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
      
    private Label getDepVarLbl(){
        Label label = new Label("Dependent Variable :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private TextField getDepVarFld(){
        TextField field = new TextField();
        field.getStylesheets().add("resources/css/textfield.css");
        field.setDisable(true);
        field.setPrefWidth(200);
        
        return field;
    }
    
    private Button getDepVarBtn(){
        Button button = new Button("...");
        button.getStylesheets().add("resources/css/button.css");
        
        return button;
    }
    
    private Label getIndVarLbl(){
        Label label = new Label("Independent Variable :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private TextArea getIndVarArea(){
        TextArea area = new TextArea();
        area.getStylesheets().add("resources/css/textarea.css");
        area.setPrefSize(200, 275);
        area.setDisable(true);
        
        return area;
    }
    
    private Button getIndVarBtn(){
        Button button = new Button("...");
        button.getStylesheets().add("resources/css/button.css");
        
        return button;
    }
    
    private Button getOkBtn(){
        Button button = new Button("OK");
        button.getStylesheets().add("resources/css/button.css");
        
        return button;
    }
    
    private Button getCancelBtn(){
        Button button = new Button("Cancel");
        button.getStylesheets().add("resources/css/button.css");
        
        return button;
    }
    
    private void bindDataToModel(){
        depVarFld.textProperty().bindBidirectional(lm.getDepVarProperty());
    }
    
    public void closeStage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
    
}
