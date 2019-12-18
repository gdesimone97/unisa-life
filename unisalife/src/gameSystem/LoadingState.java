/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import gameSystem.keySettings.KeyCommand;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class LoadingState extends GameState{
    
    private static LoadingState instance;
    
    public static LoadingState getInstance() {
        if (instance == null) {
            instance = new LoadingState();
        }
        return instance;
    }
    
    @Override
    public void init() {
        
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Loading..", Game.WIDTHSCREEN/2, Game.HEIGHTSCREEN2/2);
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        //se la vede peppe
    }
    
}
