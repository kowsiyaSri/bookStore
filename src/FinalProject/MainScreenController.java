package FinalProject;

/*
 * should we add book ID instead of comparing titles?
 */

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Kowsiya Sri
 */
public class MainScreenController implements Initializable {

    //FXML properties
    @FXML
    ListView lvDisplay;
    
    @FXML
    TextField txtTitle, txtFirstName, txtLastName, txtPrice;
            
    @FXML
    Button btnExit;
    
    @FXML
    Label lblStatus;
    
    //java properties 
    private static ArrayList<Book> books;
    File file = new File("bookList.txt");
    FileOutputStream fo;
    FileInputStream fi;
    ObjectInputStream oi;
    ObjectOutputStream os; 
    
    //handles exit button action
    @FXML
    private void onExit(ActionEvent event) throws IOException{
        if(fi!=null)fi.close();
        if(oi!=null)oi.close();
        if(os!=null)os.close();
        if(fo!=null)fo.close();
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    //handles add button action and adds new Book to file
    @FXML
    private void onAdd(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException{
        if(file.length() != 0)
            openList();
        try{
            String title = txtTitle.getText().trim();
            String fName = txtFirstName.getText().trim();
            String lName = txtLastName.getText().trim();
            String price = txtPrice.getText().trim();

            if(title.equals("")== false && fName.equals("")== false &&
                lName.equals("")== false && price.equals("")== false){
                if(txtTitle.isEditable() == true){
                    if(price.matches("^(?:0|[1-9]\\d{0,2})(?:\\.([0-9]{2}|[0-9]{1}))?$")){
                        Book b = new Book();
                        b.setTitle(title);
                        b.setFirstName(fName);
                        b.setLastName(lName);
                        b.setPrice(Double.parseDouble(price));
                        books.add(b);
                        saveBooks();
                        lblStatus.setText("Book Added Successfully!");
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Invalid Entry");
                        alert.setContentText("Please enter a price from $0.00 - $999.99! \n" 
                            + " And no leading zeros.");
                        alert.show(); 
                        lblStatus.setText("Error: Book not added!");
                        txtPrice.requestFocus();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Cannot Duplicate");
                    alert.setContentText("Cannot add book already on list. \n" +
                            "To update book please select UPDATE.");
                    alert.show(); 
                    lblStatus.setText("Error: Book not added!");
                    txtTitle.setEditable(true);
                    txtFirstName.setEditable(true);
                    txtLastName.setEditable(true);
                    txtPrice.setEditable(true);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Entry");
                alert.setContentText("All feilds must be filled!");
                alert.show(); 
                lblStatus.setText("Error: Book not added!");
            }
            
            txtTitle.clear();
            txtFirstName.clear();
            txtLastName.clear();
            txtPrice.clear();
            txtTitle.requestFocus();  
        }catch (InvalidNameException n){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Entry");
            alert.setContentText("Title should not be longer than 50 characters and"
                + " name feilds cannot be longer than 25 characters(no numbers)" );
            alert.show(); 
            lblStatus.setText("Error: Book not added!");
            txtTitle.requestFocus();
        }catch (Exception e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Entry");
            alert.setContentText("Please enter a price from $0.00 - $999.99. \n" 
                + "No other characters other than digits.");
            alert.show(); 
            lblStatus.setText("Error: Book not added!");
            txtPrice.requestFocus();
        }
        
    }
    
    //handles display button and display listview of books
    @FXML
    private void onDisplay(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{ 

        if(file.length() > 0){
            openList();
            ObservableList<Book> items = FXCollections.observableArrayList(books);
            lvDisplay.setItems(items);    
        }
        
        if(books.isEmpty() || file.length()== 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("There is no list to display.");
            alert.show(); 
            txtTitle.requestFocus();
        }
    }
    
    //handles search button action and opens new window to search array
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
    
    //handles clear button action and clears all textfields 
    @FXML
    private void onClear(ActionEvent event){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Clear");
        alert.setContentText("Are you sure you want to clear?");
        Optional<ButtonType> result = alert.showAndWait();
        if((result.isPresent())&&(result.get()== ButtonType.OK)){
            txtTitle.clear();
            txtFirstName.clear();
            txtLastName.clear();
            txtPrice.clear();
            txtTitle.setEditable(true);
            txtFirstName.setEditable(true);
            txtLastName.setEditable(true);
            txtPrice.setEditable(true);
            txtTitle.requestFocus();
            lblStatus.setText("Feilds were cleared");
        }
    }
    
    // handles selection from listview
    @FXML
    private void onSelection(MouseEvent event){
        try{
            if(lvDisplay.getSelectionModel().isEmpty()){
                txtTitle.requestFocus();
            }
            Book temp = (Book)lvDisplay.getSelectionModel().getSelectedItem();
            lblStatus.setText(temp.toString());
            
            txtTitle.setEditable(false);
            txtTitle.setText(temp.getTitle());
            txtFirstName.setText(temp.getFirstName());
            txtLastName.setText(temp.getLastName());
            txtPrice.setText(String.valueOf(temp.getPrice()));
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("There is no list to select.");
            alert.show();         
        }
    }
    
    //Handles delete button and deletes book from record
    @FXML
    private void onDelete(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Are you sure you want to DELETE the record?");
        Optional<ButtonType> result = alert.showAndWait();
        if((result.isPresent())&&(result.get()== ButtonType.OK)){
            openList();
            for(int i = 0; i < books.size(); i++){
                String b = books.get(i).getTitle();
                if(b.equals(txtTitle.getText())){
                    books.remove(i);
                    txtTitle.clear();
                    txtFirstName.clear();
                    txtLastName.clear();
                    txtPrice.clear();
                }
            }
            saveBooks();
            lblStatus.setText("Record Deleted! To refresh list press Display List.");
        }
    }
    
    //allows user to update the file
    @FXML
    private void onUpdate(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Are you sure you want to Update the record?");
        Optional<ButtonType> result = alert.showAndWait();
        if((result.isPresent())&&(result.get()== ButtonType.OK)){
            openList();
            int index = books.size();
            for(int i = 0; i < books.size(); i++){
                String b = books.get(i).getTitle();
                if(b.equals(txtTitle.getText())){
                    index = i;
                }                
            }

            try{
                String fName = txtFirstName.getText().trim();
                String lName = txtLastName.getText().trim();
                String price = txtPrice.getText();

                if(fName.equals("")== false && lName.equals("")== false && price.equals("")== false){
                    if(price.matches("^(?:0|[1-9]\\d{0,2})(?:\\.([0-9]{2}|[0-9]{1}))?$")){
                        Book b = books.get(index);
                        b.setFirstName(fName);
                        b.setLastName(lName);
                        b.setPrice(Double.parseDouble(price));
                        saveBooks();
                        lblStatus.setText("Book Updated Successfully! " +
                                "To view update please press Display List and select book.");
                    }else{
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Error");
                        alert1.setHeaderText("Invalid Entry");
                        alert1.setContentText("Please enter a price from $0.00 - $999.99! \n" 
                            + " And no leading zeros.");
                        alert1.show(); 
                        lblStatus.setText("Error: Book not added!");
                        txtPrice.requestFocus();
                    }
                }else{
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setHeaderText("Invalid Entry");
                    alert2.setContentText("All feilds must be filled!");
                    alert2.show(); 
                    lblStatus.setText("Error: Book not Updated!");
                }          
                txtTitle.clear();
                txtFirstName.clear();
                txtLastName.clear();
                txtPrice.clear();
                txtTitle.requestFocus();  

            }catch (InvalidNameException n){
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Error");
                alert3.setHeaderText("Invalid Entry");
                alert3.setContentText("Title should not be longer than 50 characters and"
                    + " name feilds cannot be longer than 25 characters(no numbers)" );
                alert3.show(); 
                lblStatus.setText("Error: Book not updated!");
                txtTitle.requestFocus();
            }catch (Exception e){

                Alert alert4 = new Alert(Alert.AlertType.ERROR);
                alert4.setTitle("Error");
                alert4.setHeaderText("Invalid Entry");
                alert4.setContentText("Please enter a price from $0.01 - $1000.00");
                alert4.show(); 
                lblStatus.setText("Error: Book not updated!");
                txtPrice.requestFocus();
            }  
        }
    }
    
    //saves arraylist of books in a file
    private void saveBooks(){
        
        try{
            
            fo = new FileOutputStream(file);
            os = new ObjectOutputStream(fo);
            for(Book b: books)
                os.writeObject(b);
            
            os.close();
            fo.close();
            
        }catch (Exception e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("File does not exist");
            alert.show(); 
        }
    }
    
    //creates array list of books from file
    private void openList() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        books.clear();
        fi = new FileInputStream(file);
        oi = new ObjectInputStream(fi);

        try{
            while(true){
                Book b = (Book)oi.readObject();
                books.add(b);
            }                
        }catch(EOFException ex){} 

        fi.close();
        oi.close();
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        books = new ArrayList<>();
    }    
    
}
