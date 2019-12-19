/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;
import character.Status;
import exam.booklet.Booklet;
import exam.booklet.Subject;
import exam.question.*;
import game.Interfaces.Initializable;
import game.Interfaces.Initializable.InitException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.FileTextManagerException;
import language.exceptions.TextFinderException;
import sound.JukeBoxMusic;
import sound.JukeBoxSound;
import unisagui.*;

/**
 * This class is used to do an exam of a given subject. The number of questions
 * is modifiable by changing maxLevel value and basicScore due to reach a
 * maximum of 30 point (a little overflow is admitted)
 *
 * @author liovi
 */
public class Exam implements Runnable {

    private final Questions questions;
    private final Subject subject;
    private final int questionTime;
    private int score;
    private int coinReward;
    private float sum;
    private int count;
    private final int maxLevel;
    private final float basicScore;
    private int lastLevelAnswered = 0;
    QuestionsIterator iter;

    /**
     * Constructor. Given a examSubject fetches the questions.
     *
     * @param materia An object Materia needed to load the correct exam's
     * questions
     *
     */
    public Exam(Subject subject) throws TextFinderException, InitException {
        this.subject = subject;
        QuestionFactory questionsFetch = new StringsQuestionFactory(subject);
        this.score = 0;
        this.sum = 0;
        this.count = 0;
        this.questionTime = 30;
        this.coinReward = 100;
        this.questions = questionsFetch.getQuestions();
        this.maxLevel = this.questions.getNumLevels();
        this.basicScore = 12 / (30 - (30 / (float) (this.maxLevel - 1)));
        this.iter = questions.iterator();
    }

    /**
     * Verify if the given answer is correct and gives a score, depending on the
     * level of the question and on the time passed before give the answer.
     *
     * @param answer A boolean value that indicates if the answer is or not is
     * correct
     * @param seconds Time passed before give the answer
     * @param level Difficulty level of the question used to give a rising value
     * for each answer
     */
    public void verifyAnswer(boolean answer, int seconds, int level) { //here the level is referred to the number of questions choosed
        if (level != maxLevel && answer) {
            int x = (this.questionTime - (this.questionTime / (this.maxLevel - level))); //seconds from which the user loses points
            this.sum += (seconds >= x ? 30 : 30 - this.basicScore * (x - seconds));
        } else if (level != maxLevel && !answer) {
            this.count++;
            this.sum += (count <= (0.4 * (maxLevel - 1)) ? 18 : 0);
        } else if (level == maxLevel) {
            this.sum += (answer ? (30 + maxLevel) : (30 - maxLevel));
        }
        lastLevelAnswered = level;
    }

    /**
     * This method is used to calculate the final score of the exam and calculate
     * the amount of money that the user has lose/win
     *
     * @return the final score of the exam
     */
    public int getScore() {
        this.score = (int) this.sum / (lastLevelAnswered);
        
        if (this.score >= 18){
            Status.setMoney((this.score - 18)*this.coinReward);
            //can be used also this expression. In fact if the user
            //doesn't pass the exame, he/she loses an amount of money that depends
            //on which is slow him/her score.
        }else{
            Status.setMoney(-50);
        }

        return this.score;
    }

    /**
     *
     * @return the sum of the score achieved during the exam
     */
    private float getCurrentScore() {
        return this.sum;
    }

    private boolean isDistinctionAvailable() {
        return lastLevelAnswered == maxLevel - 1;
    }

    @Override
    public void run() {
        GuiManager gui = GuiManager.getInstance();
        ResultGui rg = new ResultGui(questionTime);
        RequestGui praiseRequest = new RequestGui();
        RequestGui nextQuestion = new RequestGui();
        Question question;
        int answer;
        boolean answerRequest;
        long start;
        int elapsed;
        boolean correctness;

        while (iter.hasNext()) {

            if (isDistinctionAvailable()) {
                //Distinction Question
                if (getCurrentScore() != (maxLevel-1)*30) {
                    iter.next();
                    continue;
                }

                try {
                    gui.showRequest(FileTextManager.getFileTextManager().getString(new MessageInformation("LodeRequest")).get(0), praiseRequest);
                } catch (TextFinderException ex) {
                } catch (InitException ex) {
                }
                answerRequest = praiseRequest.getValue();

                if (!answerRequest) {
                    iter.next();
                    continue;
                }
            }

            //print question
            question = iter.next();
            gui.setExamQuestion(question.getQuestion());
            ArrayList<Answer> answers = question.getAnswers();
            gui.showExamDialog(this.subject.toString(), question.getQuestion(), answers.get(0).getAnswer(), answers.get(1).getAnswer(), answers.get(2).getAnswer(), answers.get(3).getAnswer(), questionTime, rg, question.getLevel());

            //init timer
            start = System.nanoTime();
            answer = rg.getValue();
            elapsed = (int) ((System.nanoTime() - start) / 1000000000);

            //check answer
            if (answer == 0) {
                verifyAnswer(false, elapsed, question.getLevel());
            } else {
                correctness = question.isCorrect(answers.get(answer - 1));
                verifyAnswer(correctness, questionTime - elapsed, question.getLevel());
                // answer can affect Stress status
                
                
                gui.isCorrect(correctness, nextQuestion);
                nextQuestion.getValue();
            }

        }
        
        gui.closeExamDialog();

        int voto = getScore();
        
        try {
            if (voto >= 18 && voto <= 30) {
                gui.showDialog(FileTextManager.getFileTextManager().getString(new MessageInformation("ScoreTaken")).get(0) + " " + voto);
                JukeBoxSound.getInstance().play("exam_passed");
//                Booklet.getInstance().setScore(subject, voto);                
            }
            else if (voto == 31) {
                gui.showDialog(FileTextManager.getFileTextManager().getString(new MessageInformation("Lode")).get(0));
                JukeBoxSound.getInstance().play("exam_passed");
//                Booklet.getInstance().setScore(subject, voto);
            }
            else {
                gui.showDialog(FileTextManager.getFileTextManager().getString(new MessageInformation("ExamFailed")).get(0));
                JukeBoxSound.getInstance().play("exam_failed");
            }
        } catch (TextFinderException ex) {
            ex.printStackTrace();
        } catch (InitException ex) {
            ex.printStackTrace();
        }
             
        //SEGNARE ESAME SUL LIBRETTO
    }

}
