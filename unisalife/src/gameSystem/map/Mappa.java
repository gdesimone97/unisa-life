package gameSystem.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import game.GameObjects.GameObject;
import game.GameObjects.ObjectManager;
import game.GameObjects.Position;
import game.Interfaces.Renderable;
import java.awt.Graphics2D;

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
    
    public GameObject removeObject(Position p) throws Exception{
        return this.objects.removeObject(p);
    }
    
    public void addObject(Position p, GameObject g) throws Exception{
        this.objects.addObject(p, g);
    }
    
//    public void render(Graphics2D g) {
//        tiledMap.render(g);
//        for(GameObject go : mapObjects.values()) {
//            if(go instanceof Renderable) {
//                ((Renderable) go).render(g);
//            }
//        }
//    }
    
    
}