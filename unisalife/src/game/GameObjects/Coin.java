/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import interaction.CoinInteractionManager;
import interaction.InteractionManager;


/**
 *
 * @author 1997g
 */
public class Coin extends Item implements Interactable {
    
    public Coin(Position p, String path, String info) {
        super(p, path, info);
    }
    
    @Override
    public void interact() {
        InteractionManager im = new CoinInteractionManager();
        im.execute(this);
    }
}
