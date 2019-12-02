/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import game.Classes.Game;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
/**
 *
 * @author simon
 */
public class MenuState extends GameState {

    /**
     *
     */
    public MenuState(){
        super();
    }

    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.WIDTHSCREEN,Game.HEIGHTSCREEN2 );
        Game.menu.render(g);
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
        if(k==KeyEvent.VK_M)Game.state=new PlayState();
    }
    
    /**
     *
     * @param k
     */
    @Override
    public void performReleaseAction(int k){}
}
