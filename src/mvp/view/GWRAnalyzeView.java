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
import mvp.model.Gwr;
import mvp.model.Variable;

/**
 *
 * @author Eki
 */
public class GWRAnalyzeView extends HBox {

    Label modelLbl = getModelLbl();
    public ComboBox<String> modelCmb;
    Label xCoordinateLbl = getXCoordinateLbl();
    public ComboBox<String> xCoordinateCmb;
    Label yCoordinateLbl = getYCoordinateLbl();
    public ComboBox<String> yCoordinateCmb;
    Label kernelLbl = getKernelLbl();
    public ComboBox<String> kernelCmb;
    Label bandwidthLbl = getBandwidthLbl();
    public ComboBox<String> bandwidthCmb;
    Label selectionLbl = getSelectionLbl();
    public ComboBox<String> selectionCmb;
    Label depVarLbl = getDepVarLbl();
    public TextField depVarFld;
    public Button indVarBtn = getIndVarBtn();
    Label indVarLbl = getIndVarLbl();
    public TextArea indVarArea = getIndVarArea();
    public Button depVarBtn = getDepVarBtn();
    public Button okBtn = getOkBtn();
    public Button cancelBtn = getCancelBtn();

    private final Data data;
    private final Gwr gwr;

    public GWRAnalyzeView(Data data, Gwr gwr) {
        this.data = data;
        this.gwr = gwr;
        initGWRAnalyze();
        bindDataToModel();
    }

    private void initGWRAnalyze() {
        modelCmb = getModelCmb();
        xCoordinateCmb = getXCoordinateCmb(data);
        yCoordinateCmb = getYCoordinateCmb(data);
        kernelCmb = getKernelCmb();
        bandwidthCmb = getBandwidthCmb();
        selectionCmb = getSelectionCmb();
        depVarFld = getDepVarFld();

        VBox leftVBox = new VBox();
        VBox rightVBox = new VBox();

        HBox yVarHBox = new HBox();
        HBox xVarHBox = new HBox();
        HBox btnHBox = new HBox();

        VBox modelVBox = new VBox();
        modelVBox.setSpacing(5);
        modelVBox.getChildren().addAll(modelLbl, modelCmb);

        VBox xCoordVBox = new VBox();
        xCoordVBox.setSpacing(5);
        xCoordVBox.getChildren().addAll(xCoordinateLbl, xCoordinateCmb);

        VBox yCoordVBox = new VBox();
        yCoordVBox.setSpacing(5);
        yCoordVBox.getChildren().addAll(yCoordinateLbl, yCoordinateCmb);

        VBox kernelVBox = new VBox();
        kernelVBox.setSpacing(5);
        kernelVBox.getChildren().addAll(kernelLbl, kernelCmb);

        VBox bandwidthVBox = new VBox();
        bandwidthVBox.setSpacing(5);
        bandwidthVBox.getChildren().addAll(bandwidthLbl, bandwidthCmb);

        VBox selectionVBox = new VBox();
        selectionVBox.setSpacing(5);
        selectionVBox.getChildren().addAll(selectionLbl, selectionCmb);

        leftVBox.getChildren().addAll(modelVBox, xCoordVBox, yCoordVBox,
                kernelVBox, bandwidthVBox, selectionVBox);
        leftVBox.setPrefSize(250, 600);
        leftVBox.setSpacing(30);
        leftVBox.setStyle("-fx-padding : 10");

        yVarHBox.getChildren().addAll(depVarFld, depVarBtn);
        yVarHBox.setAlignment(Pos.BASELINE_LEFT);
        yVarHBox.setSpacing(5);
        xVarHBox.getChildren().addAll(indVarArea, indVarBtn);
        xVarHBox.setAlignment(Pos.TOP_LEFT);
        xVarHBox.setSpacing(5);
        btnHBox.getChildren().addAll(okBtn, cancelBtn);
        btnHBox.setAlignment(Pos.BASELINE_RIGHT);
        btnHBox.setSpacing(5);

        VBox yVarVBox = new VBox();
        yVarVBox.setSpacing(5);
        yVarVBox.getChildren().addAll(depVarLbl, yVarHBox);

        VBox xVarVBox = new VBox();
        xVarVBox.setSpacing(5);
        xVarVBox.getChildren().addAll(indVarLbl, xVarHBox);

        rightVBox.getChildren().addAll(yVarVBox, xVarVBox, btnHBox);
        rightVBox.setPrefSize(250, 600);
        rightVBox.setSpacing(30);
        rightVBox.setStyle("-fx-padding : 10");

        this.setStyle("-fx-background-color : white");
        this.getChildren().addAll(leftVBox, rightVBox);
    }

