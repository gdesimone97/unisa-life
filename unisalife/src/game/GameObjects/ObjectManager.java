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
    private ConcurrentHashMap<Position,Renderable> dynamic;

    public ObjectManager(ConcurrentHashMap<Position, GameObject> fixed, ConcurrentHashMap<Position, Renderable> dynamic) {
        this.fixed = fixed;
        this.dynamic = dynamic;
    }
    
    public GameObject getObjectInNextPosition(Position p){
        GameObject g = fixed.get(p);
        return ( g == null ? dynamic.get(p) : g);
    }
    
    public synchronized GameObject removeObject(Position p)throws Exception{
        if(dynamic.containsKey(p))
            return dynamic.remove(p);
        else
            throw new Exception();
     
    }
    
    public synchronized void addObject(Position p, Renderable g) throws Exception {
        if (fixed.containsKey(p) || dynamic.containsKey(p)){
            throw new Exception();
        }
        else
            dynamic.put(p, g);
    }

    public ConcurrentHashMap<Position, GameObject> getFixed() {
        return fixed;
    }

    public ConcurrentHashMap<Position, Renderable> getDynamic() {
        return dynamic;
    }

    public void setDynamic(ConcurrentHashMap<Position, Renderable> dynamic) {
        this.dynamic = dynamic;
    }
    
    public void loadImages() throws ImageNotLoadedException {
        for(Renderable r : dynamic.values()) {
            r.loadImage();
        }
    }
}
