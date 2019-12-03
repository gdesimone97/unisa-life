/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.GameResources;
import game.GameObjects.Teleport;
import game.GameObjects.Player;
import game.GameObjects.Person;
import game.GameObjects.ObjectId;
import game.GameObjects.Item;
import game.GameObjects.Destination;
import game.GameObjects.Camera;
import game.GameObjects.Block;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    protected static Handler handler;
    protected static Camera camera;

    /**
     *
     */
    protected static BufferedImage[] texturePlayer;
    public static final int WIDTHSCREEN=500,
                            HEIGHTSCREEN=500,
                            HEIGHTSCREEN2 = HEIGHTSCREEN + 32,
                            PLAYERSPEED=2,
                            DIMENSIONSPRITE=32;
    private int WIDTHMAP,HEIGHTMAP;
    
    public final static double AMOUNTOFTICKS = 30.0;
    protected GameState state=new NotGameState(this);
    protected static Map[] maps=new Map[5];
    protected static int actualMap;
    protected static TileMap tileMap;
    protected static Player player;
    
    
    
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
        this.addKeyListener(new KeyInput(handler,this));
        
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
        texturePlayer[0]=characterImage.getSubimage(32,0, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[1]=characterImage.getSubimage(0,0, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[2]=characterImage.getSubimage(64,0, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[3]=characterImage.getSubimage(32,32, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[4]=characterImage.getSubimage(0,32, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[5]=characterImage.getSubimage(64,32, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[6]=characterImage.getSubimage(32,64, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[7]=characterImage.getSubimage(0,64, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[8]=characterImage.getSubimage(64,64, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[9]=characterImage.getSubimage(32,96, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[10]=characterImage.getSubimage(0,96, DIMENSIONSPRITE, DIMENSIONSPRITE);
        texturePlayer[11]=characterImage.getSubimage(64,96, DIMENSIONSPRITE, DIMENSIONSPRITE);
    }
        
        catch (Exception e) {
            System.exit(4);
    }
        player=Player.getIstance(this);
        player.changeFaceSet(Game.texturePlayer[0],Game.texturePlayer[3], Game.texturePlayer[6],Game.texturePlayer[9]);

        player.changeAnimationSet(new Animation(PLAYERSPEED,texturePlayer[1],texturePlayer[2]),
                                    new Animation(PLAYERSPEED,texturePlayer[4],texturePlayer[5]),
                                    new Animation(PLAYERSPEED,texturePlayer[7],texturePlayer[8]),
                                    new Animation(PLAYERSPEED,texturePlayer[10],texturePlayer[11]));
        player.setX(50);
        player.setY(50);
        
    }
    
    /**
     * method that calls all other init methods.
     */
    private void init(){
        
        initResources();
        initPlayer();
        handler = new Handler();
        camera=new Camera(0,0,player);
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
    @Override
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
    
    public void updateActualMap(int i){
        actualMap=i;
    }
    
    public Map getActualMap(){
        return maps[actualMap];
    }
    
    public Handler getHandler(){
        return handler;
    }
    
    public void setHandler(Handler h){
        handler=h;
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
    
    public float getHeightMap(){
        return HEIGHTMAP;
    }
    
    public float getWidthMap(){
        return WIDTHMAP;
    }
    
    public void setHeightMap(int h){
        this.HEIGHTMAP=h;
    }
    
    public void setWidthMap(int w){
        this.WIDTHMAP=w;
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
