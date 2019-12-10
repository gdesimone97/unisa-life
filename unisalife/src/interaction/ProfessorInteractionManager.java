/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import exam.Exam;
import exam.question.Materia;
import game.Interfaces.Interactable;

/**
 *
 * @author 1997g
 */
public class ProfessorInteractionManager implements InteractionManager {

    public ProfessorInteractionManager() {
    }

    @Override
    public void execute(Interactable obj) {
        // 1. trova la materia giusta in base al prof (il prof deve avere un attributo materia)
        
        // 2. verifica idonietà e requisiti
        
        //3. Start the exam session
        Thread esameThread = new Thread(new Exam(Materia.matematica));
        esameThread.start();
        
        // 4. modifica stato e ricompense
    }
    
}
