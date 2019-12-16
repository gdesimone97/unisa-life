/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public abstract class GameState {
   
    public GameState() {
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public abstract void handleInput();
}
