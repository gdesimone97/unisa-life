/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable.InitException;
import gameSystem.keySettings.interfaces.KeyCommand;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class LoadingState extends GameState{
    
    private static LoadingState instance;
    private BufferedImage img;
    
    public static LoadingState getInstance() throws InitException {
        if (instance == null) {
            instance = new LoadingState();
        }
        return instance;
    }
    
    private LoadingState() throws InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/loadingwindow.png"));
        } catch (IOException ex) {
            throw new InitException("Can't find Loading image");
        }
    }
    
    @Override
    public void init() {
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform at = new AffineTransform();
        g.drawImage(img, at, null);
//        g.setColor(Color.black);
//        g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
//        g.setColor(Color.white);
//        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
//        g.drawString("Loading..", Game.WIDTHSCREEN/2, Game.HEIGHTSCREEN2/2);
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitLoadingState(instance);
    }
    
}
