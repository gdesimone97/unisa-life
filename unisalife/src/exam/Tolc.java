/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;
import exam.booklet.Subject;
import exam.question.*;
import game.GameObjects.Player;
import game.Interfaces.Initializable.InitException;
import gameSystem.map.MapManager;
import java.util.ArrayList;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import sound.JukeBoxSound;
import unisagui.*;

/**
 * This class is used to do an exam of a given subject. The number of questions
 * is modifiable by changing maxLevel value and basicScore due to reach a
 * maximum of 30 point (a little overflow is admitted)
 *
 * @author 1997g
 */
public class Tolc implements Runnable {

    private final Questions questions;
    private final int questionTime;
    private int coinReward = 100;
    private Subject subject;
    private int count;
    private final int maxLevel;
    QuestionsIterator iter;

    /**
     * Constructor. Given a examSubject fetches the questions.
     *
     * @param materia An object Materia needed to load the correct exam's
     * questions
     *
     */
    public Tolc() throws TextFinderException, InitException {
        this.subject = new Subject("tolc");
        QuestionFactory questionsFetch = new StringsQuestionFactory(subject);
        this.count = 0;
        this.questionTime = 30;
        this.coinReward = 5;
        this.questions = questionsFetch.getQuestions();
        this.maxLevel = this.questions.getNumLevels();
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
    public void verifyAnswer(boolean answer) { //here the level is referred to the number of questions choosed
        count += (answer ? 1 : 0);
    }

    @Override
    public void run() {
        MapManager.getInstance().stopGeneratingCoins();
        GuiManager gui = GuiManager.getInstance();
        ResultGui rg = new ResultGui(questionTime);
        RequestGui nextQuestion = new RequestGui();
        Question question;
        int answer;
        long start;
        int elapsed;
        boolean correctness;

        while (iter.hasNext()) {
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
                verifyAnswer(false);
            } else {
                correctness = question.isCorrect(answers.get(answer - 1));
                verifyAnswer(correctness);
                
                gui.isCorrect(correctness, nextQuestion);
                nextQuestion.getValue();
            }

        }
        
        gui.closeExamDialog();

        boolean passed = isPassed();
        
        try {
            FileTextManager f = FileTextManager.getFileTextManager();
            if (passed) {
                gui.showDialog(f.getString(new MessageInformation("TolcPassedName")).get(0) + Player.getIstance().getName() + f.getString(new MessageInformation("TolcPassedName")).get(1));
                JukeBoxSound.getInstance().play("exam_passed");
//                Booklet.getInstance().setScore(subject, voto);
            }
            else {
                gui.showDialog(f.getString(new MessageInformation("TolcFailedName")).get(0) + Player.getIstance().getName() + f.getString(new MessageInformation("TolcFailedName")).get(1));
                JukeBoxSound.getInstance().play("exam_failed");
            }
        } catch (TextFinderException ex) {
            ex.printStackTrace();
        } catch (InitException ex) {
            ex.printStackTrace();
        }
        MapManager.getInstance().startGeneratingCoins();
        
    }

    private boolean isPassed() {
        return count == maxLevel/2 + 1;
    }

}
