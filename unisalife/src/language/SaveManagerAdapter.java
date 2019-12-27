/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import saving.SaveManager;
import saving.exceptions.LoadingException;

/**
 *
 * @author Giuseppe De Simone
 */
public class SaveManagerAdapter extends SaveManager {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String loadLang() throws LoadingException {
        return super.loadLang();
    }

}
