/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvp.model.Data;
import mvp.model.Variable;

/**
 *
 * @author Eki
 */
public class OutputView extends BorderPane{
    
    
    private TextArea[] textArea;
    public TabPane tabPane;
    public Button okBtn;
    public Button cancelBtn;
    private final Data data;
   
    public OutputView(Data data){
        this.data = data;
        initFrame();
    }
    

    private TabPane getBar(){
        TabPane tabPane = new TabPane();
        textArea = new TextArea[3];
        for (int i = 0; i < 3; i++) {
            Tab tab = new Tab();      
            
            
            HBox hbox = new HBox();
            textArea[i] = new TextArea();
            if(i==0){tab.setText("Result");}
            if(i==1){tab.setText("Map");}
            if(i==2){tab.setText("Graph");}
            
            textArea[i].getScrollLeft();
            textArea[i].setPrefSize(1000, 600);
            
            hbox.getChildren().add(textArea[i]);
            hbox.setAlignment(Pos.BASELINE_LEFT);
            tab.setContent(hbox);
            tabPane.getTabs().add(tab);
        }
        return tabPane;
    }
    
    
    private void initFrame(){
        tabPane = getBar();
        okBtn = getOkBtn();
        cancelBtn = getCancelBtn();
        
        HBox bottomHBox = new HBox(okBtn,cancelBtn);
        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setSpacing(5);
        bottomHBox.setPadding(new Insets(5, 5, 5, 5));
        bottomHBox.setStyle("-fx-background-color: white;"+
                "-fx-border-width: 0.3 0 0 0");
        
       
        VBox topVBox = new VBox(getBar());
        topVBox.setStyle("-fx-background-color: #cce5ff;");
              
        this.setTop(topVBox);
        this.setBottom(bottomHBox);
    }
   
    private Label getTitleLbl(){
        Label label = new Label("Output :");
        label.getStylesheets().add("resources/css/label.css");
        
        return label;
    }
    
  
    
    private Button getOkBtn(){
        Button btn = new Button("Save");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    private Button getCancelBtn(){
        Button btn = new Button("Print");
        btn.getStylesheets().add("resources/css/button.css");
        
        return btn;
    }
    
    public void closeStage(){
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
    
    public void setTextArea(int indexTab, String teks){
        TextArea text = textArea[indexTab];
        text.setText(teks);
//        text.setPrefSize(1000, 600);
//        text.getScrollLeft();
    }

    private TextArea getTextArea() {
        TextArea textView = new TextArea();
        textView.setPrefSize(1000, 600);
//        ObservableList<String> names = FXCollections.observableArrayList();
//        for(int i = 0;i < data.getVariables().size();i++){
//            names.add(data.getVariables().get(i).getName());
//        }
       textView.getScrollLeft();
       
//       textView.getStylesheets().add("resources/css/listview.css");
        
        return textView;
    }

    private Menu getResultMenu() {
         Menu menu = new Menu("Result");
         Menu openMenu = new Menu("Open",new ImageView(new Image("resources/images/sc_open.png")));
        return menu;
    }

    private Menu getMapMenu() {
         Menu menu = new Menu("Map");
         Menu openMenu = new Menu("Open",new ImageView(new Image("resources/images/sc_open.png")));
        return menu;
    }

    private Menu getGraphMenu() {
        Menu menu = new Menu("Graph");
         Menu openMenu = new Menu("Open",new ImageView(new Image("resources/images/sc_open.png")));
        return menu;
    }

    
}
