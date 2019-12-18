/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator.exceptions;

/**
 *
 * @author cmarino
 */
public class InvalidGameDataFormatException extends Exception {

    public InvalidGameDataFormatException() {
        super("Invalid or missing fields in game data file");
    }
    
}
