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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.model.Variable;

/**
 *
 * @author Eki
 */
public class DepVarSelectorView extends VBox{
    
    public Label titleLbl;
    public ListView<String>listView;
    public Button okBtn;
    public Button cancelBtn;
    
    private final Data data;
    
    public DepVarSelectorView(Data data){
        this.data = data;
        initFrame();
    }
    
    private void initFrame(){
        titleLbl = getTitleLbl();
        listView = getListView(data);
        okBtn = getOkBtn();
        cancelBtn = getCancelBtn();
        
        HBox btnHBox = new HBox();
        btnHBox.setSpacing(10);
        btnHBox.setAlignment(Pos.BASELINE_RIGHT);
        btnHBox.getChildren().addAll(okBtn,cancelBtn);
        
        this.setSpacing(10);
        this.setStyle("-fx-padding : 10;");
        this.getChildren().addAll(titleLbl,listView,btnHBox);
    }
   
    private Label getTitleLbl(){
        Label label = new Label("Dependent Variable :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
    private ListView<String> getListView(Data data){
        ListView<String> listView = new ListView<>();
        listView.setPrefSize(200, 350);
        ObservableList<String> names = FXCollections.observableArrayList();
        for(int i = 0;i < data.getVariables().size();i++){
            names.add(data.getVariables().get(i).getName());
        }
        listView.setItems(names);
        listView.getStylesheets().add("resources/css/listview.css");
        
        return listView;
    }
    
    private Button getOkBtn(){
        Button btn = new Button("OK");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    private Button getCancelBtn(){
        Button btn = new Button("Cancel");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    public void closeStage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
}
