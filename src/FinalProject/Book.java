/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

/**
 *
 * @author Kowsiya Sri
 */
public class Book implements java.io.Serializable{
    
    //Book properties
    private String title;
    private String lastName;
    private String firstName;
    private double price;
    private final int NAME_LENGTH = 25;
    private final int BOOKNAME_LENGTH = 50;
    
    //default constructor
    public Book(){   
    }
    
    //constructor
    public Book(String title, String firstName, String lastName, double price){
        
        try{
            setTitle(title);
            setFirstName(firstName);
            setLastName(lastName);
            setPrice(price);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    //accesor and mutator methods for title
    public void setTitle(String title) throws InvalidNameException{
        if(title.length() <= BOOKNAME_LENGTH)
            this.title=title;
        else 
            throw new InvalidNameException(title);
    }
    
    public String getTitle(){
        return title;
    }
    
    //accessor and mutator methods for author
    public void setLastName(String lName) throws InvalidNameException{
        if(lName.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$") 
                && lName.length() <= NAME_LENGTH)
            this.lastName = lName;
        else 
            throw new InvalidNameException(lName);
    }
    
    public String getLastName(){
        return lastName;
    }
    
    //accessor and mutator methods for author
    public void setFirstName(String fName) throws InvalidNameException{
        if(fName.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")
                && fName.length() <= NAME_LENGTH)
            this.firstName = fName;
        else 
            throw new InvalidNameException(fName);
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    //accessor and mutator methods for price
    public void setPrice(double price){
        if(price >= 0)
            this.price = price;
        else
            System.out.println("Number must be greater than 0.");
    }
    
    public double getPrice(){
        return price;
    }
    
    //method that prints book title when to string is called
    @Override
    public String toString(){
        return getTitle();
    }
}