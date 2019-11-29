/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import javax.swing.JFrame;
/**
 *
 * @author simon
 */
public class Window {
    public Window(Dimension size,String title,Game game){
        game.setMaximumSize(size);
        game.setMinimumSize(size);
        game.setPreferredSize(size);
        JFrame frame = new JFrame(title);
        frame.dispose();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setUndecorated(true);
        //frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        game.start();
    }
}
