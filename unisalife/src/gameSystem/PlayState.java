/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.GameObjects.Camera;
import gameSystem.keySettings.KeyCommand;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public class PlayState extends GameState {

    private static PlayState instance;
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

    @Override
    public void init() {
        gameManager = GameManager.getInstance();
        camera = gameManager.getCamera();
        height = gameManager.getGame().HEIGHTSCREEN2;
        weight = gameManager.getGame().WIDTHSCREEN;
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitPlayState(this);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(170,226,103));
        g.fillRect(0, 0, game.GameResources.Game.WIDTHSCREEN, game.GameResources.Game.HEIGHTSCREEN2);
        g.translate(game.GameResources.Game.camera.getX(), game.GameResources.Game.camera.getY());
        game.GameResources.Game.handler.render(g2d);
        g.translate(-game.GameResources.Game.camera.getX(), -game.GameResources.Game.camera.getY());
    }

}
