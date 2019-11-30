/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
 *
 * @author simon
 */
public class Game extends Canvas implements Runnable {

    public boolean isRunning = false;
    public Thread thread;
    static Handler handler;
    static Camera camera;
    public static BufferedImage[] texturePlayer;
    //public static int WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width,HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int WIDTHSCREEN=500,HEIGHTSCREEN=500, HEIGHTSCREEN2 = HEIGHTSCREEN + 32;
    public static int WIDTHMAP=928,HEIGHTMAP=928,speed=2;
    public static final int dimensionSprite=32;
    public double AMOUNTOFTICKS = 30.0;
   
    static Menu menu;
    static GameState state=new MenuState();
    static Map[] maps=new Map[5];
    static int actualMap;
    static TileMap tileMap;
    static Player player;
    
    private void tick(){
        state.tick();
    }
    
    
    /**
     * ciao
     */
    private void initResources(){
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
        TileMap t0  = new TileMap(32,928,928);
        t0.loadTiles("/Tilesets/tileset.gif");
	TileMap t1 = new TileMap(32,928,928);
        t1.loadTiles("/Tilesets/tileset.gif");
        t0.loadMap("/Maps/map7.map");
        t1.loadMap("/Maps/map8.map");
        maps[0]=new Map(t0);
        maps[0].addObject(new Block(150,150,ObjectId.Block));
        maps[0].addObject(new Teleport(250,250,ObjectId.Teleport,"Tileset/tileset.gif",1,new Destination(20,20)));   
        maps[1]=new Map(t1);
        maps[1].addObject(new Block(100,70,ObjectId.Block));
        maps[1].addObject(new Block(70,40,ObjectId.Block));
        
    }
    
    private void init(){
        
        initResources();
        player=new Player(50,50,ObjectId.Player,new Animation(texturePlayer[10],texturePlayer[11]),
                new Animation(texturePlayer[1],texturePlayer[2]),
                new Animation(texturePlayer[4],texturePlayer[5]),
                new Animation(texturePlayer[7],texturePlayer[8])
        );
        
        actualMap = 0;
        WIDTHMAP=maps[actualMap].getTileMap().getWidth();
        HEIGHTMAP=maps[actualMap].getTileMap().getHeight();
        menu=new Menu();
        handler = new Handler(/*actualMap.getList()*/);
        //handler.addObject(player);
        camera=new Camera(0,0);
        this.addKeyListener(new KeyInput(handler));
    }
    
    public synchronized void start(){
        if (isRunning)
            return;
        isRunning=true;
        thread=new Thread(this);
        thread.start();
    }
    
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
                  tick();
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
        /*if(state==State.Game){
            g.setColor(Color.white);
            g.fillRect(0, 0, WIDTHSCREEN, HEIGHTSCREEN2);
            BufferedImage image=null;
            try
        {
          image=ImageIO.read(new File("C:\\Users\\simon\\Desktop\\casa.png"));
          g.drawImage(image,100,100, this);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          System.exit(1);
        }
               
            g2d.translate(camera.getX(), camera.getY());
            handler.render(g2d);
            g2d.translate(-camera.getX(), -camera.getY());
        }
        else{
            g.setColor(Color.BLACK);
            g.fillRect(0,0,WIDTHSCREEN,HEIGHTSCREEN2 );
            menu.render(g);
        }
        */
        g.dispose();
        bs.show();
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Window(/*Toolkit.getDefaultToolkit().getScreenSize()*//*Toolkit.getDefaultToolkit().getScreenSize()*/new Dimension(WIDTHSCREEN,HEIGHTSCREEN2),"Demo",new Game());
        
    }
    
}
