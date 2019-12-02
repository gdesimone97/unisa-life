/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;

import game.Classes.Game;
import game.Classes.MenuState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
/**
 *
 * @author simon
 */
public class PlayState extends GameState{

    /**
     *
     */
    public PlayState(){
        super();
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.white);
            g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
            g2d.translate(Game.camera.getX(), Game.camera.getY());
            Game.handler.render(g2d);
            g2d.translate(-Game.camera.getX(), -Game.camera.getY());
    }

    /**
     *
     */
    @Override
    public void tick(){
        Game.handler.tick();
        Game.camera.tick();
    }
    
    /**
     *
     * @param k
     */
    @Override
    public void performPressAction(int k){
        if(k==KeyEvent.VK_M)Game.state=new MenuState();
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(Game.speed);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(-Game.speed);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(Game.speed);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(-Game.speed);
        if(k==KeyEvent.VK_SPACE) Game.player.dialog(Game.maps[Game.actualMap].getList());
    }

    /**
     *
     * @param k
     */
    @Override
    public void performReleaseAction(int k){
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(0);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(0);
    }
    }
