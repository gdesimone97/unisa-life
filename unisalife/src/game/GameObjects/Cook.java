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
 * A cook represents a person in the game that can prepare you a meal
 * to satisfy your hunger.
 * @author 1997g
 */
public class Cook extends Person implements Information {

    private String nome;

    private Cook() {
        super();
    }

    /**
     *
     * @param nome Name of the cook
     * @param p Position of the cook in the map
     * @param path Path of the image to be loaded in order to render the object in the game
     */
    public Cook(String nome, Position p, String path) {
        super(p, path);
        this.nome = nome;
    }

    /**
     * This method executes an interaction with the cook
     */
    @Override
    public void interact() {
        InteractionManager cookInteraction = new CookInteractionManager();
        cookInteraction.execute(this);
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    /**
     * 
     * @return the index in order to access to the Database
     */
    @Override
    public String getIndex() {
        return this.nome;
    }

    /**
     *
     * @return name of the cook
     */
    public String getNome() {
        return nome;
    }
}
