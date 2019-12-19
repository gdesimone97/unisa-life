/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import exam.booklet.Subject;
import game.GameObjects.Coin;
import game.GameObjects.Cook;
import game.GameObjects.Distributor;
import game.GameObjects.GameObject;
import game.GameObjects.Guardian;
import game.GameObjects.Item;
import game.GameObjects.ObjectManager;
import game.GameObjects.Player;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import game.GameObjects.Teleport;
import game.Interfaces.Renderable;
import gameSystem.Game;
import java.awt.Graphics2D;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author 1997g
 */


/**
 * Class map contains a tilemap tMap representing a map of the game and the list mapObject that containts all the GameObjects objects that belongs to that map(without
 * considering Player. If an objects is collected by the Player, it must be removed from the list.
 *
*/
public class Map implements Runnable {
    private TileMap tMap;
    private ObjectManager mapObjects;

    private boolean generateRandomCoins = false;

    /**
     * Constructor that initializes tMap with the passed TileMap t and
     * the mapObject with an empty list.
     * @param t TileMap
     */
    public Map(){
        tMap = new TileMap(3200, 3200, "/Tilesets/TilesetConSfondo.png", "/Maps/Mappa.map");
        try {
            ConcurrentHashMap<Position,GameObject> fixed = new ConcurrentHashMap<Position,GameObject>();
            ConcurrentHashMap<Position,GameObject> dynamic = new ConcurrentHashMap<Position,GameObject>();
            
            Position p = new Position(1248, 2144);
            dynamic.put(p.getScaledPosition(), new Professor("Foggia", p, "/Sprites/foggiasprite.png", new Subject("Matematica")));
            p = new Position(320, 160);
            dynamic.put(new Position(10,5), new Item(p, "/Sprites/note.png", "appuntidimatematica1"));
            p = new Position(320, 64);
            dynamic.put(new Position(10,2), new Item(p, "/Sprites/note.png", "appuntidimatematica2"));
            p = new Position(320, 320);
            dynamic.put(new Position(10,10), new Item(p, "/Sprites/calculator.png","calcolatrice"));
            p = new Position(0,128);
            dynamic.put(p.getScaledPosition(), new Teleport(p,0,new Position(0,0)));
            p = new Position(352,864);
            dynamic.put(p.getScaledPosition(), new Distributor(p,"distributor"));
            p = new Position(1280,2144);
            dynamic.put(p.getScaledPosition(), new Cook("cuoco", p , "/Sprites/signoramensasprite.png"));
            p = new Position(1312,2144);
            dynamic.put(p.getScaledPosition(), new Guardian("guardiano",p,"/Sprites/tiziomensasprite.png"));
            
            
            mapObjects = new ObjectManager(fixed, dynamic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        startGeneratingCoins();
    }

    /**
     * Constructor that initializes tMap with the passed TileMap t and
     * the mapObject with the passed LinkedList l.
     * @param t Tmap
     * @param hm HashMap of GameObject objects
     */
    public Map(TileMap tMap, ObjectManager mapObjects){
        this.tMap = tMap;
        this.mapObjects = mapObjects;
    }


    /**
     * Add a new GameObject object g to the list.
     * @param g GameObject
     */
    public void addObject(Position p,GameObject g){
       try{
           mapObjects.addObject(p, g);
       }
       catch(Exception e){
           System.exit(-1);
       }
    }
    /**
     * Remove(if presents) the GameObject object g from the list.
     * @param g
     * @return true if the object was present and has been removed or false if it was not present.
     */
    public GameObject removeObject(Position p){
        GameObject o = null;
        try{
            o = mapObjects.removeObject(p);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return o;
    }
    /**
     *
     * @return mapObjects List.
     */
    public ObjectManager getObjectManager(){
        return mapObjects;
    }

    public int getWidthMap(){
        return this.tMap.getWidth();
    }

    public int getHeightMap(){
        return this.tMap.getHeight();
    }
    public GameObject getGameObject(Position p){
        return mapObjects.getObjectInNextPosition(p);
    }

    /**
     *
     * @return tMap TileMap.
     */
    public TileMap getTileMap(){
        return tMap;
    }

    public void render(Graphics2D g) {
        tMap.render(g);
        for(GameObject go : mapObjects.getDynamic().values()) {
            if(go instanceof Renderable) {
                ((Renderable) go).render(g);
            }
        }
    }

    public void startGeneratingCoins() {
        new Thread(this).start();
    }

    public void stopGeneratingCoins() {
        generateRandomCoins = false;
    }

    /**
     * this run method creates a permanent cycle that randomly adds Coins in the map,
     * near the player
     */
    @Override
    public void run() {
        generateRandomCoins = true;
        Random rand = new Random();

        while(generateRandomCoins) {
            try {
                // sleep a certain period of time until next coin is spawned (could be random too)
                sleep(15*1000);
                
                int rX = 0;
                int rY = 0;
                int cX;
                int cY;
                int cell = Game.DIMENSIONSPRITE;

                // compute a random distance from the player (between 1 and 7)
                rX = rand.nextInt(Game.HEIGHTSCREEN/(2*cell)-2)+1;
                rY = rand.nextInt(Game.WIDTHSCREEN/(2*cell)-2)+1;

                // compute the position in which coin has to spawn
                cX = ((int)Player.getIstance().getX()/cell) * cell + (rand.nextBoolean() ? + rX * cell : - rX * cell);
                cY = (int)Player.getIstance().getY()/cell * cell + (rand.nextBoolean() ? + rY * cell : - rY * cell);

                // add coin in the map (if it's already present a GameObject, exception is catched and compute restarts
                Position p = new Position(cX, cY);
                mapObjects.addObject(p.getScaledPosition(), new Coin(p, "/Sprites/coin.png", "moneta"));
            } catch (InterruptedException ex) {
            } catch (Exception ex) {
            }
        }
    }



}
