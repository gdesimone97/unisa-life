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
import java.util.List;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import quests.quest.Quest;
import quests.quest.Quests;
import saving.SaveManager;
import saving.exceptions.SavingException;
import unisagui.DialogManager;
import unisagui.GuiManager;
import unisagui.RequestGui;

/**
 * InteractionManager implemented for letting the professors in the game interact
 * with the main character. It prints some dialogs or start the exam if it's possible
 *
 * @author 1997g
 */
public class ProfessorInteractionManager implements InteractionManager {

    /**
     * empty constructor
     */
    public ProfessorInteractionManager() {
    }

    /**
     * Finds the subject related to that professor and checks if the exam is available or not
     * Based on this, it can start the exam or print some tips
     * @param obj 
     */
    @Override
    public void execute(Interactable obj) {
        // 1. Find the subject of the exam
        Professor p = (Professor) obj;
        Subject s = Booklet.getInstance().getSubject(p.getSubject());
        RequestGui rg = new RequestGui();

        new Thread(() -> {
            

            // 2. verifica idonietà e requisiti
            try {
                Quest q = Quests.getInstance().getQuest(s.getInfo());
                if (q != null && q.isDone()) {
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
                        
                        Thread tolcThread = new Thread(new Tolc(s, p));
                        tolcThread.start();
                    } else {
                        Thread esameThread;
                        try {
                            esameThread = new Thread(new Exam(s, p));
                            esameThread.start();
                        } catch (Exam.CoinNotEnoughException ex) {
                            try {
                                List<String> a = FileTextManager.getFileTextManager().getString(new MessageInformation("NotEnoughMoneyExam"));
                                String str = a.get(0) + ex.getCoinRequired() + a.get(1);
                                GuiManager.getInstance().showDialog(p.getNome(), str, rg);
                                rg.getValue();
                            } catch (DialogManager.DialogAlreadyOpenedException x) {
                            }
                        }
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
