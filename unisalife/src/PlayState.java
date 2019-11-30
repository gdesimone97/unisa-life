/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
/**
 *
 * @author simon
 */
public class PlayState extends GameState{
    public PlayState(){
        super();
    }
    
    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.white);
            g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
        /*    BufferedImage image=null;
            try
        {
          image=ImageIO.read(new File("C:\\Users\\simon\\Desktop\\casa.png"));
          g.drawImage(image,100,100, this);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          System.exit(1);
        }
               */
            g2d.translate(Game.camera.getX(), Game.camera.getY());
            Game.handler.render(g2d);
            g2d.translate(-Game.camera.getX(), -Game.camera.getY());
    }
    @Override
    public void tick(){
        Game.handler.tick();
        Game.camera.tick(Game.player);
    }
    
    @Override
    public void performPressAction(int k){
        if(k==KeyEvent.VK_M)Game.state=new MenuState();
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(Game.speed);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(-Game.speed);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(Game.speed);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(-Game.speed);
    }
    @Override
    public void performReleaseAction(int k){
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(0);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(0);
    }
    }
