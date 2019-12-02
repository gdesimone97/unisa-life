/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author simon
 */
public class DownFaceState extends FaceState{
    public DownFaceState(){
        super();
    }
    
    public void drawFace(Graphics g){
        g.drawImage(Game.player.facingDownImage, (int)Game.player.x, (int)Game.player.y, null);
        return;
    }
    
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)Game.player.x+Game.player.width/4,(int)Game.player.y+Game.player.height,Game.player.width/2,Game.player.height/2);
    }
    
}
