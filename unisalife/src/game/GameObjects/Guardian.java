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
    private String nome;

    public Guardian(String nome, Position p, String path) {
        super(p, path);
    }
    
    public Guardian(){
        super();
    }

    @Override
    public void interact() {
        InteractionManager guardianInteraction = new GuardianInteractionManager();
        guardianInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    @Override
    public String getIndex() {
        return this.nome;
    }
    
}
