/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import database.DatabaseManager;
import database.FileNotSetException;
import database.NoQuestsException;
import database.ObjectNotFoundException;
import game.GameObjects.ImageNotLoadedException;
import game.GameObjects.Position;
import game.GameObjects.Renderable;
import game.GameResources.Map;
import game.Interfaces.Initializable;
import gameSystem.MapState;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author liovi
 */
public class MapManager implements Initializable, Saveable {

    private int actualMap;
    private int mapNumber;
    private final int MAX_PARALLEL = 5;
    private Map[] maps;
    private static MapManager instance;

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    private MapManager() {
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

    public void setMap(int n) throws InitException {
        if (n > mapNumber || n < 0) {
            throw new InitException("Map number not allowed or out of bounds");
        }
        this.actualMap = n;
        MapState.getInstance().setMinimap(maps[actualMap].getPathMiniMap());
    }

    public void render(Graphics2D g) {
        this.maps[actualMap].render(g);
    }

    @Override
    public void init() throws InitException {
        try {
            maps = DatabaseManager.getDatabaseManager().getMaps();
            mapNumber = maps.length;
            setMap(0);
        } catch (FileNotSetException ex) {
            throw new InitException("File not specified in Database");
        } catch (ObjectNotFoundException ex) {
            throw new InitException("Objects not found in Database");
        } catch (ClassNotFoundException ex) {
            throw new InitException("Class not found during Database query");
        } catch (InitException ex) {
            throw ex;
        }
    }

    private void addDynamicObjects(int level) throws FileNotSetException, ObjectNotFoundException, ImageNotLoadedException {
        try {
            ConcurrentHashMap<Position, Renderable>[] objectsFromLevel = DatabaseManager.getDatabaseManager().getObjectsFromLevel(level);
            for (int i = 0; i < objectsFromLevel.length; i++) {
                maps[i].addDynamicObjects(objectsFromLevel[i]);
            }
        } catch (NoQuestsException ex) {
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
        ArrayList<ConcurrentHashMap<Position, Renderable>> list = new ArrayList<>();
        for (Map map : maps) {
            ConcurrentHashMap<Position, Renderable> objects = map.getDynamicObjects();
            list.add(objects);
        }
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        ArrayList<ConcurrentHashMap<Position, Renderable>> list = (ArrayList<ConcurrentHashMap<Position, Renderable>>) obj;
        for (int i = 0; i < list.size(); i++) {
            ConcurrentHashMap<Position, Renderable> mapObject = list.get(i);
            mapObject.forEachValue(this.MAX_PARALLEL, value -> {
                Renderable object = (Renderable) value;
                try {
                    object.loadImage();
                } catch (ImageNotLoadedException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(new LoadingException("Impossibile caricare immagini dopo la fase di load"));
                }
            });
            maps[i].addDynamicObjects(mapObject);
        }
    }
    
    public void setLevel(int newLevel) {
        try {
            addDynamicObjects(newLevel);
        } catch (ImageNotLoadedException ex) {
            throw new InitException("Image not loaded for one dynamic object");
        } catch (FileNotSetException ex) {
            throw new InitException("File not specified in Database");
        } catch (ObjectNotFoundException ex) {
            throw new InitException("Objects not found in Database");
        }
    }

}
