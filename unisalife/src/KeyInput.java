/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author simon
 */
public class KeyInput extends KeyAdapter {
    
    Handler handler;
    private int vel=2;
    
    
    public void setVel(int vel){
        this.vel=vel;
    }
    public KeyInput(Handler handler){
        this.handler=handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int k=e.getKeyCode();
        if(k==KeyEvent.VK_ESCAPE)
                System.exit(0);
        if(Game.state==State.Game){
            if(k==KeyEvent.VK_M)Game.state=State.Menu;
            if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(vel);
            if(k==KeyEvent.VK_LEFT) Game.player.setVelX(-vel);
            if(k==KeyEvent.VK_DOWN) Game.player.setVelY(vel);
            if(k==KeyEvent.VK_UP) Game.player.setVelY(-vel);
        }
        else{
            if(Game.state==State.Menu)
                if(k==KeyEvent.VK_M)Game.state=State.Game;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int k=e.getKeyCode();
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(0);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(0);
    }
    
}
