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
public class UpFaceState extends FaceState{
    public UpFaceState(Player player){
        super(player);
    }
    
    @Override
    public void drawFace(Graphics g){
        g.drawImage(player.facingUpImage, (int)player.getX(),(int)player.getY(), null);
    }
    
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)player.getX()+player.getWidth()/4,(int)player.getY()-player.getHeight()/2,(int)player.getWidth()/2,player.getHeight()/2);
    }
}