    private Label getModelLbl() {
        Label label = new Label("Model :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private ComboBox<String> getModelCmb() {
        ObservableList<String> list = FXCollections.observableArrayList("OLS", "Negative-Binomial");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");

        return cmb;
    }

    private Label getXCoordinateLbl() {
        Label label = new Label("X Coordinate :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private ComboBox<String> getXCoordinateCmb(Data data) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getStylesheets().add("resources/css/combobox.css");
        ObservableList<String> variablesName = FXCollections.observableArrayList();
        for (int i = 0; i < data.getVariables().size(); i++) {
            if ((!data.getShpPath().equalsIgnoreCase("empty")) && i==0) variablesName.add("From shapefiles");
            variablesName.add(data.getVariables().get(i).getName());
        }
        cmb.setItems(variablesName);

        return cmb;
    }

    private Label getYCoordinateLbl() {
        Label label = new Label("Y Coordinate :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private ComboBox<String> getYCoordinateCmb(Data data) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getStylesheets().add("resources/css/combobox.css");
        ObservableList<String> variablesName = FXCollections.observableArrayList();
        for (int i = 0; i < data.getVariables().size(); i++) {
            if ((!data.getShpPath().equalsIgnoreCase("empty")) && i==0) variablesName.add("From shapefiles");
            variablesName.add(data.getVariables().get(i).getName());
        }
        cmb.setItems(variablesName);

        return cmb;
    }

    private Label getKernelLbl() {
        Label label = new Label("Kernel Function :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private ComboBox<String> getKernelCmb() {
        ObservableList<String> list = FXCollections.observableArrayList("Fixed Gaussian", "Adaptive Gaussian");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");

        return cmb;
    }

    private Label getBandwidthLbl() {
        Label label = new Label("Bandwidth :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private ComboBox<String> getBandwidthCmb() {
        ObservableList<String> list = FXCollections.observableArrayList("Golden Section Search");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");

        return cmb;
    }

    private Label getSelectionLbl() {
        Label label = new Label("Selection :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private ComboBox<String> getSelectionCmb() {
        ObservableList<String> list = FXCollections.observableArrayList("AIC", "CV", "AICc");
        ComboBox<String> cmb = new ComboBox<>(list);
        cmb.getStylesheets().add("resources/css/combobox.css");

        return cmb;
    }

    private Label getDepVarLbl() {
        Label label = new Label("Dependent Variable :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private TextField getDepVarFld() {
        TextField field = new TextField();
        field.getStylesheets().add("resources/css/textfield.css");
        field.setDisable(true);
        field.setPrefWidth(200);

        return field;
    }

    private Button getDepVarBtn() {
        Button button = new Button("...");
        button.getStylesheets().add("resources/css/button.css");

        return button;
    }

    private Label getIndVarLbl() {
        Label label = new Label("Independent Variable :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private TextArea getIndVarArea() {
        TextArea area = new TextArea();
        area.getStylesheets().add("resources/css/textarea.css");
        area.setPrefSize(200, 275);
        area.setDisable(true);

        return area;
    }

    private Button getIndVarBtn() {
        Button button = new Button("...");
        button.getStylesheets().add("resources/css/button.css");

        return button;
    }

    private Button getOkBtn() {
        Button button = new Button("OK");
        button.getStylesheets().add("resources/css/button.css");

        return button;
    }

    private Button getCancelBtn() {
        Button button = new Button("Cancel");
        button.getStylesheets().add("resources/css/button.css");

        return button;
    }

    private void bindDataToModel() {
        depVarFld.textProperty().bindBidirectional(gwr.getDepVarProperty());
    }

    public void closeStage() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

}
