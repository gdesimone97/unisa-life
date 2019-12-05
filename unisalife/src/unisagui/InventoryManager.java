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
public class InventoryManager {
    
    private final String info = "GUIinventory";
    private final GameFrame gameframe = GameFrame.getInstance();

    protected InventoryManager() {
        
    }
    
    public String getInfo(){
        return info;
    }
    
    public void showInventoryDialog(boolean show){
        SwingUtilities.invokeLater(() -> gameframe.InventoryDialog.setVisible(show));
    }
    /**
     * 
     * @param item represents the item that has been collected or removed
     * @param presence says if the item has been collected or removed
     */
    /*public void updateInventoryDialog(Item item, boolean presence){
        
    }
    //se viene passata la posizione dell'elemento
    // public void updateInventoryDialog(Item item, int position, boolean presence)
    */
}
