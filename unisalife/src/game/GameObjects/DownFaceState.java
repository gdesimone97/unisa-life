/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import gameSystem.Game;
import java.awt.Graphics;

/**
 * This class represents a state of the player in which he is looking down 
 * @author simon
 */
public class DownFaceState extends FaceState{
    private static DownFaceState instance;
   
    private DownFaceState(){
    }
    
    /**
     * this method renders the player facing down
     * @param g graphics of the canvas
     */
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingDownImage, (int)Player.getIstance().getX(), (int)Player.getIstance().getY(), null);
    }
    
    /**
     * 
     * @return the position the player is looking at
     */
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE+1);
    }

    /**
     * 
     * @return the position the player will occupy
     */
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE+1);
    }

    /**
     *  static method to get an instance of this singleton class
     * @return instance of class
     */
    public static DownFaceState getInstance() {
        if (instance==null)
            instance = new DownFaceState();
        return instance;
    }
}
