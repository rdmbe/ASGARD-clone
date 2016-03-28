package mvp.presenter;

import javafx.scene.Scene;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.view.DepVarSelectorView;
import mvp.view.GWRAnalyzeView;

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
    private Data data;
    
    public GWRAnalyzePresenter(GWRAnalyzeView view,Data data){
        this.view = view;
        this.data = data;
        attachEvent();
    }
    
    private void attachEvent(){
        view.depVarBtn.setOnAction(e -> showDepVarSelector());
    }
    
    private void showDepVarSelector(){
        DepVarSelectorView dvsview = new DepVarSelectorView(data);
        DepVarSelectorPresenter dvspresenter = new DepVarSelectorPresenter(dvsview);
        Stage stage = new Stage();
        Scene scene = new Scene(dvsview,250,400);
        stage.setScene(scene);
        stage.setTitle("Dependent Variable Selector");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
