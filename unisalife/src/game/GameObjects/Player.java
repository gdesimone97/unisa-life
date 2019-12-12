/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.GameResources.Animation;
import game.GameResources.Game;
import game.GameResources.Handler;
import game.GameResources.NotGameState;
import game.GameResources.PlayState;
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
public class Player extends GameObject implements Tickable, Renderable {
    
    private float velX = 0;
    protected float velY = 0;
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
    protected Game game;
    public GameInventory inventory;
    private boolean nextMove = true;

    /*public Player(float x,float y,SubjectEnum i){
     super(x,y,i);
     }*/
    private Player(float x, float y, Game g, GameInventory inventory) {
        super(x, y);
        face = new DownFaceState(this);
        game = g;
        this.inventory = inventory;

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
    
    public void changeFaceSet(BufferedImage down, BufferedImage left, BufferedImage right, BufferedImage up) {
        facingLeftImage = left;
        facingRightImage = right;
        facingUpImage = up;
        facingDownImage = down;
    }
    
    public void changeAnimationSet(Animation down, Animation left, Animation right, Animation up) {
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
    public static Player getIstance(Game g) {
        if (uniqueIstance == null) {
            return new Player(0, 0, g, new GameInventory());
        }
        return uniqueIstance;
    }
    
    public void setInventory(LinkedList<Item> l) {
        this.inventory = new GameInventory(l);
    }

    /**
     *
     * @param a
     */
    /**
     *
     * @return
     */
    public float getVelX() {
        return velX;
    }

    /**
     *
     * @return
     */
    public float getVelY() {
        return velY;
    }

    /**
     *
     * @param velX
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     *
     * @param velY
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }

    /**
     *
     * @return
     */
    public Rectangle getTopBounds() {
        return new Rectangle((int) x + 1, (int) y, (int) width - 2, 3);
    }

    /**
     *
     * @return
     */
    public Rectangle getBottomBounds() {
        return new Rectangle((int) x + 1, (int) (y + height - 3), (int) width - 2, 3);
    }

    /**
     *
     * @return
     */
    public Rectangle getLeftBounds() {
        return new Rectangle((int) x - 1, (int) y + 1, 3, (int) height - 2);
    }

    /**
     *
     * @return
     */
    public Rectangle getRightBounds() {
        return new Rectangle((int) (x + width - 1), (int) y + 1, 2, (int) height - 2);
    }

    /**
     *
     *
     */
    @Override
    public void tick(/*LinkedList<GameObject> objects*/) {
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
        collisions(game.getActualMap().getList());
        if (x + velX > 10 && x + velX < game.getWidthMap() - width && this.nextMove == true) {
            x += velX;
        }
        if (y + velY > 10 && y + velY < game.getHeightMap() - 1.5 * height && this.nextMove == true) {
            y += velY;
        }
        //collisions(game.getActualMap().getList());
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();
    }

    /**
     *
     * @param objects
     */
    public void collisions(LinkedList<GameObject> objects) {
        for (GameObject g : objects) {
            if (getBounds().intersects(g.getBounds()) && g instanceof Teleport) {
                //collisions deve lavorare con tutti i gameobjects, preferibilmente prima i teleport          
                Teleport t = (Teleport) g;
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
                x = t.getDestination().getX();
                y = t.getDestination().getY();
                break;
            }
            if (face.nextStep().intersects(g.getBounds())) {
                this.nextMove = false;
            }
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
        
    }

    //dialog deve lavorare solo con oggetti interactable (item e persone per adesso)
    public void dialog(LinkedList<GameObject> l) {
        for (GameObject g : l) {
            if (g instanceof Interactable && visualViewOfPlayer().intersects(g.getBounds())) {
                this.game.setState(new NotGameState(game));
                ((Interactable) g).interact();
                this.game.setState(new PlayState(game));
                break;
            }
        }
    }

    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (velX > 0) {
            rightWalk.drawAnimation(g, (int) x, (int) y, width, height);
            return;
        }
        
        if (velX < 0) {
            leftWalk.drawAnimation(g, (int) x, (int) y, width, height);
            return;
        }
        if (velY > 0) {
            downWalk.drawAnimation(g, (int) x, (int) y, width, height);
            return;
        }
        
        if (velY < 0) {
            upWalk.drawAnimation(g, (int) x, (int) y, width, height);
            return;
        }
        face.drawFace(g);
    }
    
    private Rectangle visualViewOfPlayer() {
        return face.visualViewOfPlayer();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
}
