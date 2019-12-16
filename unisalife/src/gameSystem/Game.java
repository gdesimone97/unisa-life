/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

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

        long start;
        long elapsed;
        long wait;

        // game loop
        while (running) {
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            
            System.out.println("CIAO" + elapsed);

            wait = TARGET_TIME - elapsed / 1000000;
            if (wait < 0) {
                wait = TARGET_TIME;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void init() {
        running = true;
//        image = new BufferedImage(WIDTH, HEIGHT, 1);
//        g = (Graphics2D) image.getGraphics();
        this.addKeyListener(new HandlerInput());
    }
    
    private void update() {
        gsm.update();
    }
    
    private void draw() {
        gsm.draw(g);
    }
    
    private void drawToScreen() {
//        Graphics g2 = getGraphics();
//        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT2 * SCALE, null);
//        g2.dispose();
    }

}
