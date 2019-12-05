/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * Window class has the aim to effectively create the game window and the frame that contains
 * all the game 
 * @author simon
 */
public class Window {
    
    /**
     *
     * @param size size is the size of the game window
     * @param title title is the name that will be showed on the frame
     * @param game game is the canvas in which the game is displayed
     */
    public Window(Dimension size,String title,Game game){
        game.setMaximumSize(size);
        game.setMinimumSize(size);
        game.setPreferredSize(size);
        JFrame frame = new JFrame(title);
        frame.dispose();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        //frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        game.start();
    }
}
