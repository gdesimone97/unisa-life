/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import interaction.DistributorInteractionManager;
import interaction.InteractionManager;

/**
 *
 * @author 1997g
 */
public class Distributor extends GameObject implements Interactable {
    
    private final String info;
    
    public Distributor(Position p,String info) {
        super(p);
        this.info=info;
    }
    
    @Override
    public void interact() {
        InteractionManager im = new DistributorInteractionManager();
        im.execute(this);
    }
    
}
