/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import character.StatusManager;
import character.Status;
import game.GameObjects.Position;
import game.GameObjects.Teleport;
import interaction.TeleportEmergencyInteractionManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariodesio
 */
public class HudUpdater implements Runnable {
    
    private final Teleport healthTeleport = new Teleport(null,3,new Position(832,448));
    private final Teleport hungerTeleport = new Teleport(null,1,new Position(51*32,30*32));
    int energyValue;
    int hungerValue;
    int stressValue;
    int delta1;
    int delta2;
    
    @Override
    public void run() {
        while (true) {
            
            //prendi da status 
            energyValue = Status.getEnergyLevel();
            hungerValue = Status.getHungerLevel();
            stressValue = Status.getStressLevel();
            delta1 = (int)(Math.random() * 2) + 1;
            delta2 = (int)(Math.random() * 2) + 1;
            
            
            // Updating stress
            if (energyValue == 0 || stressValue == 100){
                //teletrasporto al letto nella mappa piccolina e ripristino barre
                TeleportEmergencyInteractionManager t = new TeleportEmergencyInteractionManager();
                StatusManager.getInstance().setEnergy(100);
                StatusManager.getInstance().setStress(0);
                StatusManager.getInstance().setHunger(0);
                t.execute(healthTeleport);
            }
            else
                if(hungerValue==100){
                    //teletrasporto davanti alla mensa e ripristino.
                    TeleportEmergencyInteractionManager t = new TeleportEmergencyInteractionManager();
                    StatusManager.getInstance().setEnergy(100);
                    StatusManager.getInstance().setStress(0);
                    StatusManager.getInstance().setHunger(0);
                    t.execute(hungerTeleport);
                }
            
            if (energyValue <50 || hungerValue > 50) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
                StatusManager.getInstance().updateStress((delta1+delta2)/2+1);
            }
            else
                if(energyValue>=50 && hungerValue <= 50)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
                StatusManager.getInstance().updateStress(-((delta1+delta2)/2)+1);
            }
            
            // Updating energy and Updating hunger
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            StatusManager.getInstance().updateEnergy(-delta1);
            StatusManager.getInstance().updateHunger(delta2);
        }
    }

}
