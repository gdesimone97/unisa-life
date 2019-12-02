/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package question;

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
            System.out.println(questIt.next().toString());
        }
        
    }

}
