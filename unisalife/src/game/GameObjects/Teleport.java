/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

/**
 
 * @author simon
 */
public class Teleport extends GameObject{
    private int mapDest;
    private String tilePath;
    private Destination d;
    
    /**
     **Class Teleport represents a point in the map that allows the player to access
     * in a new zone.
     * @param x x position of the teleport in the map.
     * @param y y position of the teleport in the map
     * @param i objectId of the teleport
     * @param t string that represent the path of the tileset to be loaded
     * @param map int that represent the index of new map in the array of maps of game.
     * @param d destination in terms of x and y of the player in the new map
     */
    public Teleport(float x,float y,ObjectId i,String t,int map,Destination d){
        super(x,y,i);
        tilePath=t;
        mapDest=map;
        this.d=d;
    }
    

    public Destination getDestination(){
        return d;
    }
    
    /**
     *
     * @return tilepath
     */
    public String getTilePath(){
        return tilePath;
    }
    
    /**
     *
     * @return index of map of destination
     */
    public int getMapDest(){
        return mapDest;
    }
    
    
    
}
