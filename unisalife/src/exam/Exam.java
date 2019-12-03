/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam;
import java.util.Iterator;
import question.*;
/**
 *
 * @author liovi
 */
public class Exam {
    private final Questions questions;
    private int score;
    private final Materia examSubject;
    private final int basicScore;
    
    /**
     * Constructor. Given a examSubject fetches the questions.
     * 
     * @param materia An object Materia needed to load the correct exam's questions
     * 
     */
    public Exam(Materia materia){
        StringsQuestionFactory questionsFetch = new StringsQuestionFactory(materia);
        this.score = 0;
        this.examSubject = materia;
        this.questions = questionsFetch.getQuestions();
        this.basicScore = 5;
    }
    
    /**
     * Verify if the given answer is correct and gives a score, 
     * depending on the level of the question and on the time passed 
     * before give the answer.
     * 
     * @param answer A boolean value that indicates if the answer is or not is correct
     * @param seconds Time passed before give the answer
     * @param level Difficulty level of the question used to give a rising value
     * for each answer
     */
    public void verifyAnswer(boolean answer, int seconds, int level){
        if(level!=4 && answer){
            if(seconds>=10)
               this.score+=(this.basicScore*level);
            else{
                double losePoints = (((10-seconds)*0.1)*(-4)); //variable used to store losed points depending on passed time before give the answer
                this.score += (this.basicScore*level) - losePoints;
            }
        }else if (level == 4){
            if(answer)
                this.score+=1;
            else
                this.score-=1;
        }
    }
    
    /**
     *
     * @return the next question of the exam, if there aren't other questions
     * null
     */
    public Question getQuestion(){
        QuestionsIterator iter = questions.iterator();
        if(iter.hasNext())
            return iter.next();
        else
            return null;
    }
    
    /**
     *
     * @return the score
     */
    public int getScore(){
        return this.score;
    }

    
}
