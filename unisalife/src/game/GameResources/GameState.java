/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import java.awt.Graphics;
/**
 * Game State class represents the abstract of all the states that are possible into this game.
 * It's specialized in the classes NotGameState and PlayState
 * @author simon
 */
public abstract class GameState {
    Game game;
   
    public GameState(Game g){
        game=g;
    }

   
    public abstract void performPressAction(int k);

  
    public abstract void performReleaseAction(int k);

   
    public abstract void render(Graphics g);

    
    public abstract void tick();
}
