/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.presenter;

import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import mvp.model.Gwr;
import mvp.view.DepVarSelectorView;
import mvp.view.GWRAnalyzeView;
import mvp.view.IndVarSelectorView;

/**
 *
 * @author Eki
 */
public class IndVarSelectorPresenter {
    private final IndVarSelectorView view;
    private final GWRAnalyzeView gaview;
    private final Gwr gwr;
    
    public IndVarSelectorPresenter(IndVarSelectorView view,GWRAnalyzeView gaview,Gwr gwr){
        this.view = view;
        this.gaview = gaview;
        this.gwr = gwr;
        attachEvent();
    }
    
    private void attachEvent(){
        view.okBtn.setOnAction(e -> selectingItems());
        view.cancelBtn.setOnAction(e -> view.closeStage());
    }
    
    private void selectingItems(){
        ObservableList<String> indVar = FXCollections.observableArrayList();
        StringBuilder variables = new StringBuilder();
        for(String key: view.names.keySet()) {
            ObservableValue<Boolean> value = view.names.get(key);
            if (value.getValue()) {
                indVar.add(key);
                variables.append(key);
                variables.append("\n");
            }
        }
        
        gwr.setIndVar(indVar);
        
        gaview.indVarArea.setText(variables.toString());
        
        view.closeStage();
    }
}
