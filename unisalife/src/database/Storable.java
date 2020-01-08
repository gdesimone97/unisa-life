/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 * Marker interface to detect objects to be stored in the database
 *
 * @author alfon
 */
public interface Storable {

    /**
     * Gets the index of the object to retrive it from database.
     * 
     * @return an int that represents the index of the storable object 
     */
    public String getIndex();

}
