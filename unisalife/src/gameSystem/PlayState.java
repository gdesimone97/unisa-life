/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.GameObjects.Camera;
import game.GameObjects.GameObject;
import game.GameObjects.Player;
import game.Interfaces.Renderable;
import gameSystem.keySettings.KeyCommand;
import gameSystem.map.MapManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public class PlayState extends GameState {

    private static PlayState instance;
    
//    private MapManager mapManager;
    private Player player;
    private GameManager gameManager;
    private Camera camera;
    private Color color = new Color(170,226,103);
    private int height;
    private int width;

    public static PlayState getInstance() {
        if (instance == null) {
            instance = new PlayState();
        }
        return instance;
    }
    
    private PlayState() {
        init();
    }

    @Override
    public void init() {
        gameManager = GameManager.getInstance();
        player = Player.getIstance();
        camera = gameManager.getCamera();
        height = gameManager.getGame().HEIGHTSCREEN2;
        width = gameManager.getGame().WIDTHSCREEN;
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitPlayState(this);
    }

    @Override
    public void tick() {
        player.tick();
        camera.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.translate(camera.getX(), camera.getY());
        
        //render the player and the map
        MapManager.getInstance().render(g); 
        player.render(g);
        
        g.translate(-camera.getX(), -camera.getY());
    }

}
