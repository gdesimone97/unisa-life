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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class MapState extends GameState{
    private static MapState instance;
    private BufferedImage img;
    
    public static MapState getInstance() throws Initializable.InitException {
        if (instance == null) {
            instance = new MapState();
        }
        return instance;
    }
    
    private MapState() throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/map.jpg"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Map image");
        }
    }
    
    
    
    @Override
    public void init() {
        
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform at = new AffineTransform();
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
        g.drawImage(img, at, null);
        g.setColor(Color.red);
        int xPlayer = Player.getIstance().getPosition().getX();
        int yPlayer = Player.getIstance().getPosition().getY();
        int heightMap = MapManager.getInstance().getMap().getHeightMap();
        int widthMap = MapManager.getInstance().getMap().getWidthMap();
        int xPlayerInMap = (int)Math.floor(xPlayer*img.getWidth()/widthMap);
        int yPlayerInMap = (int)Math.floor(yPlayer*img.getHeight()/heightMap);
        g.setStroke(new BasicStroke(2));
        g.drawLine(xPlayerInMap - 3, yPlayerInMap, xPlayerInMap + 3, yPlayerInMap);
        g.drawLine(xPlayerInMap, yPlayerInMap-3, xPlayerInMap, yPlayerInMap+3);
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        //se la vede peppe
    }
    
}
