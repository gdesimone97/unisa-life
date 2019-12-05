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
 * This class implements the abstract methids of the class FaceState, focusing on the situation
* in which the face of the player is addressed upward
 * @author simon
 */
public class UpFaceState extends FaceState{
    /**
     *
     * @param player  the constructor takes in input a player
     */
    public UpFaceState(Player player){
        super(player);
    }
    
    /**
     * This method allows the drawing of the facing-down player, 
     * @param g g Graphic object used for the drawing, x and y coordinates of the player are passed in this method
     */
    @Override
    public void drawFace(Graphics g){
        g.drawImage(player.facingUpImage, (int)player.getX(),(int)player.getY(), null);
    }
    
      /**
     * 
     * @return the rectangle that represents the player visual based on where the face is focused
     */
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)player.getX()+player.getWidth()/4,(int)player.getY()-player.getHeight()/2,(int)player.getWidth()/2,player.getHeight()/2);
    }
}
