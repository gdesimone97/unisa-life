/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameResources.TileMap;
import java.io.Serializable;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author alfon
 */
public class TileMapWrapper implements Storable, Serializable{
    
    @Id
    private int id;
    private String p;
    private String m;
    private int l;
    private int w;

    public TileMapWrapper(int id, int l, int w, String p, String m) {
        this.id = id;
        this.p = p;
        this.m = m;
        this.l = l;
        this.w = w;
    }
    
    private TileMapWrapper(){
        
    }

    public int getId() {
        return id;
    }

    public String getP() {
        return p;
    }

    public String getM() {
        return m;
    }

    public int getL() {
        return l;
    }

    public int getW() {
        return w;
    }
    
    public TileMap buildTileMap(){
        return new TileMap(id, l, w, p, m);
    }
    
    
    
    @Override
    public String getIndex() {
        return Integer.toString(this.id);
    }
    
}
