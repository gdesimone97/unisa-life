/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.util.ArrayList;

/**
 * This class represents a specific question in the game. The object has 3 attributes:
 * - the string of the question
 * - the list of the answers
 * - the level of difficulty that is represented as Integer
 * @author 1997g
 */
public class Question {
    private String question;
    private ArrayList<Answer> answers;
    private Integer level;

    /**
    * The constructor returns an instance of the Question
    * @param level is the Integer representing the difficulty of that question
    * @param question is the string
    * @param answers is the list of the Answer object of that question
    */
    public Question(Integer level, String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
        this.level = level;
        
        this.mixUpAnswers();
    }
    
    /**
    * This methods returns if the selected answer is correct or not
    * @param answer is the specific answer that is choosen
    * @return the correctness of that answer
    */
    public boolean isCorrect(Answer answer) {
        return answer.isCorrect();
    }

    /**
    * This methods returns the string question
    */
    public String getQuestion() {
        return question;
    }

    /**
    * This methods returns the list of answers
    */
    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    /**
    * This methods returns the level of difficulty of the question
    */
    public Integer getLevel() {
        return level;
    }
    
    /**
    * This private method is used only when a question is instantiated:
    * it mix up all the list of answers because it may be ordered
    */
    private void mixUpAnswers() {
        int randomTimes = ((int) (Math.random() * 10));
        int randomPos;
        
        for (int i = 0; i < randomTimes; i++) {
            randomPos = ((int) (Math.random() * 10) % answers.size());
            answers.add(answers.remove(randomPos));
        }
    }

    public String toString() {
        String str = "";
        str += this.getQuestion() + "\n";
        for(Answer a : answers) {
            str += a.toString() + "\n";
        }
        return str; 
    }
    
}
