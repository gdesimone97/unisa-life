/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.SwingUtilities;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class HUDManager {
    
    private final String info = "GUIhud";
    private final GameFrame gameframe = GameFrame.getInstance();

    /**
     * empty constructor
     */
    protected HUDManager() {
        
    }
    
    /**
     *
     * @return info
     */
    public String getInfo(){
        return info;
    }
    
    /**
     *
     * @param energy the total value of the bar
     */
    public void updateEnergyBar(int energy){
        SwingUtilities.invokeLater(() -> gameframe.EnergyProgressBar.setValue(energy));
    }
    
    /**
     *
     * @param hunger the toal value of the bar
     */
    public void updateHungerBar(int hunger){
        SwingUtilities.invokeLater(() -> gameframe.HungerProgressBar.setValue(hunger));
    }
    
    /**
     *
     * @param stress the total value of the bar
     */
    public void updateStressBar(int stress){
        SwingUtilities.invokeLater(() -> gameframe.StressProgressBar.setValue(stress));
    }
    
    /**
     *
     * @param money the total value
     */
    public void updateMoney(int money){
        SwingUtilities.invokeLater(() -> gameframe.MoneyLabel.setText(Integer.toString(money)));
    }
}
