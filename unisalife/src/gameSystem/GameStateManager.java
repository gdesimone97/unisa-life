/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public class GameStateManager {
    private GameState gm;
    private static GameStateManager instance;
    
    public static GameStateManager getInstance(){
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }
    
    public void GameStateManager() {
        gm = PlayState.getInstance();
    }
    
    public void setState(GameState state) {
        gm = state;
    }
    
    public GameState getState() {
        return gm;
    }
    
    public void tick() {
        gm.tick();
    }
    
    public void render(Graphics2D g) {
        gm.render(g);
    }
}
