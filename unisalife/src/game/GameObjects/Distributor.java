/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import database.Storable;
import game.Interfaces.Interactable;
import interaction.DistributorInteractionManager;
import interaction.InteractionManager;
import org.dizitart.no2.objects.Id;

/**
 * Distributor represent an object in the game the allows the player to 
 * buy a meal to satisfy the hunger
 * @author 1997g
 */
public class Distributor extends GameObject implements Interactable, Storable {

    @Id
    private String info;

    private Distributor() {
        super();
    }

    /**
     * Costructor of the class
     * @param p position of the object in the map
     * @param info paramter for Database
     */
    public Distributor(Position p, String info) {
        super(p);
        this.info = info;
    }

    /**
     * This method executes the interaction of player with a distributor
     */
    @Override
    public void interact() {
        InteractionManager im = new DistributorInteractionManager();
        im.execute(this);
    }

    /**
     * Returns index in order to access to the Database
     * @return
     */
    @Override
    public String getIndex() {
        return this.info;
    }

}
