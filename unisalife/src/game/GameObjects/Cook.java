/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import interaction.CookInteractionManager;
import interaction.InteractionManager;
import language.Information;

/**
 *
 * @author 1997g
 */
public class Cook extends Person implements Information {

    public Cook(Position p, String path) {
        super(p, path);
    }

    @Override
    public void interact() {
        InteractionManager cookInteraction = new CookInteractionManager();
        cookInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
