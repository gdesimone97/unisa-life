/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import java.awt.Graphics;
/**
 *
 * @author simon
 */
public abstract class FaceState {

    public FaceState(){
    }
    public abstract Position visualViewOfPlayer();
    public abstract void drawFace(Graphics g);
    public abstract Position nextStep();
    
}
