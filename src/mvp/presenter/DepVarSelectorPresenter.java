/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.presenter;

import mvp.view.DepVarSelectorView;
import mvp.view.GWRAnalyzeView;

/**
 *
 * @author Eki
 */
public class DepVarSelectorPresenter {
    
    private final DepVarSelectorView view;
    private final GWRAnalyzeView gaview;
    
    public DepVarSelectorPresenter(DepVarSelectorView view,GWRAnalyzeView gaview){
        this.view = view;
        this.gaview = gaview;
        attachEvent();
    }
    
    private void attachEvent(){
        view.okBtn.setOnAction(e -> selectingItem());
        view.cancelBtn.setOnAction(e -> view.closeStage());
    }
    
    private void selectingItem(){
        gaview.depVarFld.setText(view.listView.getSelectionModel().getSelectedItem());
        view.closeStage();
    }

}
