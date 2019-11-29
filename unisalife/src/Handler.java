/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;


/**
 *
 * @author simon
 */
public class Handler {
    public LinkedList<GameObject> objects=Game.actualMap.getList();
    //private TileMap map;
    
    public Handler(){
       
    }
    
    public Handler(LinkedList<GameObject> l){
        objects=l;
    }
    
    //private GameObject tempObj;
    public void tick(){
        /*for(int i=0;i < objects.size();i++){
            tempObj=objects.get(i);
            tempObj.tick(objects);
        }*/
        Game.player.tick(objects);
    }
    
    public void render(Graphics2D g){
        Game.actualMap.getTileMap().render(g);
        
        /*for(int i=0;i < objects.size();i++){
            tempObj=objects.get(i);
            tempObj.render(g);
        }*/
        Game.player.render(g);
        for(GameObject go:objects)
            go.render(g);
    }
    
    /*public GameObject getPlayer(){
        for(int i=0;i<objects.size();i++)
            if(objects.get(i).id==ObjectId.Player)
                return objects.get(i);
        return null;
    }*/
    
    public void addObject(GameObject g){
        this.objects.add(g);
    }
    
    public void removeObject(GameObject g){
        this.objects.remove(g);
    }
}
