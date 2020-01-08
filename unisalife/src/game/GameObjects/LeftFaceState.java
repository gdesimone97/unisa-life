/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import java.awt.Graphics;
/**
 *  This class represents a state of the player in which he is looking left
 * @author simon
 */
public class LeftFaceState extends FaceState{
    private static LeftFaceState instance;
    private LeftFaceState(){
        
    }
    
    /**
     *
     * @return the instance of this singleton class
     */
    public static LeftFaceState getInstance(){
        if (instance == null)
            instance= new LeftFaceState();
        return instance;
    }
    
    /**
     * this method renders the player facing left
     * @param g graphics of the canvas
     */
    @Override
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingLeftImage, (int)Player.getIstance().getX(),(int)Player.getIstance().getY(), null);
    }
    
    /**
     * 
     * @return the position the player is looking at
     */
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE-1,Player.getIstance().getY()/Game.DIMENSIONSPRITE);
    }
    
    /**
     * 
     * @return the position the player will occupy
     */
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE-1,Player.getIstance().getY()/Game.DIMENSIONSPRITE);
    }
    
}
