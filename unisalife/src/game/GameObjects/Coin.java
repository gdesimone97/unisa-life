/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import interaction.CoinInteractionManager;
import interaction.InteractionManager;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Coin object represents a currency for shopping in the game 
 * @author 1997g
 */
public class Coin extends Item implements Interactable {
    
    /**
     * Costructor of class
     * @param p position of the coin
     * @param path path of the coin's image to be rendered in game
     * @param info parameter for Database
     */
    public Coin(Position p, String path, String info) {
        super(p, path, info);
        try {
            loadImage();
        } catch (ImageNotLoadedException ex) {}
    }
    
    private Coin(){
        super();
    }
    
    /**
     * This method is used to collect the coin
     */
    @Override
    public void interact() {
        InteractionManager im = new CoinInteractionManager();
        im.execute(this);
    }
}
