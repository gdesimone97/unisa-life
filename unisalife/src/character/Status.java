/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 * This class represents the state of the character in terms of sleep, hunger
 * and stress level. Are also available character's money.
 *
 * @author liovi
 */
public class Status implements Serializable, Saveable {

    private static int energyLevel = 100;
    private static int hungerLevel = 0;
    private static int stressLevel = 0;
    private static int money = 0;

    /**
     *
     * @return the sleep level of the character
     */
    public static int getEnergyLevel() {
        return energyLevel;
    }

    /**
     *
     * @return the hunger level of the character
     */
    public static int getHungerLevel() {
        return hungerLevel;
    }

    /**
     *
     * @return the stress level of the character
     */
    public static int getStressLevel() {
        return stressLevel;
    }

    /**
     *
     * @return the amount of money of the character
     */
    public static int getMoney() {
        return money;
    }

    /**
     *
     * @param energyLevel used to increase/decrease this parameter
     */
    public static void setEnergyLevel(int energyLevel) {
        if (energyLevel < 0) {
            energyLevel = 0;
        }
        if (energyLevel > 100) {
            energyLevel = 100;
        }
        Status.energyLevel = energyLevel;
    }

    /**
     *
     * @param hungerLevel used to increase/decrease this parameter
     */
    public static void setHungerLevel(int hungerLevel) {
        if (hungerLevel < 0) {
            hungerLevel = 0;
        }
        if (hungerLevel > 100) {
            hungerLevel = 100;
        }
        Status.hungerLevel = hungerLevel;
    }

    /**
     *
     * @param stressLevel used to increase/decrease this parameter
     */
    public static void setStressLevel(int stressLevel) {
        if (stressLevel < 0) {
            stressLevel = 0;
        }
        if (stressLevel > 100) {
            stressLevel = 100;
        }
        Status.stressLevel = stressLevel;
    }

    /**
     *
     * @param money used to increase/decrease the amount of money
     */
    public static void setMoney(int money) {
        Status.money = money;
    }

    /**
     *
     * @return a Serializable useful to save the status of the character
     */
    @Override
    public Serializable save() {
        List<Integer> stat = new ArrayList<>();
        stat.add(energyLevel);
        stat.add(hungerLevel);
        stat.add(stressLevel);
        stat.add(money);
        return (Serializable) stat;
    }

    /**
     *
     * @param obj is a Serializable. Downcast is necessary before load the obj
     */
    @Override
    public void load(Serializable obj)throws LoadingException {
        List<Integer> stat = (ArrayList<Integer>) obj;
        energyLevel = stat.get(0);
        hungerLevel = stat.get(1);
        stressLevel = stat.get(2);
        money = stat.get(3);
    }

}
