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
 * This state shows the minimap of the actual map
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
    
    /**
     * returns the instance
     * @return
     * @throws game.Interfaces.Initializable.InitException 
     */
    public static MapState getInstance() throws Initializable.InitException{
        if (instance == null) {
            instance = new MapState();
        }
        return instance;
    }
    
    private MapState() {
    }
    
    /**
     * initialize the image
     * @throws game.Interfaces.Initializable.InitException 
     */
    @Override
    public void init() throws Initializable.InitException {
        try {
            here = ImageIO.read(getClass().getResource("/Sprites/here.png"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Map image");
        }
    }

    /**
     * nothing to tick
     */
    @Override
    public void tick() {

    }

    /**
     * renders the image on the screen
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
        g.drawImage(img, 0, 0,Game.WIDTHSCREEN,Game.HEIGHTSCREEN2, null);
        xPlayer = Player.getIstance().getPosition().getX();
        yPlayer = Player.getIstance().getPosition().getY();
        heightMap = MapManager.getInstance().getMap().getHeightMap();
        widthMap = MapManager.getInstance().getMap().getWidthMap();
        xPlayerInMap = (int)(xPlayer*(Game.WIDTHSCREEN-32)/img.getWidth());
        yPlayerInMap = (int)(yPlayer*Game.HEIGHTSCREEN/img.getHeight());
        g.drawImage(here, xPlayerInMap, yPlayerInMap,35,35,null);
    }

    /**
     * handles the input of this state
     * @param cmd 
     */
    @Override
    public void handleInput(KeyCommand cmd) {

        cmd.visitMapState(instance);
    }
    
    /**
     * sets the minimap of the actual map
     * @param path
     * @throws game.Interfaces.Initializable.InitException 
     */
    public void setMiniMap(String path) throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Map image");
        }
    }

}
