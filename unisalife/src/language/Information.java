/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

/**
 * Interface to implement a strategy pattern. This will be implemented by all the game objects that aim to print something.
 * @author alfon
 */
public interface Information {
    
    /**
     * Method to get the most rapresentative string for the caller (instance of Information).
     * This will be used to make a query in the current language pack.
     * @return The desired string
     */
    String getInfo();
    
    
}
