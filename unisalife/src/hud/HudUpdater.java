/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import character.StatusManager;
import character.Status;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariodesio
 */
public class HudUpdater implements Runnable {
    private StatusManager manager;

    public HudUpdater(StatusManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            
            //prendi da status 
            int energyValue = Status.getEnergyLevel();
            int hungerValue = Status.getHungerLevel();
            int stressValue = Status.getStressLevel();
            
            
            // Updating stress
            if (energyValue <30 && hungerValue > 80) {
                StatusManager.updateStress(1);
            }
            
            
            // Updating energy
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            StatusManager.updateEnergy(-1);
            
             // Updating hunger
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            StatusManager.updateHunger(1);
            
            
        }
    }

}
