/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import exam.Exam;
import exam.ExamResult;
import exam.question.Materia;
import game.GameObjects.Professor;
import game.Interfaces.Interactable;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.*;
import language.exceptions.FileTextManagerException;
import language.exceptions.TextFinderException;
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
        // 1. trova la materia giusta in base al prof (il prof deve avere un attributo materia)
        Professor p = (Professor)obj;
        Materia m = p.getSubject();
        // 2. verifica idonietà e requisiti
        
        //3. Start the exam session
        ExamResult er = new ExamResult();
        Thread esameThread = new Thread(new Exam(m, er));
        esameThread.start();
        
        int finalVote = er.getValue();
        try {
            GuiManager.getInstance().showHint(FileTextManager.getFileTextManager().getString(new MessageInformation("ScoreTaken")).get(0) + finalVote);
        } catch (TextFinderException ex) {
            GuiManager.getInstance().showHint("ERRORE - " + ex);
        } catch (FileTextManagerException ex) {
            GuiManager.getInstance().showHint("ERRORE - " + ex);
        }
        
        // 4. modifica stato e ricompense
        
    }
    
}
