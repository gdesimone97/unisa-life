/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import game.GameObjects.FaceState;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author simon
 */
public class RightFaceState extends FaceState {
     public RightFaceState(Player p){
        super(p);
    }
    
    public void drawFace(Graphics g){
        g.drawImage(player.facingRightImage,(int)player.getX(), (int)player.getY(), null);
    }
    /*
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)player.getX()+player.getWidth(),(int)player.getY()+player.getHeight()/4,player.getWidth()/2,player.getHeight()/2);
    }
    @Override
    public Rectangle nextStep(){
        return new Rectangle((int)player.getX()+player.getWidth(),(int)player.getY(),(int)Game.PLAYERSPEED,player.getHeight());
    }
    */
    @Override
    public Position visualViewOfPlayer(){
        return new Position(player.getX()/Game.DIMENSIONSPRITE+1,player.getY()/Game.DIMENSIONSPRITE);
    }
    @Override
    public Position nextStep(){
        return new Position(player.getX()/Game.DIMENSIONSPRITE+1,player.getY()/Game.DIMENSIONSPRITE);
    }
}
