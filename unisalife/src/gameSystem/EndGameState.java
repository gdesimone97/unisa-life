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
 *
 * @author 1997g
 */
public class EndGameState extends GameState {
    
    private static EndGameState instance;
    private BufferedImage img;
    
    public static EndGameState getInstance() {
        if (instance == null) {
            instance = new EndGameState();
        }
        return instance;
    }
    
    private EndGameState() {
    }
    
    @Override
    public void init() throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/graduationwindow.png"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find EndGame image");
        }
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(img, 0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2, null);
    }
    
    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitEndGameState(this);
    }
    
}
