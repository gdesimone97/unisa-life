/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

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
import language.exceptions.StringNotFoundException;
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
            Player.getIstance().setX(30*32);
            Player.getIstance().setY(10*32);
            System.out.println(Player.getIstance().getX());
            System.out.println(Player.getIstance().getY());
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                /*ms = new MessageInformation("EmergencyStatus");
                try {
                    toShow = tm.getString(ms).get(0);
                } catch (TextFinderException ex) {
                    Logger.getLogger(TeleportEmergencyInteractionManager.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                try{
                    GuiManager.getInstance().showHint("riguardati");
                }
                catch(Exception e){}
                JukeBoxSound.getInstance().play("wrong");
                GameStateManager.getInstance().setState(PlayState.getInstance());
                
            }).start();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
