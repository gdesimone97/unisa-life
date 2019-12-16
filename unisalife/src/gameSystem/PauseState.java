/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import gameSystem.keySettings.KeyCommand;
import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public class PauseState extends GameState {
    
    private static PauseState instance;
    
    public static PauseState getInstance() {
        if (instance == null) {
            instance = new PauseState();
        }
        return instance;
    }
    
    @Override
    public void init() {
        
    }
    
    @Override
    public void update() {
        
    }
    
    @Override
    public void draw(Graphics2D g) {
        
    }
    
    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitPauseState(this);
    }
    
}
