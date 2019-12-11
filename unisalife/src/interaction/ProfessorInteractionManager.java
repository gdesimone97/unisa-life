/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import exam.Exam;
import exam.question.Materia;
import game.GameObjects.Professor;
import game.Interfaces.Interactable;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.FileTextManagerException;
import language.exceptions.TextFinderException;
import quests.quest.QuestsSingleton;
import unisagui.GuiManager;

/**
 * InteractionManager implemented for Professor's exam process
 * @author 1997g
 */
public class ProfessorInteractionManager implements InteractionManager {

    public ProfessorInteractionManager() {
    }

    @Override
    public void execute(Interactable obj) {
        // 1. Find the subject of the exam
        Professor p = (Professor)obj;
        Materia m = p.getSubject();
        
        // 2. verifica idonietà e requisiti
        if(QuestsSingleton.getInstance().getQuest().get(m).isAvailable()){
            //3. Start the exam session
            Thread esameThread = new Thread(new Exam(m));
            esameThread.start();
        } else {
            if (QuestsSingleton.getInstance().getQuest().get(m).isDone()) {
                try {
                    GuiManager.getInstance().showHint(FileTextManager.getFileTextManager().getString(new MessageInformation("ExamAlreadyDone")).get(0));
                } catch (FileTextManagerException ex) {
                } catch (TextFinderException ex) {
                }
            }
            else {
                try {
                GuiManager.getInstance().showHint(FileTextManager.getFileTextManager().getString(new MessageInformation("NotAllowed")).get(0));
            } catch (FileTextManagerException ex) {
            } catch (TextFinderException ex) {
            }
            }
        }
        
        
        
        
        // 4. modifica stato e ricompense
        
    }
    
}
