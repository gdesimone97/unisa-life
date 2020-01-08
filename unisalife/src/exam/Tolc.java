/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import character.StatusManager;
import exam.booklet.Booklet;
import exam.booklet.Subject;
import exam.question.*;
import game.GameObjects.Player;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import game.GameObjects.Teleport;
import game.GameResources.Map;
import game.Interfaces.Initializable.InitException;
import gameSystem.map.MapManager;
import hud.HudUpdater;
import java.util.ArrayList;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import sound.JukeBoxSound;
import unisagui.*;

/**
 * This class is used to do the tolc. The number of questions is setted by
 * the lenght of questions loaded from the file. This runnable class can be
 * runned and allows to do an exam based session.
 *
 * @author 1997g
 */
public class Tolc implements Runnable {

    private final Questions questions;
    private final int questionTime;
    private int coinReward = 25;
    private Subject subject;
    private String professorName;
    private int count;
    private final int maxLevel;
    QuestionsIterator iter;
    private final Professor professor;

    /**
     * the constructor of the tolc
     * @param s the subject
     * @param p the professor
     * @throws TextFinderException
     * @throws game.Interfaces.Initializable.InitException 
     */
    public Tolc(Subject s, Professor p) throws TextFinderException, InitException {
        this.subject = s;
        QuestionFactory questionsFetch = new StringsQuestionFactory(subject);
        this.count = 0;
        this.questionTime = 60;
        this.coinReward = 5;
        this.questions = questionsFetch.getQuestions();
        this.maxLevel = this.questions.getNumLevels();
        this.iter = questions.iterator();
        this.professor = p;
        this.professorName = p.getNome();
    }

    /**
     * increments the counter of right answers
     * @param answer the boolean 
     */
    public void verifyAnswer(boolean answer) { //here the level is referred to the number of questions choosed
        count += (answer ? 1 : 0);
    }

    /**
     * this run method allows to start the tolc session. It iterates on the questions
     * and checks if answers are correct or not. At the end, it end and gives reward
     * and the result
     */
    @Override
    public void run() {
        MapManager.getInstance().stopGeneratingCoins();
        HudUpdater.pause();
        GuiManager gui = GuiManager.getInstance();
        ResultGui rg = new ResultGui(questionTime);
        RequestGui nextQuestion = new RequestGui();
        Question question;
        int answer;
        boolean correctness;

        while (iter.hasNext()) {
            //print question
            question = iter.next();
            gui.setExamQuestion(question.getQuestion());
            ArrayList<Answer> answers = question.getAnswers();
            gui.showExamDialog(this.subject.toString(), question.getQuestion(), answers.get(0).getAnswer(), answers.get(1).getAnswer(), answers.get(2).getAnswer(), answers.get(3).getAnswer(), questionTime, rg, question.getLevel(), maxLevel);
            answer = rg.getValue();

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
            RequestGui r = new RequestGui();
            FileTextManager f = FileTextManager.getFileTextManager();
            if (passed) {
                Booklet.getInstance().setScore(subject, 30);
                try {
                    JukeBoxSound.getInstance().play("exam_passed");
                    gui.showDialog(professorName, f.getString(new MessageInformation("TolcPassedName")).get(0) + Player.getIstance().getName() + f.getString(new MessageInformation("TolcPassedName")).get(1), r);
                    r.getValue();
                    Thread.sleep(250);
                    gui.showDialog(professorName, f.getString(new MessageInformation("TolcPassedName")).get(2), r);
                    r.getValue();
                } catch (DialogManager.DialogAlreadyOpenedException ex) {
                } catch (TextFinderException ex) {
                } catch (InterruptedException ex) {
                }


                // add the 2 teleports after tolc completed
                Position destPosition = new Position(1248, 1408);
                Position p1 = new Position(1952, 352);
                Map map = MapManager.getInstance().getMap();
                map.addFixedObject(p1.getScaledPosition(), new Teleport(p1, 1, destPosition));
                p1 = new Position(1984, 352);
                map.addFixedObject(p1.getScaledPosition(), new Teleport(p1, 1, destPosition));
                
                
                // remove professor
                map.removeObject(professor.getPosition().getScaledPosition());
                StatusManager.getInstance().updateMoney(this.coinReward);
            } else {
                try {
                    gui.showDialog(professorName, f.getString(new MessageInformation("TolcFailedName")).get(0) + Player.getIstance().getName() + f.getString(new MessageInformation("TolcFailedName")).get(1), r);
                    r.getValue();
                } catch (DialogManager.DialogAlreadyOpenedException ex) {
                }
                JukeBoxSound.getInstance().play("exam_failed");
            }
            
            HudUpdater.resume();
        } catch (TextFinderException ex) {
            ex.printStackTrace();
        } catch (InitException ex) {
            ex.printStackTrace();
        }

    }

    private boolean isPassed() {
        return count >= maxLevel/2 + 1;
    }
    
}
