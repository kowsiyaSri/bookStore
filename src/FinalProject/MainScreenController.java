package FinalProject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
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
    ListView lvDisplay;
    
    @FXML
    TextField txtTitle, txtFirstName, txtLastName, txtPrice;
            
    @FXML
    Button btnExit;
    
    private static ArrayList<Book> bookList = new ArrayList<>();
    
    @FXML
    private void onExit(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onAdd(ActionEvent event){
        
        Book b = new Book();
        b.setTitle(txtTitle.getText());
        b.setFirstName(txtFirstName.getText());
        b.setLastName(txtLastName.getText());
        b.setPrice(Double.parseDouble(txtPrice.getText()));
        bookList.add(b);

    }
    
    @FXML
    private void onSave(ActionEvent event){
        
    }
    
    @FXML
    private void onDisplay(ActionEvent event){
        ObservableList<Book> items = FXCollections.observableArrayList(bookList);
        lvDisplay.setItems(items);    
    }
    
    @FXML
    private void onSearch(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("SearchFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Search");
        stage.setScene(new Scene(root1));
        stage.setMaximized(true);
        stage.show(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
