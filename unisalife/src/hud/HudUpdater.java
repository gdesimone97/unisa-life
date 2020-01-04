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
import gameSystem.map.MapManager;
import interaction.TeleportEmergencyInteractionManager;
import interaction.TeleportInteractionManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import unisagui.GuiManager;

/**
 *
 * @author mariodesio
 */
public class HudUpdater implements Runnable {
    
    private final Teleport hungerTeleport = new Teleport(null,MapManager.getInstance().getActualMap(),new Position(3000,3000));
    private final Teleport healthTeleport = new Teleport(null,MapManager.getInstance().getActualMap(),new Position(5000,5000));
    int energyValue;
    int hungerValue;
    int stressValue;
    
    @Override
    public void run() {
        while (true) {
            
            //prendi da status 
            energyValue = Status.getEnergyLevel();
            hungerValue = Status.getHungerLevel();
            stressValue = Status.getStressLevel();
            
            
            // Updating stress
            if (energyValue == 0 || stressValue == 100){
                //teletrasporto al letto nella mappa piccolina e ripristino barre
                TeleportEmergencyInteractionManager t = new TeleportEmergencyInteractionManager();
                StatusManager.getInstance().setEnergy(100);
                StatusManager.getInstance().setStress(0);
                t.execute(healthTeleport);
            }
            else
                if(hungerValue==100){
                    //teletrasporto davanti alla mensa e ripristino.
                    TeleportInteractionManager t = new TeleportInteractionManager();
                    StatusManager.getInstance().setHunger(0);
                    t.execute(hungerTeleport);
                }
            
            if (energyValue <30 && hungerValue > 70) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
                StatusManager.getInstance().updateStress(1);
            }
            else
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
                StatusManager.getInstance().updateStress(-1);
            }
            
            // Updating energy and Updating hunger
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(HudUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            StatusManager.getInstance().updateEnergy(-1);
            StatusManager.getInstance().updateHunger(1);
        }
    }

}
