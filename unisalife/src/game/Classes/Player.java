/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import game.Interfaces.Tickable;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 *
 * @author simon
 */
public class Player extends GameObject implements Tickable,Renderable{
    private Handler handler; 
    private float velX=0;
    private float velY=0;
    private Animation upWalk;
    private Animation downWalk;
    private Animation leftWalk;
    private Animation rightWalk;
    protected BufferedImage facingDownImage;
    protected BufferedImage facingLeftImage;
    protected BufferedImage facingRightImage;
    protected BufferedImage facingUpImage;
    private FaceState face;
    
    /*public Player(float x,float y,ObjectId i){
        super(x,y,i);
    }*/
    private Player(float x,float y,ObjectId i){
        super(x,y,i);
        face=new DownFaceState();
        
        /*try {
        image = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/gatto.png")
        );}
        catch (Exception e) {
            System.exit(1);
    }
        upWalk=u;
        downWalk=d;
        leftWalk=l;
        rightWalk=r;
        facingDownImage=Game.texturePlayer[0];
        facingLeftImage=Game.texturePlayer[3];
        facingRightImage=Game.texturePlayer[6];
        facingUpImage=Game.texturePlayer[9];
    
    */
    }
    
    /**
     *
     * @param i
     */
    public void setFacingLeftImage(BufferedImage i){
        facingLeftImage=i;
    }

    /**
     *
     * @param i
     */
    public void setFacingRightImage(BufferedImage i){
        facingRightImage=i;
    }

    /**
     *
     * @param i
     */
    public void setFacingUpImage(BufferedImage i){
        facingUpImage=i;
    }

    /**
     *
     * @param i
     */
    public void setFacingDownImage(BufferedImage i){
        facingDownImage=i;
    }
    
    private static Player uniqueIstance=null;

    /**
     *
     * @return
     */
    public static Player getIstance(){
        if(uniqueIstance==null){
            return new Player(0,0,ObjectId.Player);
        }
        return uniqueIstance;
    }
    
    /**
     *
     * @param a
     */
    public void setUpAnimation(Animation a){
        upWalk=a;
    }

    /**
     *
     * @param a
     */
    public void setDownAnimation(Animation a){
        downWalk=a;
    }

    /**
     *
     * @param a
     */
    public void setLeftAnimation(Animation a){
        leftWalk=a;
    }

    /**
     *
     * @param a
     */
    public void setRightAnimation(Animation a){
        rightWalk=a;
    }
    
    /**
     *
     * @return
     */
    public float getVelX(){
        return velX;
    }
    
    /**
     *
     * @return
     */
    public float getVelY(){
        return velY;
    }
    
    /**
     *
     * @param velX
     */
    public void setVelX(float velX){
        this.velX=velX;
    }
    
    /**
     *
     * @param velY
     */
    public void setVelY(float velY){
        this.velY=velY;
    }
    
    /**
     *
     * @return
     */
    public Rectangle getTopBounds(){
        return new Rectangle((int)x+1,(int)y,(int)width-2,3);
    }
    
    /**
     *
     * @return
     */
    public Rectangle getBottomBounds(){
        return new Rectangle((int)x+1, (int)(y+height-3),(int)width-2,3);
    }
    
    /**
     *
     * @return
     */
    public Rectangle getLeftBounds(){
        return new Rectangle((int)x-1,(int)y+1,3,(int)height-2);
    }
    
    /**
     *
     * @return
     */
    public Rectangle getRightBounds(){
        return new Rectangle((int)(x+width-1),(int)y+1,2,(int)height-2);
    }
    
    /**
     *
     * @param objects
     */
    @Override
    public void tick(/*LinkedList<GameObject> objects*/)
    {
        if(velX>0) face=new RightFaceState();
        if(velX<0) face=new LeftFaceState();
        if(velY>0) face=new DownFaceState();
        if(velY<0) face=new UpFaceState();
        if(x+velX>0&&x+velX<Game.WIDTHMAP-width)
            x+=velX;
        if(y+velY>0&&y+velY<Game.HEIGHTMAP-height)
            y+=velY;
        collisions(Game.maps[Game.actualMap].getList());
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();
    }
    
    /**
     *
     * @param objects
     */
    public void collisions(LinkedList<GameObject> objects){
        for(GameObject g:objects){     
            if(getBounds().intersects(g.getBounds())&&g.getId()==ObjectId.Teleport){
       //collisions deve lavorare con tutti i gameobjects, preferibilmente prima i teleport          
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
                break;
            }
            if(getBottomBounds().intersects(g.getBounds()))//&&g.getId()!=ObjectId.Teleport)
            {
                y=g.y-height;
                break;
            }
            
            if(getTopBounds().intersects(g.getBounds())){//&&g.getId()!=ObjectId.Tel){
                y=g.y+height;
                break;
            }
            if(getLeftBounds().intersects(g.getBounds())){//&&g.getId()!=ObjectId.Block){
                x=g.x+width+1;
                break;
            }
            if(getRightBounds().intersects(g.getBounds())){//&&g.getId()!=ObjectId.Block){
                x=g.x-width-1;
                break;
            }
            }
            
        }
    
    
    //dialog deve lavorare solo con oggetti interactable (item e persone per adesso)
    public void dialog(LinkedList<GameObject> l){
        for(GameObject g:l){
            if(g instanceof Interactable && visualViewOfPlayer().intersects(g.getBounds())){
                ((Interactable)g).interact();
                break;
            }
        }
    }
    
    /**
     *
     * @param g
     */
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
        face.drawFace(g);
    }
    
    private Rectangle visualViewOfPlayer(){
        return face.visualViewOfPlayer();
    }
}
