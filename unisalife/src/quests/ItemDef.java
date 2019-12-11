/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests;

/**
 *
 * @author liovi
 */
public enum ItemDef {
    appuntidimatematica1 ("appuntidimatematica1"),
    appuntidimatematica2 ("appuntidimatematica2"),
    calcolatrice ("calcolatrice");
    
    private String itemName;

    private ItemDef(String itemName) {
        this.itemName = itemName;
    }
    
    @Override
    public String toString() {
        return itemName;
    }
}
