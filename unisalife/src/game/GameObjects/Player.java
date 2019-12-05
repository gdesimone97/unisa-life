/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.GameResources.Animation;
import game.GameResources.Game;
import game.GameResources.Handler;
import game.Interfaces.Tickable;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * Class Player represents the controllable object of the game, the effective player
 * @author simon
 */
public class Player extends GameObject implements Tickable,Renderable{
    private Handler handler; 
    private float velX=0;
    protected float velY=0;
    protected Animation upWalk;
    protected Animation downWalk;
    protected Animation leftWalk;
    protected Animation rightWalk;
    protected BufferedImage facingDownImage;
    protected BufferedImage facingLeftImage;
    protected BufferedImage facingRightImage;
    protected BufferedImage facingUpImage;
    protected FaceState face;
    private static Player uniqueIstance=null;
    protected Game game;
    protected GameInventory inventory;
    
    /*public Player(float x,float y,ObjectId i){
        super(x,y,i);
    }*/
    
    /**
     * The constructor creates a Player instance and takes in input
     * @param x x coordinate of the position we want the player to spawn
     * @param y y coordinate of the position we want the player to spawn
     * @param i i represents the enum-id of the player
     * @param g g represents the thread that runs the game
     * @param inventory inventory stands for the inventory associated with the player
     */
    private Player(float x,float y,ObjectId i,Game g,GameInventory inventory){
        super(x,y,i);
        face=new DownFaceState(this);
        game=g;
        this.inventory=inventory;
        
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
     * This method allows to change the face set that is associated with the player
     * @param down down is the image for down face
     * @param left left is the image for left face
     * @param right right is the image for right face
     * @param up up is the image for up face
     */
    
    public void changeFaceSet(BufferedImage down,BufferedImage left,BufferedImage right,BufferedImage up){
        facingLeftImage=left;
        facingRightImage=right;
        facingUpImage=up;
        facingDownImage=down;
    }
    
    
    /**
     * This method allows to change the animation set that is associated with the player
     * @param down down is the animation for down movements 
     * @param left left is the animation for left movements
     * @param right right is the animation for right movements 
     * @param up up is the animation for up movements
     */
    public void changeAnimationSet(Animation down,Animation left,Animation right,Animation up){
        upWalk=up;
        downWalk=down;
        leftWalk=left;
        rightWalk=right;
    }
    
    /**
     * Application of the singleton pattern, this method returns the unique istance of the player
     *@param g 
     * @return
     * 
     */
    public static Player getIstance(Game g){
        if(uniqueIstance==null){
            return new Player(0,0,ObjectId.Player,g,new GameInventory());
        }
        return uniqueIstance;
    }
    
    /**
     * Set up the player's inventory
     * @param l list of items
     */
    public void setInventory(LinkedList<Item> l){
        this.inventory=new GameInventory(l);
    }
    
    /**
     *
     * @param a
     */
   
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
     * This method returns a rectangle simbolyzing the upper bounds of the player, 
     * to avoid collisions
     * @return
     */
    public Rectangle getTopBounds(){
        return new Rectangle((int)x+1,(int)y,(int)width-2,3);
    }
    
    /**
     *This method returns a rectangle simbolyzing the bottom bounds of the player, 
     * to avoid collisions
     * @return
     */
    public Rectangle getBottomBounds(){
        return new Rectangle((int)x+1, (int)(y+height-3),(int)width-2,3);
    }
    
    /**
     *This method returns a rectangle simbolyzing the left bounds of the player, 
     * to avoid collisions
     * @return
     */
    public Rectangle getLeftBounds(){
        return new Rectangle((int)x-1,(int)y+1,3,(int)height-2);
    }
    
    /**
     *This method returns a rectangle simbolyzing the right bounds of the player, 
     * to avoid collisions
     * @return
     */
    public Rectangle getRightBounds(){
        return new Rectangle((int)(x+width-1),(int)y+1,2,(int)height-2);
    }
    
    /**
     * This mathod updates the position, runs the animations, updates the facestates.
     * 
     */
    @Override
    public void tick(/*LinkedList<GameObject> objects*/)
    {
        if(velX>0) face=new RightFaceState(this);
        if(velX<0) face=new LeftFaceState(this);
        if(velY>0) face=new DownFaceState(this);
        if(velY<0) face=new UpFaceState(this);
        if(x+velX>0&&x+velX<game.getWidthMap()-width)
            x+=velX;
        if(y+velY>0&&y+velY<game.getHeightMap()-height)
            y+=velY;
        collisions(game.getActualMap().getList());
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();
    }
    
    /**
     * This mathod menage the collisions between the game objects
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
                game.updateActualMap(t.getMapDest());
                game.setWidthMap(game.getActualMap().getTileMap().getWidth());
                game.setHeightMap(game.getActualMap().getTileMap().getHeight());
                game.setHandler(new Handler());
                x=t.getDestination().getX();
                y=t.getDestination().getY();            
                break;
            }
            if(getBottomBounds().intersects(g.getBounds()))//&&g.getId()!=ObjectId.Teleport)
            {
                y=g.getY()-height;
                break;
            }
            
            if(getTopBounds().intersects(g.getBounds())){//&&g.getId()!=ObjectId.Tel){
                y=g.getY()+height;
                break;
            }
            if(getLeftBounds().intersects(g.getBounds())){//&&g.getId()!=ObjectId.Block){
                x=g.getX()+width+1;
                break;
            }
            if(getRightBounds().intersects(g.getBounds())){//&&g.getId()!=ObjectId.Block){
                x=g.getX()-width-1;
                break;
            }
            }
            
        }
    
    
    //dialog deve lavorare solo con oggetti interactable (item e persone per adesso)
    public void dialog(LinkedList<GameObject> l){
        for(GameObject g:l){
            if(g instanceof Interactable && visualViewOfPlayer().intersects(g.getBounds())){
                ((Interactable)g).interact();
                if(g.getId()==ObjectId.Item){
                    this.inventory.addItem((Item)g);
                    l.remove(g);
                    for(Item i:inventory)
                        System.out.print(i.getTitle());
                }
                break;
            }
        }
    }
    
    /**
     * This method allows to visualize on the screen the player image
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
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
}
