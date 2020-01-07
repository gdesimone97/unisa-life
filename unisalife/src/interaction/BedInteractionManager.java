/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.Bed;
import game.GameObjects.DownFaceState;
import game.GameObjects.Player;
import game.Interfaces.Initializable.InitException;
import game.Interfaces.Interactable;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.PlayState;
import gameSystem.SleepState;
import hud.change.DormitoryHudBarChange;
import hud.change.HudBarChange;
import sound.JukeBoxSound;

/**
 * the interaction manager used when the player goes to sleep
 * @author 1997g
 */
public class BedInteractionManager implements InteractionManager {

    /**
     * execute method for the related object
     * @param obj 
     */
    @Override
    public void execute(Interactable obj) {
        Bed b = (Bed) obj;
        
        try{
            GameStateManager.getInstance().setState(SleepState.getInstance());
            Player.getIstance().setVelX(0);
            Player.getIstance().setVelY(0);
            Player.getIstance().setState(DownFaceState.getInstance());
            Player.getIstance().setX(b.getDestPosition().getX());    
            Player.getIstance().setY(b.getDestPosition().getY());
            JukeBoxSound.getInstance().play("snoring");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                HudBarChange dbc = new DormitoryHudBarChange();
                dbc.execute();
                GameStateManager.getInstance().setState(PlayState.getInstance());
            }).start();

        }
        catch(InitException e) {
            e.printStackTrace();
        }
    }
    
}
