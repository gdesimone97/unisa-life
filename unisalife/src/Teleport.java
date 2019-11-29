/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
/**
 *
 * @author simon
 */
public class Teleport extends GameObject {
    private int mapDest;
    private String tilePath;
    private Destination d;
    
    public Teleport(float x,float y,ObjectId i,String t,int map,Destination d){
        super(x,y,i);
        tilePath=t;
        mapDest=map;
        this.d=d;
    }
    
    //render is void because a Teleport is invisible.
    @Override
    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
    //tick is void because the collision is handled by Player's tick method.
    @Override
    public void tick(LinkedList<GameObject> objects){}
    
    public Destination getDestination(){
        return d;
    }
    
    public String getTilePath(){
        return tilePath;
    }
    
    public int getMapDest(){
        return mapDest;
    }
    
    
    
}
