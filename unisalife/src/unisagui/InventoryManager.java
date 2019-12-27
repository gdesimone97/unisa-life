/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import game.GameObjects.Item;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import language.*;

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
    
    
    public void updateInventoryDialog(){
        JLabel label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getImage()))); //getqualcosa che mi restituisca il path del'immagine
        String name = FileTextManager.getFileTextManager().getString(item).get(0);
        SwingUtilities.invokeLater(() -> gameframe.model.addRow(new Object[]{name, 1 ,label}));
        SwingUtilities.invokeLater(() -> gameframe.InventoryDialog.setVisible(true));
    }
    //se viene passata la posizione dell'elemento
    // public void updateInventoryDialog(Item item, int position, boolean presence)
    
}
