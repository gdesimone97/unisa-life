/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.GameResources.Game;
import game.GameObjects.FaceState;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author simon
 */
public class LeftFaceState extends FaceState{
    public LeftFaceState(Player p){
        super(p);
    }
    
    public void drawFace(Graphics g){
        g.drawImage(player.facingLeftImage, (int)player.getX(),(int)player.getY(), null);
        return;
    }
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)player.getX()-player.getWidth()/2,(int)player.getY()+player.getHeight()/4,player.getWidth()/2,player.getHeight()/2);
    }
    @Override
    public Rectangle nextStep(){
        return new Rectangle((int)player.getX()-Game.PLAYERSPEED,(int)player.getY(),(int)Game.PLAYERSPEED,player.getHeight());
    }
    
}
