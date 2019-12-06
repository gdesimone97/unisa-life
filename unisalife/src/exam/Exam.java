/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam;
import exam.question.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import unisagui.*;

/**
 * This class is used to do an exam of a given subject.
 * The number of questions is modifiable by changing maxLevel value and 
 * basicScore due to reach a maximum of 30 point (a little overflow is admitted)
 *
 * @author liovi
 */
public class Exam implements Runnable {
    private final Questions questions;
    private final Materia subject;
    private int answered;
    private final int questionTime;
    private int score;
    private float sum;
    private int count;
    private final int maxLevel;
    private final float basicScore;
    QuestionsIterator iter;
    
    /**
     * Constructor. Given a examSubject fetches the questions.
     * 
     * @param materia An object Materia needed to load the correct exam's questions
     * 
     */
    public Exam(Materia materia){
        this.subject = materia;
        QuestionFactory questionsFetch = new StringsQuestionFactory(subject);
        this.score = 0;
        this.sum = 0;
        this.count = 0;
        this.questionTime = 30;
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
        int x = (this.questionTime-(this.questionTime/(this.maxLevel-level))); //seconds from which the user loses points
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
     * @return the final score of the exam
     */
    public int getScore(){
        this.score = (int)this.sum/(maxLevel-1);
        return this.score;
    }
    
    /**
     *
     * @return the sum of the score achieved during the exam
     */
//    public float getCurrentScore(){
//        return this.sum;
//    }
    
    public synchronized void setAnswered(int position){
        this.answered = position;
        this.notify();
    }
    
     public synchronized int pause() {
        long start;
        long elapsed;
        
        System.out.println("Ciao porco dio bastardo");
         
        //beginning of the time passing
        start = System.nanoTime();
        try {
            this.wait(this.questionTime*1000);
        } catch (InterruptedException ex) {}
        finally{
            elapsed = System.nanoTime() - start;
        }
        System.out.println("Ciao porca madonna in croce");
        return (int) elapsed/1000000000;
     }
            

    @Override
    public void run() {
        GuiManager gui = GuiManager.getInstance();
        gui.showExamDialog(this.subject.toString(),true);
        int elapsedTime;
        Question question;
        int j=1;
        
        while(iter.hasNext() && j<4){
            question = iter.next();
            gui.setExamQuestion(question.getQuestion());
            ArrayList<Answer> answers = question.getAnswers();
            
            int i = 1;
            for(Answer a : answers){
                try {
                    gui.setExamAnswer(a.toString(),i++);
                } catch (Exception ex) {}
            }
            elapsedTime = pause();
            
            boolean correctness = question.isCorrect(answers.get(this.answered-1));
            
            verifyAnswer(correctness,elapsedTime,question.getLevel());
            j++;
        }
        
        System.out.println("Voto: "+ getScore());
    }
    
}
