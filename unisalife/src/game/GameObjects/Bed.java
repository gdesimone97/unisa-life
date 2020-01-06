/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import interaction.BedInteractionManager;
import interaction.InteractionManager;

/**
 *
 * @author 1997g
 */
public class Bed extends GameObject implements Interactable {
    
    private Position dest;
    
    private Bed() {
        super();
    }

    /**
     * constructor for Bed game object
     * @param p The position in pixels where to add object
     * @param d The position in pixels to let the player spawn in
     */
    public Bed(Position p, Position dest) {
        super(p);
        this.dest = dest;
    }
    
    public Position getDestPosition() {
        return this.dest;
    }

    @Override
    public String getIndex() {
        return "Bed";
    }

    @Override
    public void interact() {
        InteractionManager im = new BedInteractionManager();
        im.execute(this);
    }
    
}
