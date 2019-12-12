/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
/**
 * This inherited class simbolyze the game state in which we are not playing the game, but we are in the menus
 * @author simon
 */
public class NotGameState extends GameState {

    /**
     *
     */
    public NotGameState(Game g){
        super(g);
    }

    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g){
        
    }

    /**
     *
     */
    @Override
    public void tick(){}
    
    /**
     *
     * @param k
     */
    @Override
    public void performPressAction(int k){
        
    }
    
    /**
     *
     * @param k
     */
    @Override
    public void performReleaseAction(int k){}
}
