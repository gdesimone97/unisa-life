/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable;
import gameSystem.keySettings.interfaces.KeyCommand;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This state is shown when the player goes to sleep
 * @author 1997g
 */
public class SleepState extends GameState {
    
    private static SleepState instance;
    private BufferedImage img;
    
    /**
     * returns the instance
     * @return 
     */
    public static SleepState getInstance() {
        if (instance == null) {
            instance = new SleepState();
        }
        return instance;
    }
    
    private SleepState() {
    }

    /**
     * init the image
     * @throws game.Interfaces.Initializable.InitException 
     */
    @Override
    public void init() throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/sleepingwindow.png"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Sleeping image");
        }
    }

    /**
     * nothing to tick
     */
    @Override
    public void tick() {
    }

    /**
     * render the image
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    /**
     * handles the input for this state
     * @param cmd 
     */
    @Override
    public void handleInput(KeyCommand cmd) {
    }
    
}
