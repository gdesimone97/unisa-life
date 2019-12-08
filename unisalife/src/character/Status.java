/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import exam.booklet.Saveable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the state of the character in terms of sleep, hunger 
 * and stress level. Are also available character's money.
 * 
 * @author liovi
 */
public class Status implements Serializable, Saveable{
    private static int sleepLevel = 0;
    private static int hungerLevel = 0;
    private static int stressLevel = 0;
    private static int money = 0;

    /**
     * 
     * @return the sleep level of the character
     */
    public static int getSleepLevel() {
        return sleepLevel;
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
     * @param sleepLevel used to increase/decrease this parameter
     */
    public static void setSleepLevel(int sleepLevel) {
        Status.sleepLevel += sleepLevel;
    }

    /**
     *
     * @param hungerLevel used to increase/decrease this parameter
     */
    public static void setHungerLevel(int hungerLevel) {
        Status.hungerLevel += hungerLevel;
    }

    /**
     *
     * @param stressLevel used to increase/decrease this parameter
     */
    public static void setStressLevel(int stressLevel) {
        Status.stressLevel += stressLevel;
    }

    /**
     *
     * @param money used to increase/decrease the amount of money
     */
    public static void setMoney(int money) {
        Status.money += money;
    }

    /**
     *
     * @return a Serializable useful to save the status of the character
     */
    @Override
    public Serializable save() {
        List<Integer> stat = new ArrayList<>();
        stat.add(sleepLevel);
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
    public void load(Serializable obj) {
        List<Integer> stat = (ArrayList<Integer>) obj;
        sleepLevel = stat.get(0);
        hungerLevel = stat.get(1);
        stressLevel = stat.get(2);
        money = stat.get(3);
    }
    
    
    
}
