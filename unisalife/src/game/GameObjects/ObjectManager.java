/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author simon
 */
public class ObjectManager {
    private ConcurrentHashMap<Position,GameObject> fixed;
    private ConcurrentHashMap<Position,GameObject> dynamic;

    public ObjectManager(ConcurrentHashMap<Position, GameObject> fixed, ConcurrentHashMap<Position, GameObject> dynamic) {
        this.fixed = fixed;
        this.dynamic = dynamic;
    }
    
    public GameObject getObjectInNextPosition(Position p){
        GameObject g = fixed.get(p);
        return ( g == null ? dynamic.get(g) : g);        
    }
    
    public synchronized GameObject removeObject(Position p)throws Exception{
        if(dynamic.containsKey(p))
            return dynamic.remove(p);
        else
            throw new Exception();
     
    }
    
    public synchronized void addObject(Position p, GameObject g) throws Exception {
        if (dynamic.containsKey(p)){
            throw new Exception();
        }
        else
            dynamic.put(p, g);
    }
    
}
