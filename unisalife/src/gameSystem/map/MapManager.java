/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import game.GameResources.Map;
import java.awt.Graphics2D;
import java.util.HashMap;


/**
 *
 * @author liovi
 */
public class MapManager {
    private int actualMap;
    
    private Mappa[] maps;
    public static MapManager instance;
    
    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    public MapManager() {
        maps[0] = new Mappa("2TilesetMap");
//        maps[1] = new Mappa();
        actualMap = 0;
    }
    
    public int getActualMap(){
        return actualMap;
    }
    
    public Mappa getMap() {
        return maps[actualMap];
    }
    
    public void setMap(int n){
        this.actualMap = n;
    }
    
    public void render(Graphics2D g) {
        this.maps[actualMap].render(g);
    }
    
}
