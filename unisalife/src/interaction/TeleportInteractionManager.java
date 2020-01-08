/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.DownFaceState;
import game.GameObjects.Player;
import game.GameObjects.Teleport;
import game.Interfaces.Initializable.InitException;
import game.Interfaces.Interactable;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.PlayState;
import gameSystem.map.MapManager;
import jubox.JukeBoxSound;

/**
 * interaction manager used to interact with teleports
 * @author simon
 */
public class TeleportInteractionManager implements InteractionManager {

    /**
     * execute method for the related object
     * @param obj 
     */
    @Override
    public void execute(Interactable obj) {
        Teleport t=(Teleport)obj;
        try{
            MapManager.getInstance().setMap(t.getMapDestination());
            GameStateManager.getInstance().setState(LoadingState.getInstance());
            Player.getIstance().setVelX(0);
            Player.getIstance().setVelY(0);
            Player.getIstance().setState(DownFaceState.getInstance());
            Player.getIstance().setX(t.getPositionDestination().getX());    
            Player.getIstance().setY(t.getPositionDestination().getY());
            JukeBoxSound.getInstance().play("teleport");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                GameStateManager.getInstance().setState(PlayState.getInstance());
            }).start();

        }
        catch(InitException e) {
            e.printStackTrace();
        }
    }
    
}
