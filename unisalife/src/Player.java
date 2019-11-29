/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.awt.*;
/**
 *
 * @author simon
 */
public class Player extends GameObject {
    private Handler handler; 
    
    public Player(float x,float y,ObjectId i){
        super(x,y,i);
        
    }
    
    public void tick(LinkedList<GameObject> objects){
        if(x+velX>0&&x+velX<Game.WIDTHMAP-width)
            x+=velX;
        if(y+velY>0&&y+velY<Game.HEIGHTMAP-height)
            y+=velY;
        collisions(objects);
    }
    
    public void collisions(LinkedList<GameObject> objects){
        for(GameObject g:objects){
            if(getBottomBounds().intersects(g.getBounds())&&g.getId()==ObjectId.Block)
            {
                
                y=g.y-height;
                break;
            }
            if(getTopBounds().intersects(g.getBounds())&&g.getId()==ObjectId.Block){
                
                y=g.y+height;
                break;
            }
            if(getLeftBounds().intersects(g.getBounds())&&g.getId()==ObjectId.Block){
                
                x=g.x+width+1;
                break;
            }
            if(getRightBounds().intersects(g.getBounds())&&g.getId()==ObjectId.Block){
                
                x=g.x-width-1;
                break;
            }
            
            if(getBounds().intersects(g.getBounds())&&g.getId()==ObjectId.Teleport){
                velX=0;
                velY=0;
                Teleport t = (Teleport)g;
                /*
                System.out.print("hello");
                System.out.print("tile"+t.tilePath);
                System.out.print(t.mapPath);
                Game.tileMap.loadTiles(t.tilePath);
                */
                Game.actualMap=Game.maps[t.getMapDest()];
                Game.WIDTHMAP=Game.actualMap.getTileMap().getWidth();
                Game.HEIGHTMAP=Game.actualMap.getTileMap().getHeight();
                Game.handler=new Handler();
                x=t.getDestination().getX();
                y=t.getDestination().getY();
                
            }
        }
    }
    
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y, width, height);
    }
    
    public Rectangle getTopBounds(){
        return new Rectangle((int)x+1,(int)y,(int)width-2,3);
    }
    
    public Rectangle getBottomBounds(){
        return new Rectangle((int)x+1,(int)y+height-3,(int)width-2,3);
    }
    public Rectangle getLeftBounds(){
        return new Rectangle((int)x-1,(int)y+1,3,(int)height-2);
    }
    public Rectangle getRightBounds(){
        return new Rectangle((int)x+width-1,(int)y+1,2,(int)height-2);
    }
    
    
}
