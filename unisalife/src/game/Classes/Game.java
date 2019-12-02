/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.Classes;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
/**
 * Game represents the thread that runs the game. 
 * @author simon
 */
public class Game extends Canvas implements Runnable {

    /**
     *variable that says if the game has started.
     */
    public boolean isRunning = false;

    /**
     *
     */
    public Thread thread;
    static Handler handler;
    static Camera camera;

    /**
     *
     */
    public static BufferedImage[] texturePlayer;
    //public static int WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width,HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int WIDTHSCREEN=500,

    /**
     *
     */
    HEIGHTSCREEN=500,

    /**
    
     */
    HEIGHTSCREEN2 = HEIGHTSCREEN + 32;
    public static int WIDTHMAP=928,

    /**
     *
     */
    HEIGHTMAP=928,

    /**
     *
     */
    speed=2;

    /**
     *
     */
    public static final int dimensionSprite=32;

    /**
     *
     */
    public double AMOUNTOFTICKS = 30.0;
   
    public static Menu menu;
    public static GameState state=new MenuState();
    static Map[] maps=new Map[5];
    public static int actualMap;
    static TileMap tileMap;
    static Player player;
    
    
    
    /**
     * method that loads the resources of game(maps, objects, camera, handler and key listener).
     */
    private void initResources(){
        TileMap t0  = new TileMap(32,928,928);
        t0.loadTiles("/Tilesets/tileset.gif");
	TileMap t1 = new TileMap(32,928,928);
        t1.loadTiles("/Tilesets/tileset.gif");
        t0.loadMap("/Maps/map7.map");
        t1.loadMap("/Maps/map8.map");
        maps[0]=new Map(t0);
        maps[0].addObject(new Block(150,150,ObjectId.Block));
        maps[0].addObject(new Teleport(250,250,ObjectId.Teleport,"Tileset/tileset.gif",1,new Destination(20,20)));
        maps[0].addObject(new Item(300,300,ObjectId.Item,"/Sprites/item.png","Sfera pokè"));
        maps[1]=new Map(t1);
        maps[1].addObject(new Block(100,70,ObjectId.Block));
        maps[1].addObject(new Block(70,40,ObjectId.Block));
        maps[1].addObject(new Person(200,200,ObjectId.Interactable,"/Sprites/foggia.png"));
        actualMap = 0;
        WIDTHMAP=maps[actualMap].getTileMap().getWidth();
        HEIGHTMAP=maps[actualMap].getTileMap().getHeight();
        menu=new Menu();
        handler = new Handler();
        camera=new Camera(0,0);
        this.addKeyListener(new KeyInput(handler));
        
    }
    /**
     * method that load the textures and animations of the player
     */
    
    private void initPlayer(){
        texturePlayer=new BufferedImage[12];
        try {
        BufferedImage characterImage= ImageIO.read(
				getClass().getResourceAsStream("/Sprites/character.png")
        );
        texturePlayer[0]=characterImage.getSubimage(32,0, dimensionSprite, dimensionSprite);
        texturePlayer[1]=characterImage.getSubimage(0,0, dimensionSprite, dimensionSprite);
        texturePlayer[2]=characterImage.getSubimage(64,0, dimensionSprite, dimensionSprite);
        texturePlayer[3]=characterImage.getSubimage(32,32, dimensionSprite, dimensionSprite);
        texturePlayer[4]=characterImage.getSubimage(0,32, dimensionSprite, dimensionSprite);
        texturePlayer[5]=characterImage.getSubimage(64,32, dimensionSprite, dimensionSprite);
        texturePlayer[6]=characterImage.getSubimage(32,64, dimensionSprite, dimensionSprite);
        texturePlayer[7]=characterImage.getSubimage(0,64, dimensionSprite, dimensionSprite);
        texturePlayer[8]=characterImage.getSubimage(64,64, dimensionSprite, dimensionSprite);
        texturePlayer[9]=characterImage.getSubimage(32,96, dimensionSprite, dimensionSprite);
        texturePlayer[10]=characterImage.getSubimage(0,96, dimensionSprite, dimensionSprite);
        texturePlayer[11]=characterImage.getSubimage(64,96, dimensionSprite, dimensionSprite);
    }
        
        catch (Exception e) {
            System.exit(4);
    }
        player=Player.getIstance();
        player.setFacingDownImage(Game.texturePlayer[0]);
        player.setFacingLeftImage(Game.texturePlayer[3]);
        player.setFacingRightImage(Game.texturePlayer[6]);
        player.setFacingUpImage(Game.texturePlayer[9]);
        player.setDownAnimation(new Animation(texturePlayer[1],texturePlayer[2]));
        player.setLeftAnimation(new Animation(texturePlayer[4],texturePlayer[5]));
        player.setRightAnimation(new Animation(texturePlayer[7],texturePlayer[8]));
        player.setUpAnimation(new Animation(texturePlayer[10],texturePlayer[11]));
        player.setX(50);
        player.setY(50);
    }
    
    /**
     * method that calls all other init methods.
     */
    private void init(){
        
        initResources();
        initPlayer();
    }
    
    /**
     *method that run the thread
     */
    public synchronized void start(){
        if (isRunning)
            return;
        isRunning=true;
        thread=new Thread(this);
        thread.start();
    }
    /**
     * method that initializes the game engine and calls init methods.
     * It calls render and tick methods according to the setting of game engine.
     */
    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / AMOUNTOFTICKS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                state.tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                updates = 0;
                }
            }
        }
    
    /**
     * method that is responsible for rendering all the graphical objects.
     */
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;
        state.render(g);
        g.dispose();
        bs.show();
        
    }
    
    /**
     * method that creates a new window and starts the game
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Window(new Dimension(WIDTHSCREEN,HEIGHTSCREEN2),"Demo",new Game());
        
    }
    
}
