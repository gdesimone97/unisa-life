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
    private float sum;
    private int count;
    private final int maxLevel;
    private float basicScore;
    QuestionsIterator iter;
    
    /**
     * Constructor. Given a examSubject fetches the questions.
     * 
     * @param materia An object Materia needed to load the correct exam's questions
     * 
     */
    public Exam(Materia materia){
        QuestionFactory questionsFetch = new StringsQuestionFactory(materia);
        this.score = 0;
        this.sum = 0;
        this.count = 0;
        this.questions = questionsFetch.getQuestions();
        this.maxLevel = this.questions.getNumLevels();
        this.basicScore = 12/(30-(30/(float)(this.maxLevel-1)));
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
        int x = (30-(30/(this.maxLevel-level))); //seconds from which the user loses points
        if(level!=maxLevel && answer){
            this.sum += (seconds >= x ? 30 : 30-this.basicScore*(x-seconds));
        }else if(level!=maxLevel && !answer){
            this.count++;
            this.sum += (count <= (0.4*(maxLevel-1)) ? 18 : 0);                    
        }else if (level == maxLevel && this.score == 30){
            this.score += (answer ? 1 : -1);
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
    public int getScore(){
        this.score = (int)this.sum/(maxLevel-1);
        return this.score;
    }
    
    public float getCurrentScore(){
        return this.sum;
    }
    
}
