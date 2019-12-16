/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.map;

import java.util.HashMap;


/**
 *
 * @author liovi
 */
public class MapManager {
    
    private HashMap<Integer, Mappa> mappe;

    public MapManager(int numMaps, String[] nomimappe) {
        mappe = new HashMap<>();
        
        for (int i=0; i<numMaps; i++){
            mappe.put(i, new Mappa(nomimappe[0]));
        }
    }
    
    public Mappa getMap(int n){
        return this.mappe.get(n);
    }
    
    public void setMap(int n, Mappa map){
        this.mappe.replace(n, map);
    }
    
    
}
