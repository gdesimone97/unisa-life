/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import database.DatabaseManager;
import database.FileNotSetException;
import database.ObjectNotFoundException;
import game.GameResources.Map;
import game.Interfaces.Initializable;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import quests.quest.QuestsSingleton;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author liovi
 */
public class MapManager implements Initializable {

    private int actualMap;

    private Map[] maps;
    public static MapManager instance;

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    private MapManager() {
        // Soluzione momentanea, quando c'è il database, dovrà essere vuoto!
        
        //this.maps = getMaps(QuestsSingleton.getInstance().getCurrentLevel());
        maps = new Map[2];
        maps[0] = new Map();
        actualMap = 0;
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
            actualMap = 0;
        } catch (FileNotSetException ex) {
            throw new InitException("File not specified in Database");
        } catch (ObjectNotFoundException ex) {
            throw new InitException("Objects not found in Database");
        }
    }
    
    public void addDynamicObjects() {
        
    }
}
