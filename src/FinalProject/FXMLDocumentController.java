/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kowsiya Sri
 */
public class FXMLDocumentController implements Initializable {
    
    
    //method handles action of login button
    @FXML
    private void onLogin(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("MainScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("New Window");
        stage.setScene(new Scene(root1));
        stage.setMaximized(true);
        stage.show(); 
    }
    
    //method handle action of EXit menu item
    @FXML
    private void onExit(ActionEvent event){
       System.exit(0);
    }
    
    //method handles action of About us menu item
    @FXML
    private void onAbout(ActionEvent event){
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Us");
        alert.setHeaderText("Bookstore Inventory");
        alert.setContentText("This program was created to help bookstore management "
                + "keep track of inventory levels at bookstore that only has "
                + "one of a kind books. Employees can search, add and remove books "
                + "from the inventory list using the various features.");
        alert.show();
    }
    
    //method handles action of Contact us menu itme
    @FXML
    private void onContact(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact Us");
        alert.setHeaderText("Bookstore Inventory");
        alert.setContentText("Email : bookstore@inventory.com \n" 
                + "Telephone: 1-888-881-6657");
        alert.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
