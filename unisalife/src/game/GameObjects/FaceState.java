/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author simon
 */
public abstract class FaceState {
    Player player;
    public FaceState(Player player){
        this.player=player;
    }
    public abstract Position visualViewOfPlayer();
    public abstract void drawFace(Graphics g);
    public abstract Position nextStep();
    
}
