/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import game.GameResources.Map;
import java.awt.Graphics2D;
import java.io.Serializable;
import quests.quest.QuestsSingleton;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author liovi
 */
public class MapManager implements Saveable {

    private final int NUMBEROFMAPS = 2;
    private int actualMap;

    private Map[] maps = new Map[NUMBEROFMAPS];
    public static MapManager instance;

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    public MapManager() {
        //    this.maps = getMaps(QuestsSingleton.getInstance().getCurrentLevel());
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
    public Serializable save() {
        return maps;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        this.maps = (Map[]) obj;
    }

}
