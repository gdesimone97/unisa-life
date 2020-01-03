/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.NormalPerson;
import game.GameObjects.Player;
import game.Interfaces.Interactable;
import gameSystem.map.MapManager;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import unisagui.GuiManager;

/**
 *
 * @author 1997g
 */
public class NormalPersonInteractionManager implements InteractionManager {
    
    private List<String> str;
    private GuiManager gui = GuiManager.getInstance();

    @Override
    public void execute(Interactable obj) {
        try {
            NormalPerson p = (NormalPerson) obj;
            // if in the tutorial, a normal person explains the game
            // else in the rest, a normal person says random phrases

            FileTextManager ftm = FileTextManager.getFileTextManager();

            if (MapManager.getInstance().getActualMap() == 0) {
                str = ftm.getString(new MessageInformation("TutorialFrasi"));
//                gui.showDialog(p.getNome(), str.get(0) + Player.getIstance().getName() + str.get(1));
//                for (int i = 2; i < str.size(); i++)
//                    gui.showDialog(p.getNome(), str.get(i));
//            } else {
                Random random = new Random();
                str = ftm.getString(new MessageInformation("RandomPeople"));
                int i = random.nextInt(str.size());
//                gui.showDialog(p.getNome(), str.get(i));
            }

        } catch (TextFinderException ex) {
            ex.printStackTrace();
        }
    }

}
