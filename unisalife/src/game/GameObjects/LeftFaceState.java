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
public class LeftFaceState extends FaceState{
    private static LeftFaceState instance;
    private LeftFaceState(){
        
    }
    
    public static LeftFaceState getInstance(){
        if (instance == null)
            instance= new LeftFaceState();
        return instance;
    }
    
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingLeftImage, (int)Player.getIstance().getX(),(int)Player.getIstance().getY(), null);
    }
    
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE-1,Player.getIstance().getY()/Game.DIMENSIONSPRITE);
    }
    
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE-1,Player.getIstance().getY()/Game.DIMENSIONSPRITE);
    }
    
}
