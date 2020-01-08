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
public class MainMenuManager {
    
    private final String info = "GUImainmenu";
    private final GameFrame gameframe = GameFrame.getInstance();
    
    /**
     * emty constructor
     */
    protected MainMenuManager(){
        
    }
    
    /**
     *
     * @param show main menu
     */
    public void showMainMenu(boolean show){
        SwingUtilities.invokeLater(() -> gameframe.MainMenuDialog.setVisible(show));
    }
    
    /**
     *
     * @return info
     */
    public String getInfo(){
        return info;
    }
    
    
}
