/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import game.GameObjects.Item;
import javax.swing.JLabel;
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
     * @param quantity is how many items of certain type are added or deleted, if 0 the item is removed from the inventory.
     */
    public void updateInventoryDialog(Item item, int quantity){
        JLabel label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getImage()))); //getqualcosa che mi restituisca il path del'immagine
        SwingUtilities.invokeLater(() -> gameframe.model.addRow(new Object[]{"nome", "quantity",label}));
        SwingUtilities.invokeLater(() -> gameframe.InventoryDialog.setVisible(true));
    }
    //se viene passata la posizione dell'elemento
    // public void updateInventoryDialog(Item item, int position, boolean presence)
    
}
