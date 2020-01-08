/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 * This exception is thrown when a line in the data file doesn't follow the required 
 * structure "[type of object] [parameters]" 
 * @author cmarino
 */
public class InvalidGameDataFormatException extends Exception {

    /**
     * Construct the exception including details as message field of Exception
     */
    public InvalidGameDataFormatException() {
        super("Invalid or missing fields in game data file");
    }
    
}
