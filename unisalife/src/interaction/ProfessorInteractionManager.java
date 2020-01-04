/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import exam.Exam;
import exam.Tolc;
import exam.booklet.Booklet;
import exam.booklet.Subject;
import game.GameObjects.Player;
import game.GameObjects.Professor;
import game.Interfaces.Initializable.InitException;
import game.Interfaces.Interactable;
import gameSystem.map.MapManager;
import java.util.List;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import quests.quest.Quests;
import saving.SaveManager;
import saving.exceptions.SavingException;
import unisagui.DialogManager;
import unisagui.GuiManager;
import unisagui.RequestGui;

/**
 * InteractionManager implemented for Professor's exam process
 *
 * @author 1997g
 */
public class ProfessorInteractionManager implements InteractionManager {

    public ProfessorInteractionManager() {
    }

    @Override
    public void execute(Interactable obj) {
        // 1. Find the subject of the exam
        Professor p = (Professor) obj;
        Subject s = Booklet.getInstance().getSubject(p.getSubject());
        RequestGui rg = new RequestGui();

        new Thread(() -> {
            

            // 2. verifica idonietà e requisiti
            try {
                if (Quests.getInstance().getQuest(s.getInfo()).isDone()) {
                    try {
                        GuiManager.getInstance().showDialog(p.getNome(), FileTextManager.getFileTextManager().getString(new MessageInformation("ExamAlreadyDone")).get(0), rg);
                        rg.getValue();
                    } catch (DialogManager.DialogAlreadyOpenedException ex) {
                    }
                    
                } else if (Quests.getInstance().getQuest(s.getInfo()).isAvailable()) {

                    //3. Start the exam session
                    if (s.getInfo().compareTo("tolc") == 0) {
                        List<String> str = FileTextManager.getFileTextManager().getString(new MessageInformation("BeforeTolcDialogName"));
                        try {
                            GuiManager.getInstance().showDialog(p.getNome(), str.get(0) + Player.getIstance().getName() + str.get(1) + "\n" + str.get(2), rg);
                            rg.getValue();
                        } catch (DialogManager.DialogAlreadyOpenedException ex) {
                        }
                        
                        Thread tolcThread = new Thread(new Tolc(s, p.getNome()));
                        tolcThread.start();
                    } else {
                        Thread esameThread = new Thread(new Exam(s, p.getNome()));
                        esameThread.start();
                    }

                } else {
                    try {
                        GuiManager.getInstance().showDialog(p.getNome(), FileTextManager.getFileTextManager().getString(new MessageInformation("NotAllowed")).get(0), rg);
                        rg.getValue();
                    } catch (DialogManager.DialogAlreadyOpenedException ex) {
                    }
                
                }

                // autosave
                SaveManager.getSaveManager().save();
            } catch (TextFinderException ex) {
            } catch (InitException ex) {
            } catch (SavingException ex) {
            }
        }).start();
    }
}
