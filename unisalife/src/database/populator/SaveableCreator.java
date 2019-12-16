/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import exam.booklet.Saveable;

/**
 *
 * @author cmarino
 */
public abstract class SaveableCreator {
    
    
    static final String DELIMETER = "%";
    /**
    * 
    * @param s The string to tokenize in order to get the constructor parameters for every object that needs to be created
     * @return a Saveable reference to the object that has just been created
     * @throws database.populator.exceptions.InvalidArgumentListException If the list of arguments doesn't contain all the required arguments needed for the creation of the object
    */    
    public abstract Saveable create( String s );
    
}
