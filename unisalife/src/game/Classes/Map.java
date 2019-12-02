/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
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
    private LinkedList<GameObject> mapObjects;
    
    
    /**
     * Constructor that initializes tMap with the passed TileMap t and
     * the mapObject with an empty list.
     * @param t TileMap
     */     
    public Map(TileMap t){
        tMap=t;
        mapObjects=new LinkedList<>();
    }
    
    /**
     * Constructor that initializes tMap with the passed TileMap t and
     * the mapObject with the passed LinkedList l.
     * @param t Tmap
     * @param l LinkedList of GameObject objects
     */
    public Map(TileMap t,LinkedList<GameObject> l){
        tMap=t;
        mapObjects=l;
    }
    /**
     * Add a new GameObject object g to the list.
     * @param g GameObject
     */
    public void addObject(GameObject g){
        mapObjects.add(g);
    }
    /**
     * Remove(if presents) the GameObject object g from the list.
     * @param g 
     * @return true if the object was present and has been removed or false if it was not present.
     */
    public boolean removeObject(GameObject g){
        return mapObjects.remove(g);
    }
    /**
     * 
     * @return mapObjects List.
     */
    public LinkedList<GameObject> getList(){
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
