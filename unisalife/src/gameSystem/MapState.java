/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.GameObjects.Player;
import game.Interfaces.Initializable;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.map.MapManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class MapState extends GameState {

    private static MapState instance;
    private static BufferedImage img;
    private static BufferedImage here;
    
    private int xPlayer;
    private int yPlayer;
    private int heightMap;
    private int widthMap;
    private int xPlayerInMap;
    private int yPlayerInMap;
    
    public static MapState getInstance() throws Initializable.InitException{
        if (instance == null) {
            instance = new MapState();
        }
        return instance;
    }
    
    private MapState() {
    }
    
    @Override
    public void init() throws Initializable.InitException {
        try {
            here = ImageIO.read(getClass().getResource("/Sprites/here.png"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Map image");
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
        g.drawImage(img, 0, 0,Game.WIDTHSCREEN,Game.HEIGHTSCREEN2, null);
        xPlayer = Player.getIstance().getPosition().getX();
        yPlayer = Player.getIstance().getPosition().getY();
        heightMap = MapManager.getInstance().getMap().getHeightMap();
        widthMap = MapManager.getInstance().getMap().getWidthMap();
        xPlayerInMap = (int)(xPlayer*Game.WIDTHSCREEN/widthMap);
        yPlayerInMap = (int)(yPlayer*Game.HEIGHTSCREEN2/heightMap);
        g.drawImage(here, xPlayerInMap, yPlayerInMap,35,35,null);
    }

    @Override
    public void handleInput(KeyCommand cmd) {

        cmd.visitMapState(instance);
    }
    
    public void setMinimap(String path) throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Map image");
        }
    }

}
