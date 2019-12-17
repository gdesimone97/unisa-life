/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import interaction.GuardianInteractionManager;
import interaction.InteractionManager;
import language.Information;

/**
 *
 * @author 1997g
 */
public class Guardian extends Person implements Information {

    public Guardian(Position p, String path) {
        super(p, path);
    }

    @Override
    public void interact() {
        InteractionManager guardianInteraction = new GuardianInteractionManager();
        guardianInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
