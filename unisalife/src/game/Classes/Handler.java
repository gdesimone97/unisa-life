/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import game.Interfaces.Renderable;


/**
 *
 * @author simon
 */
public class Handler {

    /**
     *
     */
    public LinkedList<GameObject> objects=Game.maps[Game.actualMap].getList();
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
    public Handler(LinkedList<GameObject> l){
        objects=l;
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
        for(GameObject go:objects){
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
    
    
    public void addObject(GameObject g){
        Game.maps[Game.actualMap].getList().add(g);
    }
    
    /**
     *
     * @param g
     */
    public void removeObject(GameObject g){
        Game.maps[Game.actualMap].getList().remove(g);
    }
}
