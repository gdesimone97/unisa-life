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

    public SaveableObject(T innerObj) {
        this.innerObj = innerObj;
    }

    private SaveableObject() {

    }

    public T getInnerObj() {
        return innerObj;
    }

    public String toString() {
        return innerObj.getClass().getSimpleName();
    }
}
