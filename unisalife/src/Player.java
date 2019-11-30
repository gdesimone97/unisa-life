/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.lang.Exception;
/**
 *
 * @author simon
 */
public class Player extends GameObject {
    private Handler handler; 
    private float velX=0;
    private float velY=0;
    private final Animation upWalk;
    private final Animation downWalk;
    private final Animation leftWalk;
    private final Animation rightWalk;
    private Facing face=Facing.Down;
    private final BufferedImage facingLeftImage;
    private final BufferedImage facingRightImage;
    private final BufferedImage facingUpImage;
    
    
    public Player(float x,float y,ObjectId i,Animation u,Animation d,Animation l,Animation r){
        super(x,y,i);
        /*try {
        image = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/gatto.png")
        );}
        catch (Exception e) {
            System.exit(1);
    }*/
        upWalk=u;
        downWalk=d;
        leftWalk=l;
        rightWalk=r;
        facingDownImage=Game.texturePlayer[0];
        facingLeftImage=Game.texturePlayer[3];
        facingRightImage=Game.texturePlayer[6];
        facingUpImage=Game.texturePlayer[9];
    
    
    }
    
    public float getVelX(){
        return velX;
    }
    
    public float getVelY(){
        return velY;
    }
    
    public void setVelX(float velX){
        this.velX=velX;
    }
    
    public void setVelY(float velY){
        this.velY=velY;
    }
    
    public Rectangle getTopBounds(){
        return new Rectangle((int)x+1,(int)y,(int)width-2,3);
    }
    
    public Rectangle getBottomBounds(){
        return new Rectangle((int)x+1, (int)(y+height-3),(int)width-2,3);
    }
    
    public Rectangle getLeftBounds(){
        return new Rectangle((int)x-1,(int)y+1,3,(int)height-2);
    }
    
    public Rectangle getRightBounds(){
        return new Rectangle((int)(x+width-1),(int)y+1,2,(int)height-2);
    }
    
    @Override
    public void tick(LinkedList<GameObject> objects){
        if(velX>0) face=Facing.Right;
        if(velX<0) face=Facing.Left;
        if(velY>0) face=Facing.Down;
        if(velY<0) face=Facing.Up;
        if(x+velX>0&&x+velX<Game.WIDTHMAP-width)
            x+=velX;
        if(y+velY>0&&y+velY<Game.HEIGHTMAP-height)
            y+=velY;
        collisions(objects);
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();
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
                
                Teleport t = (Teleport)g;
                /*
                System.out.print("hello");
                System.out.print("tile"+t.tilePath);
                System.out.print(t.mapPath);
                Game.tileMap.loadTiles(t.tilePath);
                */
                Game.actualMap=t.getMapDest();
                Game.WIDTHMAP=Game.maps[Game.actualMap].getTileMap().getWidth();
                Game.HEIGHTMAP=Game.maps[Game.actualMap].getTileMap().getHeight();
                Game.handler=new Handler();
                x=t.getDestination().getX();
                y=t.getDestination().getY();            
            }
        }
    }
    
    @Override
    public void render(Graphics g){
        if(velX>0){
            rightWalk.drawAnimation(g, (int)x, (int)y,width,height);
            return;
        }
        
        if(velX<0){
            leftWalk.drawAnimation(g, (int)x, (int)y,width,height);
            return;
        }
        if(velY>0){
            downWalk.drawAnimation(g, (int)x, (int)y,width,height);
            return;
        }
        
        if(velY<0){
            upWalk.drawAnimation(g, (int)x, (int)y,width,height);
            return;
        }
        if(face==Facing.Down){
            g.drawImage(facingDownImage, (int)x, (int)y,width,height, null);
            return;
        }
        if(face==Facing.Left){
            g.drawImage(facingLeftImage, (int)x, (int)y,width,height, null);
            return;
        }
        if(face==Facing.Right){
            g.drawImage(facingRightImage, (int)x, (int)y,width,height, null);
            return;
        }
        g.drawImage(facingUpImage, (int)x, (int)y,width,height, null);
    }
        
}
