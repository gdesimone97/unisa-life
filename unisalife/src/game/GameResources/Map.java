/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import exam.question.Materia;
import game.GameObjects.GameObject;
import game.GameObjects.Item;
import game.GameObjects.ObjectManager;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import game.Interfaces.Renderable;
import gameSystem.map.Mappa;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quests.ItemDef;
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
    public Map(){
        mapObjects=new ObjectManager();
        
        tMap = new TileMap(32, 288, 288);
        tMap.loadTiles("/Tilesets/PT.gif");
        tMap.loadMap("/Maps/map9.map");
        try {
            Position p = new Position(640, 640);
            mapObjects.addObject(new Position(20,20), new Professor("Foggia", p, "/Sprites/foggia.png", Materia.matematica));
            p = new Position(320, 160);
            mapObjects.addObject(new Position(10,5), new Item(p, "/Sprites/note.png", ItemDef.appuntidimatematica1.toString(), ItemDef.appuntidimatematica1));
            p = new Position(320, 64);
            mapObjects.addObject(new Position(10,2), new Item(p, "/Sprites/note.png", ItemDef.appuntidimatematica2.toString(), ItemDef.appuntidimatematica2));
            p = new Position(320, 320);
            mapObjects.addObject(new Position(10,10), new Item(p, "/Sprites/calculator.png", ItemDef.calcolatrice.toString(), ItemDef.calcolatrice));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    
    public int getWidthMap(){
        return this.tMap.getWidth();
    }
    
    public int getHeightMap(){
        return this.tMap.getHeight();
    }
    
    /**
     * 
     * @return tMap TileMap.
     */
    public TileMap getTileMap(){
        return tMap;
    }
    
    public void render(Graphics2D g) {
        tMap.render(g);
        for(GameObject go : mapObjects.values()) {
            if(go instanceof Renderable) {
                ((Renderable) go).render(g);
            }
        }
    }
    
}
