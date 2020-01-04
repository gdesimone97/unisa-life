/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import java.awt.Graphics;

/**
 *
 * @author simon
 */
public class RightFaceState extends FaceState {
    private static RightFaceState instance;
    private RightFaceState(){
        
    }
    
    public static RightFaceState getInstance(){
        if (instance == null)
            instance= new RightFaceState();
        return instance;
    }
    
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingRightImage,(int)Player.getIstance().getX(), (int)Player.getIstance().getY(), null);
    }
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE+1,Player.getIstance().getY()/Game.DIMENSIONSPRITE);
    }
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE+1,Player.getIstance().getY()/Game.DIMENSIONSPRITE);
    }
}
