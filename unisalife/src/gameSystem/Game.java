/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import exam.question.Materia;
import game.GameObjects.Item;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import game.GameResources.Map;
import game.GameResources.TileMap;
import gameSystem.keySettings.HandlerInput;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import quests.ItemDef;

/**
 * This class is the main thread of the game, it calls render() and tick() methods 
 * on the GameStateManager; it keeps some info about the width and height of the screen
 * 
 * @author 1997g
 */
public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    
    public static final int WIDTHSCREEN = 500,
            HEIGHTSCREEN = 500,
            HEIGHTSCREEN2 = HEIGHTSCREEN + 32,
            PLAYERSPEED = 32,
            ANIMATIONSPEED=4,
            AMOUNTOFTICKS=30,
            DIMENSIONSPRITE = 32;
    public static int WIDTHMAP, HEIGHTMAP;
    
    private boolean running = false;
    private final int FPS = 30;
    private final int TARGET_TIME = 1000 / FPS;
    
    private Graphics g;
    private Graphics2D g2d;
    
    private GameStateManager gsm;
    
    /**
     * This method initializes the game and runs continuously until the game
     * stops. Here 2 main methods are called: tick() and render(), that are
     * functions of GameStateManager, and allows to render and visualize all the
     * objects in the game, the player, the camera, and so on...
     */
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

        
        
        long start;
        long elapsed;
        long wait;
            
        while (running) {
                start = System.nanoTime();
                
                tick();
                render();

                elapsed = System.nanoTime() - start;

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

//        while (running) {
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            while (delta >= 1) {
//                tick();
//                updates++;
//                delta--;
//            }
//            render();
//            frames++;
//            if (System.currentTimeMillis() - timer > 1000) {
//                timer += 1000;
//                System.out.println(frames + " " + updates);
//                frames = 0;
//                updates = 0;
//            }
//        }
    }
    
    private void init() {
        gsm = GameStateManager.getInstance();
        this.addKeyListener(new HandlerInput());
        running = true;
    }
    
    private void tick() {
        gsm.tick();
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g2d = (Graphics2D) g;
        gsm.render(g2d);
        g.dispose();
        bs.show();
    }
    
    public void stopGame() {
        running = false;
    }
}
