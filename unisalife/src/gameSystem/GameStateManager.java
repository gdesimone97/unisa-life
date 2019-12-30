/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable;
import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public class GameStateManager implements Initializable {

    private GameState gs;
    private static GameStateManager instance;

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public void setState(GameState state) {
        gs = state;
    }

    public GameState getState() {
        return gs;
    }

    public void tick() {
        gs.tick();
    }

    public void render(Graphics2D g) {
        gs.render(g);
    }

    @Override
    public void init() throws InitException {
        LoadingState.getInstance().init();
        MapState.getInstance().init();
        PlayState.getInstance().init();
        PauseState.getInstance().init();
        EndGameState.getInstance().init();
        gs = LoadingState.getInstance();
    }
}
