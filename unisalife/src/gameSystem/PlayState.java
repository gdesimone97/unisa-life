/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import gameSystem.keySettings.KeyCommand;
import java.awt.Graphics2D;

/**
 *
 * @author 1997g
 */
public class PlayState extends GameState {

    private static PlayState instance;

    public static PlayState getInstance() {
        if (instance == null) {
            instance = new PlayState();
        }
        return instance;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitPlayState(this);
    }

}
