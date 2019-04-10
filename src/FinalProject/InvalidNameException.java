/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

/**
 *
 * @author Mary Fahmy & Kowsiya Sri
 */
public class InvalidNameException extends Exception{
    
    private String name;
    
    public InvalidNameException(String name){    
        super("Invalid Name " + name);
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
