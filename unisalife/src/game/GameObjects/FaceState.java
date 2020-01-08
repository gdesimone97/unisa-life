/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import java.awt.Graphics;
/**
 * this class represents a state of the player as he looks in one direction
 * @author simon
 */
public abstract class FaceState {

    /**
     * costructor 
     */
    public FaceState(){
    }

    /**
     * 
     * @return the position the player is looking at
     */
    public abstract Position visualViewOfPlayer();

    /**
     * this method renders the player facing down
     * @param g graphics of the canvas
     */
    public abstract void drawFace(Graphics g);

    /**
     * 
     * @return the position the player will occupy
     */
    public abstract Position nextStep();
    
}
