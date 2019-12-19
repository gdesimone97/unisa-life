/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import game.Interfaces.Initializable;
import hud.HudUpdater;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import saving.Saveable;
import saving.exceptions.LoadingException;
import unisagui.GameFrame;
import unisagui.GuiManager;

/**
 *
 * @author mariodesio
 */
public class StatusManager implements Saveable, Initializable {
    
    private final GameFrame gameframe = GameFrame.getInstance();
    private HudUpdater updater;
    private static StatusManager instance;
    
    private StatusManager() {
        this.updater = new HudUpdater();
        Thread up = new Thread(updater);
        up.start();
    }
    
    public static StatusManager getInstance() {
        if (instance == null) {
            instance = new StatusManager();
        }
        return instance;
    }
    
    public synchronized void updateEnergy(int increment) {
        int newValue = Status.getEnergyLevel() + increment;
        Status.setEnergyLevel(newValue);
        GuiManager.getInstance().updateEnergyBar(newValue);
    }
    
    public synchronized void updateHunger(int increment) {
        int newValue = Status.getHungerLevel() + increment;
        Status.setHungerLevel(newValue);
        GuiManager.getInstance().updateHungerBar(newValue);
    }
    
    public synchronized void updateStress(int increment) {
        int newValue = Status.getStressLevel() + increment;
        Status.setStressLevel(newValue);
        GuiManager.getInstance().updateStressBar(newValue);
    }
    
    public synchronized void updateMoney(int increment) {
        int newValue = Status.getMoney() + increment;
        Status.setMoney(newValue);
        GuiManager.getInstance().updateMoney(newValue);
    }

    /**
     *
     * @return a Serializable useful to save the status of the character
     */
    @Override
    public Serializable save() {
        List<Integer> stat = new ArrayList<>();
        stat.add(Status.getEnergyLevel());
        stat.add(Status.getHungerLevel());
        stat.add(Status.getStressLevel());
        stat.add(Status.getMoney());
        return (Serializable) stat;
    }

    /**
     *
     * @param obj is a Serializable. Downcast is necessary before load the obj
     */
    @Override
    public void load(Serializable obj) throws LoadingException {
        List<Integer> stat = (ArrayList<Integer>) obj;
        int energyLevel = stat.get(0);
        int hungerLevel = stat.get(1);
        int stressLevel = stat.get(2);
        int money = stat.get(3);
        updateEnergy(energyLevel);
        updateHunger(hungerLevel);
        updateMoney(money);
        updateStress(stressLevel);
    }
    
    @Override
    public void init() {
        updateEnergy(100);
        updateHunger(0);
        updateMoney(0);
        updateStress(0);
    }
}
