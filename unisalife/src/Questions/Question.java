/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Questions;

import java.util.ArrayList;

/**
 *
 * @author 1997g
 */
public class Question {
    private String question;
    private ArrayList<Answer> answers;
    private Integer level;

    public Question(Integer level, String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
        this.level = level;
        
        this.mixUpAnswers();
    }
    
    public boolean isCorrect(Answer answer) {
        return answer.isCorrect();
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public Integer getLevel() {
        return level;
    }
    
    private void mixUpAnswers() {
        int randomTimes = ((int) (Math.random() * 10));
        int randomPos;
        
        for (int i = 0; i < randomTimes; i++) {
            randomPos = ((int) (Math.random() * 10) % answers.size());
            answers.add(answers.remove(randomPos));
        }
    }
    
}
