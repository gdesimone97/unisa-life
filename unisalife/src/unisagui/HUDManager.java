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

    
    protected HUDManager() {
        
    }
    
    public String getInfo(){
        return info;
    }
    
    public void updateEnergyBar(int energy){
        SwingUtilities.invokeLater(() -> gameframe.EnergyProgressBar.setValue(energy));
    }
    
    public void updateHungerBar(int hunger){
        SwingUtilities.invokeLater(() -> gameframe.HungerProgressBar.setValue(hunger));
    }
    
    public void updateStressBar(int stress){
        SwingUtilities.invokeLater(() -> gameframe.StressProgressBar.setValue(stress));
    }
    
    public void updateMoney(int money){
        SwingUtilities.invokeLater(() -> gameframe.MoneyLabel.setText(Integer.toString(money)));
    }
}
