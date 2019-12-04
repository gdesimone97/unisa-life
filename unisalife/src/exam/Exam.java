/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam;
import java.util.Iterator;
import question.*;

/**
 * This class is used to do an exam of a given subject.
 * The number of questions is modifiable by changing maxLevel value and 
 * basicScore due to reach a maximum of 30 point (a little overflow is admitted)
 *
 * @author liovi
 */
public class Exam {
    private final Questions questions;
    private int score;
    private final int maxLevel;
    private final double basicScore;
    QuestionsIterator iter;
    
    /**
     * Constructor. Given a examSubject fetches the questions.
     * 
     * @param materia An object Materia needed to load the correct exam's questions
     * 
     */
    public Exam(Materia materia){
        StringsQuestionFactory questionsFetch = new StringsQuestionFactory(materia);
        int j = 0;
        this.score = 0;
        this.questions = questionsFetch.getQuestions();
        this.maxLevel = 6;
        for(int i=1; i<=this.maxLevel;i++)
            j+=i;
        this.basicScore = 30/j;
        this.iter = questions.iterator();
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
    public void verifyAnswer(boolean answer, int seconds, int level){ //here the level is referred to the number of questions choosed
        if(level!=maxLevel && answer){
            if(seconds>=10)
               this.score+=(this.basicScore*level);
            else{
                double losePoints = (((10-seconds)*0.1)*(basicScore)); //variable used to store losed points depending on passed time before give the answer
                this.score += this.basicScore*level - losePoints;
            }
        }else if (level == maxLevel && this.score >= 30){
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
        
        if(iter.hasNext())
            return iter.next();
        else
            return null;
    }
    
    /**
     *
     * @return the score
     */
    public double getScore(){
        return this.score;
    }

    
}
