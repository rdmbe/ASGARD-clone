/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp.view;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvp.model.Data;

/**
 *
 * @author Eki
 */
public class OutputView extends VBox {

    private TextArea[] textArea;
    public TabPane tabPane;
    public Button okBtn;
    public Button cancelBtn;
    private final Data data;
    private int indVarSize;
    private Image img;

    public OutputView(Data data) {
        this.data = data;
        initFrame();
    }

    public OutputView(Data data, int ivs) {
        this.data = data;
        this.indVarSize = ivs;
        initFrameMap();
    }

    private TabPane getBar() {
        TabPane tabPane = new TabPane();
        textArea = new TextArea[2];
        for (int i = 0; i < 2; i++) {
            Tab tab = new Tab();
            VBox topBox = new VBox();
            textArea[i] = new TextArea();
            if (i == 0) {
                tab.setText("Result");
            }
            if (i == 1) {
                tab.setText("Graph");
            }

            textArea[i].getScrollLeft();
            textArea[i].setPrefSize(1000, 600);

            topBox.getChildren().add(textArea[i]);

            topBox.setAlignment(Pos.CENTER);
            tab.setContent(topBox);
            tabPane.getTabs().add(tab);
        }
        return tabPane;
    }

    private TabPane getBarMap() {
        TabPane tabPane = getBar();
        
        Tab tab = new Tab();
        tab.setText("Map");

        VBox upperTopBox = new VBox(getCmbPeta());
        upperTopBox.setAlignment(Pos.TOP_CENTER);
        upperTopBox.setSpacing(5);

        VBox lowerTopBox = new VBox();
        Pane canvas = new Pane();
//        File file = new File(System.getProperty("user.dir") + "/src/resources/maps/peta_b0.png");
//        Image image = new Image(file.toURI().toString());
        ImageView iv = new ImageView(getImg());
        canvas.getChildren().add(iv);
        canvas.setPrefSize(1000, 600);
        lowerTopBox.getChildren().add(canvas);
        lowerTopBox.setAlignment(Pos.BOTTOM_CENTER);
        lowerTopBox.setSpacing(5);

        VBox topBox = new VBox(upperTopBox,lowerTopBox);
        topBox.setAlignment(Pos.CENTER);
        tab.setContent(topBox);

        tabPane.getTabs().add(tab);
        return tabPane;
    }

    private void initFrame() {
        tabPane = getBar();
        okBtn = getOkBtn();
        cancelBtn = getCancelBtn();

        HBox bottomBox = new HBox(okBtn, cancelBtn);
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);
        bottomBox.setSpacing(0);
        bottomBox.setPadding(new Insets(5, 5, 5, 5));
        bottomBox.setStyle("-fx-background-color: #cce5ff;"
                + "-fx-border-width: 0.3 0 0 0");

        HBox topBox = new HBox(tabPane);
        topBox.setSpacing(5);
        topBox.setStyle("-fx-background-color: #cce5ff;");
        topBox.setAlignment(Pos.TOP_LEFT);

        this.getChildren().addAll(topBox, bottomBox);
    }

    private void initFrameMap() {
        tabPane = getBarMap();
        okBtn = getOkBtn();
        cancelBtn = getCancelBtn();

        HBox bottomBox = new HBox(okBtn, cancelBtn);
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);
        bottomBox.setSpacing(0);
        bottomBox.setPadding(new Insets(5, 5, 5, 5));
        bottomBox.setStyle("-fx-background-color: #cce5ff;"
                + "-fx-border-width: 0.3 0 0 0");

        HBox topBox = new HBox(tabPane);
        topBox.setSpacing(5);
        topBox.setStyle("-fx-background-color: #cce5ff;");
        topBox.setAlignment(Pos.TOP_LEFT);

        this.getChildren().addAll(topBox, bottomBox);
    }

    private Label getTitleLbl() {
        Label label = new Label("Output :");
        label.getStylesheets().add("resources/css/label.css");

        return label;
    }

    private Button getOkBtn() {
        Button btn = new Button("Save");
        btn.getStylesheets().add("resources/css/button.css");

        return btn;
    }

    private Button getCancelBtn() {
        Button btn = new Button("Print");
        btn.getStylesheets().add("resources/css/button.css");

        return btn;
    }

    public void closeStage() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

    public void setTextArea(int indexTab, String teks) {
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
        Menu openMenu = new Menu("Open", new ImageView(new Image("resources/images/sc_open.png")));
        return menu;
    }

    private Menu getMapMenu() {
        Menu menu = new Menu("Map");
        Menu openMenu = new Menu("Open", new ImageView(new Image("resources/images/sc_open.png")));
        return menu;
    }

    private Menu getGraphMenu() {
        Menu menu = new Menu("Graph");
        Menu openMenu = new Menu("Open", new ImageView(new Image("resources/images/sc_open.png")));
        return menu;
    }

    /**
     * @return the cmbPeta
     */
    public ComboBox<String> getCmbPeta() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < indVarSize; i++) {
            list.add("Koefisien GWR b" + (i + 1));
        }
        ComboBox cmbPeta = new ComboBox<>(list);
        cmbPeta.setPromptText(list.get(0));
        cmbPeta.getStylesheets().add("resources/css/combobox.css");

        return cmbPeta;
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image img) {
        this.img = img;
    }
}
