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
 * this object represents a guardian in the game
 * @author 1997g
 */
public class Guardian extends Person implements Information {

    private String nome;

    /**
     * costructor
     * @param nome name of the guardian
     * @param p position of the guardian in the map
     * @param path path of the image to be loaded and rendered during the game
     */
    public Guardian(String nome, Position p, String path) {
        super(p, path);
        this.nome = nome;
    }

    /**
     * costructor
     */
    public Guardian() {
        super();
    }

    /**
     * this method executes the interaction between the player and the guardian
     */
    @Override
    public void interact() {
        InteractionManager guardianInteraction = new GuardianInteractionManager();
        guardianInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    /**
     * 
     * @return the index of the object in order to access to the Database
     */
    @Override
    public String getIndex() {
        return this.nome;
    }

    /**
     * 
     * @return name of the guardian
     */
    public String getNome() {
        return nome;
    }

}
