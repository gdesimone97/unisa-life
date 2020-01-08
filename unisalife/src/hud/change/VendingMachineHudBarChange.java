/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud.change;

import character.StatusManager;

/**
 * This class is one of the ConcreteStrategy classes implementing the strategy HudBarChange.
 * It implements the bar changes due to the vending machine.
 * @author mariodesio
 */
public class VendingMachineHudBarChange implements HudBarChange {

    /**
     * This method allows to implement the bar changes due to the vending machine.
     * Hunger will be decreased by 30 per cent.
     */
    @Override
    public void execute() {
        StatusManager.getInstance().updateHunger(-30);
    }
    
}
