/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import database.DatabaseManager;
import database.FileNotSetException;
import database.ObjectNotFoundException;
import game.GameObjects.GameObject;
import game.GameObjects.ImageNotLoadedException;
import game.GameObjects.Position;
import game.GameObjects.Renderable;
import game.GameResources.Map;
import game.Interfaces.Initializable;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author liovi
 */
public class MapManager implements Initializable, Saveable {
    
    private int actualMap;
    private int mapNumber;

    private Map[] maps;
    private static MapManager instance;

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    private MapManager() {
        // Soluzione momentanea, quando c'è il database, dovrà essere vuoto!

//        maps = new Map[2];
//        maps[0] = new Map();
//        actualMap = 0;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public int getActualMap() {
        return actualMap;
    }

    public Map getMap() {
        return maps[actualMap];
    }

    public void setMap(int n) {
        this.actualMap = n;
    }

    public void render(Graphics2D g) {
        this.maps[actualMap].render(g);
    }

    @Override
    public void init() throws InitException {
        try {
            maps = DatabaseManager.getDatabaseManager().getMaps();
            mapNumber = maps.length;
            actualMap = 0;
            addDynamicObjects();
        } catch (FileNotSetException ex) {
            throw new InitException("File not specified in Database");
        } catch (ObjectNotFoundException ex) {
            throw new InitException("Objects not found in Database");
        } catch (ClassNotFoundException ex) {
            throw new InitException("Class not found during Database query");
        }
    }

    private void addDynamicObjects() throws FileNotSetException, ObjectNotFoundException {
        ConcurrentHashMap<Position, GameObject>[] objectsFromLevel = DatabaseManager.getDatabaseManager().getObjectsFromLevel(0);
        for (int i = 0; i < objectsFromLevel.length; i++) {
            maps[i].addDynamicObjects(objectsFromLevel[i]);
        }
    }

    public void startGeneratingCoins() {
        this.maps[actualMap].startGeneratingCoins();
    }

    public void stopGeneratingCoins() {
        this.maps[actualMap].stopGeneratingCoins();
    }

    @Override
    public Serializable save() {
        ArrayList<ConcurrentHashMap<Position, GameObject>> list = new ArrayList<>();
        for (Map map : maps) {
            ConcurrentHashMap<Position, GameObject> objects = map.getDynamicObjects();
            list.add(objects);
        }
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        ArrayList<ConcurrentHashMap<Position, GameObject>> list = (ArrayList<ConcurrentHashMap<Position, GameObject>>) obj;
        for (int i = 0; i < list.size(); i++) {
            ConcurrentHashMap<Position,GameObject> mapObject = list.get(i);
            mapObject.forEachValue(5, value ->{
                if(value instanceof Renderable){
                    Renderable object = (Renderable) value;
                    try{
                        object.loadImage();
                    }catch(ImageNotLoadedException ex){
                        System.out.println(ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }

}
