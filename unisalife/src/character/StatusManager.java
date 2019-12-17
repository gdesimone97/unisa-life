/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import hud.HudUpdater;
import unisagui.GameFrame;
import unisagui.GuiManager;

/**
 *
 * @author mariodesio
 */
public class StatusManager {

    private final GameFrame gameframe = GameFrame.getInstance();
    private HudUpdater updater;
    private static StatusManager instance;
    
    private StatusManager() {
        this.updater = new HudUpdater();
        Thread up = new Thread(updater);
        up.start();
    }
    
    public static StatusManager getInstance(){
        if(instance == null) {
            instance = new StatusManager();
        }
        return instance;
    }

    public static synchronized void updateEnergy(int increment) {
        int newValue = Status.getEnergyLevel() + increment;
        Status.setEnergyLevel(newValue);
        GuiManager.getInstance().updateEnergyBar(newValue);
    }

    public static synchronized void updateHunger(int increment) {
        int newValue = Status.getHungerLevel() + increment;
        Status.setHungerLevel(newValue);
        GuiManager.getInstance().updateHungerBar(newValue);
    }

    public static synchronized void updateStress(int increment) {
        int newValue = Status.getStressLevel() + increment;
        Status.setStressLevel(newValue);
        GuiManager.getInstance().updateStressBar(newValue);
    }

    public static synchronized void updateMoney(int increment) {
        int newValue = Status.getMoney() + increment;
        Status.setMoney(newValue);
        GuiManager.getInstance().updateMoney(newValue);
    }
}
