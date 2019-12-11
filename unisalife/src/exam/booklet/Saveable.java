/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.booklet;

import java.io.Serializable;

/**
 *
 * @author liovi
 */
public interface Saveable {
    public Serializable save();
    public void load (Serializable obj);
}
