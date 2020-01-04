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
public class UpFaceState extends FaceState{
    private static UpFaceState instance;
    private UpFaceState(){  
    }
    
    public static UpFaceState getInstance(){
        if (instance == null)
            instance = new UpFaceState();
        return instance;
    }
    
    @Override
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingUpImage, (int)Player.getIstance().getX(),(int)Player.getIstance().getY(), null);
    }
    
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE-1);
    }
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE-1);
    }
}
