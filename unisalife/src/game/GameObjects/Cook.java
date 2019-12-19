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
    private String nome;

    public Cook(String nome, Position p, String path) {
        super(p, path);
    }

    @Override
    public void interact() {
        InteractionManager cookInteraction = new CookInteractionManager();
        cookInteraction.execute(this);
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
