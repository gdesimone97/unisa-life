/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import interaction.DistributorInteractionManager;
import interaction.InteractionManager;
import quests.ItemDef;

/**
 *
 * @author 1997g
 */
public class Distributor extends Item {
    
    public Distributor(Position p, String path, String info, ItemDef id) {
        super(p, path, info);
    }
    
    @Override
    public void interact() {
        InteractionManager im = new DistributorInteractionManager();
        im.execute(this);
    }
    
}
