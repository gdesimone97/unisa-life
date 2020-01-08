/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import java.awt.Graphics;

/**
 * this abstract class represents a person in the game
 * @author simon
 */
public abstract class Person extends Renderable implements Interactable {

    /**
     *
     * @param p position of the person
     * @param path path of the image to be loaded and rendered in the game
     */
    public Person(Position p, String path) {
        super(p, path);
    }

    /**
     * costructor
     */
    protected Person() {
        super();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.getImage(), p.getX(), p.getY(), width, height, null);
    }

}
