/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author simon
 */
public class KeyInput extends KeyAdapter {
    
    Handler handler;
    Game game;
    private boolean trigg=false;
    
    
    /**
     *
     * @param vel
     */

    /**
     *
     * @param handler
     */
    public KeyInput(Handler handler,Game g){
        this.handler=handler;
        this.game=g;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int k=e.getKeyCode();
        if(k==KeyEvent.VK_ESCAPE)
                System.exit(0);
        game.state.performPressAction(k);
        
    }
    
    
    
    
    
    @Override
    public void keyReleased(KeyEvent e){
        int k=e.getKeyCode();
        game.state.performReleaseAction(k);
    }
    
}
