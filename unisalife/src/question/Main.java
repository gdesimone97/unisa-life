/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package question;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 1997g
 */
public class Main {
    
    public static void main(String[] args) {
        QuestionFactory sqf = new StringsQuestionFactory(Materia.matematica);

        Questions q = sqf.getQuestions();
        
        QuestionsIterator questIt = q.iterator();
        
        while(questIt.hasNext()) {
            Question question = questIt.next();
            String stringa = JOptionPane.showInputDialog(question.toString() + "\n\n Risposta: ");
            ArrayList<Answer> answers = question.getAnswers();
            
            Answer answer = answers.get(Integer.parseInt(stringa)-1);
            System.out.println("Hai risposto " + answer + ". " + (question.isCorrect(answer)));
            
        }
        
        System.out.println("Il voto è: Mario Gay");
        
    }

}