/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable;
import java.awt.Graphics2D;

/**
 * GameStateManager is a state pattern that defines the state of the game
 * defines an common interface for all states and let change/get the 
 * actual state of the game
 * @author 1997g
 */
public class GameStateManager implements Initializable {

    private GameState gs;
    private static GameStateManager instance;

    /**
     * returns the instance
     * @return 
     */
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    private GameStateManager() {
    }    

    /**
     * allows to change the actual state
     * @param state 
     */
    public void setState(GameState state) {
        gs = state;
    }

    /**
     * returns the actual state
     * @return 
     */
    public GameState getState() {
        return gs;
    }

    /**
     * calls the tick method on the actual state
     */
    public void tick() {
        gs.tick();
    }

    /**
     * calls the render method on the actual state
     * @param g 
     */
    public void render(Graphics2D g) {
        gs.render(g);
    }

    /**
     * calls all the init methods on all available states and sets 
     * LoadingState as first state
     * @throws game.Interfaces.Initializable.InitException 
     */
    @Override
    public void init() throws InitException {
        LoadingState.getInstance().init();
        MapState.getInstance().init();
        PlayState.getInstance().init();
        PauseState.getInstance().init();
        EndGameState.getInstance().init();
        SleepState.getInstance().init();
        gs = LoadingState.getInstance();
    }
}
