package gameSystem.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import game.GameObjects.GameObject;
import game.GameObjects.ObjectManager;
import game.GameObjects.Position;

public class Mappa{
    TiledMap tiledMap;
    ObjectManager objects;

    public Mappa(String filename) {
        this.tiledMap = new TmxMapLoader().load("../Resources/Maps/"+filename+".tmx");
        this.objects = new ObjectManager();
    }
    
    public GameObject getObject(Position p){
        return this.objects.getObjectInNextPosition(p);
    }
    
    public void removeObject(Position p) throws Exception{
        this.objects.removeObject(p);
    }
    
    public void addObject(Position p, GameObject g) throws Exception{
        this.objects.addObject(p, g);
    }
    
    
    
}