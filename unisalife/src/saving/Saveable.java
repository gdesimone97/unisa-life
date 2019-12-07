/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import java.io.Serializable;
import saving.exceptions.*;

/**
 * This interface declares two methods in order to perform the saving and
 * loading operations
 *
 * @author
 */
public interface Saveable {

    /**
     * This method have to be implemented by sub-classes.
     *
     * @return seriarizable object containig the objects that sub-classes want
     * to save
     */
    public Serializable save();

    /**
     * This method have to be implemented by sub-classes.
     *
     * @param obj containing the object that sub-class has saved and they want
     * load it
     * @throws LoadingException if some errors occur during the loading process
     */
    public void load(Serializable obj) throws LoadingException;
}
