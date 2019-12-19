/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

/**
 *
 * @author Davide e Virginia
 * interface for common method of different JukeBox
 */

interface JukeBox {
    
    public abstract void play(String s);   
    public abstract void readFile(String path)throws Exception;
    public abstract void setVolume(String s, float f);
    public abstract void load(String path, String key) throws Exception;    
}
