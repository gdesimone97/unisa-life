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
        g.setColor(new Color(170,226,103));
        g.fillRect(0, 0, game.GameResources.Game.WIDTHSCREEN, game.GameResources.Game.HEIGHTSCREEN2);
        g.translate(camera.getX(), camera.getY());
        
        //render the player and the map
        player.render(g);
//        mapManager.render(g); -> la map mantiene ObjectManager e deve renderizzare tutti gli oggetti presenti nella mappa

        g.translate(-camera.getX(), -camera.getY());
    }

}
