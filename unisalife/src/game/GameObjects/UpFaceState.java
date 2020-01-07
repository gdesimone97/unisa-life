/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import java.awt.Graphics;

/**
 * This class represents a state of the player in which he is looking up
 * @author simon
 */
public class UpFaceState extends FaceState{
    private static UpFaceState instance;
    private UpFaceState(){  
    }
    
    /**
     *
     * @return the instance of this singleton class
     */
    public static UpFaceState getInstance(){
        if (instance == null)
            instance = new UpFaceState();
        return instance;
    }
    
    /**
     * this method renders the player facing up
     * @param g graphics of the canvas
     */
    @Override
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingUpImage, (int)Player.getIstance().getX(),(int)Player.getIstance().getY(), null);
    }
    
    /**
     *
     * @return the position the player is looking at
     */
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE-1);
    }

    /**
     *
     * @return the position the player will occupy
     */
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE-1);
    }
}
