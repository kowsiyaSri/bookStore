/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SearchFXMLController implements Initializable {

    //FXML Properties
    @FXML
    TextField txtSearch;
    
    @FXML
    Button btnExit;
    
    @FXML
    Label lblStatus, lblDisplay;
    
    //Java propterties
    private static ArrayList<Book> books;
    File file = new File("bookList.txt");
    FileInputStream fi;
    ObjectInputStream oi;   
    
    //handles search when button enter is pressed
    @FXML
    private void onEnter(ActionEvent event){
        
        String search = txtSearch.getText().trim();
        if(search.length() <= Book.BOOKNAME_LENGTH){
            openList();
            int result = 0;
            for(int i = 0; i < books.size(); i++){
                String b = books.get(i).getTitle();
                if(b.equals(search)){
                    Book temp = books.get(i);
                    String title = temp.getTitle();
                    String fName = temp.getFirstName();
                    String lName = temp.getLastName();
                    Double price = temp.getPrice();

                    lblDisplay.setText("Title: " + title + "\n" 
                            + "First Name: " + fName + "\n" + "Last Name: " +
                            lName + "\n" + "Price: " + String.valueOf(price));

                    lblStatus.setText("Search Complete: one match");
                    txtSearch.requestFocus();
                    result = 1;
                }
            }
            if(result == 0){                             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Not Found");
                alert.setHeaderText("Not Found");
                alert.setContentText("Could not find book from Search.");
                alert.show();
                lblDisplay.setText("");
                lblStatus.setText("Search Complete: No Match Found!");
                txtSearch.requestFocus();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Entry");
            alert.setContentText("Title should not be longer than 50 characters");
            alert.show(); 
            lblStatus.setText("Error: Search not completed");
            txtSearch.clear();
        }
    }
    
    //handles clear button action and clears all textfields 
    @FXML
    private void onClear(ActionEvent event){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Clear");
        alert.setContentText("Are you sure you want to clear?");
        Optional<ButtonType> result = alert.showAndWait();
        if((result.isPresent())&&(result.get()== ButtonType.OK)){
            txtSearch.clear();
            txtSearch.requestFocus();
            lblDisplay.setText("");
            lblStatus.setText("Feilds were cleared");
        }
    }
    
    @FXML
    private void onExit(ActionEvent event){
        
        try{
            if(fi!=null)fi.close();
            if(oi!=null)oi.close();
        }catch(IOException i){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("File error!");
            alert.show(); 
        }finally{
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        }
    }
    
    //creates array list of books from file
    private void openList(){
        
        try{
            books.clear();
            fi = new FileInputStream(file);
            oi = new ObjectInputStream(fi);

            while(true){
                Book b = (Book)oi.readObject();
                books.add(b);
            } 
        }catch(EOFException ex){
            System.out.println("End of file");
        }catch(Exception e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File Not Found");
            alert.setContentText("File or Class cannot be found.");
            alert.show();          
        }
        try{
            fi.close();
            oi.close();
        }catch(IOException i){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File Not Found");
            alert.setContentText("File or Class cannot be found.");
            alert.show();   
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        books = new ArrayList<>();
    }    
    
}
