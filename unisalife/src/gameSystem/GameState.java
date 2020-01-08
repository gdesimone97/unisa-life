/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable;
import gameSystem.keySettings.interfaces.KeyCommand;
import java.awt.Graphics2D;

/**
 * Abstract class that defines all the methods every state should implement
 * @author 1997g
 */
public abstract class GameState {
    
    /**
     * empty constructor
     */
    public GameState() {
    }

    /**
     * init method is initial conditions and initializations
     * @throws game.Interfaces.Initializable.InitException 
     */
    public abstract void init() throws Initializable.InitException;

    /**
     * tick method is updates
     */
    public abstract void tick();

    /**
     * render method is the graphic shown in this state
     * @param g 
     */
    public abstract void render(Graphics2D g);

    /**
     * handleinput method is what key commands are available for this state
     * @param cmd 
     */
    public abstract void handleInput(KeyCommand cmd);
}
