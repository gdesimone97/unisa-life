/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import java.awt.Graphics;

/**
 *
 * @author simon
 */
public abstract class Person extends Renderable implements Interactable {

    public Person(Position p, String path) {
        super(p, path);
    }

    protected Person() {
        super();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.getImage(), p.getX(), p.getY(), width, height, null);
    }

}
