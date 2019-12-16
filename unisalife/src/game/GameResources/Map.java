/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import game.GameObjects.GameObject;
import game.GameObjects.ObjectManager;
import game.GameObjects.Position;
import java.util.HashMap;
import java.util.LinkedList;
/**
 *
 * @author simon
 */


/**
 * Class map contains a tilemap tMap representing a map of the game and the list mapObject that containts all the GameObjects objects that belongs to that map(without
 * considering Player. If an objects is collected by the Player, it must be removed from the list.
 * 
*/
public class Map {
    private TileMap tMap;
    private ObjectManager mapObjects;
    //private ObjectManager mapObjects = new ObjectManager();
    
    
    /**
     * Constructor that initializes tMap with the passed TileMap t and
     * the mapObject with an empty list.
     * @param t TileMap
     */     
    public Map(TileMap t){
        tMap=t;
        mapObjects=new ObjectManager();
    }
    
    /**
     * Constructor that initializes tMap with the passed TileMap t and
     * the mapObject with the passed LinkedList l.
     * @param t Tmap
     * @param hm HashMap of GameObject objects
     */
    public Map(TileMap t,ObjectManager o ){
        tMap=t;
        mapObjects=o;
    }
    /**
     * Add a new GameObject object g to the list.
     * @param g GameObject
     */
    public void addObject(Position p,GameObject g){
       try{
           mapObjects.addObject(p, g);
       }
       catch(Exception e){
           System.exit(-1);
       }
       
    }
    /**
     * Remove(if presents) the GameObject object g from the list.
     * @param g 
     * @return true if the object was present and has been removed or false if it was not present.
     */
    public GameObject removeObject(Position p){
        GameObject o = null;
        try{
            o = mapObjects.removeObject(p);
        }
        catch(Exception e){
            System.exit(-1);
        }
        return o;
    }
    /**
     * 
     * @return mapObjects List.
     */
    public ObjectManager getObjectManager(){
        return mapObjects;
    }
    
    /**
     * 
     * @return tMap TileMap.
     */
    public TileMap getTileMap(){
        return tMap;
    }
    
    
}
