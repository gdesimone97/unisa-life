/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.DownFaceState;
import game.GameObjects.Player;
import game.GameObjects.Teleport;
import game.Interfaces.Interactable;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.PlayState;
import gameSystem.map.MapManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import sound.JukeBoxSound;
import unisagui.DialogManager;
import unisagui.GuiManager;

/**
 *
 * @author simon
 */
public class TeleportEmergencyInteractionManager implements InteractionManager {
    
    FileTextManager tm;
    String toShow = null;
    MessageInformation ms;
    
    
    @Override
    public void execute(Interactable obj) {
        Teleport t=(Teleport)obj;
        try{
            GameStateManager.getInstance().setState(LoadingState.getInstance());
            MapManager.getInstance().setMap(t.getMapDestination());
            Player.getIstance().setX(t.getPositionDestination().getX());
            Player.getIstance().setY(t.getPositionDestination().getY());
            Player.getIstance().setState(DownFaceState.getInstance());
            Player.getIstance().setVelX(0);
            Player.getIstance().setVelY(0);
            System.out.println(Player.getIstance().getX());
            System.out.println(Player.getIstance().getY());
            new Thread(() -> {
                try {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                    }
                    
                    toShow = tm.getString(new MessageInformation("EmergencyStatus")).get(0);
                    
                    GuiManager.getInstance().showHint(toShow);
                    
                    JukeBoxSound.getInstance().play("wrong");
                    GameStateManager.getInstance().setState(PlayState.getInstance());
                    
                } catch (TextFinderException ex) {
                } catch (DialogManager.HintAlreadyOpenedException ex) {
                }
                
            }).start();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
