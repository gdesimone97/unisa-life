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
 *
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
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.WIDTHSCREEN,Game.HEIGHTSCREEN2 );
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
        if(k==KeyEvent.VK_M)game.state=new PlayState(game);
    }
    
    /**
     *
     * @param k
     */
    @Override
    public void performReleaseAction(int k){}
}
