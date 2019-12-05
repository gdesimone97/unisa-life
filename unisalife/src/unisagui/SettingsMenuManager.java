/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.SwingUtilities;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class SettingsMenuManager {
    
    private final String info = "GUIsettings";
    private final GameFrame gameframe = GameFrame.getInstance();
    
    protected SettingsMenuManager(){
        
    }
    
    public String getInfo(){
        return info;
    }
    
    public void showSettingsMenu(boolean show){
        SwingUtilities.invokeLater(() -> gameframe.MainMenuDialog.setVisible(show));
    }
    
    
}
