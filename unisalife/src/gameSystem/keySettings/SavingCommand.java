/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.LoadingState;
import gameSystem.PauseState;
import gameSystem.PlayState;
import saving.SaveManager;
import saving.exceptions.SavingException;

/**
 * Class to handle the save key command
 *
 * @author Giuseppe De Simone
 */
class SavingCommand extends KeyCommand implements ActionCommand {

    private void save() throws SavingException {
        SaveManager.getSaveManager().save();
        System.out.println("Salvato");
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitPlayState(PlayState playState) {
        try {
            save();
        } catch (SavingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitPauseState(PauseState pauseState) {
        try {
            save();
        } catch (SavingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitLoadingState(LoadingState loadState) {

    }
}
