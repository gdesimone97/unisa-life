/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.GameResources.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * This class implements the abstract methods of the class FaceState, focusing on the 
 * situation in which the face of the player is addressed downward.
 * 
 * @author simon
 */
public class DownFaceState extends FaceState{
    
    /**
     *
     * @param player  the constructor takes in input a player
     */
    public DownFaceState(Player player){
        super(player);
    }
    /**
     * This method allows the drawing of the facing-down player, 
     * @param g g Graphic object used for the drawing, x and y coordinates of the player are passed in this method
     */
    
    
    public void drawFace(Graphics g){
        g.drawImage(player.facingDownImage, (int)player.getX(), (int)player.getY(), null);
        return;
    }
    
    /**
     * 
     * @return the rectangle that represents the player visual based on where the face is focused
     */
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)player.getX()+player.getWidth()/4,(int)player.getY()+player.getHeight(),(int)player.getWidth()/2,(int)player.getHeight()/2);
    }
    
}
