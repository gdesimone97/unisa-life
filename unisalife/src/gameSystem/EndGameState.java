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
 * This state shows an image of end game
 * @author 1997g
 */
public class EndGameState extends GameState {
    
    private static EndGameState instance;
    private BufferedImage img;
    
    /**
     * returns the instance
     * @return 
     */
    public static EndGameState getInstance() {
        if (instance == null) {
            instance = new EndGameState();
        }
        return instance;
    }
    
    private EndGameState() {
    }
    
    /**
     * Method to init the state
     * @throws game.Interfaces.Initializable.InitException 
     */
    @Override
    public void init() throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/graduationwindow.png"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find EndGame image");
        }
    }
    
    /**
     * tick of the state
     */
    @Override
    public void tick() {
    }
    
    /**
     * render of the image
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(img, 0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN, null);
    }
    
    /**
     * handles input of this state
     * @param cmd 
     */
    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitEndGameState(this);
    }
    
}
