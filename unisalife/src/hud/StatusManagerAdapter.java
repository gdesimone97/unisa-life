/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud;

import character.StatusManager;

/**
 * This class extends StatusManager class, returning the thread up that is protected in its parent.
 * @author mariodesio
 */
class StatusManagerAdapter extends StatusManager{
    /**
     * 
     * @return thread associated with the status manager
     */
    public static Thread getThread(){
        return up;
    }
}
