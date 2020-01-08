/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.GameObjects.Camera;
import game.GameObjects.Player;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.map.MapManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This state allows the game work as it should
 * @author 1997g
 */
public class PlayState extends GameState {

    private static PlayState instance;

//    private MapManager mapManager;
    private Player player;
    private GameManager gameManager;
    private Camera camera;
    private Color color = new Color(170, 226, 103);
    private int height;
    private int width;

    /**
     * returns the instance
     * @return 
     */
    public static PlayState getInstance() {
        if (instance == null) {
            instance = new PlayState();
        }
        return instance;
    }

    private PlayState() {
    }

    /**
     * initializes some properties
     */
    @Override
    public void init() {
        gameManager = GameManager.getInstance();
        player = Player.getIstance();
        camera = gameManager.getCamera();
        height = gameManager.getGame().HEIGHTSCREEN2;
        width = gameManager.getGame().WIDTHSCREEN;
    }

    /**
     * handles the input of this state
     * @param cmd 
     */
    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitPlayState(this);
    }

    /**
     * calls the tick on the player and on the camera
     */
    @Override
    public void tick() {
        player.tick();
        camera.tick();
    }
    
    /**
     * sets the background color and calls the render method on the map and player
     * @param g 
     */
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
