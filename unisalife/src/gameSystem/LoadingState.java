/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable.InitException;
import gameSystem.keySettings.interfaces.KeyCommand;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Loading State is the state present every time there is a heavy operation to do
 * It shows a Loading image
 * @author simon
 */
public class LoadingState extends GameState {

    private static LoadingState instance;
    private BufferedImage img;
    
    /**
     * returns the instance
     * @return 
     */
    public static LoadingState getInstance() {
        if (instance == null) {
            instance = new LoadingState();
        }
        return instance;
    }
    
    private LoadingState() {
    }
    
    /**
     * initialize the image
     * @throws game.Interfaces.Initializable.InitException 
     */
    @Override
    public void init() throws InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/loadingwindow.png"));
        } catch (IOException ex) {
            throw new InitException("Can't find Loading image");
        }
    }

    /**
     * nothing to tick
     */
    @Override
    public void tick() {

    }

    /**
     * renders the image on the screen
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    /**
     * handles the input of this state
     * @param cmd 
     */
    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitLoadingState(instance);
    }

}
