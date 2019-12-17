/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import java.awt.Graphics2D;

/**
 *
 * @author liovi
 */


public class MapManagerMio {
    private final int NUMBEROFMAPS = 2;
    private int actualMap;
    
    private MappaMia[] maps;
    public static MapManagerMio instance;
    
    public static MapManagerMio getInstance() {
        if (instance == null) {
            instance = new MapManagerMio();
        }
        return instance;
    }

    private MapManagerMio() {
        maps[0] = new MappaMia("ModernTownMap.tmx");
//        maps[1] = new Mappa("2TilesetMap");
        actualMap = 0;
    }
    
    public int getActualMap(){
        return actualMap;
    }
    
    public MappaMia getMap() {
        return maps[actualMap];
    }
    
    public void setMap(int n){
        this.actualMap = n;
    }
    
    public void render() {
        this.maps[actualMap].render();
    }
    
}
