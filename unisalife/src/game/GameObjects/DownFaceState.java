/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.GameResources.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author simon
 */
public class DownFaceState extends FaceState{
    public DownFaceState(Player player){
        super(player);
    }
    
    public void drawFace(Graphics g){
        g.drawImage(player.facingDownImage, (int)player.getX(), (int)player.getY(), null);
    }
    /*
    @Override
    public Position visualViewOfPlayer(){
        return new Rectangle((int)player.getX()+player.getWidth()/4,(int)player.getY()+player.getHeight(),(int)player.getWidth()/2,(int)player.getHeight()/2);
    }
    
    */
    /*@Override
    public Rectangle nextStep(){
        return new Rectangle((int)player.getX(),(int)player.getY()+player.getHeight(),(int)player.getWidth(),Game.PLAYERSPEED);
    }
    */
    @Override
    public Position visualViewOfPlayer(){
        return new Position(player.getX(),player.getY()+Game.DIMENSIONSPRITE);
    }
    @Override
    public Position nextStep(){
        return new Position(player.getX(),player.getY()+Game.DIMENSIONSPRITE);
    }
}
