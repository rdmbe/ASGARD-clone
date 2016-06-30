/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.presenter;

import mvp.view.OutputView;
import mvp.view.GWRAnalyzeView;
import mvp.view.LMAnalyzeView;

/**
 *
 * @author Eki
 */
public class OutputPresenter {
    
    private final OutputView view;
    private final GWRAnalyzeView gaview;
    private final LMAnalyzeView laview;
    
    public OutputPresenter(OutputView view,GWRAnalyzeView gaview,LMAnalyzeView laview ){
        this.view = view;
        this.gaview = gaview;
        this.laview = laview;
        attachEvent();
    }
    
    private void attachEvent(){
        view.okBtn.setOnAction(e -> selectingItem());
        view.cancelBtn.setOnAction(e -> view.closeStage());
        }
    
    private void selectingItem(){
        view.closeStage();
    }

}
