/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mvp.presenter.MainWindowPresenter;
import mvp.view.MainWindowView;

/**
 *
 * @author Eki
 */
public class ASGARD extends Application {    
    BooleanProperty ready = new SimpleBooleanProperty(false);
    Scene scene;

    @Override
    public void init() throws Exception {
        longStart();  
        MainWindowView mainWindowView = new MainWindowView();
        MainWindowPresenter mainWindowPresenter = new MainWindowPresenter(mainWindowView);
        scene = new Scene(mainWindowView);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(visualBounds.getMinX());
        primaryStage.setY(visualBounds.getMinY());
        primaryStage.setWidth(visualBounds.getWidth());
        primaryStage.setHeight(visualBounds.getHeight());
       
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/resources/images/icon.png"));
        primaryStage.setTitle("Asgard - Geographically Statistics Software");

        ready.addListener( (ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {  
            if ( Boolean.TRUE.equals( t1 ) ) {  
                Platform.runLater( primaryStage::show );  
            }  
        } );;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void longStart() {  
        Task<Void> task = new Task<Void>() {  
            @Override  
            protected Void call() throws Exception {  
                int max = 5;  
                for ( int i = 1; i <= max; i++ ) {  
                    Thread.sleep( 1000 );  
                    notifyPreloader( new Preloader.ProgressNotification( ( ( double ) i ) / max ) );  
                }  
                ready.setValue( Boolean.TRUE );  
                notifyPreloader( new Preloader.StateChangeNotification(  
                Preloader.StateChangeNotification.Type.BEFORE_START ) );  

                return null;  
            }  
        };  
        new Thread( task ).start();  
    }
}
