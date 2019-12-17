/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.GameResources.Animation;
import gameSystem.Game;


import gameSystem.PlayState;
import game.Interfaces.Tickable;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import static gameSystem.Game.ANIMATIONSPEED;
import static gameSystem.Game.DIMENSIONSPRITE;
import gameSystem.GameManager;
import gameSystem.GameStateManager;
import gameSystem.PauseState;
import gameSystem.map.MapManager;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author simon
 */
public class Player extends GameObject implements Tickable, Renderable,Saveable {
    
    private int velX = 0;
    protected int velY = 0;
    protected Animation upWalk;
    protected Animation downWalk;
    protected Animation leftWalk;
    protected Animation rightWalk;
    protected BufferedImage facingDownImage;
    protected BufferedImage facingLeftImage;
    protected BufferedImage facingRightImage;
    protected BufferedImage facingUpImage;
    protected FaceState face;
    private static Player uniqueIstance = null;
    private boolean nextMove = true;
    
    /*public Player(float x,float y,SubjectEnum i){
     super(x,y,i);
     }*/
    private Player(Position p) {
        super(p);
        face = new DownFaceState(this);
        initialize(32, "Ciao");
        
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
    
    private void changeFaceSet(BufferedImage down, BufferedImage left, BufferedImage right, BufferedImage up) {
        facingLeftImage = left;
        facingRightImage = right;
        facingUpImage = up;
        facingDownImage = down;
    }
    
    private void changeAnimationSet(Animation down, Animation left, Animation right, Animation up) {
        upWalk = up;
        downWalk = down;
        leftWalk = left;
        rightWalk = right;
    }

    /**
     * @param g
     * @return
     *
     */
    
    private void initialize(int skin,String name){
        BufferedImage texturePlayer[] = new BufferedImage[12];
        try {
            BufferedImage characterImage = ImageIO.read(
                    getClass().getResourceAsStream("/Sprites/sprite"+32+".png")
            );
            texturePlayer[0] = characterImage.getSubimage(32, 0, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[1] = characterImage.getSubimage(0, 0, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[2] = characterImage.getSubimage(64, 0, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[3] = characterImage.getSubimage(32, 32, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[4] = characterImage.getSubimage(0, 32, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[5] = characterImage.getSubimage(64, 32, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[6] = characterImage.getSubimage(32, 64, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[7] = characterImage.getSubimage(0, 64, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[8] = characterImage.getSubimage(64, 64, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[9] = characterImage.getSubimage(32, 96, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[10] = characterImage.getSubimage(0, 96, DIMENSIONSPRITE, DIMENSIONSPRITE);
            texturePlayer[11] = characterImage.getSubimage(64, 96, DIMENSIONSPRITE, DIMENSIONSPRITE);
        } catch (Exception e) {
            System.exit(4);
        }
        changeFaceSet(texturePlayer[6], texturePlayer[3], texturePlayer[9], texturePlayer[0]);

        changeAnimationSet(new Animation(ANIMATIONSPEED, texturePlayer[7], texturePlayer[8]),
            new Animation(ANIMATIONSPEED, texturePlayer[4], texturePlayer[5]),
            new Animation(ANIMATIONSPEED, texturePlayer[10], texturePlayer[11]),
            new Animation(ANIMATIONSPEED, texturePlayer[1], texturePlayer[2]));
        p.setX(0);
        p.setY(0);
        
    }
    
    public static Player getIstance() {
        if (uniqueIstance == null) {
            uniqueIstance = new Player(new Position(0,0));
        }
        return uniqueIstance;
    }
    
    public void setX(int x){
        this.p.setX(x);
    }
    
    public void setY(int y){
        this.p.setY(y);
    }
    
    /**
     *
     * @param a
     */
    /**
     *
     * @return
     */
    
    public int getX(){
        return p.getX();
    }
    
    public int getY(){
        return p.getY();
    }
    
    public int getVelX() {
        return velX;
    }

    /**
     *
     * @return
     */
    public int getVelY() {
        return velY;
    }

    /**
     *
     * @param velX
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     *
     * @param velY
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }


    
     
    @Override
    public void tick(/*LinkedList<GameObject> objects*/) {
        int x=p.getX();
        int y=p.getY();
        nextMove = true;
        if (velX > 0) {
            face = new RightFaceState(this);
        } else if (velX < 0) {
            face = new LeftFaceState(this);
        } else if (velY > 0) {
            face = new DownFaceState(this);
        } else if (velY < 0) {
            face = new UpFaceState(this);
        }
        
        collisions(MapManager.getInstance().getMap().getObjectManager());
        if (x + velX > 0 && x + velX < 900 - Game.DIMENSIONSPRITE && nextMove == true) {
            p.setX(p.getX() +velX);
        }
        if (y + velY > 0 && y + velY < 900 - Game.DIMENSIONSPRITE && nextMove == true) {
            p.setY(p.getY()+velY);
        }
        //collisions(game.getActualMap().getList());
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();
    }

    /**
     *
     * @param ObjectsManager
     */
    private void collisions(ObjectManager objMan) {
        GameObject g = objMan.get(face.nextStep());
            if (g!=null)
            {   
                
                if(g instanceof Teleport){
                    Teleport t = (Teleport)g;
                /*
                 System.out.print("hello");
                 System.out.print("tile"+t.tilePath);
                 System.out.print(t.mapPath);
                 Game.tileMap.loadTiles(t.tilePath);
                 */
                   /* Game.getGame().updateActualMap(t.getMapDest());
                    Game.getGame().setWidthMap(Game.getGame().getActualMap().getTileMap().getWidth());
                    Game.getGame().setHeightMap(Game.getGame().getActualMap().getTileMap().getHeight());
                    Game.getGame().setHandler(new Handler());
                    */
                    p.setX(t.getDestination().getX());
                    p.setY(t.getDestination().getY());
                }
                
                nextMove=false;
                
                
            }
            //if (face.nextStep().intersects(g.getBounds())) {
                
            /*
             if (getBottomBounds().intersects(g.getBounds()))//&&g.getId()!=SubjectEnum.Teleport)
             {
                
             y = g.getY() - height-1;
             break;
             }

             if (getTopBounds().intersects(g.getBounds())) {//&&g.getId()!=SubjectEnum.Tel){
                
             y = g.getY() + g.height+1;
             break;
             }
             if (getLeftBounds().intersects(g.getBounds())) {//&&g.getId()!=SubjectEnum.Block){
             nextMove=false;
             x = g.getX() + g.width +1;
             break;
             }
             if (getRightBounds().intersects(g.getBounds())) {//&&g.getId()!=SubjectEnum.Block){
             nextMove=false;
             x = g.getX() - width - 1;
             break;
             }
             */
        }
        
    

    //dialog deve lavorare solo con oggetti interactable (item e persone per adesso)
    public void dialog(ObjectManager o) {
        GameObject g = o.get(face.nextStep());
        if (!(g instanceof Interactable))
        {
                setVelX(0);
                setVelY(0);
                GameStateManager.getInstance().setState(PauseState.getInstance());
                
                ((Interactable) g).interact();
                GameStateManager.getInstance().setState(PlayState.getInstance());
                
        }    
    }

    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (velX > 0) {
            rightWalk.drawAnimation(g,p.getX(), p.getY(), width, height);
            return;
        }
        
        if (velX < 0) {
            leftWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        if (velY > 0) {
            downWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        
        if (velY < 0) {
            upWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        face.drawFace(g);
    }
    
    private Position visualViewOfPlayer() {
        return face.visualViewOfPlayer();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    @Override
    public Serializable save() {
        ArrayList<Serializable> list = new ArrayList<>();
        list.add(getX());
        list.add(getY());
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        List<Serializable> list = (List<Serializable>) obj;
        setX((int) list.get(0));
        setY((int) list.get(1));
    }

}
