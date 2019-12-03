/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import java.awt.Graphics;
/**
 *
 * @author simon
 */
public abstract class GameState {
    Game game;
    /**
     *
     */
    public GameState(Game g){
        game=g;
    }

    /**
     *
     * @param k
     */
    public abstract void performPressAction(int k);

    /**
     *
     * @param k
     */
    public abstract void performReleaseAction(int k);

    /**
     *
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     *
     */
    public abstract void tick();
}
