/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author simon
 */
public abstract class FaceState {
    public FaceState(){
     
    }
    public abstract Rectangle visualViewOfPlayer();
    public abstract void drawFace(Graphics g);
    
}
