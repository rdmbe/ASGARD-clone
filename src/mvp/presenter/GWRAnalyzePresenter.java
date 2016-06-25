package mvp.presenter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.model.Gwr;
import mvp.model.OlsGwr;
import mvp.view.DepVarSelectorView;
import mvp.view.GWRAnalyzeView;
import mvp.view.IndVarSelectorView;
import mvp.view.MainWindowView;
import mvp.view.OutputView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eki
 */
public class GWRAnalyzePresenter {
    private final GWRAnalyzeView view;
    private final MainWindowView mwview;
    private final Data data;
    private final Gwr gwr;
    
    public GWRAnalyzePresenter(GWRAnalyzeView view,MainWindowView mwview,Data data,Gwr gwr){
        this.view = view;
        this.mwview = mwview;
        this.data = data;
        this.gwr = gwr;
        attachEvent();
    }
    
    private void attachEvent(){
        view.modelCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            gwr.setModel(newValue);
        });
        
        view.xCoordinateCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            gwr.setXCoord(newValue);
        });
        
        view.yCoordinateCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            gwr.setYCoord(newValue);
        });
        
        view.kernelCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            gwr.setKernel(newValue);
        });
        
        view.bandwidthCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            gwr.setBandwidth(newValue);
        });
        
        view.selectionCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            gwr.setSelection(newValue);
        });
        
        view.depVarBtn.setOnAction(e -> showDepVarSelector());
        view.indVarBtn.setOnAction(e -> showIndVarSelector());
        view.okBtn.setOnAction(e -> makeVector());
        view.cancelBtn.setOnAction (e -> exitStage());
        
    }
    private void exitStage(){
        view.closeStage();
    }
    
    private void showDepVarSelector(){
        DepVarSelectorView dvsview = new DepVarSelectorView(data);
        DepVarSelectorPresenter dvspresenter = new DepVarSelectorPresenter(dvsview,view, null);
        Stage stage = new Stage();
        Scene scene = new Scene(dvsview,250,400);
        stage.setScene(scene);
        stage.setTitle("Dependent Variable Selector");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    private void showIndVarSelector(){
        IndVarSelectorView ivsview = new IndVarSelectorView(data);
        IndVarSelectorPresenter dvspresenter = new IndVarSelectorPresenter(ivsview,view,gwr,null,null);
        Stage stage = new Stage();
        Scene scene = new Scene(ivsview,250,400);
        stage.setScene(scene);
        stage.setTitle("Independent Variable Selector");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    private void makeVector(){
        int xCoordIdx = mwview.spreadsheet.getGrid().getColumnHeaders().indexOf(gwr.getXCoord());
        int yCoordIdx = mwview.spreadsheet.getGrid().getColumnHeaders().indexOf(gwr.getYCoord());
        int depVarIdx = mwview.spreadsheet.getGrid().getColumnHeaders().indexOf(gwr.getDepVar());
        
        ObservableList<Integer> indVarsIdx = FXCollections.observableArrayList();
        for(int i = 0;i < gwr.getIndVar().size();i++){
            indVarsIdx.add(mwview.spreadsheet.getGrid().getColumnHeaders().indexOf(gwr.getIndVar().get(i)));
        }
        
        ObservableList<Object> xCoordValues = FXCollections.observableArrayList();
        ObservableList<Object> yCoordValues = FXCollections.observableArrayList();
        ObservableList<Object> depVarValues = FXCollections.observableArrayList();
        for(int i = 0;i < data.getRowNumber();i++){
            xCoordValues.add(mwview.spreadsheet.getGrid().getRows().get(i).get(xCoordIdx).getItem());
            yCoordValues.add(mwview.spreadsheet.getGrid().getRows().get(i).get(yCoordIdx).getItem());
            depVarValues.add(mwview.spreadsheet.getGrid().getRows().get(i).get(depVarIdx).getItem());
        }
        
        ObservableList<ObservableList<Object>> indVarValues = FXCollections.observableArrayList();
        for(int i = 0;i < indVarsIdx.size();i++){
            ObservableList<Object> indvarValue = FXCollections.observableArrayList();
            for(int j = 0;j < data.getRowNumber();j++){
                indvarValue.add(mwview.spreadsheet.getGrid().getRows().get(j).get(indVarsIdx.get(i)).getItem());
            }
            indVarValues.add(indvarValue);
        }

        gwr.setxCoordValues(xCoordValues);
        gwr.setyCoordValues(yCoordValues);
        gwr.setDepVarValues(depVarValues);
        gwr.setIndVarValues(indVarValues);

        gwr.calculate();
        
        OutputView outview = new OutputView(data);
        OutputPresenter outpresenter = new OutputPresenter(outview,view,null);
    
        Stage stage = new Stage();
        Scene scene = new Scene(outview,1600,700);
        stage.setScene(scene);
        stage.setTitle("Output ASGARD");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        outview.setTextArea(0,gwr.hasil());
        
        view.closeStage();
    }
    
   public void ShowOutputView(){
        
     OutputView outview = new OutputView(data);
     OutputPresenter outpresenter = new OutputPresenter(outview,view,null);
    
        Stage stage = new Stage();
        Scene scene = new Scene(outview,1600,700);
        stage.setScene(scene);
        stage.setTitle("Output ASGARD");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
}
}
