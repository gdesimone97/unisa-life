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
 * this class represents a person in the game
 * @author 1997g
 */
public class NormalPerson extends Person implements Information {
    
    private String nome;

    /**
     *
     * @param nome name of the person
     * @param p position of the person
     * @param path path of the image to be loaded and rendered in the game
     */
    public NormalPerson(String nome, Position p, String path) {
        super(p, path);
        this.nome = nome;
    }
    
    /**
     * costructor
     */
    public NormalPerson() {
        super();
    }

    /**
     * this method allows player to talk with the person
     */
    @Override
    public void interact() {
        InteractionManager normalPersonInteraction = new NormalPersonInteractionManager();
        normalPersonInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    /**
     * 
     * @return the index of the person in order to access to Database
     */
    @Override
    public String getIndex() {
        return this.nome;
    }

    /**
     * 
     * @return the name of the person
     */
    public String getNome() {
        return nome;
    }
    
}
