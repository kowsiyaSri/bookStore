/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Mary Fahmy & Kowsiya Sri
 */
public class FinalProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            public void handle(WindowEvent event){
                System.out.println("on hiding event");           
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm");
                alert.setContentText("Are you sure you want to close?");
                Optional<ButtonType> result = alert.showAndWait();
                if((result.isPresent())&&(result.get()== ButtonType.OK)){
                   
                }else{
                    event.consume();
                }  
            }
        });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
