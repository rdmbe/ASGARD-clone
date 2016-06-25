package mvp.presenter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvp.model.Data;
import mvp.model.Lm;
import mvp.view.DepVarSelectorView;
import mvp.view.LMAnalyzeView;
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
public class LMAnalyzePresenter {
    private final LMAnalyzeView view;
    private final MainWindowView mwview;
    private final Data data;
    private final Lm lm;
    
    
    public LMAnalyzePresenter(LMAnalyzeView view,MainWindowView mwview,Data data,Lm lm ){
        this.view = view;
        this.mwview = mwview;
        this.data = data;
        this.lm = lm;
        
        attachEvent();
    }
    
    private void attachEvent(){
        view.modelCmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            lm.setModel(newValue);
        });
        
        
        view.depVarBtn.setOnAction(e -> showDepVarSelector());
        view.indVarBtn.setOnAction(e -> showIndVarSelector());
        view.okBtn.setOnAction(e ->  makeVector());
        view.cancelBtn.setOnAction(e -> exitStage());
    }
    
    private void exitStage(){
        view.closeStage();
    }
    
    private void showDepVarSelector(){
        DepVarSelectorView dvsview = new DepVarSelectorView(data);
        DepVarSelectorPresenter dvspresenter = new DepVarSelectorPresenter(dvsview,null,view);
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
        IndVarSelectorPresenter dvspresenter = new IndVarSelectorPresenter(ivsview,null,null,view,lm);
        Stage stage = new Stage();
        Scene scene = new Scene(ivsview,250,400);
        stage.setScene(scene);
        stage.setTitle("Independent Variable Selector");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    private void makeVector(){
        
        try {
            int depVarIdx = mwview.spreadsheet.getGrid().getColumnHeaders().indexOf(lm.getDepVar());
            
            ObservableList<Integer> indVarsIdx = FXCollections.observableArrayList();
            for(int i = 0;i < lm.getIndVar().size();i++){
                indVarsIdx.add(mwview.spreadsheet.getGrid().getColumnHeaders().indexOf(lm.getIndVar().get(i)));
            }

            ObservableList<Object> depVarValues = FXCollections.observableArrayList();
            for(int i = 0;i < data.getRowNumber();i++){
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
            
            lm.setDepVarValues(depVarValues);
            lm.setIndVarValues(indVarValues);
            lm.calculate();
            
            OutputView outview = new OutputView(data);
            OutputPresenter outpresenter = new OutputPresenter(outview,null,view);
            
    
        Stage stage = new Stage();
        Scene scene = new Scene(outview,1600,680);
        stage.setScene(scene);
        stage.setTitle("Output ASGARD");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        outview.setTextArea(0, lm.hasil());
            
            view.closeStage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LMAnalyzePresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
 public void ShowOutputView(){
        OutputView outview = new OutputView(data);
     OutputPresenter outpresenter = new OutputPresenter(outview,null,view);
    
        Stage stage = new Stage();
        Scene scene = new Scene(outview,1600,700);
        stage.setScene(scene);
        stage.setTitle("Output ASGARD");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
     
}
}
