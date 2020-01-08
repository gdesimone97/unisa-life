/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import gameSystem.keySettings.interfaces.KeyCommand;
import java.awt.Graphics2D;

/**
 * This state set the pause on everything
 * @author 1997g
 */
public class PauseState extends GameState {

    private static PauseState instance;

    /**
     * returns the instance
     * @return 
     */
    public static PauseState getInstance() {
        if (instance == null) {
            instance = new PauseState();
        }
        return instance;
    }

    /**
     * nothing to init
     */
    @Override
    public void init() {

    }

    /**
     * handles the input of this state
     * @param cmd 
     */
    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitPauseState(this);
    }

    /**
     * nothing to tick
     */
    @Override
    public void tick() {
    }

    /**
     * nothing to render
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {
    }

}
