/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Questions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 1997g
 */
public class Questions {
    private HashMap<Integer, ArrayList<Question>> questions;

    public Questions() {
        this.questions = new HashMap<Integer, ArrayList<Question>>();
    }
    
    public void addQuestion(Question question) {
        questions.get(question.getLevel()).add(question);
    }
    
    public QuestionsIterator iterator() {
        return new QuestionsIteratorImpl(this.questions);
    }
    
    
    private class QuestionsIteratorImpl implements QuestionsIterator {
        private Integer actualLevel;
        private HashMap<Integer, ArrayList<Question>> questions;

        public QuestionsIteratorImpl(HashMap<Integer, ArrayList<Question>> questions) {
            this.questions = questions;
            this.actualLevel = 1;
        }
        
        @Override
        public boolean hasNext() {
            return (actualLevel < questions.keySet().size());
        }

        @Override
        public Question next() {
            ArrayList<Question> levelQuestions = questions.get(actualLevel++);
            int randomIndex = ((int) (Math.random() * 10)) % levelQuestions.size();
            return levelQuestions.get(randomIndex);
        }
        
    }
    
    
}
