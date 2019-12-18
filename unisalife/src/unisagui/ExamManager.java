/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import sound.JukeBoxMusic;
import sound.JukeBoxSound;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola This class manages the entire panel dedicated to the
 * exam screen. It contains methods for updating text fields and for
 * communicating with the exam thread.
 */
 class ExamManager { // package limited (default)

    private GameFrame gameframe = GameFrame.getInstance();
    private static final String EMPTY_TEXT = "";
    protected static int RESULT = 0;
    protected ResultGui rg;
    protected RequestGui confirm;
    private int time = 0;
    private Timer timing;
    protected static ExamManager instance;
    private int level = 0;
    private Color color;
    private final Color basecolor=new java.awt.Color(75,125,167);

    /**
     *
     * @return the current instance of the class if exists, otherwise it creates
     * and returns a new one. Singleton Pattern static factory method that
     * ensure to have a single instance of the class with synchronized it's ok
     * in case of multithreading application
     *
     */
    protected static ExamManager getInstance() {
        if (instance == null) {
            instance = new ExamManager();
        }

        return instance;
    }

    /**
     * This method close the ExamDialog
     */
    protected void closeExamDialog() {
        SwingUtilities.invokeLater(() -> gameframe.ExamDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> JukeBoxMusic.getInstance().play("game_music"));
        SwingUtilities.invokeLater(() -> JukeBoxMusic.getInstance().stop("exam"));
    }

    protected void setConfirm() {
        this.confirm.setValue(true);

    }

    /**
     * @param result in this parameter the answer given by the user within the
     * time limit will be saved. If the user does not respond this parameter
     * will remain equal to 0
     */
    protected void setRESULT(int result) {
        RESULT = result;
        rg.setValue(result);
        this.manageButtons(false);
    }

    /**
     *
     * @param correctness arrive from thred exam and says if the user has given
     * the right answer
     * @param confirm is an instance of RequestGui This method decides, based on
     * correctness, which response to illuminate and what color
     */
    protected void isCorrect(boolean correctness, RequestGui confirm) {
        this.confirm = confirm;
        if (correctness) {
            color = new java.awt.Color(115, 205, 105);//green
            SwingUtilities.invokeLater(() -> JukeBoxSound.getInstance().play("correct"));
        } else {
            color = new java.awt.Color(195, 60, 84); //red
            SwingUtilities.invokeLater(() -> JukeBoxSound.getInstance().play("wrong"));
        }
        timing.stop();

        switch (RESULT) {
            case 1:
                SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setBackground(color));
                break;
            case 2:
                SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setBackground(color));
                break;
            case 3:
                SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setBackground(color));
                break;
            case 4:
                SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setBackground(color));
                break;
        }

    }

    /**
     * This method resets all text fields of the Exam label
     */
    private void clearExam() {
        SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setBackground(basecolor));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setBackground(basecolor));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setBackground(basecolor));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setBackground(basecolor));
        SwingUtilities.invokeLater(() -> gameframe.NameOfExamLabel.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.ConfirmAnswer.setEnabled(false));
    }

    /**
     * @param examName Subject of the exam
     * @param question one of the question for this subject
     * @param answer1 first answer
     * @param answer2 second answer
     * @param answer3 third answer
     * @param answer4 fourth answer This method sets all the text fields of the
     * Exam label
     */
   private void fillExam(String examName, String question, String answer1, String answer2, String answer3, String answer4) {
        this.manageButtons(true);
        this.clearExam();
        SwingUtilities.invokeLater(() -> gameframe.ConfirmAnswer.setEnabled(false));
        SwingUtilities.invokeLater(() -> gameframe.ExamDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(question));
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(answer1));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(answer2));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(answer3));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(answer4));
        SwingUtilities.invokeLater(() -> gameframe.NameOfExamLabel.setText(examName));
        if (level == 4) {
            SwingUtilities.invokeLater(() -> gameframe.LevelOfQuestionLabel.setText("Laud Question "));
        } else {
            SwingUtilities.invokeLater(() -> gameframe.LevelOfQuestionLabel.setText("Question " + Integer.toString(level) + "/3"));
        }

    }

    /**
     *
     * @param enable if is false all the button will be disabled and viceversa
     */
    protected void manageButtons(boolean enable) {
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setEnabled(enable));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setEnabled(enable));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setEnabled(enable));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setEnabled(enable));

    }

    /**
     * This method updates in real time the time remaining for the user to
     * answer the question (in the respective label). This is done using the
     * Swing Timer.
     */
    private void showTimer() {

        timing = new Timer(1000, (ActionEvent e) -> {
            if (time <= 0) {
                timing.stop();
                return; // for stop at time =0
            }
            SwingUtilities.invokeLater(() -> gameframe.TimeLabel.setText(String.valueOf(time / 1000)));
            time = time - 1000;
        });
        timing.start();

    }

    /**
     *
     * @param examName Subject of the exam
     * @param question one of the question for this subject
     * @param answer1 first answer
     * @param answer2 second answer
     * @param answer3 third answer
     * @param answer4 fourth answer
     * @param time the number of second that the user has to answer the question
     * @param lock is an instance of ResultGui that is used by ExamManager to
     * put the right value of RESULT in the "setValue" method of ResultGui
     *
     * In this method all the methods useful for updating the interface relative
     * to the exam are called
     */
     protected void showExamDialog(String examName, String question, String answer1, String answer2, String answer3, String answer4, int time, ResultGui lock,int level) {
        this.level=level;
        if (level ==1){
            SwingUtilities.invokeLater(() -> JukeBoxMusic.getInstance().stop("game_music"));
            SwingUtilities.invokeLater(() -> JukeBoxMusic.getInstance().play("exam"));
        }
        if (level > 1) {
            timing.stop();
        }
        this.time = time * 1000;
        this.showTimer();
        this.fillExam(examName, question, answer1, answer2, answer3, answer4);
        rg = lock;

    }

    /**
     * When called the exam question is written on the Exam Dialog
     *
     * @param question is the question that has to be written
     */
    public void setExamQuestion(String question) {
        SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(question));
    }

    /**
     * When called one of the exam question is written in the Exam Dialog
     *
     * @param answer is the answer that has to be written
     * @param position is the position of the answer in the exam dialog,
     * position <=4
     */
    public void setExamAnswer(String answer, int position) throws Exception {
        if (position > 4 || position < 1) {
            throw new Exception();
        }
        switch (position) {
            case 1:
                SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(answer));
                break;
            case 2:
                SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(answer));
                break;
            case 3:
                SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(answer));
                break;
            default:
                SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(answer));
                break;
        }

    }

}
