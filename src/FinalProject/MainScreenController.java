package FinalProject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Kowsiya Sri
 */
public class MainScreenController implements Initializable {

    @FXML
    private void onAdd(ActionEvent event)throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("AddNewBook.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(new Scene(root1));
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.show(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
