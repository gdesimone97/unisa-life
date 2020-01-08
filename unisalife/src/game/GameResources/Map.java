/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;

import game.GameObjects.Coin;
import game.GameObjects.GameObject;
import game.GameObjects.ImageNotLoadedException;
import game.GameObjects.ObjectManager;
import game.GameObjects.Player;
import game.GameObjects.Position;
import game.GameObjects.Renderable;
import gameSystem.Game;
import java.awt.Graphics2D;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Class map contains a tilemap tMap representing a map of the game and the list
 * mapObject that containts all the GameObjects objects that belongs to that
 * map(without considering Player. If an objects is collected by the Player, it
 * must be removed from the list.
 * @author 1997g
 */
public class Map implements Runnable {
    
    private TileMap tMap;
    private ObjectManager mapObjects;
    private String pathMiniMap;
    private boolean generateRandomCoins = false;
    

    /**
<<<<<<< HEAD
     * Constructor 
=======
     * Constructor that initializes tMap with the passed TileMap t and the
     * mapObject with an empty list.
     *
>>>>>>> f962c1e5bfca245c83cc2b53fadfbdb8b1e9b40e
     */
    public Map() {
    }

    /**
<<<<<<< HEAD
     * Constructor 
     *
     * @param tMap TileMap of the map 
     * @param mapObjects ObjectManager containing all GameObject of the map
     * @param pathMiniMap String represents the path of image to be rendered when game is in MapState
=======
     * Constructor that initializes tMap, mapObjects and the path of the relative
     * mini map
     *
     * @param tMap the tiled map
     * @param mapObjects the objects of the map
     * @param pathMiniMap the path of the mini map
>>>>>>> f962c1e5bfca245c83cc2b53fadfbdb8b1e9b40e
     */
    public Map(TileMap tMap, ObjectManager mapObjects, String pathMiniMap) {
        this.tMap = tMap;
        this.mapObjects = mapObjects;
        this.pathMiniMap=pathMiniMap;
    }
    
    /**
     * Get the mini map
     * @return the path of the mini map
     */
    public String getPathMiniMap() {
        return pathMiniMap;
    }

    /**
     * Add a new GameObject object g to the list.
<<<<<<< HEAD
     * @param p position of the GameObject
     * @param g GameObject to be added
=======
     *
     * @param p the position of the object
     * @param g the Renderable object
>>>>>>> f962c1e5bfca245c83cc2b53fadfbdb8b1e9b40e
     */
    public void addObject(Position p, Renderable g) {
        try {
            mapObjects.addObject(p, g);
        } catch (Exception e) {
        }
    }
    
    /**
     * Used to add the fixed objects to the map
     * 
     * @param p the position of the object
     * @param g the GameObject
     */
    public void addFixedObject(Position p, GameObject g) {
        try {
            mapObjects.addFixedObject(p, g);
        } catch (Exception e) {
        }
    }

    /**
     * Remove (if presents) the object from the list and returns it.
     *
<<<<<<< HEAD
     * @param p position of the object
     * @return true if the object was present and has been removed or false if
     * it was not present.
=======
     * @param p the position of the element
     * @return the GameObject if present otherwise null
>>>>>>> f962c1e5bfca245c83cc2b53fadfbdb8b1e9b40e
     */
    public GameObject removeObject(Position p) {
        GameObject o = null;
        try {
            o = mapObjects.removeObject(p);
        } catch (Exception e) {
        }
        return o;
    }

    /**
<<<<<<< HEAD
     *
     * @return the ObjectManager of the map
=======
     * Gives the ObjectManager
     * 
     * @return the mapObjects
>>>>>>> f962c1e5bfca245c83cc2b53fadfbdb8b1e9b40e
     */
    public ObjectManager getObjectManager() {
        return mapObjects;
    }

    /**
     * Returns the width of the map
     * 
     * @return an int that represents the width of the map
     */
    public int getWidthMap() {
        return this.tMap.getWidth();
    }

    /**
     * Returns the height of the map
     * 
     * @return an int that represents the height of the map
     */
    public int getHeightMap() {
        return this.tMap.getHeight();
    }

    /**
     * Gets the object in the next position
     * 
     * @param p the position of the objects
     * @return the object in the passed position
     */
    public GameObject getGameObject(Position p) {
        return mapObjects.getObjectInNextPosition(p);
    }

    /**
<<<<<<< HEAD
     *
     * @return TileMap of the map
=======
     * Gets the TileMap
     * 
     * @return the TileMap
>>>>>>> f962c1e5bfca245c83cc2b53fadfbdb8b1e9b40e
     */
    public TileMap getTileMap() {
        return tMap;
    }

    /**
     * Renderize the passed object
     * 
     * @param g the object to be renderized
     */
    public void render(Graphics2D g) {
        tMap.render(g);
        for (Renderable r : mapObjects.getDynamic().values()) {
            r.render(g);
        }
    }

    /**
     * Starts the thread that generates random coins
     */
    public void startGeneratingCoins() {
        new Thread(this).start();
    }

    /**
     * Stops the thread that generates random coins
     */
    public void stopGeneratingCoins() {
        generateRandomCoins = false;
    }

    /**
     * This run method creates a permanent cycle that randomly adds Coins in the
     * map, near the player
     */
    @Override
    public void run() {
        generateRandomCoins = true;
        Random rand = new Random();

        while (generateRandomCoins) {
            try {
                // sleep a certain period of time until next coin is spawned (could be random too)
                sleep(25 * 1000);
                
                int rX = 0;
                int rY = 0;
                int cX;
                int cY;
                int cell = Game.DIMENSIONSPRITE;

                // compute a random distance from the player (between 1 and 7)
                rX = rand.nextInt(Game.HEIGHTSCREEN / (2 * cell) - 2) + 1;
                rY = rand.nextInt(Game.WIDTHSCREEN / (2 * cell) - 2) + 1;

                // compute the position in which coin has to spawn
                cX = ((int) Player.getIstance().getX() / cell) * cell + (rand.nextBoolean() ? +rX * cell : -rX * cell);
                cY = (int) Player.getIstance().getY() / cell * cell + (rand.nextBoolean() ? +rY * cell : -rY * cell);

                // add coin in the map (if it's already present a GameObject, exception is catched and compute restarts
                Position p = new Position(cX, cY);
                mapObjects.addObject(p.getScaledPosition(), new Coin(p, "/Sprites/coin.png", "moneta"));
                
            } catch (InterruptedException ex) {
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Merge the passed ConcurrentHashMap of dynamic objects with the actual
     * 
     * @param dynamic a ConcurrentHashMap of dynamic objects
     */
    public void addDynamicObjects(ConcurrentHashMap<Position, Renderable> dynamic) {
        mapObjects.mergeDynamic(dynamic);
    }

    /**
     *  Gets the initial position of the player
     * 
     * @return a Position that indicates the initial position of the player
     */
    public Position getInitialPosition() {
        return tMap.getInitialPosition();
    }

    /**
     * Gets all the dynamic objects of the map
     * 
     * @return a ConcurrentHashMap of dynamic objects
     */
    public ConcurrentHashMap<Position, Renderable> getDynamicObjects() {
        return this.mapObjects.getDynamic();
    }
    
    /**
     * Reloads all the images of all the renderable objects
     * 
     * @throws ImageNotLoadedException
     */
    public void loadImages() throws ImageNotLoadedException {
        mapObjects.loadImages();
    }

}
