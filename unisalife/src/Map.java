/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
/**
 *
 * @author simon
 */

//class map contains a tilemap and the list of objects that belongs to that map
//without considering Player.
//if an objects is collected, it must be removed from the list
public class Map {
    private TileMap tMap;
    private LinkedList<GameObject> mapObjects;
    
    
            
    public Map(TileMap t){
        tMap=t;
        mapObjects=new LinkedList<>();
    }
    
    
    public Map(TileMap t,LinkedList<GameObject> l){
        tMap=t;
        mapObjects=l;
    }
    
    public void addObject(GameObject g){
        mapObjects.add(g);
    }
    public boolean removeObject(GameObject g){
        return mapObjects.remove(g);
    }
    
    public LinkedList<GameObject> getList(){
        return mapObjects;
    }
    
    
    
    public TileMap getTileMap(){
        return tMap;
    }
    
    
}
