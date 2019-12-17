/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;

/**
 *
 * @author alfon
 * @param <T>
 */
public class SaveableObject<T> implements Serializable {

    private T innerObj;

    public SaveableObject(T innerObj) {
        this.innerObj = innerObj;
    }

    private SaveableObject() {

    }

    public T getInnerObj() {
        return innerObj;
    }

    public String toString(){
        return innerObj.getClass().getSimpleName();
    }
}
