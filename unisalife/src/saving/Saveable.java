/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import java.io.Serializable;

/**
 *
 * @author 
 */
public interface Saveable {
    public Serializable save();
    public void load(Serializable obj);
}
