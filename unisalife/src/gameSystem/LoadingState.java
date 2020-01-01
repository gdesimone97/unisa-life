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
 *
 * @author simon
 */
public class LoadingState extends GameState {

    private static LoadingState instance;
    private BufferedImage img;
    
    public static LoadingState getInstance() {
        if (instance == null) {
            instance = new LoadingState();
        }
        return instance;
    }
    
    private LoadingState() {
    }
    
    @Override
    public void init() throws InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/loadingwindow.png"));
        } catch (IOException ex) {
            throw new InitException("Can't find Loading image");
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        cmd.visitLoadingState(instance);
    }

}
