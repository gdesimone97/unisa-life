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
public class DownFaceState extends FaceState{
    private static DownFaceState instance;
   
    private DownFaceState(){
    }
    
    
    public void drawFace(Graphics g){
        g.drawImage(Player.getIstance().facingDownImage, (int)Player.getIstance().getX(), (int)Player.getIstance().getY(), null);
    }
    
    @Override
    public Position visualViewOfPlayer(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE+1);
    }
    @Override
    public Position nextStep(){
        return new Position(Player.getIstance().getX()/Game.DIMENSIONSPRITE,Player.getIstance().getY()/Game.DIMENSIONSPRITE+1);
    }

    public static DownFaceState getInstance() {
        if (instance==null)
            instance = new DownFaceState();
        return instance;
    }
}
