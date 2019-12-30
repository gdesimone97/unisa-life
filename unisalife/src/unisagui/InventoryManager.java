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
import game.GameObjects.GameInventory;
import game.Interfaces.Initializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import language.exceptions.TextFinderException;

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
    
    public void showInventoryDialog(){
        SwingUtilities.invokeLater(() -> gameframe.InventoryDialog.setVisible(true));
    }
    
    
    public void updateInventoryDialog(){
        
        /*gameframe.model = new DefaultTableModel();
        gameframe.model.setColumnIdentifiers(new Object[]{"Name","Icon"});*/
        gameframe.initializingTable();
        for (Item item : GameInventory.getInstance()) {
            JLabel label = new JLabel();
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getPath()))); //getqualcosa che mi restituisca il path del'immagine
            String name;
            try {
                name = FileTextManager.getFileTextManager().getString(item).get(0);
            } catch (Initializable.InitException | TextFinderException ex) {
                name = item.getInfo();
                System.err.println("errore");
            }
            gameframe.model.addRow(new Object[]{name ,label});
        }
        gameframe.InventoryTable.setModel(gameframe.model);
        
    }
    
    
}
