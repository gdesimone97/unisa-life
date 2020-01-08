/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud.change;

import character.StatusManager;

/**
 * This class is one of the ConcreteStrategy classes implementing the strategy HudBarChange.
 * It implements the bar changes due to a correct answer in the exam.
 * @author mariodesio
 */
public class CorrectAnswerHudBarChange implements HudBarChange {

    /**
     * This method allows to implement the bar changes due to the correct answer in the exam.
     * Stress will be decreased by 20 per cent.
     */
    @Override
    public void execute() {
        StatusManager.getInstance().updateStress(-20);
    }
    
}
