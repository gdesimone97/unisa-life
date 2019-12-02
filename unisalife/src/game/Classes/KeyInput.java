/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author simon
 */
public class KeyInput extends KeyAdapter {
    
    Handler handler;
    private int vel=2;
    
    /**
     *
     * @param vel
     */
    public void setVel(int vel){
        this.vel=vel;
    }

    /**
     *
     * @param handler
     */
    public KeyInput(Handler handler){
        this.handler=handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int k=e.getKeyCode();
        if(k==KeyEvent.VK_ESCAPE)
                System.exit(0);
        Game.state.performPressAction(k);
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int k=e.getKeyCode();
        Game.state.performReleaseAction(k);
    }
    
}
