/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;

import static game.Classes.GameObject.width;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author simon
 */
public class RightFaceState extends FaceState {
     public RightFaceState(){
        super();
    }
    
    public void drawFace(Graphics g){
        g.drawImage(Game.player.facingRightImage,(int)Game.player.x, (int)Game.player.y, null);
        return;
    }
    @Override
    public Rectangle visualViewOfPlayer(){
        return new Rectangle((int)Game.player.x+width,(int)Game.player.y+Game.player.height/4,Game.player.width/2,Game.player.height/2);
    }
}
