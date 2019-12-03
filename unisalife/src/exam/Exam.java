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
    private Questions questions;
    private int score;
    private Materia examSubject;
    private boolean available;
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
        this.available = true;
        this.questions = questionsFetch.getQuestions();
        this.basicScore = 5;
    }
    
    /**
     * Verify if the given answer is correct and gives a score, 
     * depending on the level of the question and on the time passed 
     * before give the answer.
     * 
     * The time box to give the correct answer is of 30 seconds;
     * if the answer is given whitin 20 seconds a full score is given,
     * otherwise will be applied a factor of reduction of 1 point:
     * 
     * 1 point lost if the answer is given in 7,8 or 9 seconds before timeout
     * 2 point lost if the answer is given in 5 or 6 seconds before timeout
     * 3 point lost if the answer is given in 3 or 4 seconds before timeout
     * 4 point lost if the answer is given in 1 or 2 seconds before timeout
     * 
     * The cum laude question must be given only if the score after the firt 
     * tree questions is 30 and is represented by giving a score 
     * of 31 at the end. If a wrong answer is given the user losts 1 point.
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
            else
                switch (seconds) { /*default is not used instead to avoid a long check if the answer was given in time*/
                    case 9:
                    case 8:
                    case 7:
                        this.score+=(this.basicScore*level)-1;
                        break;
                    case 6:
                    case 5:
                        this.score+=(this.basicScore*level)-2;
                        break;
                    case 4:
                    case 3:
                        this.score+=(this.basicScore*level)-3;
                        break;
                    case 2:
                    case 1:
                        this.score+=(this.basicScore*level)-4;
                        break;
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
     * @return the availability of the class
     */
    public boolean isAvailable(){
        return this.available;
    }
    
    /**
     *
     * @return the exam subject
     */
    public Materia getInfo(){
        return this.examSubject;
    }
    
    /**
     *
     * @return the score
     */
    public int getScore(){
        return this.score;
    }

    
}
