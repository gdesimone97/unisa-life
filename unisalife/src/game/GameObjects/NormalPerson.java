/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import interaction.InteractionManager;
import interaction.NormalPersonInteractionManager;
import language.Information;

/**
 *
 * @author 1997g
 */
public class NormalPerson extends Person implements Information {
    
    private String nome;

    public NormalPerson(String nome, Position p, String path) {
        super(p, path);
        this.nome = nome;
    }
    
    public NormalPerson() {
        super();
    }

    @Override
    public void interact() {
        InteractionManager normalPersonInteraction = new NormalPersonInteractionManager();
        normalPersonInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    @Override
    public String getIndex() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }
    
}
