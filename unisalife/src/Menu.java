/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 *
 * @author simon
 */
public class Menu {
    public void render(Graphics g){
        Font f1=new Font("arial",Font.BOLD,50);
        g.setFont(f1);
        g.setColor(Color.white);
        g.drawString("Menu",Game.WIDTHSCREEN/2,Game.HEIGHTSCREEN/2);
    }
}
