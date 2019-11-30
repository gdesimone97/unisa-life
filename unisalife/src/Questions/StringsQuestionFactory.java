/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Questions;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 1997g
 */
public class StringsQuestionFactory extends QuestionFactory {
    private Questions questions;
    private String delimiter = "#";
    
    public StringsQuestionFactory(Materia materia) {
        super(materia);
        questions = new Questions();
        this.parseQuestions();
    }

    @Override
    public Questions getQuestions() {
        return questions;
    }
    
    private void parseQuestions() {
        String[] strList = {"0#Qual è la capitale della Francia?#Parigi#Roma#Londra#Madrid#"};
        
        Scanner sc;
        boolean count = true;
        Integer level;
        String quest;
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        for (String str : strList) {
            sc = new Scanner(str);
            sc.useDelimiter(delimiter);
            
            level = sc.nextInt();
            quest = sc.next();
            while(sc.hasNext()) {
                answers.add(new Answer(sc.next(), count));
                if (count) {
                    count = false;
                }
            }
            
            Question q = new Question(level, quest, answers);
            questions.addQuestion(q);
        }
    }
    
}
