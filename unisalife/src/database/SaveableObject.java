/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import saving.Saveable;

/**
 * Wrapper class to encapsulate a Saveable object.
 *
 * @author alfon
 * @param <T> any objects that extends Saveable inferface
 */
public class SaveableObject<T extends Saveable> implements Serializable {

    private T innerObj;

    /**
     * Constructor that sets the inner object that needs to be encapsulated.
     * @param innerObj the object to encapsulate
     */
    public SaveableObject(T innerObj) {
        this.innerObj = innerObj;
    }

    private SaveableObject() {

    }

    /**
     * 
     * @return the encapsulated object in this "wrapper"
     */
    public T getInnerObj() {
        return innerObj;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return innerObj.getClass().getSimpleName();
    }
}
