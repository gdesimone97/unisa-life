/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class is the set of all the Question objects that are in a specific subject
 * Questions are structured in a dictionary that pairs the level of difficulty of questions with an
 * ArrayList of Question
 * @author 1997g
 */
public class Questions implements QuestionsCollection {
    private HashMap<Integer, ArrayList<Question>> questions;

    /**
     * creates an empty hashmap
     */
    public Questions() {
        this.questions = new HashMap<Integer, ArrayList<Question>>();
    }
    
    /**
     * This method adds new question in the list of this class
     * @param question is the question to be added
     */
    public void addQuestion(Question question) {
        ArrayList list = questions.get(question.getLevel());
        if(list == null) {
            questions.put(question.getLevel(), new ArrayList());
            
        }
        questions.get(question.getLevel()).add(question);
    }
    
    /**
     *
     * @return
     */
    public int getNumLevels() {
        return questions.size();
    }
    
    /**
     * This method provides an instance of a class that implements Iterator interface
     * that has next() and hasNext() methods.
     * @return the instance of iterator
     */
    public QuestionsIterator iterator() {
        return new QuestionsIteratorImpl(this.questions);
    }
    
    /**
     * this is a private inner class, that is a implementation of the interface
     * QuestionsIterator. For this reason, this class implements 
     * next() and hasNext() methods
     */
    private class QuestionsIteratorImpl implements QuestionsIterator {
        private Integer actualLevel;
        private int actualSeen;
        private HashMap<Integer, ArrayList<Question>> questions;
        private Random generator;

        public QuestionsIteratorImpl(HashMap<Integer, ArrayList<Question>> questions) {
            this.questions = questions;
            this.actualLevel = 0;
            this.actualSeen = 0;
        }
        
        /**
         * This method checks if there is another question of a following difficulty level
         * @return a bool
         */
        @Override
        public boolean hasNext() {
            return (actualSeen < questions.keySet().size());
        }

        /**
         * This method provides the next level following question
         * @return a question
         */
        @Override
        public Question next() {
            ArrayList<Question> levelQuestions;
            do {
                levelQuestions = questions.get(++actualLevel);
            } while (levelQuestions == null);
            actualSeen++;
            generator = new Random(System.nanoTime());
            int randomIndex = ((int) (generator.nextInt(levelQuestions.size())));
            return levelQuestions.get(randomIndex);
        }
        
    }
    
    
}
