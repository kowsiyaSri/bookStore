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
public class Book {
    
    //Book properties
    private String title;
    private String author;
    private double price;
    private final int STRING_LENGTH = 20;
    
    //default constructor
    public Book(){   
    }
    
    //constructor
    public Book(String t, String a, double p){
        setTitle(t);
        setAuthor(a);
        setPrice(p);
    }
    
    //accesor and mutator methods for title
    public void setTitle(String title){
        
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
    //accessor and mutator methods for author
    public void setAuthor(String author){
        this.author = author;
    }
    
    public String getAuthor(){
        return author;
    }
    
    //accessor and mutator methods for price
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return price;
    }
    
    @Override
    public String toString(){
        return"";
    }
}
