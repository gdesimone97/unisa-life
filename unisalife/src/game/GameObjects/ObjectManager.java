/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is the manager of objects. It mantains two containers of objects,
 * the fixed ones in the map, that are not removed, and the dynamic ones, that
 * changes in every level. This class also allows to add, remove or search an
 * element in a certain position
 * @author 1997g
 */
public class ObjectManager {
    private ConcurrentHashMap<Position,GameObject> fixed;
    private ConcurrentHashMap<Position,Renderable> dynamic;

    public ObjectManager(ConcurrentHashMap<Position, GameObject> fixed, ConcurrentHashMap<Position, Renderable> dynamic) {
        this.fixed = fixed;
        this.dynamic = dynamic;
    }
    
    /**
     * Check if in that position there is a fixed or dynamic object
     * @param p the ScaledPosition of the object
     * @return the object or null if it's not present
     */
    public GameObject getObjectInNextPosition(Position p){
        GameObject g = fixed.get(p);
        return ( g == null ? dynamic.get(p) : g);
    }
    
    /**
     * method used to remove a dynamic object in a certain position
     * @param p the Scaled position of the object
     * @return the object or null if there isn't
     * @throws Exception if p is not valid
     */
    public synchronized GameObject removeObject(Position p)throws Exception{
        if(dynamic.containsKey(p))
            return dynamic.remove(p);
        else
            throw new Exception();
     
    }
    
    /**
     * adds a new object in the dynamic, but checks if there is an object
     * in the fixed or removed objects
     * @param p is the scaled position
     * @param g the renderable object
     * @throws Exception if p is not valid
     */
    public synchronized void addObject(Position p, Renderable g) throws Exception {
        if (fixed.containsKey(p) || dynamic.containsKey(p)){
            throw new Exception();
        }
        else
            dynamic.put(p, g);
    }
    
    /**
     * adds a new object in the fixed, but checks if there is an object
     * @param p is the scaled position
     * @param g the object
     * @throws Exception if p is not valid
     */
    public synchronized void addFixedObject(Position p, GameObject g) throws Exception {
        if (fixed.containsKey(p) || dynamic.containsKey(p)){
            throw new Exception();
        }
        else
            fixed.put(p, g);
    }

    public ConcurrentHashMap<Position, GameObject> getFixed() {
        return fixed;
    }

    public ConcurrentHashMap<Position, Renderable> getDynamic() {
        return dynamic;
    }
    
    /**
     * used to union two containers of dynamics
     * @param newDynamic the container to be added
     */
    public void mergeDynamic(ConcurrentHashMap<Position, Renderable> newDynamic) {
        this.dynamic.putAll(newDynamic);
    }

    /**
     * changes complitely the container of dynamics
     * @param dynamic the new container to be replaced
     */
    public void setDynamic(ConcurrentHashMap<Position, Renderable> dynamic) {
        this.dynamic = dynamic;
    }
    
    /**
     * method used to load all the images of the renderable objects in the dynamic
     * @throws ImageNotLoadedException if an image is not loaded correctly
     */
    public void loadImages() throws ImageNotLoadedException {
        for(Renderable r : dynamic.values()) {
            r.loadImage();
        }
    }
}
