/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import game.GameObjects.GameObject;
import game.GameObjects.ObjectManager;
import game.GameObjects.Position;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import game.Interfaces.Renderable;


/**
 * Handler is a class that menage the position updating and visualizing all the objects contained in the
 * list of GameObject that is passed.
 * @author simon
 */
public class Handler {

    /**
     *
     */
    public ObjectManager objects=Game.maps[Game.actualMap].getObjectManager();
    //private TileMap map;
    
    /**
     *
     */
    public Handler(){
       
    }
    
    /**
     *
     * @param l
     */
    public Handler(ObjectManager o){
        objects=o;
    }
    
    //private GameObject tempObj;

    /**
     *
     */
        public void tick(){
        /*for(int i=0;i < objects.size();i++){
            tempObj=objects.get(i);
            tempObj.tick(objects);
        }*/
        Game.player.tick();
    }
    
    /**
     *
     * @param g
     */
    public void render(Graphics2D g){
        Game.maps[Game.actualMap].getTileMap().render(g);
        
        /*for(int i=0;i < objects.size();i++){
            tempObj=objects.get(i);
            tempObj.render(g);
        }*/
        Game.player.render(g);
        for(GameObject go:objects.values()){
            if(go instanceof Renderable)
                ((Renderable)go).render(g);
        }
    }
    
    /*public GameObject getPlayer(){
        for(int i=0;i<objects.size();i++)
            if(objects.get(i).id==ObjectId.Player)
                return objects.get(i);
        return null;
    }*/

    /**
     *
     * @param g
     */
    
    
    public void addObject(Position p,GameObject g){
        try{
        Game.maps[Game.actualMap].getObjectManager().addObject(p, g);
        }
        catch(Exception e){
        System.exit(4);
    }
    }
    
    /**
     *
     * @param g
     */
    public void removeObject(Position p,GameObject g){
        try{
        Game.maps[Game.actualMap].getObjectManager().remove(g);
        }
        catch(Exception e){
            System.exit(4);
        }
    }
}
