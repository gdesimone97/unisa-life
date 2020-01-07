/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import character.StatusManager;

/**
 *
 * @author mariodesio
 */
class StausManagerAdapter extends StatusManager{
    /**
     * 
     * @return 
     */
    public static Thread getThread(){
        return up;
    }
}
