/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

/**
 * This class is the representation of an answer in the game. 
 * The class has two attributes: the string and a boolean that represents if that specific answer is correct or not
 * @author 1997g
 */
public class Answer {
    private String answer;
    private boolean correct;

    /**
    * The constructor creates the object
    * @param answer is the string
    * @param correct is the correctness of the answer
    */
    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }
    
    /**
    * @return the string
    */
    public String getAnswer() {
        return answer;
    }

    /**
    * @return the correctness
    */
    public boolean isCorrect() {
        return correct;
    }

    public String toString() {
        return answer;
    }
    
}
