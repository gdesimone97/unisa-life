/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.util.HashMap;

/**
 *
 * @author simon
 */
public class ObjectManager extends HashMap<Position,GameObject> {
    public GameObject getObjectInNextPosition(Position p){
        return this.get(p);
    }
    
    public synchronized GameObject removeObject(Position p)throws Exception{
        if(this.containsKey(p))
            return this.remove(p);
        else
            throw new Exception();
     
    }
    
    public synchronized void addObject(Position p, GameObject g) throws Exception {
        if (this.containsKey(p)){
            throw new Exception();
        }
        else
            this.put(p, g);
    }
    
}
