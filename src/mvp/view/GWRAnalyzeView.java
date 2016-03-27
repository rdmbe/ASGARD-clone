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

/**
 *
 * @author Eki
 */
public class GWRAnalyzeView extends HBox{
    
    Label modelLbl = getModelLbl();
    ComboBox<String> modelCmb = getModelCmb();
    Label xCoordinateLbl = getXCoordinateLbl();
    ComboBox<String> xCoordinateCmb = getXCoordinateCmb();
    Label yCoordinateLbl = getYCoordinateLbl();
    ComboBox<String> yCoordinateCmb = getYCoordinateCmb();
    Label kernelLbl = getKernelLbl();
    ComboBox<String> kernelCmb = getKernelCmb();
    Label bandwidthLbl = getBandwidthLbl();
    ComboBox<String> bandwidthCmb = getBandwidthCmb();
    Label selectionLbl = getSelectionLbl();
    ComboBox<String> selectionCmb = getSelectionCmb();
    Label indVarLbl = getIndVarLbl();
    TextField indVarFld = getIndVarFld();
    Button indVarBtn = getIndVarBtn();
    Label depVarLbl = getDepVarLbl();
    TextArea depVarArea = getDepVarArea();
    Button depVarBtn = getDepVarBtn();
    Button okBtn = getOkBtn();
    Button cancelBtn = getCancelBtn();
    
    public GWRAnalyzeView(){
        initGWRAnalyze();
    }
    
    private void initGWRAnalyze(){
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
        xCoordVBox.getChildren().addAll(xCoordinateLbl,xCoordinateCmb);
        
        VBox yCoordVBox = new VBox();
        yCoordVBox.setSpacing(5);
        yCoordVBox.getChildren().addAll(yCoordinateLbl,yCoordinateCmb);
        
        VBox kernelVBox = new VBox();
        kernelVBox.setSpacing(5);
        kernelVBox.getChildren().addAll(kernelLbl,kernelCmb);
        
        VBox bandwidthVBox = new VBox();
        bandwidthVBox.setSpacing(5);
        bandwidthVBox.getChildren().addAll(bandwidthLbl,bandwidthCmb);
        
        VBox selectionVBox = new VBox();
        selectionVBox.setSpacing(5);
        selectionVBox.getChildren().addAll(selectionLbl,selectionCmb);
        
        leftVBox.getChildren().addAll(modelVBox,xCoordVBox,yCoordVBox,
                kernelVBox,bandwidthVBox,selectionVBox);
        leftVBox.setPrefSize(250,600);
        leftVBox.setSpacing(30);
        leftVBox.setStyle("-fx-padding : 10");
        
        yVarHBox.getChildren().addAll(indVarFld,indVarBtn);
        yVarHBox.setAlignment(Pos.BASELINE_LEFT);
        yVarHBox.setSpacing(5);
        xVarHBox.getChildren().addAll(depVarArea,depVarBtn);
        xVarHBox.setAlignment(Pos.TOP_LEFT);
        xVarHBox.setSpacing(5);
        btnHBox.getChildren().addAll(okBtn,cancelBtn);
        btnHBox.setAlignment(Pos.BASELINE_RIGHT);
        btnHBox.setSpacing(5);
        
        VBox yVarVBox = new VBox();
        yVarVBox.setSpacing(5);
        yVarVBox.getChildren().addAll(indVarLbl,yVarHBox);
        
        VBox xVarVBox = new VBox();
        xVarVBox.setSpacing(5);
        xVarVBox.getChildren().addAll(depVarLbl,xVarHBox);
        
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
        ObservableList<String>list = FXCollections.observableArrayList("OLS","Logistic","Negative-Binomial","Poisson");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
    private Label getXCoordinateLbl(){
        Label label = new Label("X Coordinate :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ComboBox<String> getXCoordinateCmb(){
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
    private Label getYCoordinateLbl(){
        Label label = new Label("Y Coordinate :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ComboBox<String> getYCoordinateCmb(){
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
    private Label getKernelLbl(){
        Label label = new Label("Kernel Function :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ComboBox<String> getKernelCmb(){
        ObservableList<String>list = FXCollections.observableArrayList("Fixed Gaussian","Adaptive Gaussian");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
    private Label getBandwidthLbl(){
        Label label = new Label("Bandwidth :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ComboBox<String> getBandwidthCmb(){
        ObservableList<String>list = FXCollections.observableArrayList("Golden Section Search");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
    private Label getSelectionLbl(){
        Label label = new Label("Selection :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ComboBox<String> getSelectionCmb(){
        ObservableList<String>list = FXCollections.observableArrayList("AIC","CV","AICc");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");
        
        return cmb;
    }
    
    private Label getIndVarLbl(){
        Label label = new Label("Independent Variable :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private TextField getIndVarFld(){
        TextField field = new TextField();
        field.getStylesheets().add("resources/css/textfield.css");
        field.setDisable(true);
        field.setPrefWidth(200);
        
        return field;
    }
    
    private Button getIndVarBtn(){
        Button button = new Button("...");
        button.getStylesheets().add("resources/css/button.css");
        
        return button;
    }
    
    private Label getDepVarLbl(){
        Label label = new Label("Independent Variable :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private TextArea getDepVarArea(){
        TextArea area = new TextArea();
        area.getStylesheets().add("resources/css/textarea.css");
        area.setPrefSize(200, 275);
        
        return area;
    }
    
    private Button getDepVarBtn(){
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
    
}
