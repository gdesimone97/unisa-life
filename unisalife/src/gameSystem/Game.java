/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import static game.GameResources.Game.AMOUNTOFTICKS;
import gameSystem.keySettings.HandlerInput;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author 1997g
 */
public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    
    public static final int WIDTHSCREEN = 500,
            HEIGHTSCREEN = 500,
            HEIGHTSCREEN2 = HEIGHTSCREEN + 32,
            PLAYERSPEED = 2,
            ANIMATIONSPEED=4,
            DIMENSIONSPRITE = 32;
    private int WIDTHMAP, HEIGHTMAP;
    
    private boolean running = false;
    private final int FPS = 30;
    private final int TARGET_TIME = 1000 / FPS;
    
    private BufferedImage image;
    private Graphics2D g;
    
    private GameStateManager gsm = GameStateManager.getInstance();
	
    
    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / AMOUNTOFTICKS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
    }
    
    private void init() {
        running = true;
//        image = new BufferedImage(WIDTH, HEIGHT, 1);
//        g = (Graphics2D) image.getGraphics();
        this.addKeyListener(new HandlerInput());
    }
    
    private void tick() {
        gsm.tick();
    }
    
    private void render() {
        gsm.render(g);
    }
}
