/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;

import exam.question.Materia;
import game.GameObjects.Teleport;
import game.GameObjects.Player;
import game.GameObjects.Person;
import game.GameObjects.SubjectEnum;
import game.GameObjects.Item;
import game.GameObjects.Destination;
import game.GameObjects.Camera;
import game.GameObjects.Block;
import game.GameObjects.GameObject;
import game.GameObjects.Professor;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import saving.Saveable;
import saving.exceptions.LoadingException;
import quests.ItemDef;
import unisagui.GuiManager;

/**
 * Game represents the thread that runs the game.
 *
 * @author simon
 */
public class Game extends Canvas implements Runnable, Saveable {

    /**
     * variable that says if the game has started.
     */
    public boolean isRunning = false;
    private final static Game instance = new Game();

    private Game() {
    }

    public static Game getGame() {
        return instance;
    }
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
    public static final int WIDTHSCREEN = 500,
            HEIGHTSCREEN = 500,
            HEIGHTSCREEN2 = HEIGHTSCREEN + 32,
            PLAYERSPEED = 2,
            ANIMATIONSPEED=4,
            DIMENSIONSPRITE = 32;
    private int WIDTHMAP, HEIGHTMAP;

    public final static double AMOUNTOFTICKS = 30.0;
    protected GameState state = new PlayState(this);
    protected static Map[] maps = new Map[5];
    protected static int actualMap;
    protected static TileMap tileMap;
    protected static Player player;
    protected int skin;
    protected String namePlayer;
    private LinkedList<Item> listOfAllItem = new LinkedList<Item>();

    /**
     * method that loads the resources of game(maps, objects, camera, handler
     * and key listener).
     */

    public void setSkin(int s) {
        this.skin = s;
    }

    public void setName(String s) {
        this.namePlayer = s;
    }

    private void initResources() {
        TileMap t0 = new TileMap(16, 960, 960);
        t0.loadTiles("/Tilesets/ExtTileset.gif");
        TileMap t1 = new TileMap(16, 960, 960);
        t1.loadTiles("/Tilesets/ExtTileset.gif");
        t0.loadMap("/Maps/ExtMap.map");
        t1.loadMap("/Maps/ExtMap.map");
        maps[0] = new Map(t0);
        maps[0].addObject(new Block(150, 150));
        maps[0].addObject(new Teleport(250, 250, "Tileset/tileset.gif", 1, new Destination(20, 20)));
        maps[0].addObject(new Item(300, 300, "/Sprites/item.png", "Sfera pokè", 0,ItemDef.calcolatrice));
        maps[1] = new Map(t1);
        maps[1].addObject(new Block(100, 70));
        maps[1].addObject(new Block(70, 40));
        maps[1].addObject(new Professor("Foggia", 200, 200, "/Sprites/foggia.png",Materia.matematica));
        actualMap = 0;
        WIDTHMAP = maps[actualMap].getTileMap().getWidth();
        HEIGHTMAP = maps[actualMap].getTileMap().getHeight();
        this.addKeyListener(new KeyInput(handler, this));

    }

    /**
     * method that load the textures and animations of the player
     */
    private void initPlayer() {
        texturePlayer = new BufferedImage[12];
        try {
            BufferedImage characterImage = ImageIO.read(
                    getClass().getResourceAsStream("/Sprites/sprite32.png")
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
        player = Player.getIstance(this);
        player.changeFaceSet(Game.texturePlayer[6], Game.texturePlayer[3], Game.texturePlayer[9], Game.texturePlayer[0]);

        player.changeAnimationSet(new Animation(ANIMATIONSPEED, texturePlayer[7], texturePlayer[8]),
                new Animation(ANIMATIONSPEED, texturePlayer[4], texturePlayer[5]),
                new Animation(ANIMATIONSPEED, texturePlayer[10], texturePlayer[11]),
                new Animation(ANIMATIONSPEED, texturePlayer[1], texturePlayer[2]));
        player.setX(50);
        player.setY(50);

    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        if (!(obj instanceof ArrayList)) {
            throw new LoadingException();
        } else {
            ArrayList<Serializable> l = (ArrayList) obj;
            {
                skin = (int) l.get(0);
                actualMap = (int) l.get(1);
                player.setX((int) l.get(2));
                player.setY((int) l.get(3));
                player.setInventory((LinkedList) l.get(4));
                /*initPlayer();
                initInventory();   */

            }
        }
    }

    @Override
    public Serializable save() {
        ArrayList<Serializable> l = new ArrayList<>();
        l.add(skin);
        l.add(actualMap);
        l.add(player.getX());
        l.add(player.getY());
        l.add(player.inventory);
        return l;
    }

    /*da completare
     @Override
     public Serializable save(){

     }/*


     //da completare
     @Override
     public void load(Serializable obj){
     if(obj instanceof List)
     {
     List<Serializable> l = (List)obj;
     for(Serializable s:l)
     {

     }
     }

     }
     /*
     /**
     * method that calls all other init methods.
     */
    private void initDefaultMaps() {
        LinkedList<Item> listOfMap1ToSpawn = new LinkedList<>(listOfAllItem);
        listOfMap1ToSpawn.removeIf(item -> item.getMapToSpawn() == 0);
        LinkedList<Item> listOfMap0ToSpawn = new LinkedList<>(listOfAllItem);
        listOfMap0ToSpawn.removeIf(item -> item.getMapToSpawn() == 1);
        for (Item i : listOfMap0ToSpawn) {
            maps[0].addObject(i);
        }
        for (Item i : listOfMap1ToSpawn) {
            maps[1].addObject(i);
        }
    }

    private void initMaps() {

        LinkedList<Item> listOfMap1ToSpawn = new LinkedList<>(listOfAllItem);
        listOfMap1ToSpawn.removeIf(item -> item.getMapToSpawn() == 0);
        LinkedList<Item> listOfMap0ToSpawn = new LinkedList<>(listOfAllItem);
        listOfMap0ToSpawn.removeIf(item -> item.getMapToSpawn() == 1);
        listOfMap0ToSpawn.removeAll(player.inventory.getInventory());
        listOfMap1ToSpawn.removeAll(player.inventory.getInventory());
        for (Item i : listOfMap0ToSpawn) {
            maps[0].addObject(i);
        }
        for (Item i : listOfMap1ToSpawn) {
            maps[1].addObject(i);
        }

    }

    private void init() {
        initResources();
        initPlayer();
        if (player.inventory.length() != 0) {
            initMaps();
        } else {
            initDefaultMaps();
        }
        handler = new Handler();
        camera = new Camera(0, 0, player);
    }

    /**
     * method that run the thread
     */
    public synchronized void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * method that initializes the game engine and calls init methods. It calls
     * render and tick methods according to the setting of game engine.
     */
    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / AMOUNTOFTICKS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                state.tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
    }

    public void updateActualMap(int i) {
        actualMap = i;
    }

    public Map getActualMap() {
        return maps[actualMap];
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler h) {
        handler = h;
    }

    /**
     * method that is responsible for rendering all the graphical objects.
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        state.render(g);
        g.dispose();
        bs.show();

    }

    public float getHeightMap() {
        return HEIGHTMAP;
    }

    public float getWidthMap() {
        return WIDTHMAP;
    }

    public void setHeightMap(int h) {
        this.HEIGHTMAP = h;
    }

    public void setWidthMap(int w) {
        this.WIDTHMAP = w;
    }

    /**
     * method that creates a new window and starts the game
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GuiManager guiManager = GuiManager.getInstance();
        guiManager.startGame(Game.getGame());

    }

}
